package com.validate.util;


import com.sun.mail.util.MailSSLSocketFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtil {

    /*
     * 读取配置文件
     *
     * 		1,io【输入流】src目录下
     *
     *
     */
    //属性对象
    static Properties properties = new Properties();

    static {
        try {
            //加载配置文件信息
            InputStream in = EmailUtil.class.getClassLoader().getResourceAsStream("email.properties");


            //加载流中的数据
            properties.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /*
     * 【通用】发送邮件
     *
     * 	  参数：
     * 		1，接受者
     * 		2，主题
     * 		3，内容
     *
     * 	返回值类型
     * 		boolean
     */
    public static boolean sendEmail(String to, String subject, String content) {
        Properties props = System.getProperties();
        props.setProperty("mail.host", "smtp.163.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.debug", "true");// 开启debug日志，日志更详细

        //阿里云服务器禁用25端口，所以服务器上改为465端口
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");


        try {

            // 1.获取默认session对象
            Session session = Session.getDefaultInstance(props, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(properties.getProperty("from"), properties.getProperty("password")); // 发件人邮箱账号、授权码
                }
            });
            //开启DEBUG模式,在控制台中或日志中有日志信息显示
            session.setDebug(true);
            // 2.创建邮件对象
            MimeMessage message = new MimeMessage(session);

            //设置发送者
            message.setFrom(new InternetAddress(properties.getProperty("from")));

            //设置接受者
            message.setRecipients(RecipientType.TO, String.valueOf(new InternetAddress(to)));

            //设置主题标题
            message.setSubject(subject);

            //设置发送的内容，设置内容的类型
            message.setContent(content, "text/html;charset=utf-8");

//
//            //3.发送文件
//            //4.根据session对象获取邮件传输对象
//            Transport transport = session.getTransport("smtp");
//            transport.connect(properties.getProperty("host"), properties.getProperty("from"), properties.getProperty("password"));
//            //发送邮件，并发送到所有收件人地址，message.getAllRecipients()获取到创建又见对象时添加的所有收件人抄送人密送人
//            transport.sendMessage(message, message.getAllRecipients());
//            //如果只想发送给指定的人，可以如下写法
//            //transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});
//            //5.关闭邮件链接
//            transport.close();

            Transport.send(message);
            System.out.println("邮件成功发送!");
            return true;
        } catch (Exception e) {

            return false;
        }
    }

}
