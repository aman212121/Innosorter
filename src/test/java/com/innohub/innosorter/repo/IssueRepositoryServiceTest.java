package com.innohub.innosorter.repo;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.AssertionFailedError;

public class IssueRepositoryServiceTest implements IssueRepositoryService {

	private IssueRepositoryService issueRepository;
	@Before
	void SetUp()
	{
		issueRepository = new IssueRepositoryService();
	}
	
	@Test
	public void shouldNotStoreIssuesWithoutContext() {
		issueRepository = new IssueRepositoryService();
		
		Cluster issue = new Cluster();
		issue.setName("IssueName1");
		issue.setSummary("Javascript Not Working");
		issue.setNoRelatedPosts("12");
		issue.setNoOfUsers("20");
		issue.setContext("");
		issue.SetAssignees("Karthik");
		issue.CurrentStatus("Assigned");
	    issueRepository.storeIssue(issue);
	    Boolean ifStoreIssuesWithoutContext = false;
	    if(!issue.contains(context)) {
	    	ifStoreIssuesWithoutContext = true;
	    }
	    assertTrue(ifStoreIssuesWithoutContext);
	}
	
	@Test
	public void shouldNotStoreIssuesWithoutUsersImpacted()
	{
		Cluster issue = new Cluster();
		issue.setUsersImpacted("");
	}
	
	@Test
	public void shouldFindIssueWithIssueTitle()
	{
		Issue issue = new Issue();
		issueRepository.getTitle(issue);
		
	}

}
