package com.innohub.innosorter.management;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.omg.CORBA.portable.ApplicationException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.innohub.innosorter.entity.Administrator;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Developer;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.repo.IssueRepositoryService;
import com.innohub.innosorter.util.ApplicationConstants;

public class IssueManagerTest {

    @InjectMocks
    private IssueManager issueManager;

    @Mock
    private IssueRepositoryService mockIssueRepositoryService;

    @Before
    public void setUp(){
        initMocks(this);
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void shouldAllowAdminUserToAddForumPostToCluster() throws SQLException{
        //Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();

        //And
        Mockito.when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(true);
        Mockito.when(mockIssueRepositoryService.checkPostExist(postOne.getPostID())).thenReturn(true);

        //And
        Administrator admin = new Administrator("AdminUser");

        //When
        issueManager.addPostToCluser(admin, clusterOne, postOne);

        //Then
        verify(mockIssueRepositoryService).addPostToCluster(clusterOne.getClusterID(), postOne);
    }

    @Test
    public void shouldAllowAdminUserToAddForumPostThatExistsInOtherClusterIntoCluster() throws SQLException{
        //Given
        Cluster clusterOne = new Cluster();
        Cluster clusterTwo = new Cluster();
        Post postOne = new Post();
        Administrator admin = new Administrator("AdminUser");

        //And
        Mockito.when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(true);
        Mockito.when(mockIssueRepositoryService.checkPostExist(postOne.getPostID())).thenReturn(true);

        //And
        issueManager.addPostToCluser(admin, clusterOne, postOne);

        //And
        issueManager.addPostToCluser(admin, clusterTwo, postOne);

        //Then
        verify(mockIssueRepositoryService).addPostToCluster(clusterOne.getClusterID(), postOne);
        verify(mockIssueRepositoryService).addPostToCluster(clusterTwo.getClusterID(), postOne);
    }

    @Test
    public void shouldNotAllowNonAdminUsersToAddAForumPostToACluster() throws SQLException{
        //Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();
        Developer developer = new Developer("DeveloperOne");

        //And
        Mockito.when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(true);
        Mockito.when(mockIssueRepositoryService.checkPostExist(postOne.getPostID())).thenReturn(true);

        //And
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);

        //When
        issueManager.addPostToCluser(developer, clusterOne, postOne);
    }
    
    @Test
    public void shouldNotAllowNonAdminUsersToRemoveAForumPostFromCluster() throws SQLException{
        //Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();

        //And
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);

        //And
        Developer developer = new Developer("DeveloperOne");

