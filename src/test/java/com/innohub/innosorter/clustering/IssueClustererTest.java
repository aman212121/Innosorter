package com.innohub.innosorter.clustering;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import com.innohub.innosorter.clustering.IssueClusterer;
import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.entity.User;
import com.innohub.innosorter.util.ApplicationConstants;

public class IssueClustererTest {

    private static IssueClusterer clustering;

//    private 
    @BeforeClass
    public static void SetUp() {

        
        clustering = Mockito.spy(new IssueClusterer());
    }

    public ExpectedException expected = ExpectedException.none();
    
    // Verify that the cluster title is not null
    @Test
    public void shouldThrowExceptionWhenTitleOfClusterIsNull() {
        // Given
        Cluster issue = new Cluster();
        issue.setTitle(null);
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(0);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);
        issue.setPosts(new ArrayList<Post>());
   
    	clustering.clusters.put(1, issue);
    	
       // When
        clustering.validateClusterData();;

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_TITLE_IS_NULL_MSG);
    }
    
    // Verify that the every cluster have at least one forum post assigned 
    @Test
    public void shouldThrowExceptionWhenClusterHasNoForumPosts() {
        // Given
        Cluster issue = new Cluster();
        issue.setTitle("IssueName2");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(0);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);
        issue.setPosts(new ArrayList<Post>());
   
    	clustering.clusters.put(1, issue);
    	
       // When
        clustering.validateClusterForumPostMapping();

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_HAVE_NO_FORUM_POSTS_MSG);
    }
    
    // Verify that the cluster title is not empty
    @Test
    public void shouldThrowExceptionWhenTitleOfClusterIsEmpty() {
        // Given
        Cluster issue = new Cluster();
        issue.setTitle("");
        issue.setSummary("Javascript Not Working");
        issue.setNumOfForumPosts(0);
        issue.setNumOfUserImpacted(20);
        issue.setAssignees(Arrays.asList(new User("Karthik"), new User("Hesam")));
        issue.setCurrentStatus("ASSIGNED");
        issue.setPriority(3);
        issue.setPosts(new ArrayList<Post>());
   
    	clustering.clusters.put(1, issue);
    	
       // When
        clustering.validateClusterData();;

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.CLUSTER_TITLE_IS_EMPTY_MSG);
    }
    
    // Verify that the every forum post has a cluster
    @Test
    public void shouldNotLeftAnyForumPostsWithoutCluster() {
        // Given
    	Post post = new Post();
    	post.setContent("Content");
    	post.setDate("date");
    	post.setInfoId(1234);
    	post.setTitle("title");
    	post.setUrl("url");
    	post.setUserName("name");
   
    	clustering.forumPosts.add(post);
    	
       // When
        clustering.validateClusterForumPostMapping();

        // Then
        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.FORUM_POST_HAVE_NO_CLUSTER_ASSIGNED_MSG);
    }
    
    // Verify that the raw forum post data file is processed successfully
    @Test
    public void shouldProccessForumRawDataSuccessfully() throws FileNotFoundException, IOException {
        // Given
        String forumDataFile = "sample10.arff";

        // When
        ArrayList<Post> returnedForumPosts = clustering.processFroumPosts(forumDataFile);

        // Then
        Assert.assertTrue("Structured data structure of forum posts are empty.", !(returnedForumPosts.isEmpty() || returnedForumPosts == null));
    }

    // Verify that it creates clusters after processing the raw training data
    @Test
    public void shouldPutSimilarForumPostsInOneCluster() throws Exception {
        Map<Integer, Cluster> clusters = new HashMap<Integer, Cluster>();

        // Given
        String trainingDataFile = "training_data.txt";

        // When
        clusters = clustering.createClustersMap(trainingDataFile);

        // Then
        Assert.assertTrue("No clusters are created by processing training data", !(clusters.isEmpty() || clusters == null));
    }
}