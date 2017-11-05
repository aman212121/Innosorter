package com.innohub.innosorter.entity;

import weka.core.Instance;

public class Post {
	
	int postId;
	private String title;
	private String date;
	private String userName;
	private int infoId;
	private String content;
	private String url;
	private int clusterID = -1;

    public Post(Instance rawData){
    	postId = (int)rawData.value(0);
        setTitle(rawData.stringValue(1));
        setDate(rawData.stringValue(2));
        setUserName(rawData.stringValue(3));
        setInfoId((int)rawData.value(4));
        setContent(rawData.stringValue(5));
        setUrl(rawData.stringValue(6));
    }
    
    public Post() {}

	public Object getId() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public int getPostID() {
		return postId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getClusterID() {
		return clusterID;
	}

	public void setClusterID(int clusterID) {
		this.clusterID = clusterID;
	}
	
	
}
