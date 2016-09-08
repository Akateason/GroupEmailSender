package xtc.mail;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gson.Gson;

import cn.myapp.model.ResultObj;
import xtc.http.HttpRequest;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class XtMail {
	
	// 邮箱发送者用户名
	private final static String kEmailSenderUserName = "stat@subaojiang.com" ;
	// 邮箱发送者密码
	private final static String kEmailSenderPassword = "123hehedA" ;	
	// 接受者
	private final static String kEmailReceiveName	 = "rank@subaojiang.com" ; 			
//	private final static String kEmailReceiveName	 = "xietianchen@subaojiang.com" ; // TEST
	
	public static void sendMail(String fromMail, String user, String password, String toMail, String mailTitle,
			String mailContent) throws Exception 
	{
		Properties props = new Properties(); 
		props.put("mail.smtp.host", "smtp.exmail.qq.com");// 存储发送邮件服务器的信息
		props.put("mail.smtp.auth", "true");// 同时通过验证
		props.put("mail.smtp.port", 25) ;
		
		Session session = Session.getInstance(props); // 根据属性新建一个邮件会话
		// session.setDebug(true); //有他会打印一些调试信息。
		
		MimeMessage message = new MimeMessage(session);// 由邮件会话新建一个消息对象
		message.setFrom(new InternetAddress(fromMail));// 设置发件人的地址
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(toMail));// 设置收件人,并设置其接收类型为TO
		message.setSubject(mailTitle);// 设置标题
		// 设置信件内容
		message.setText(mailContent); //发送 纯文本 邮件 todo
		//message.setContent(mailContent, "text/html;charset=gbk"); // 发送HTML邮件，内容样式比较丰富
		message.setSentDate(new Date());// 设置发信时间
		message.saveChanges();// 存储邮件信息
		
		// 发送邮件
		 Transport transport = session.getTransport("smtp");
//		Transport transport = session.getTransport();
		transport.connect(user, password);
		transport.sendMessage(message, message.getAllRecipients());// 发送邮件,其中第二个参数是所有已设好的收件人地址
		transport.close();
	}
		
	public static boolean couldSendOnTime() {
		Calendar calendar = Calendar.getInstance() ;
		calendar.set(Calendar.HOUR_OF_DAY, 10); 
		calendar.set(Calendar.MINUTE, 0); 
		calendar.set(Calendar.SECOND, 0); 
		Date tenClock = calendar.getTime();
		Date now = new Date() ;
	    long diff = now.getTime() - tenClock.getTime() ;
		
		return (diff <= 1000 * 60 * 10)  ;
	}
	
	
	// URL [ fetch email info ]
	private final static String kUrlFetchInfo 		= "http://localhost/GsdataApp/note/fetchGroupSendInfo" ;		
	
	/**
	 * actionInLoop
	 * 1 .send mail
	 * in an one day loop .
	 * @throws Exception
	 */
	public static void actionInLoop() throws Exception 
	{	
		// protect for send time . if IN TEST . cancel next line .
		if (!couldSendOnTime()) return ;
				
		// do request .
		String response = HttpRequest.sendGet(kUrlFetchInfo, "") ;
		Gson gson = new Gson() ;
		ResultObj resultObj = gson.fromJson(response, ResultObj.class) ;
		// success .
		if ( Integer.parseInt(resultObj.getReturnCode()) == 1001 ) {		
			
			HashMap<String, Object> map = (HashMap<String, Object>) resultObj.getReturnData() ;
			//get EMIAL TITLE , CONTENT
			String sEmailTitle = (String) map.get("title") ;
			String sEmailDetail = (String) map.get("detail") ;
			
			// SEND EMAIL .
			XtMail.sendMail(kEmailSenderUserName ,
					kEmailSenderUserName ,
					kEmailSenderPassword ,
					kEmailReceiveName ,
					sEmailTitle , 
					sEmailDetail) ;	
			
			// DingDing
			HttpRequest.sendGet("http://localhost/GsdataApp/ding/index", "") ;
		} 		
	}

}
