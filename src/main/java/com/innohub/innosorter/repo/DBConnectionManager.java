package com.innohub.innosorter.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionManager {

    protected static Connection dbConnection;

    public static Connection setup(String uriParam, String userName, String password) {
        try {
            if (uriParam == null) {
                uriParam = "jdbc:mysql://localhost:3306/innosorter";
            }

            if (userName == null)
                userName = "root";
            if (password == null)
                password = "";

            dbConnection = DriverManager.getConnection(uriParam, userName, password);

            if (dbConnection == null) {
                throw new RuntimeException("Failed to make connection!");
            }
            return dbConnection;

        } catch (SQLException ex) {
            System.out.println("Can not connect to Databas with parameters: " + uriParam);
            throw new RuntimeException("Failed to make connection!");
        }
    }

    public static Connection getConnection() {
        return dbConnection;
    }

    public static ResultSet getNumberOfClusters() throws Exception {
        PreparedStatement statement = dbConnection.prepareStatement("SELECT COUNT(*) FROM CLUSTER");
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            System.out.println(rs.getInt(1));
        }
        statement.close();
        return rs;
    }

}
