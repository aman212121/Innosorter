package com.innohub.innosorter.repo;

import static org.junit.Assert.*;

import java.util.Arrays;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.innohub.innosorter.entity.Administrator;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Developer;
import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.repo.IssueRepositoryService;
import com.innohub.innosorter.util.ApplicationConstants;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class IssueRepositoryServiceTest {

	private IssueRepositoryService issueRepository;
    
	@Mock
    private IssueRepositoryService mockIssueRepositoryService;
    
	@Before
	public void SetUp() {
		issueRepository = new IssueRepositoryServiceImpl();
		initMocks(this);
	}

	@Rule
	public ExpectedException expected = ExpectedException.none();

	@Test
	public void shouldNotStoreIssuesWithoutContext() {

		Cluster issue = new Cluster();
		issue.setTitle("IssueName1");
		issue.setSummary("Javascript Not Working");
		issue.setNumOfRelatedPosts(12);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setCurrentStatus("ASSIGNED");
		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_CONTEXT_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);
	}

	@Test
	public void shouldNotStoreIssuesWithoutUsersImpacted() {
		Cluster issue = new Cluster();
		issue.setTitle("IssueName1");
		issue.setSummary("Javascript Not Working");
		issue.setNumOfRelatedPosts(12);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setCurrentStatus("ASSIGNED");
		issue.setContext("CONTEXT");

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_NUM_OF_IMPACTED_USER_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);
	}

	@Test
	public void shouldNotStoreIssueWithoutIssueTitle() {
		Cluster issue = new Cluster();
		issue.setSummary("Javascript Not Working");
		issue.setNumOfRelatedPosts(12);
		issue.setNumOfUserImpacted(20);
		issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
		issue.setContext("CONTEXT");
		issue.setCurrentStatus("ASSIGNED");

		expected.expect(RuntimeException.class);
		expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_NOT_AVAILABLE_MSG);

		issueRepository.storeIssue(issue);

	}
	
	@Test
	public void shouldNotAllowDeveloperUserToDeleteCluster(){
		
		expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);
        
        Cluster cluster = new Cluster();
		Developer developer = new Developer("Dev");
		
		issueRepository.deleteCluster(developer, cluster);
       
	}
	
	@Test
	public void shouldAllowAdminUserToDeleteCluster(){
		
		Cluster cluster = new Cluster();
		Administrator admin = new Administrator("Admin");
		
		assertTrue(issueRepository.deleteCluster(admin, cluster));
	}
}
