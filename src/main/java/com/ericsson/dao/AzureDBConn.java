package com.ericsson.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ericsson.dto.request.SignUpUserProfileRequestDTO;
import com.ericsson.utils.KeychainUtils;
import com.ericsson.vo.UserProfileVO;

public class AzureDBConn {
	
	private Connection connection;
	
	public AzureDBConn() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    String hostName = "keychain-db";
	    String dbName = "keychain-db";
	    String user = "keychain-admin";
	    String password = "Key@2020";
	    String url = String.format("jdbc:sqlserver://keychain-db.database.windows.net:1433;database=keychain-db;user=keychain-admin@keychain-db;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;", hostName, dbName, user, password);
	    connection = DriverManager.getConnection(url, user, password);
	    
	}
	
	public void closeConnection(){
		if (null != connection){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
	public UserProfileVO searchUserProfile(String userName) throws ClassNotFoundException, SQLException {
		
		UserProfileVO userProfileVO = new UserProfileVO();
		Statement stat = connection.createStatement();
		ResultSet rs = stat.executeQuery(
				"select USER_ID,USER_EMAIL,USER_NAME,USER_FIRST_NAME,USER_LAST_NAME,USER_PASSWORD,CREATE_TIMESTAMP,LAST_UPDATE_TIMESTAMP from IAM_PROFILE where USER_NAME='"
						+ userName + "'");
		while (rs.next()) {
			userProfileVO.setUserId(rs.getString("USER_ID"));
			userProfileVO.setUserEmail(rs.getString("USER_EMAIL"));
			userProfileVO.setUserName(rs.getString("USER_NAME"));
			userProfileVO.setUserPassword(rs.getString("USER_PASSWORD"));
			userProfileVO.setUserFirstName(rs.getString("USER_FIRST_NAME"));
			userProfileVO.setUserLastName(rs.getString("USER_LAST_NAME"));
			userProfileVO.setCreateTimestamp(rs.getString("CREATE_TIMESTAMP"));
			userProfileVO.setUserLastName(rs.getString("LAST_UPDATE_TIMESTAMP"));
		}
		rs.close();
		stat.close();
		
		return userProfileVO;

	}
	
	
	public void signUpUser(SignUpUserProfileRequestDTO userObj) throws ClassNotFoundException, SQLException{
		
		String insertSQL = "insert into IAM_PROFILE (USER_ID,USER_EMAIL,USER_NAME,USER_PASSWORD,USER_FIRST_NAME,USER_LAST_NAME,CREATE_TIMESTAMP) values "
				+ "('" + userObj.getUserId() + "','" + userObj.getUserEmail() + "','" + userObj.getUserName() + "','" + userObj.getUserPassword() + "','"
				+ userObj.getUserFirstName() + "','" + userObj.getUserLastName() + "','"
				+ KeychainUtils.getCurrentTimestamp() + "')";
		
		Statement stat = connection.createStatement();
		stat.executeUpdate(insertSQL);
		stat.close();
	   /* ResultSet rs = stat.executeQuery("select USER_ID,USER_EMAIL,USER_NAME, from IAM_PROFILE where USER_ID='" + userId + "'");
	    while(rs.next()) {
	        System.out.println(rs.getString("USER_ID"));
	    }
	    rs.close();
	    stat.close();*/
		
	}
	
	 
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		AzureDBConn azure = new AzureDBConn();
		SignUpUserProfileRequestDTO userProfile = new SignUpUserProfileRequestDTO();
		userProfile.setUserEmail("keychain@test.com");
		userProfile.setUserId(KeychainUtils.generateUserId());
		userProfile.setUserName("keychain");
		userProfile.setUserFirstName("Key");
		userProfile.setUserLastName("keychain last");
		userProfile.setCreateTimestamp(KeychainUtils.getCurrentTimestamp());
		azure.signUpUser(userProfile);
	}
	
	public void testAzureConnect() throws ClassNotFoundException, SQLException {

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String hostName = "keychain-db";
		String dbName = "keychain-db";
		String user = "keychain-admin";
		String password = "Key@2020";
		String url = String.format(
				"jdbc:sqlserver://keychain-db.database.windows.net:1433;database=keychain-db;user=keychain-admin@keychain-db;password={your_password_here};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
				hostName, dbName, user, password);
		Connection conn = DriverManager.getConnection(url, user, password);
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("select 1+1 as sum");
		while (rs.next()) {
			System.out.println(rs.getInt("sum"));
		}
		rs.close();
		stat.close();
		conn.close();
	}

}
