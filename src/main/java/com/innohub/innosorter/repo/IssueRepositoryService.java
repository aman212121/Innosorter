package com.innohub.innosorter.repo;

import java.sql.SQLException;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;

public interface IssueRepositoryService {

    void deleteClusterIssue(Cluster issue);

    void updateClusterIssue(Cluster newIssue);

    void insertCluster(Cluster issue);

    void addPostToCluster(Integer clusterId, Post post) throws SQLException;

    boolean checkClusterExist(int clusterID);

    boolean checkPostExist(int postID);

    boolean deleteCluster(User user, Cluster cluster);

    void removePostFromCluster(Cluster cluster, Post post) throws SQLException;

    Boolean checkClusterPostRelationExist(Cluster issue, Post post) throws SQLException;
}
