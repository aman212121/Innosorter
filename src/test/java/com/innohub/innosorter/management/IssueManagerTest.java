package com.innohub.innosorter.management;


import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Developer;
import com.innohub.innosorter.entity.Post;

public class IssueManagerTest {

    private IssueManager issueManager;

    @Before
    public void setUp(){
        this.issueManager = new IssueManager();
    }
/*    @Test
    public void shouldShowAPostInClusterIfAdminAddsThatPostToThatCluster(){
        Cluster clusterOne = new Cluster();
        Post postOne = new Post();

        issueManager.addPostToCluser(clusterOne, postOne);

        List<Post> clusterOnePosts = issueManager.getClusterPosts(clusterOne);

        Boolean isPostExists = false;
        for (Post post : clusterOnePosts){
            if (post.getId().equals(postOne.getId()))
                isPostExists = true;
        }

        assertTrue(isPostExists);
    }*/
    @Rule
    public ExpectedException expected = ExpectedException.none();
    
    @Test
    public void shouldNotAllowNonAdminUsersToAddAForumPostToACluster(){

        Cluster clusterOne = new Cluster();
        Post postOne = new Post();

        expected.expect(RuntimeException.class);
        expected.expectMessage("User does not have enough priviledge to do this action.");

        Developer developer = new Developer("DeveloperOne");
        issueManager.addPostToCluser(developer, clusterOne, postOne);
    }
}
