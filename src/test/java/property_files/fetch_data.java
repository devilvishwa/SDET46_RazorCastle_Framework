package property_files;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class fetch_data {
	public static void main(String[] args) throws IOException {
		//step 1--- convert the physical file into java readable object
		FileInputStream fis=new FileInputStream("./src/test/resources/Data/rmg.properties");
		
		//step 2--- create object for properties class
		Properties prop = new Properties();
		
		//step3----  load all keys
		prop.load(fis);
		
		//step4 --- fetch data
		String password = prop.getProperty("password");
		System.out.println(password);
		
		//step5 --- close input stream
		fis.close();
		
		
	}
}
