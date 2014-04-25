package weddingsite.client;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class test {
	
	
	
	public static void main (String [] args) {
		
		Calendar calendar = new GregorianCalendar();
		calendar.set(2014, 3, 1);

//		int year       = calendar.get(Calendar.YEAR);
//		int month      = calendar.get(Calendar.MONTH); 
//		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // Jan = 0, not 1
//		int dayOfWeek  = calendar.get(Calendar.DAY_OF_WEEK);
//		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
//		int weekOfMonth= calendar.get(Calendar.WEEK_OF_MONTH);
//
//		int hour       = calendar.get(Calendar.HOUR);        // 12 hour clock
//		int hourOfDay  = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
//		int minute     = calendar.get(Calendar.MINUTE);
//		int second     = calendar.get(Calendar.SECOND);
//		int millisecond= calendar.get(Calendar.MILLISECOND);
		
		System.out.println(calendar.get(GregorianCalendar.DAY_OF_WEEK));
		
		System.out.println(calendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
	}

}
