package com.innohub.innosorter.management;

import static org.junit.Assert.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.repo.IssueRepositoryService;

public class IssueSorterTest {

    @InjectMocks
    IssueSorter issueSorter;

    @Mock
    IssueRepositoryService issueRepositoryService;

    @Before
    public void setUp() {
        initMocks(this);
    }

    public Cluster buildACorrectClusterIssueObject() {
        Cluster issue = new Cluster();
        issue.setClusterID(1001001);
        issue.setTitle("Error 404");
        issue.setSummary("JavaScript not working");
        issue.setNumOfForumPosts(4);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(10);
        issue.setPosts(new ArrayList<Post>());
        return issue;
    }

    @Test
    public void shouldReturnListOfIssuesSortedByNumberOfRelatedPosts() {
        // Given
        Cluster issueOne = buildACorrectClusterIssueObject();
        issueOne.setClusterID(1000);
        issueOne.setNumOfForumPosts(100);
        Cluster issueTwo = buildACorrectClusterIssueObject();
        issueTwo.setClusterID(1001);
        issueTwo.setNumOfForumPosts(200);
        Cluster issueThree = buildACorrectClusterIssueObject();
        issueThree.setClusterID(1002);
        issueThree.setNumOfForumPosts(150);

        // And
        List<Cluster> listOfClusterFromRepo = Arrays.asList(issueOne, issueTwo, issueThree);
        Mockito.doReturn(listOfClusterFromRepo).when(issueRepositoryService).getListOfClusters();

        // When
        List<Cluster> sortedListOfCluster = issueSorter.getSortedListOfIssues();

        // Then
        assertTrue(sortedListOfCluster.get(0).getNumOfForumPosts() < sortedListOfCluster.get(1).getNumOfForumPosts());
        assertTrue(sortedListOfCluster.get(1).getNumOfForumPosts() < sortedListOfCluster.get(2).getNumOfForumPosts());
        assertTrue(sortedListOfCluster.get(0).getNumOfForumPosts() < sortedListOfCluster.get(2).getNumOfForumPosts());
    }

}
