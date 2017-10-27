package com.innohub.innosorter.management;

import java.util.Arrays;
import java.util.List;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.repo.IssueRepositoryService;

public class IssueSorter {

    IssueRepositoryService IssueRepositoryService;

    List<Cluster> getSortedListOfIssues() {
        List<Cluster> listOfClusters = IssueRepositoryService.getListOfClusters();

        Cluster[] sortedList = listOfClusters.toArray(new Cluster[0]);

        for (int outerLoopIndex = 0; outerLoopIndex < sortedList.length - 1; outerLoopIndex++) {
            int index = outerLoopIndex;
            for (int innerLoopIndex = outerLoopIndex + 1; innerLoopIndex < sortedList.length; innerLoopIndex++)
                if (sortedList[innerLoopIndex].getNumOfForumPosts() < sortedList[index].getNumOfForumPosts())
                    index = innerLoopIndex;

            Cluster smallerNumber = sortedList[index];
            sortedList[index] = sortedList[outerLoopIndex];
            sortedList[outerLoopIndex] = smallerNumber;
        }

        return Arrays.asList(sortedList);

    }
}
