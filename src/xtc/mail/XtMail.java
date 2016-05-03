package xtc.mail;

//import javax.mail.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import xtc.model.gsdata.Nickname;
import xtc.data.fetch.FetchGsdata;

import java.util.Date;
import java.util.Properties;


public class XtMail {
	
	// 邮箱发送者用户名
	private final static String kEmailSenderUserName = "stat@subaojiang.com" ;
	// 邮箱发送者密码
	private final static String kEmailSenderPassword = "123hehedA" ;	
	// 接受者
	private final static String kEmailReceiveName	 = "rank@subaojiang.com" ; 			
	
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
	
	public static void doSendMail() throws Exception 
	{
		FetchGsdata fetcher = new FetchGsdata() ;	
		// GET SUBAO DETAIL NICKNAME INFO .
		Nickname nickname = fetcher.fetchSubaoNickname() ; 
		// EMAIL CONTENT .
		EmailContentDisplay display = new EmailContentDisplay() ;
		String subaoDetailInfo = display.getEmailContentWillDisplay(nickname) ;
		
		// GET SORT INFO
		String sortInfo = fetcher.fetchSortFromTwoDaysAgo() ;
		String emailContentStr = sortInfo + subaoDetailInfo ;
		
		// EMIAL TITLE .
		String sEmailTitle = "【通知】" + nickname.getResult_day() + "数据分析" ;		
		
		// DO SEND EMAIL .
		XtMail.sendMail(kEmailSenderUserName ,
				kEmailSenderUserName ,
				kEmailSenderPassword ,
				kEmailReceiveName ,
				sEmailTitle , 
				emailContentStr) ;
		
//		XtMail.sendMainMethod2(sEmailTitle, emailContentStr) ; 
	}
	
	
	/*
	 * 
	 private static final String ALIDM_SMTP_HOST = "smtp.exmail.qq.com" ; //"smtpdm.aliyun.com";
    private static final int ALIDM_SMTP_PORT = 25;

    public static void sendMainMethod2(String title, String content) throws MessagingException {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", ALIDM_SMTP_HOST);
        props.put("mail.smtp.port", ALIDM_SMTP_PORT);   
        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置, 
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // props.put("mail.smtp.socketFactory.port", "465");
        // props.put("mail.smtp.port", "465");

        // 发件人的账号
        props.put("mail.user", kEmailSenderUserName);
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", kEmailSenderPassword);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(
                props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人
        InternetAddress to = new InternetAddress(kEmailReceiveName);
        message.setRecipient(MimeMessage.RecipientType.TO, to);

        // 设置邮件标题
        message.setSubject(title);
        // 设置邮件的内容体
//        message.setContent(content, "text/html;charset=UTF-8");
        message.setText(content);
        
        // 发送邮件
        Transport.send(message);
    }
    
	 * */
}
