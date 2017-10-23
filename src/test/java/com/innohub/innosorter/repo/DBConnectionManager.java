package com.innohub.innosorter.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class DBConnectionManager {

    protected static String uri;

    protected static Connection dbConnection;
    
    
    public static Connection setup(String uriParam, String userName, String password) throws Exception {
          if(uriParam == null){
              uri = "jdbc:mysql://localhost:3306/innosorter";
          } else {
              uri = uriParam;
          }

          if (userName == null ) userName = "root";
          if (password == null ) password = "rootpass";
           
          dbConnection = DriverManager.getConnection(uri, userName, password);
          
          if (dbConnection == null) {
              throw new Exception("Failed to make connection!");
          }
          return dbConnection;
    }

    public static void getNumberOfClusters() throws Exception{
        PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(*) FROM CLUSTER");
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            System.out.println(rs.getInt(1));
        }
        statement.close();
//        this.dbConnection.close();
    }
    

}
