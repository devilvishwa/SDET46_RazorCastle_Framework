package vtiger_crm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class getDate2 {
	public static void main(String[] args) throws ParseException {
		String dateString="1_Mar_2020";
		SimpleDateFormat sdf=new SimpleDateFormat("dd_MMM_yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(dateString));
		//cal.add(Calendar.DAY_OF_MONTH, -3);
		String date= sdf.format(cal.getTime());
		System.out.println(date);
		
	}
}
