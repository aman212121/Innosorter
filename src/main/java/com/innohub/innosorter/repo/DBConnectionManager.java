package com.innohub.innosorter.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DBConnectionManager {

    protected static String uri;

    protected static Connection dbConnection;
    
    
    public static Connection setup(String uriParam, String userName, String password) throws SQLException {
          if(uriParam == null){
              uri = "jdbc:mysql://localhost:3306/innosorter";
          } else {
              uri = uriParam;
          }

          if (userName == null ) userName = "root";
          if (password == null ) password = "";
           
          dbConnection = DriverManager.getConnection(uri, userName, password);
          
          if (dbConnection == null) {
              throw new RuntimeException("Failed to make connection!");
          }
          return dbConnection;
    }

    public static Connection getConnection(){
        return dbConnection;
    }

    public static ResultSet getNumberOfClusters() throws Exception{
        PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(*) FROM CLUSTER");
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            System.out.println(rs.getInt(1));
        }
        statement.close();
        return rs;
//        this.dbConnection.close();
    }
    

}
