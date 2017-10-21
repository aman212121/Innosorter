package com.innohub.innosorter.management;

import java.util.List;

import org.junit.Test;

public class IssueManagerTest {

    private IssueManager issueManager;
    @Test
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
    }
    
}
