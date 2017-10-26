package com.innohub.innosorter.repo;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;

public interface IssueRepositoryService {

    void storeIssue(Cluster cluster);

    void deleteClusterIssue(Cluster issue);

    void updateClusterIssue(Cluster newIssue);

    void addPostToCluster(Cluster cluster, Post post);

    boolean checkClusterExist(int clusterID);

    boolean checkPostExist(int postID);

    boolean deleteCluster(User user, Cluster cluster);

    void removePostFromCluster(Cluster cluster, Post post);

}
