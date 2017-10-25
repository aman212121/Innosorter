package com.innohub.innosorter.repo;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import com.innohub.innosorter.entity.Administrator;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Developer;
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

    // 69
    @Test
    public void shouldNotStoreIssuesWithoutContext() {

        // Given
        Cluster issue = new Cluster();
        issue.setTitle("IssueName1");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_CONTEXT_NOT_AVAILABLE_MSG);

        issueRepository.storeIssue(issue);
    }

    // 70
    @Test
    public void shouldNotStoreIssuesWhenContextIsNull() {

        Cluster issue = new Cluster();
        issue.setTitle("IssueName2");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setContext(null);

        if (issue.getContext() == null || issue.getContext() == "") {
            expected.expect(RuntimeException.class);
            expected.expectMessage(ApplicationConstants.CLUSTER_CONTEXT_NOT_AVAILABLE_MSG);
        }
        issueRepository.storeIssue(issue);
    }

    // 71
    @Test
    public void shouldNotStoreIssuesWhenContextIsEmpty() {
        // Given
        Cluster issue = new Cluster();
        issue.setTitle("IssueName3");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(15);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setContext("");

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_CONTEXT_IS_EMPTY_MSG);

        // When
        issueRepository.storeIssue(issue);
    }

    // 72
    @Test
    public void shouldNotStoreIssuesWhenContextIsOverTheCharacterLimit() {

        // Gvien
        Cluster issue = new Cluster();
        issue.setTitle("IssueName4");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setContext("context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123");

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_CONTEXT_IS_OVER_CHARACTER_LIMIT_MSG);

        // When
        issueRepository.storeIssue(issue);
    }

    // 73
    @Test
    public void shouldNotStoreIssuesWhenContextIsLessThanCharacterLimit() {

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

    // 74
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

    // 75
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
        expected.expectMessage(ApplicationConstants.CLUSTER_USERS_IMPACTED_IS_NEGATIVE_VALUE_MSG);

        issueRepository.storeIssue(issue);
    }

    // 77
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
        expected.expectMessage(ApplicationConstants.CLUSTER_USERS_IMPACTED_IS_ZERO_MSG);

        issueRepository.storeIssue(issue);
    }

    // 80
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
        expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_IS_OVER_THE_CHARACTER_LIMIT_MSG);

        issueRepository.storeIssue(issue);

    }

    // 81
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
        expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_IS_LESS_THAN_CHARACTER_LIMIT_MSG);

        issueRepository.storeIssue(issue);

    }

    // 82
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
        expected.expectMessage(ApplicationConstants.CLUSTER_SUMMARY_IS_NULL_MSG);

        issueRepository.storeIssue(issue);

    }

    // 83
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
        expected.expectMessage(ApplicationConstants.CLUSTER_SUMMARY_IS_EMPTY_MSG);

        issueRepository.storeIssue(issue);

    }

    // 84
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
        expected.expectMessage(ApplicationConstants.CLUSTER_SUMMARY_IS_OVER_THE_CHARACTER_LIMIT_MSG);

        issueRepository.storeIssue(issue);

    }

    // 86
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
        expected.expectMessage(ApplicationConstants.CLUSTER_NO_FORUM_POSTS_IS_NULL_MSG);

        issueRepository.storeIssue(issue);

    }

    // 92
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
        expected.expectMessage(ApplicationConstants.PRIORITY_OF_FORUM_POSTS_IS_ZERO_MSG);

        issueRepository.storeIssue(issue);

    }

    // 93
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
        expected.expectMessage(ApplicationConstants.PRIORITY_OF_FORUM_POSTS_IS_NEGATIVE_VALUE_MSG);

        issueRepository.storeIssue(issue);

    }

    // 78
    @Test
    public void shouldNotStoreIssuesWhenIssueTitleIsNull() {
        // Given
        Cluster issue = new Cluster();
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setTitle(null);

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

        // When
        issueRepository.storeIssue(issue);

    }

    // 79
    @Test
    public void shouldNotStoreIssuesWhenIssueTitleIsEmpty() {
        // Given
        Cluster issue = new Cluster();
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setTitle("");

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

        // When
        issueRepository.storeIssue(issue);

    }

    // 85
    @Test
    public void shouldNotStoreIssuesWhenSummaryIsLessThanCharacterLimit() {
        // When
        Cluster issue = new Cluster();
        issue.setTitle("Error 404");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setSummary("D");

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_SUMMARY_IS_LESS_THAN_CHARACTER_LIMIT_MSG);

        // When
        issueRepository.storeIssue(issue);
    }

    // 87
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
        expected.expectMessage(ApplicationConstants.CLUSTER_NO_FORUM_POSTS_IS_ZERO_MSG);

        issueRepository.storeIssue(issue);

    }

    // 88
    @Test
    public void shouldNotStoreIssuesWhenNumberOfForumPostsIsNegativeValue() {
        Cluster issue = new Cluster();
        issue.setTitle("Error 404");
        issue.setSummary("JavaScript not working");
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        issue.setNumOfForumPosts(-1);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_NO_FORUM_POSTS_IS_NEGATIVE_VALUE_MSG);

        issueRepository.storeIssue(issue);

    }

    // 90
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
        expected.expectMessage(ApplicationConstants.PRIORITY_OF_FORUM_POSTS_IS_NULL_MSG);

        issueRepository.storeIssue(issue);

    }

    // 148
    @Test
    public void shouldAllowAdminUserToDeleteCluster() {

        Cluster cluster = new Cluster();
        Administrator admin = new Administrator("Admin");

        assertTrue(issueRepository.deleteCluster(admin, cluster));
    }

    // 147
    @Test
    public void shouldNotAllowDeveloperUserToDeleteCluster() {

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);

        Cluster cluster = new Cluster();
        Developer developer = new Developer("Dev");

        issueRepository.deleteCluster(developer, cluster);

    }

}
