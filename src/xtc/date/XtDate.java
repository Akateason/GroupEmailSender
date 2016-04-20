package xtc.date;

import java.util.Calendar;
import java.util.Date;

public class XtDate {
	
	public static Date getCurrentDayOriginalDate() {
		Calendar calendar = Calendar.getInstance() ;
		calendar.set(Calendar.HOUR_OF_DAY, 0); 
		calendar.set(Calendar.MINUTE, 0); 
		calendar.set(Calendar.SECOND, 0); 
		Date today = calendar.getTime();
		return today ;
	}
		
}
