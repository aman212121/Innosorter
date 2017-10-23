package com.innohub.innosorter.repo;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;

public interface IssueRepositoryService {

    void storeIssue(Cluster cluster);

    void addPostToCluster(Cluster cluster, Post post);
    
    boolean checkClusterExist(int clusterID);
    
    boolean checkPostExist(int postID);
}

