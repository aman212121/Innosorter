package com.innohub.innosorter.clustering;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.innohub.innosorter.entity.Cluster;
import com.innohub.innosorter.entity.Post;
import com.innohub.innosorter.util.ApplicationConstants;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.DBSCAN;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class IssueClusterer {

	String forumDataPath = "sample10.txt";
	String forumPostsArff = "forumPostsArff.arff";
	String trainingDataPath = "training_data.txt";
	String traningDataArff = "training_data.arff";
	ArrayList<Post> forumPosts = new ArrayList<Post>();
	Map<Integer, Cluster> clusters = new HashMap<Integer, Cluster>();
	ClusterEvaluation eval;
	Instances trainingData;

	public void convertTextFileIntoArffFile(String textFilePath, String arffFilePath) throws IOException {
		String forumPostDataFileCsv = "csvFileTransformation.csv";
		File txtFile = new File(textFilePath);
		File csvFile = new File(forumPostDataFileCsv);
		txtFile.renameTo(csvFile);

		CSVLoader loader = new CSVLoader();
		loader.setSource(new File(forumPostDataFileCsv));
		Instances data = loader.getDataSet();

		ArffSaver saver = new ArffSaver();
		saver.setInstances(data);
		saver.setFile(new File(arffFilePath));
		saver.writeBatch();
	}

	public ArrayList<Post> processFroumPosts(String forumDataFile) throws FileNotFoundException, IOException {
		Instances data = new Instances(new BufferedReader(new FileReader(forumDataFile)));
		ArrayList<Post> posts = new ArrayList<Post>();
		for (int i = 0; i < data.numInstances(); i++) {
			Post post = new Post(data.instance(i));
			posts.add(post);
		}
		return posts;
	}

	public void setPosts() throws FileNotFoundException, IOException {
		convertTextFileIntoArffFile(forumDataPath, forumPostsArff);
		forumPosts = processFroumPosts(forumPostsArff);
	}

	public void setClusters() throws Exception {
		convertTextFileIntoArffFile(trainingDataPath, traningDataArff);
		clusters = createClustersMap(traningDataArff);
	}

	public Map<Integer, Cluster> createClustersMap(String trainingDataFile) throws Exception {
		trainingData = new Instances(new BufferedReader(new FileReader(trainingDataFile)));
		StringToWordVector s = new StringToWordVector();
		s.setInputFormat(trainingData);
		trainingData = Filter.useFilter(trainingData, s);
		DBSCAN dbscan = new DBSCAN();
		dbscan.setEpsilon(1);
		dbscan.setMinPoints(1);
		dbscan.buildClusterer(trainingData);
		eval = new ClusterEvaluation();
		eval.setClusterer(dbscan);
		eval.evaluateClusterer(trainingData);
		Map<Integer, Cluster> clusters = new HashMap<Integer, Cluster>();
		for (int i = 0; i < eval.getNumClusters(); i++) {
			clusters.put(i, new Cluster(i));
		}
		return clusters;
	}

	public void createClusterForumPostMapping() {
		double[] assignments = eval.getClusterAssignments();
		for (int i = 0; i < assignments.length; i++) {
			int clusterNum = (int) assignments[i];
			forumPosts.get(i).setClusterID(clusterNum);
			clusters.get(clusterNum).setClusterID(clusterNum);
			clusters.get(clusterNum).addForumPost(forumPosts.get(i));
		}
	}

	// Process the context clusting in the final step
	public void processContextOfClusters(Cluster cluster) throws Exception {
		Set<Integer> postIds = new HashSet<Integer>();
		List<Post> postList = cluster.getPosts();
		for (Post post : postList) {
			int postId = post.getPostID();
			postIds.add(new Integer(postId));
		}

		List<String[]> contextList = new LinkedList<String[]>();
		for (int id : postIds) {
			for (Instance instance : trainingData) {
				if (instance.stringValue(3).equals(String.valueOf(id)) && instance.stringValue(2).equals(0)) {
					String[] data = new String[] { instance.stringValue(3), instance.stringValue(2) };
					contextList.add(data);
				}
			}
		}
		String[] contexts = new String[] {};
		int i = 0;
		for (String[] record : contextList) {
			String oneString = record[0] + "," + record[1];
			contexts[i] = oneString;
			i++;
		}
		BufferedWriter br = new BufferedWriter(new FileWriter("contexts.csv"));
		StringBuilder sb = new StringBuilder();
		for (String element : contexts) {
			sb.append(element);
			sb.append("%n");
		}
		br.write(sb.toString());
		br.close();

		// Convert the csv file to
		CSVLoader loader1 = new CSVLoader();
		loader1.setSource(new File("contexts.csv"));
		Instances data1 = loader1.getDataSet();

		ArffSaver saver1 = new ArffSaver();
		saver1.setInstances(data1);
		saver1.setFile(new File("contexts.arff"));
		// saver1.setDestination(new File("sentences.arff"));
		saver1.writeBatch();

		ArffLoader arffLoader = new ArffLoader();
		arffLoader.setSource(new File("contexts.arff"));
		Instances contextInstances = arffLoader.getDataSet();

		StringToWordVector s = new StringToWordVector();
		s.setInputFormat(contextInstances);
		contextInstances = Filter.useFilter(contextInstances, s);
		DBSCAN dbscan = new DBSCAN();
		dbscan.setEpsilon(1);
		dbscan.setMinPoints(1);
		dbscan.buildClusterer(contextInstances);
		ClusterEvaluation evalContext = new ClusterEvaluation();
		evalContext.setClusterer(dbscan);
		evalContext.evaluateClusterer(contextInstances);
	}

	// Use text summarization algorithm create the title of the cluster using
	// the forum post titles
	public void generateClusterTitle(Cluster cluster) {
		// TODO Auto-generated method stub
	}

	// Use text summarization algorithm to create the summary of the cluster
	// using the content of cluster
	public void generateClusterSummary(Cluster cluster) {
		// TODO Auto-generated method stub
	}

	public void validateClusterForumPostMapping() {
		for (Map.Entry<Integer, Cluster> entry : clusters.entrySet()) {
			if(entry.getValue().getPosts().isEmpty()){
				throw new RuntimeException(ApplicationConstants.CLUSTER_HAVE_NO_FORUM_POSTS_MSG);
			}
		}
		for(Post post : forumPosts){
			if(post.getClusterID() == -1){
				throw new RuntimeException(ApplicationConstants.FORUM_POST_HAVE_NO_CLUSTER_ASSIGNED_MSG);
			}
		}
	}
	
	public void validateClusterData() {
		for (Map.Entry<Integer, Cluster> entry : clusters.entrySet()) {
			if(entry.getValue().getTitle().equals(null)){
				throw new RuntimeException(ApplicationConstants.CLUSTER_TITLE_IS_NULL_MSG);
			}else if(entry.getValue().getTitle().equals("")){
				throw new RuntimeException(ApplicationConstants.CLUSTER_TITLE_IS_EMPTY_MSG);
			}
		}
	}
	
	public void processClustering() throws Exception {
		setPosts();
		setClusters();
		createClusterForumPostMapping();
		validateClusterForumPostMapping();
		for (Map.Entry<Integer, Cluster> entry : clusters.entrySet()) {
			processContextOfClusters(entry.getValue());
			generateClusterTitle(entry.getValue());
			generateClusterSummary(entry.getValue());
		}
		validateClusterData();
	}

}