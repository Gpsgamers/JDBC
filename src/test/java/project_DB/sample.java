package project_DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

public class sample {

	public static Connection connection = null;
	public static Statement statement = null;
	public static ResultSet result = null;
	static String DBurl = "jdbc:mysql://localhost:3306/?user=root";
	static String user = "root";
	static String password = "admin1234";
	static String DB_name = "sakila";
	
	public static Connection getConnection(String DBurl, String user, String password) throws SQLException {
		connection = DriverManager.getConnection(DBurl, user, password);
		connection.setAutoCommit(true);
		return connection;
	}
	public static ResultSet getdata(String query) throws SQLException {
		List ls1 = null, ls2;
		try {
			connection = getConnection(DBurl, user, password);
			System.out.println("connection successful!....\n****************");
			connection.setCatalog(DB_name);
			statement = connection.createStatement();
			result = statement.executeQuery(query);
//			ls1 = new ArrayList<List>();
//			for (int j = 0; result.next(); j++) {
//				ls2 = new ArrayList();
//				ls1.add(ls2);
//				for (int i = 1; true; i++) {
//					try {
//						ls2.add(result.getString(i));
//					} catch (Exception e) {
//						break;
//					}
//				}
//			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connection.close();
			 return result;
			//return ls1;
		}
	}

	public static void main(String[] args) throws SQLException {
		ResultSet res = getdata("select first_name , last_name from actor where first_name like '%oo%' or last_name like '%oo%'");
		while (res.next()) {
			System.out.println(res.getString("first_name")); 
		}
	}
}
