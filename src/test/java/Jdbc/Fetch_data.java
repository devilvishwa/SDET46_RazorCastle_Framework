package Jdbc;

import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import com.mysql.cj.jdbc.Driver;

	public class Fetch_data {
		public static void main(String[] args) throws SQLException {
			//step-1 create instance for driver ---> register driver to jdbc

			Driver dbDriver=new Driver();
			DriverManager.registerDriver(dbDriver);
			
			Connection connection=null;
			try {
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet46","root","root");
				
				//step-3 create statement
				Statement statement = connection.createStatement();
				
				//step-4 execute querry
				ResultSet result = statement.executeQuery("select * from employee");
				
				//step5 iterate data and verify or fetch
				while(result.next())
				{
					String empName = result.getString("emp_name");
					int empId = result.getInt(1);
					System.out.println(empName+" "+empId);
				}
				
			} 
			finally {
				//step 6 -- close connection
				connection.close();
				System.out.println("connection closed");
			}
		}
	}


