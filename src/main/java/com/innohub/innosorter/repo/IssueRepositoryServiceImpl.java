package com.innohub.innosorter.repo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.sql.PreparedStatement;

import com.innohub.innosorter.entity.Administrator;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.repo.IssueRepositoryService;
import com.innohub.innosorter.util.ApplicationConstants;

public class IssueRepositoryServiceImpl implements IssueRepositoryService {

    private static final int CONTEXT_CHARACHTER_OVER_LIMIT = 256;
    private static final int CONTEXT_LESS_CHARACTER_LIMIT = 8;
    private static final int IssueTitleOverLimit = 256;
    private static final int IssueTitleLessThanLimit = 8;
    private static final int IssueSummaryOverLimit = 256;
    private static final int IssueSummaryLessThanLimit = 8;
    private static String DB_URL = "jdbc:mysql://localhost:3306/innosorter";
    // Database credentials
    private static String user = "root";
    private static String pass = "";
    private static Connection conn;
    ResultSet rest;

    public void validateCluster(Cluster cluster) {
        if (cluster.getContext() == null) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_CONTEXT_NOT_AVAILABLE_MSG);
        } else if (cluster.getContext() == "") {
            throw new RuntimeException(ApplicationConstants.CLUSTER_CONTEXT_IS_EMPTY_MSG);
        } else if (cluster.getContext().length() > CONTEXT_CHARACHTER_OVER_LIMIT) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_CONTEXT_IS_OVER_CHARACTER_LIMIT_MSG);
        } else if (cluster.getContext().length() < CONTEXT_LESS_CHARACTER_LIMIT) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_CONTEXT_IS_LESS_CHARACTER_LIMIT_MSG);
        } else if (cluster.getTitle() == "") {
            throw new RuntimeException(ApplicationConstants.CLUSTER_ISSUE_TITLE_IS_EMPTY);
        } else if (cluster.getTitle() == null) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_ISSUE_TITLE_IS_NULL);
        } else if (cluster.getTitle().length() > IssueTitleOverLimit) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_ISSUE_TITLE_IS_OVER_THE_CHARACTER_LIMIT_MSG);
        } else if (cluster.getTitle().length() < IssueTitleLessThanLimit) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_ISSUE_TITLE_IS_LESS_THAN_CHARACTER_LIMIT_MSG);
        } else if (cluster.getNumOfUserImpacted() == null) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_NUM_OF_IMPACTED_USER_NOT_AVAILABLE_MSG);
        } else if (cluster.getNumOfUserImpacted() < 0) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_USERS_IMPACTED_IS_NEGATIVE_VALUE_MSG);
        } else if (cluster.getNumOfUserImpacted() == 0) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_USERS_IMPACTED_IS_ZERO_MSG);
        } else if (cluster.getTitle() == "") {
            throw new RuntimeException(ApplicationConstants.CLUSTER_SUMMARY_IS_EMPTY_MSG);
        } else if (cluster.getTitle() == null) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_SUMMARY_IS_NULL_MSG);
        } else if (cluster.getTitle().length() > IssueSummaryOverLimit) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_SUMMARY_IS_OVER_THE_CHARACTER_LIMIT_MSG);
        } else if (cluster.getTitle().length() < IssueSummaryLessThanLimit) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_SUMMARY_IS_LESS_THAN_CHARACTER_LIMIT_MSG);
        } else if (cluster.getNumOfForumPosts() == null) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_NO_FORUM_POSTS_IS_NULL_MSG);
        } else if (cluster.getNumOfForumPosts() == 0) {
            throw new RuntimeException(ApplicationConstants.CLUSTER_NO_FORUM_POSTS_IS_ZERO_MSG);
        } else if (cluster.getNumOfForumPosts().toString() == "") {
            throw new RuntimeException(ApplicationConstants.CLUSTER_NO_FORUM_POSTS_IS_EMPTY_MSG);
        } else if (cluster.getPriority() == null) {
            throw new RuntimeException(ApplicationConstants.PRIORITY_OF_FORUM_POSTS_IS_NULL_MSG);
        } else if (cluster.getPriority() < 0) {
            throw new RuntimeException(ApplicationConstants.PRIORITY_OF_FORUM_POSTS_IS_NEGATIVE_VALUE_MSG);
        }  else if (cluster.getPriority() == 0) {
            throw new RuntimeException(ApplicationConstants.PRIORITY_OF_FORUM_POSTS_IS_ZERO_MSG);
        } else if (cluster.getPriority().toString() == "") {
            throw new RuntimeException(ApplicationConstants.PRIORITY_OF_FORUM_POSTS_IS_EMPTY_MSG);
//        } else if (cluster.getPosts().isEmpty()) {
//            throw new RuntimeException(ApplicationConstants.CLUSTER_NO_FORUM_POSTS_IS_EMPTY_MSG);
//        } else if (cluster.getPosts() == null) {
//            throw new RuntimeException(ApplicationConstants.PRIORITY_OF_FORUM_POSTS_IS_NULL_MSG);
//        }
        }
    }

    public void storeIssue(Cluster issue){
        try {
            validateCluster(issue);
            conn = DBConnectionManager.setup(DB_URL, user, pass);

            String query = " insert into cluster (title, summary, numofforumposts, numofUserImpacted, context,priority,assignees,currentStatus)" + " values (?, ?, ?, ?, ?,?,?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, issue.getTitle());
            preparedStmt.setString(2, issue.getSummary());
            preparedStmt.setInt(3, issue.getNumOfForumPosts());
            preparedStmt.setInt(4, issue.getNumOfUserImpacted());
            preparedStmt.setString(5, issue.getContext());
            preparedStmt.setInt(6, issue.getPriority());
            preparedStmt.setString(7, issue.getAssignees().toString());
            preparedStmt.setString(8, "Pending");
            preparedStmt.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println("Exception happened when trying to store cluster in DB: " + e.toString());
            throw new RuntimeException("");
        }
    }
    
    public void deleteClusterIssue(Cluster issue1) {
        try {
            //validateCluster(issue1);
            conn = DBConnectionManager.setup(DB_URL, user, pass);
//              

            String query = " delete from cluster where title =" + "?";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, issue1.getTitle());
            preparedStmt.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println((e.toString()));
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Connecting to a selected database... ");
    }
    
    public void updateClusterIssue(Cluster issueNew) {
        try {
            validateCluster(issueNew);
            conn = DBConnectionManager.setup(DB_URL, user, pass);
//              

            String query = " update cluster  set title = ?, summary = ?, numofforumposts = ?, numofUserImpacted = ?, context = ?,priority = ?,assignees = ?,currentStatus = ?)" + " values (?, ?, ?, ?, ?,?,?,?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            
            preparedStmt.setString(1, issueNew.getTitle());
            preparedStmt.setString(2, issueNew.getSummary());
            preparedStmt.setInt(3, issueNew.getNumOfForumPosts());
            preparedStmt.setInt(4, issueNew.getNumOfUserImpacted());
            preparedStmt.setString(5, issueNew.getContext());
            preparedStmt.setInt(6, issueNew.getPriority());
            preparedStmt.setString(7, issueNew.getAssignees().toString());
            preparedStmt.setString(8, "Pending");
            preparedStmt.execute();

            conn.close();
        } catch (SQLException e) {
            System.out.println((e.toString()));
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Connecting to a selected database... ");
    }

	public boolean deleteCluster(User user, Cluster cluster) {
		
		if (!(user instanceof Administrator)){
            throw new RuntimeException(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);
        }
		else {
			return true;
		}
	}

    @Override
    public void addPostToCluster(Cluster clusterOne, Post postOne) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean checkClusterExist(int clusterID) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean checkPostExist(int postID) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void removePostFromCluster(Cluster cluster, Post post) {
        // TODO Auto-generated method stub
        
    }
}
