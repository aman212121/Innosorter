package com.innohub.innosorter.management;

import com.innohub.innosorter.entity.Administrator;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;

public class IssueManager {

    public void addPostToCluser(User user, Cluster clusterOne, Post postOne) {
        if (!(user instanceof Administrator)){
            throw new RuntimeException("User does not have enough priviledge to do this action.");
        }

    }

}
