package vtiger_crm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class getDate {
	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("dd_MM_yyyy");
		String date = sdf.format(new Date());
		System.out.println(date);
	}
}
