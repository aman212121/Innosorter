package com.innohub.innosorter.management;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Developer;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.util.ApplicationConstants;

public class IssueManagerTest {

    private IssueManager issueManager;

    @Before
    public void setUp(){
        this.issueManager = new IssueManager();
    }

    @Rule
    public ExpectedException expected = ExpectedException.none();
    
    @Test
    public void shouldNotAllowNonAdminUsersToAddAForumPostToACluster(){

        Cluster clusterOne = new Cluster();
        Post postOne = new Post();

        expected.expect(RuntimeException.class);
        expected.expectMessage(ApplicationConstants.DOES_NOT_PRIVILEGE_MSG);

        Developer developer = new Developer("DeveloperOne");
        issueManager.addPostToCluser(developer, clusterOne, postOne);
    }
}
