package model;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ConnectionUtil {
	
	private static DataSource ds = new BasicDataSource().ds;
	
	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	

		
		
	}
	
	class BasicDataSource{
		DataSource ds=null;
		BasicDataSource(){
			try {
				InitialContext initialContext = new InitialContext();
				ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/mycp");
				}catch(Exception ex) {ex.printStackTrace();}
			
		}
		
	}