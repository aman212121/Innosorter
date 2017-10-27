package com.innohub.innosorter.repo;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import com.innohub.innosorter.entity.Administrator;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Developer;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.util.ApplicationConstants;

public class IssueRepositoryServiceTest {

    private static IssueRepositoryService issueRepository;

    private static Connection mockConnection;

    @BeforeClass
    public static void SetUp() {
        mockConnection = Mockito.mock(Connection.class);

        issueRepository = Mockito.spy(new IssueRepositoryServiceImpl(mockConnection));
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();

    public Cluster buildACorrectClusterIssueObject() {
        Cluster issue = new Cluster();
        issue.setClusterID(1_000_000);
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

        issueRepository.insertCluster(issue);
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
        issueRepository.insertCluster(issue);
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
        issueRepository.insertCluster(issue);
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
        issueRepository.insertCluster(issue);
    }

    // 73
    @Test
    public void shouldNotStoreIssuesWhenContextIsLessThanCharacterLimit() {

        // Given
        Cluster issue = new Cluster();
        issue.setTitle("IssueName4");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setContext("context");

        // Then expected
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_CONTEXT_IS_LESS_CHARACTER_LIMIT_MSG);

        // When
        issueRepository.insertCluster(issue);
    }

    // 74
    @Test
    public void shouldNotStoreIssuesWhenUsersImpactedIsNull() {

        // Given
        Cluster issue = new Cluster();
        issue.setTitle("IssueName1");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setContext("CONTEXT123");
        issue.setPriority(2);

        // And
        issue.setNumOfUserImpacted(null);

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_USERS_IMPACTED_IS_NULL_MSG);

