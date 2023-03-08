package Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class sample_fetch1 {
	public static void main(String[] args) throws SQLException {
		Driver dbDriver =new Driver();
		DriverManager.registerDriver(dbDriver);
		
		Connection connection=null;
		try {
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/razor","root","root");
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from owner");
			
			while(result.next())
			{
				String ownerName = result.getString("OwnerName");
				int mobile = result.getInt(3);
				System.out.println(ownerName+ " "+mobile);
			}
		} 
		finally {
			
			connection.close();
			System.out.println("connection closed");
			
		}
	}
}
