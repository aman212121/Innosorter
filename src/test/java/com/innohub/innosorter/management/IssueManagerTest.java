package com.innohub.innosorter.management;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.mockito.Mockito.verify;

import org.mockito.InjectMocks;
import org.mockito.Mock;
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
    public void shouldAllowAdminUserToAddForumPostToCluster(){
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();

        Administrator admin = new Administrator("AdminUser");

        issueManager.addPostToCluser(admin, clusterOne, postOne);

        verify(mockIssueRepositoryService).addPostToCluster(clusterOne, postOne);
    }

    @Test
    public void shouldNotAllowNonAdminUsersToAddAForumPostToACluster(){

        Cluster clusterOne = new Cluster();
        Post postOne = new Post();

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);

        Developer developer = new Developer("DeveloperOne");
        issueManager.addPostToCluser(developer, clusterOne, postOne);
    }
    
    @Test
    public void shouldNotAllowNonAdminUsersToRemoveAForumPostFromCluster(){
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);

        Developer developer = new Developer("DeveloperOne");
        issueManager.removePostFromCluster(developer, clusterOne, postOne);
        
    }
}
