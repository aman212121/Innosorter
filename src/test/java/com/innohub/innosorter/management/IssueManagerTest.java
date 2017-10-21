package com.innohub.innosorter.management;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;

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
