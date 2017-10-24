package com.innohub.innosorter.repo;

import java.util.Arrays;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.repo.IssueRepositoryService;
import com.innohub.innosorter.util.ApplicationConstants;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IssueRepositoryServiceTest {

    private IssueRepositoryService issueRepository;

    @Before
    public void SetUp() {
        issueRepository = new IssueRepositoryServiceImpl();
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void shouldNotStoreIssuesWithoutContext() {

        Cluster issue = new Cluster();
        issue.setTitle("IssueName1");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_CONTEXT_NOT_AVAILABLE_MSG);
        
        issueRepository.storeIssue(issue);
    }

    @Test
    public void shouldNotStoreIssuesWhenContextIsNull() {

        Cluster issue = new Cluster();
        issue.setTitle("IssueName2");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setContext(null);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_CONTEXT_NOT_AVAILABLE_MSG);

        issueRepository.storeIssue(issue);
    }

    @Test
    public void shouldNotStoreIssuesWhenContextIsEmpty() {

        Cluster issue = new Cluster();
        issue.setTitle("IssueName3");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(15);
        issue.setNumOfUserImpacted(20);
        issue.setContext("");
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_CONTEXT_IS_EMPTY_MSG);

        issueRepository.storeIssue(issue);
    }

    @Test
    public void shouldNotStoreIssuesWhenContextIsOverTheCharacterLimit() {

        int contextCharacterLimit_higher = 256;

        Cluster issue = new Cluster();
        issue.setTitle("IssueName4");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setContext("context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123");
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_CONTEXT_IS_OVER_CHARACTER_LIMIT_MSG);

        issueRepository.storeIssue(issue);
    }

    @Test
    public void shouldNotStoreIssuesWhenContextIsLessThanCharacterLimit() {

        int contextCharacterLimit_lower = 32;

        Cluster issue = new Cluster();
        issue.setTitle("IssueName4");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setContext("context123context123context123");
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);


        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_CONTEXT_IS_LESS_CHARACTER_LIMIT_MSG);

        issueRepository.storeIssue(issue);
    }

    @Test
    public void shouldNotStoreIssuesWhenUsersImpactedIsNegativeValue() {
        Cluster issue = new Cluster();
        issue.setTitle("IssueName1");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setContext("CONTEXT");
        issue.setNumOfUserImpacted(-1);
		issue.setPriority(3);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_USERS_IMPACTED_IS_NULL_MSG);

        issueRepository.storeIssue(issue);
    }

    @Test
    public void shouldNotStoreIssuesWhenUsersImpactedIsNull() {
        Cluster issue = new Cluster();
        issue.setTitle("IssueName1");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setContext("CONTEXT");
        issue.setNumOfUserImpacted(null);
        issue.setPriority(2);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_USERS_IMPACTED_IS_NULL_MSG);

        issueRepository.storeIssue(issue);
    }

    @Test
    public void shouldNotStoreIssuesWhenUsersImpactedIsZero() {
        Cluster issue = new Cluster();
        issue.setTitle("IssueName1");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setContext("CONTEXT");
        issue.setNumOfUserImpacted(0);
		issue.setPriority(3);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_USERS_IMPACTED_IS_NULL_MSG);

        issueRepository.storeIssue(issue);
    }

    @Test
    public void shouldNotStoreIssueWithoutIssueTitle() {
        Cluster issue = new Cluster();
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT");
        issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

        issueRepository.storeIssue(issue);

    }

	@Test
	public void shouldNotStoreIssueWhenIssueTitleIsNull() {
		Cluster issue = new Cluster();
		issue.setTitle(null);
		issue.setSummary("Javascript Not Working");
		issue.setNumOfForumPosts(12);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssueWhenIssueTitleIsEmpty() {
		Cluster issue = new Cluster();
		issue.setTitle("");
		issue.setSummary("Javascript Not Working");
		issue.setNumOfForumPosts(12);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenIssueTitleIsOverTheCharacterLimit() {
		Cluster issue = new Cluster();
		issue.setTitle("context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123");
		issue.setSummary("Javascript Not Working");
		issue.setNumOfForumPosts(12);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenIssueTitleIsLessThanCharacterLimit() {
		Cluster issue = new Cluster();
		issue.setTitle("D");
		issue.setSummary("Javascript Not Working");
		issue.setNumOfForumPosts(12);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenSummaryIsNull() {
		Cluster issue = new Cluster();
		issue.setTitle("Error 404");
		issue.setSummary(null);
		issue.setNumOfForumPosts(12);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenSummaryIsEmpty() {
		Cluster issue = new Cluster();
		issue.setTitle("Error 404");
		issue.setSummary("");
		issue.setNumOfForumPosts(12);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenSummaryOverTheCharacterLimit() {
		Cluster issue = new Cluster();
		issue.setTitle("Error 404");
		issue.setSummary("context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123");
		issue.setNumOfForumPosts(12);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenSummaryLessThanCharacterLimit() {
		Cluster issue = new Cluster();
		issue.setTitle("Error 404");
		issue.setSummary("D");
		issue.setNumOfForumPosts(12);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenNumberOfForumPostsIsNull() {
		Cluster issue = new Cluster();
		issue.setTitle("Error 404");
		issue.setSummary("JavaScript not working");
		issue.setNumOfForumPosts(null);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenNumberOfForumPostsIsZero() {
		Cluster issue = new Cluster();
		issue.setTitle("Error 404");
		issue.setSummary("JavaScript not working");
		issue.setNumOfForumPosts(0);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenNumberOfForumPostsIsNegativeValue() {
		Cluster issue = new Cluster();
		issue.setTitle("Error 404");
		issue.setSummary("JavaScript not working");
		issue.setNumOfForumPosts(-1);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(3);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenPriorityIsNull() {
		Cluster issue = new Cluster();
		issue.setTitle("Error 404");
		issue.setSummary("JavaScript not working");
		issue.setNumOfForumPosts(4);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(null);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenPriorityIsZero() {
		Cluster issue = new Cluster();
		issue.setTitle("Error 404");
		issue.setSummary("JavaScript not working");
		issue.setNumOfForumPosts(4);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(0);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}

	@Test
	public void shouldNotStoreIssuesWhenPriorityIsNegativeValue() {
		Cluster issue = new Cluster();
		issue.setTitle("Error 404");
		issue.setSummary("JavaScript not working");
		issue.setNumOfForumPosts(4);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");
		issue.setPriority(-2);

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}


    @Test
    public void shouldBeAbleToInsertIntoDatabase() {
        Cluster isssueCluster = new Cluster();
        isssueCluster.setTitle("Interstellar");
        isssueCluster.setSummary("The end of world is not the end of us");
        isssueCluster.setNumOfForumPosts(22);
        isssueCluster.setNumOfUserImpacted(30);
        isssueCluster.setContext("Jonathan");
        isssueCluster.setAssignees(Arrays.asList(new User("Nolan"), new User("Thrope")));
        isssueCluster.setCurrentStatus("NotAssigned");
        isssueCluster.setPriority(3);

        issueRepository.storeIssue(isssueCluster);
    }

    @Test
    public void shouldBeAbleToDeleteFromDatabase() {
        Cluster isssueCluster = new Cluster();
        isssueCluster.setTitle("Interstellar");

        issueRepository.deleteClusterIssue(isssueCluster);
    }

    @Test
    public void shouldBeAbleToUpdateTheDatabase() {
        Cluster isssueCluster = new Cluster();
        isssueCluster.setTitle("The Martian");
        isssueCluster.setSummary("The end of world");
        isssueCluster.setNumOfForumPosts(21);
        isssueCluster.setNumOfUserImpacted(30);
        isssueCluster.setContext("Matt Damon");
        isssueCluster.setAssignees(Arrays.asList(new User("Martin"), new User("Thrope")));
        isssueCluster.setCurrentStatus("Assigned");
        isssueCluster.setPriority(4);

        issueRepository.storeIssue(isssueCluster);
    }
}
