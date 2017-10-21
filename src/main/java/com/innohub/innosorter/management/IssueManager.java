package com.innohub.innosorter.management;

import java.util.List;

import com.innohub.innosorter.entity.Administrator;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Developer;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;

public class IssueManager {

//    IssueRepositoryService issueRepositoryService;
    public void addPostToCluser(User user, Cluster clusterOne, Post postOne) {
        if (!(user instanceof Administrator)){
            throw new RuntimeException("User does not have enough priviledge to do this action.");
        }
        // TODO Auto-generated method stub

    }

    public List<Post> getClusterPosts(Cluster clusterOne) {
        // TODO Auto-generated method stub
        return null;
    }

}
