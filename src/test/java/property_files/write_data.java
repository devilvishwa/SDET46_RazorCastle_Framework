package property_files;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class write_data {
	public static void main(String[] args) throws IOException {
		
		//step 1--- convert the physical file into java readable object
		FileOutputStream fos= new FileOutputStream("./src/test/resources/Data/rmg.properties");
		
		//step 2--- create object for properties class
		Properties prop = new Properties();
		
		//step3--- store data
		prop.setProperty("url", "http://localhost:8888");
		
		//step4---- save data
		prop.store(fos, "");
		
		//step5 ---- close stream
		fos.close();
		
	}
}
