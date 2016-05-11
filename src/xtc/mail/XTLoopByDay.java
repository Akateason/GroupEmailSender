package xtc.mail;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class XTLoopByDay implements ServletContextListener {

	private Timer timer = null ;
//	private final static long kTimerTaskDuration = 1000 * 10 ; 	// FOR TEST 
	private final static long kTimerTaskDuration = 24 * 60 * 60 * 1000 ; 
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {		
		timer.cancel(); // RELEASE
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance() ;
		calendar.set(Calendar.HOUR_OF_DAY, 10); 
		calendar.set(Calendar.MINUTE, 0); 
		calendar.set(Calendar.SECOND, 0); 
		Date timeInDay = calendar.getTime();
		System.out.println("执行时间：" + timeInDay);// FETCH TIME IN EVERY DAY.

		timer = new Timer(true) ;		
	     // beginning at the specified time. Subsequent executions take place at
	     // approximately regular intervals, separated by the specified period.
		timer.scheduleAtFixedRate(new contractTask(), timeInDay, kTimerTaskDuration);
	}
}

class contractTask extends TimerTask {
	
	public void run() {
        
		Date date = new Date(this.scheduledExecutionTime());
        System.out.println("本次执行该线程的时间：" + date);
        
        try {
			XtMail.actionInLoop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
