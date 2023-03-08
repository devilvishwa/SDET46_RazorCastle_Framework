package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Write_data {
	public static void main(String[] args) throws SQLException {
		
		Driver dbDriver= new Driver();
		DriverManager.registerDriver(dbDriver);
		
		Connection connection=null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet46","root","root");
			Statement statement = connection.createStatement();
			int result = statement.executeUpdate("insert into employee values(4,'jerry', 65000);");
			
			if(result==1)
			{
				System.out.println("data added successfully");
			}
			
		} finally {
			connection.close();
			System.out.println("connection closed");
		}
		
	}

}
