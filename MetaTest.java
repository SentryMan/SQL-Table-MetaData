package com.collabera.metadatatest;

import java.sql.*;

public class MetaTest {
	
	static final String URL = "jdbc:mysql://localhost:3306/sakila";
	static final String USERNAME = "root";
	static final String PASSWORD = "root";
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			System.out.println("Success! Connection was made");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	
	
	public static void main(String[] args) {
		
		Connection conn = MetaTest.getConnection();
		Statement statement;
		
		try {
			statement = conn.createStatement();
		
			ResultSet rs = statement.executeQuery("Select * from actor");

			ResultSetMetaData MD = rs.getMetaData();
			int numberOfColumns = MD.getColumnCount();
			
			for(int i = 1; i <= numberOfColumns; i++) {
		
				String name = MD.getColumnName(i);
				int type = MD.getColumnType(i);
				String typeName = MD.getColumnTypeName(i);
			
			
				System.out.println("\nColumn name is: " + name);
				System.out.println("Column type: " + type);
				System.out.println("Column type in english is: " + typeName);
			}
			
			conn.close();
			System.out.println("\nConnection was closed my guy!");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}