        // When
        issueRepository.insertCluster(issue);
    }

    // 75
    @Test
    public void shouldNotStoreIssuesWhenUsersImpactedIsNegativeValue() {

        // Given
        Cluster issue = new Cluster();
        issue.setTitle("IssueName1");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setContext("CONTEXT123");
        issue.setPriority(3);

        // And
        issue.setNumOfUserImpacted(-1);

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_USERS_IMPACTED_IS_NEGATIVE_VALUE_MSG);

        // When
        issueRepository.insertCluster(issue);
    }

    // 77
    @Test
    public void shouldNotStoreIssuesWhenUsersImpactedIsZero() {

        // Given
        Cluster issue = new Cluster();
        issue.setTitle("IssueName1");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setContext("CONTEXT123");
        issue.setPriority(3);

        // And
        issue.setNumOfUserImpacted(0);

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_USERS_IMPACTED_IS_ZERO_MSG);

        // When
        issueRepository.insertCluster(issue);
    }

    // 80
    @Test
    public void shouldNotStoreIssuesWhenIssueTitleIsOverTheCharacterLimit() {
        // Given
        Cluster issue = new Cluster();
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setTitle("context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123");

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_IS_OVER_THE_CHARACTER_LIMIT_MSG);

        // When
        issueRepository.insertCluster(issue);

    }

    // 81
    @Test
    public void shouldNotStoreIssuesWhenIssueTitleIsLessThanCharacterLimit() {

        // Given
        Cluster issue = new Cluster();
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setTitle("D");

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_IS_LESS_THAN_CHARACTER_LIMIT_MSG);

        // When
        issueRepository.insertCluster(issue);

    }

    // 82
    @Test
    public void shouldNotStoreIssuesWhenSummaryIsNull() {

        // Given
        Cluster issue = new Cluster();
        issue.setTitle("Error 404");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setSummary(null);

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_SUMMARY_IS_NULL_MSG);

        // when
        issueRepository.insertCluster(issue);

    }

    // 83
    @Test
    public void shouldNotStoreIssuesWhenSummaryIsEmpty() {

        // Given
        Cluster issue = new Cluster();
        issue.setTitle("Error 404");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        issue.setSummary("");

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_SUMMARY_IS_EMPTY_MSG);

        // Then
        issueRepository.insertCluster(issue);

    }

    // 84
    @Test
    public void shouldNotStoreIssuesWhenSummaryOverTheCharacterLimit() {

        // Given
        Cluster issue = new Cluster();
        issue.setTitle("Error 404");
        issue.setNumOfForumPosts(12);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        issue.setSummary("context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123context123");
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_SUMMARY_IS_OVER_THE_CHARACTER_LIMIT_MSG);

        // When
        issueRepository.insertCluster(issue);

    }

    // 86
    @Test
    public void shouldNotStoreIssuesWhenNumberOfForumPostsIsNull() {
        // Given
        Cluster issue = new Cluster();
        issue.setTitle("Error 404");
        issue.setSummary("JavaScript not working");
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        issue.setNumOfForumPosts(null);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_NO_FORUM_POSTS_IS_NULL_MSG);

        // When
        issueRepository.insertCluster(issue);

    }

    // 92
    @Test
    public void shouldNotStoreIssuesWhenPriorityIsZero() {
        // Given
        Cluster issue = new Cluster();
        issue.setTitle("Error 404");
        issue.setSummary("JavaScript not working");
        issue.setNumOfForumPosts(4);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");

        issue.setPriority(0);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.PRIORITY_OF_FORUM_POSTS_IS_ZERO_MSG);

        // When
        issueRepository.insertCluster(issue);

    }

    // 93
    @Test
    public void shouldNotStoreIssuesWhenPriorityIsNegativeValue() {
        // Given
        Cluster issue = new Cluster();
        issue.setTitle("Error 404");
        issue.setSummary("JavaScript not working");
        issue.setNumOfForumPosts(4);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");

        issue.setPriority(-2);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.PRIORITY_OF_FORUM_POSTS_IS_NEGATIVE_VALUE_MSG);

        // When
        issueRepository.insertCluster(issue);

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
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setTitle(null);

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_IS_NULL);

        // When
        issueRepository.insertCluster(issue);

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
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setTitle("");

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_ISSUE_TITLE_IS_EMPTY);

        // When
        issueRepository.insertCluster(issue);

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
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        // And
        issue.setSummary("D");

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_SUMMARY_IS_LESS_THAN_CHARACTER_LIMIT_MSG);

        // When
        issueRepository.insertCluster(issue);
    }

    // 87
    @Test
    public void shouldNotStoreIssuesWhenNumberOfForumPostsIsZero() {
        // Given
        Cluster issue = new Cluster();
        issue.setTitle("Error 404");
        issue.setSummary("JavaScript not working");
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        issue.setNumOfForumPosts(0);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_NO_FORUM_POSTS_IS_ZERO_MSG);

        // When
        issueRepository.insertCluster(issue);

    }

    // 88
    @Test
    public void shouldNotStoreIssuesWhenNumberOfForumPostsIsNegativeValue() {
        // Given
        Cluster issue = new Cluster();
        issue.setTitle("Error 404");
        issue.setSummary("JavaScript not working");
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);

        issue.setNumOfForumPosts(-1);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_NO_FORUM_POSTS_IS_NEGATIVE_VALUE_MSG);

        // When
        issueRepository.insertCluster(issue);

    }

    // 90
    @Test
    public void shouldNotStoreIssuesWhenPriorityIsNull() {
        // Given
        Cluster issue = new Cluster();
        issue.setTitle("Error 404");
        issue.setSummary("JavaScript not working");
        issue.setNumOfForumPosts(4);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setContext("CONTEXT123");
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(null);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.PRIORITY_OF_FORUM_POSTS_IS_NULL_MSG);

        // When
        issueRepository.insertCluster(issue);

    }

    // 148
    @Test
    public void shouldAllowAdminUserToDeleteCluster() {

        // Given
        Cluster cluster = new Cluster();
        Administrator admin = new Administrator("Admin");

        // When
        assertTrue(issueRepository.deleteCluster(admin, cluster));
    }

    // 147
    @Test
    public void shouldNotAllowDeveloperUserToDeleteCluster() {

        // Given
        Cluster cluster = new Cluster();
        Developer developer = new Developer("Dev");

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);

        // When
        issueRepository.deleteCluster(developer, cluster);

    }

    @Test
    public void shouldNewRowIsAddedToClusterTableUponInsert() throws SQLException {
        // Given
        Cluster issue = buildACorrectClusterIssueObject();
        PreparedStatement query = Mockito.mock(PreparedStatement.class);

        // And
        Mockito.doReturn(query).when(mockConnection).prepareStatement(Mockito.anyString());

        // When
        issueRepository.insertCluster(issue);

        // Then
        Mockito.verify(query).execute();
    }

    @Test
    public void shouldNotStoreIssueWhenListOfForumPostIdsAreNull() {
        // Given
        Cluster issue = buildACorrectClusterIssueObject();

        issue.setPosts(null);

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_FORUM_POSTS_LIST_IS_NULL_MSG);

        // When
        issueRepository.insertCluster(issue);

    }

    @Test
    public void shouldCreateNewRowInForumPostMappingTableWhenStoreNewClusterAndMappingAreNotExisitngForGivenForumPostAndCluster() throws SQLException {
        // Given
        Cluster issue = buildACorrectClusterIssueObject();
        PreparedStatement query = Mockito.mock(PreparedStatement.class);
        Post postOne = new Post();
        issue.setPosts(Arrays.asList(postOne));

        // And
        Mockito.doReturn(query).when(mockConnection).prepareStatement(Mockito.anyString());
        Mockito.doReturn(false).when(issueRepository).checkClusterPostRelationExist(issue, postOne);

        // When
        issueRepository.insertCluster(issue);

        // Then
        Mockito.verify(issueRepository).checkClusterPostRelationExist(issue, postOne);
        // execute will be called: n + 1; to add isse(1) + to add posts relations (n)
        Mockito.verify(query, Mockito.times(issue.getPosts().size() + 1)).execute();
    }

    @Test
    public void shouldNotCreateNewRowInForumPostMappingTableWhenStoreNewClusterAndMappingAreNotExisitngForGivenForumPostAndCluster() throws SQLException {
        // Given
        Cluster issue = buildACorrectClusterIssueObject();
        PreparedStatement query = Mockito.mock(PreparedStatement.class);
        Post postOne = new Post();
        Post postTwo = new Post();

        issue.setPosts(Arrays.asList(postOne));

        // And
        Mockito.doReturn(query).when(mockConnection).prepareStatement(Mockito.anyString());
        Mockito.doReturn(false).when(issueRepository).checkClusterPostRelationExist(issue, postOne);
        Mockito.doReturn(false).when(issueRepository).checkClusterPostRelationExist(issue, postTwo);

        // When
        issueRepository.insertCluster(issue);

        // Then
        Mockito.verify(issueRepository).checkClusterPostRelationExist(issue, postOne);
        Mockito.verify(issueRepository, Mockito.times(0)).checkClusterPostRelationExist(issue, postTwo);
        // execute will be called: n + 1; to add isse(1) + to add posts relations (n)
        Mockito.verify(query, Mockito.times(issue.getPosts().size() + 1)).execute();

    }

    @Test
    public void shouldDeleteRowInForumPostMappingTableWhenUpdateTheClusterToDeleteForumPostFromCluster() throws SQLException {
        // Given
        Cluster issue = buildACorrectClusterIssueObject();
        PreparedStatement query = Mockito.mock(PreparedStatement.class);
        Post postOne = new Post();
        Post postTwo = new Post();
        issue.setPosts(Arrays.asList(postOne, postTwo));

        // And
        Mockito.doReturn(query).when(mockConnection).prepareStatement(Mockito.startsWith("DELETE FROM"));

        // When
        issueRepository.removePostFromCluster(issue, postOne);

        // Then
        Mockito.verify(query).execute();
    }

    @Test
    public void shouldUpdateNumberOfForumPostsInClusterWhenAddForumPostToCluster() throws SQLException{
        // Given
        Cluster issue = buildACorrectClusterIssueObject();
        PreparedStatement query = Mockito.mock(PreparedStatement.class);
        Post postOne = new Post();
        Post postTwo = new Post();
        issue.setPosts(Arrays.asList(postOne));

        // And
        Mockito.doReturn(query).when(mockConnection).prepareStatement(Mockito.startsWith("INSERT INTO CLUSTER_POST"));
        Mockito.doReturn(query).when(mockConnection).prepareStatement(Mockito.startsWith("UPDATE CLUSTER"));

        // When
        issueRepository.addPostToCluster(issue.getClusterID(), postTwo);

        // Then
        Mockito.verify(query, Mockito.times(2)).execute();
    }
}
