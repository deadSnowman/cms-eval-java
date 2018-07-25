package com.test.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public abstract class Entity {
	public static final String DRIVER = "org.sqlite.JDBC";
	public static final String CONNECTION_STRING = "jdbc:sqlite:test2db";

	
	public Connection getConnection() throws Exception {
		Connection conn = null;
		
		try{
			
	        Class.forName(DRIVER);  
	        conn = DriverManager.getConnection(CONNECTION_STRING);
	        if(!hasInitData(conn)) {

	        	initDb(conn);
	        }
	    }catch (Exception ex){
	          throw ex;
	    }
		
		return conn;
	}
	
	public boolean hasInitData(Connection conn) {
		boolean hasData = false;
		
		try {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet set = meta.getTables(null, null, "%",new String[] {"TABLE"});
			while(set.next()) {
				String tableName = set.getString("TABLE_NAME");
				if(tableName.equalsIgnoreCase("employee")) {
					hasData = true;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return hasData;
	}
	
	public void initDb(Connection con) {
		String initSql = getInitScriptAsString();
		
		try {
			con.createStatement().executeUpdate(initSql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public String getInitScriptAsString() {
		String sql = "";
		
		try (BufferedReader scanner = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/init.sql")))){
			StringBuilder builder = new StringBuilder();
			String line = "";
			
			while((line=scanner.readLine()) != null) {
			    builder.append(line);
			}
			
			sql = builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sql;
	}
}