        //Then
        issueManager.removePostFromCluster(developer, clusterOne, postOne);
    }

    @Test
    public void shouldNotAllowAdminUserToAddForumPostIntoClusterWhenForumPostIsAlreadyInCluster() throws SQLException{
        //Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();
        Administrator admin = new Administrator("AdminUser");

        //And
        when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(true);
        when(mockIssueRepositoryService.checkPostExist(postOne.getPostID())).thenReturn(true);

        Mockito.doNothing().doThrow(new RuntimeException(ApplicationConstants.CLUSTER_ALREADY_HAS_THE_POST_MSG))
        .when(mockIssueRepositoryService).addPostToCluster(clusterOne.getClusterID(), postOne);

        issueManager.addPostToCluser(admin, clusterOne, postOne);

        //And
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_ALREADY_HAS_THE_POST_MSG);

        //When
        issueManager.addPostToCluser(admin, clusterOne, postOne);
    }
    
    @Test
    public void shouldNotAllowAdminUserToAddForumPostIntoNonExistingCluster() throws SQLException{
        //Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();
        Administrator admin = new Administrator("AdminUser");

        //And
        Mockito.when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(false);

        //And
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_DOES_NOT_EXSIST_MSG);

        //When
        issueManager.addPostToCluser(admin, clusterOne, postOne);
    }

    @Test
    public void shouldNotAllowAdminUserToAddNonexistingForumPostIntoCluster() throws SQLException{
        //Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();
        Administrator admin = new Administrator("AdminUser");

        //And
        Mockito.when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(true);
        Mockito.when(mockIssueRepositoryService.checkPostExist(postOne.getPostID())).thenReturn(false);

        //And
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.FORUM_POST_DOES_NOT_EXSIST_MSG);

        //When
        issueManager.addPostToCluser(admin, clusterOne, postOne);
    }
    
    @Test
    public void shouldNotAllowAdminUserToAddNoneexistingForumPostIntoNonexistingCluster() throws SQLException{
        //Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();
        Administrator admin = new Administrator("AdminUser");

        //And
        Mockito.when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(false);
        Mockito.when(mockIssueRepositoryService.checkPostExist(postOne.getPostID())).thenReturn(false);

        //And
        expected.expect(RuntimeException.class);
        
        //And (because this condition fires first)
        expected.expectMessage(ApplicationConstants.CLUSTER_DOES_NOT_EXSIST_MSG);

        //When
        issueManager.addPostToCluser(admin, clusterOne, postOne);
    }

    @Test
    public void shouldAllowAdminUserToRemoveForumPostFromCluster() throws SQLException {
        // Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();

        // And
        Mockito.when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(true);
        Mockito.when(mockIssueRepositoryService.checkPostExist(postOne.getPostID())).thenReturn(true);

        // And
        Administrator admin = new Administrator("AdminUser");

        // When
        issueManager.removePostFromCluster(admin, clusterOne, postOne);

        // Then
        verify(mockIssueRepositoryService).removePostFromCluster(clusterOne, postOne);
    }

    @Test
    public void shouldAllowAdminUserToRemoveForumPostFromClusterThatExistInMoreThanOneCluster(){
        // find 
    }

    @Test
    public void shouldNotAllowAdminUserToRemoveForumPostFromClusterWhenForumPostIsNotInCluster() throws SQLException{
        // Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();
        Administrator admin = new Administrator("AdminUser");

        // And
        Mockito.when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(true);
        Mockito.when(mockIssueRepositoryService.checkPostExist(postOne.getPostID())).thenReturn(true);

        //And
        expected.expect(RuntimeException.class);
        //And (Should be JDBC exception)
        expected.expectMessage(ApplicationConstants.CLUSTER_DOES_NOT_HAVE_SUCH_A_POST);

        //And
        Mockito.doThrow(new RuntimeException(ApplicationConstants.CLUSTER_DOES_NOT_HAVE_SUCH_A_POST)).when(mockIssueRepositoryService).removePostFromCluster(clusterOne, postOne);

        // When
        issueManager.removePostFromCluster(admin, clusterOne, postOne);

        // Then
        verify(mockIssueRepositoryService).removePostFromCluster(clusterOne, postOne);        
    }

    @Test
    public void shouldNotAllowAdminUserToRemoveNonexistingForumPostFromCluster() throws SQLException{
        //Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();
        Administrator admin = new Administrator("AdminUser");

        //And
        Mockito.when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(true);
        Mockito.when(mockIssueRepositoryService.checkPostExist(postOne.getPostID())).thenReturn(false);

        //And
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.FORUM_POST_DOES_NOT_EXSIST_MSG);

        //When
        issueManager.removePostFromCluster(admin, clusterOne, postOne);
    }

    @Test
    public void shouldNotAllowAdminUserToRemoveForumPostFromNonexistingCluster() throws SQLException{
        //Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();
        Administrator admin = new Administrator("AdminUser");

        //And
        Mockito.when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(false);

        //And
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_DOES_NOT_EXSIST_MSG);

        //When
        issueManager.removePostFromCluster(admin, clusterOne, postOne);
    }
    //shouldNotAllowAdminUserToRemoveNonexistingForumPostFromNonexistingCluster
    @Test
    public void shouldNotAllowAdminUserToRemoveNonexistingForumPostFromNonexistingCluster() throws SQLException{
        //Given
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();
        Administrator admin = new Administrator("AdminUser");

        //And
        Mockito.when(mockIssueRepositoryService.checkClusterExist(clusterOne.getClusterID())).thenReturn(false);
        Mockito.when(mockIssueRepositoryService.checkPostExist(postOne.getPostID())).thenReturn(false);

        //And
        expected.expect(RuntimeException.class);
        
        //And (because this condition fires first)
        expected.expectMessage(ApplicationConstants.CLUSTER_DOES_NOT_EXSIST_MSG);

        //When
        issueManager.removePostFromCluster(admin, clusterOne, postOne);
    }

}
