package com.innohub.innosorter.entity;

public enum UserType {

	DEVELOPER, ADMIN;
	 
	public static UserType forValue(String value) {
	    for (UserType val : values())
	        if (val.toString().equalsIgnoreCase(value))
	            return val;
	    return null;
	}
}
