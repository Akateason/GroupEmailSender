package xtc.mail;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import xtc.json.JsonToMap;
import xtc.model.gsdata.Nickname;
import xtc.http.HttpRequest;

import java.util.Date;
import java.util.Properties;


public class XtMail {
	
	// 邮箱发送者用户名
	private final static String kEmailSenderUserName = "xietianchen@subaojiang.com" ;
	// 邮箱发送者密码
	private final static String kEmailSenderPassword = "x123456" ;	
	// 接受者
	private final static String kEmailReceiveName	 = "rank@subaojiang.com" ; 	
	// URL [gsdata/api]
	private final static String kUrlGsdataApi	 	 = "http://114.55.74.220:8080/gsdata/api" ; 	
	
	public static void sendMail(String fromMail, String user, String password, String toMail, String mailTitle,
			String mailContent) throws Exception {
		Properties props = new Properties(); // 可以加载一个配置文件
		// 使用smtp：简单邮件传输协议
		props.put("mail.smtp.host", "smtp.exmail.qq.com");// 存储发送邮件服务器的信息
		props.put("mail.smtp.auth", "true");// 同时通过验证

		Session session = Session.getInstance(props);// 根据属性新建一个邮件会话
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
		// Transport transport = session.getTransport("smtp");
		Transport transport = session.getTransport();
		transport.connect(user, password);
		transport.sendMessage(message, message.getAllRecipients());// 发送邮件,其中第二个参数是所有已设好的收件人地址
		transport.close();
	}
	
	
	private final static String kSName7Days	  = "wx/opensearchapi/nickname_order_list" ;
	private final static String kParamJsonStr = "{\"num\":7,\"sort\":\"asc\",\"wx_nickname\":\"日本流行每日速报\"}" ;
	
	public static void doSendMail() throws Exception {
		
		// REQUEST .
		String resultStr = HttpRequest.sendGet(kUrlGsdataApi, "spaceName=" + kSName7Days + "&jsonStr=" + kParamJsonStr) ; 
		// PARSE .
		JsonObject resultMap = JsonToMap.parseJson(resultStr) ;		
		JsonObject resultData = resultMap.get("returnData").getAsJsonObject() ;
		JsonArray itemsList = resultData.get("items").getAsJsonArray() ;
		JsonElement lastDayInfoElement = itemsList.get((itemsList.size() - 2)) ; // 前天的 .		
		Gson gson = new Gson() ;
		// GET NICKNAME INFO .
		Nickname nickname = gson.fromJson(lastDayInfoElement, Nickname.class) ;
		// EMAIL CONTENT .
		EmailContentDisplay display = new EmailContentDisplay() ;
		String emailContentStr = display.getEmailContentWillDisplay(nickname) ;
		// EMIAL TITLE 
		String sEmailTitle = "【通知】" + nickname.getResult_day() + "数据分析" ;		
		
		// DO SEND EMAIL .
		XtMail.sendMail(kEmailSenderUserName,
				kEmailSenderUserName,
				kEmailSenderPassword,
				kEmailReceiveName,
				sEmailTitle, 
				emailContentStr) ;
		
	}
	
}
