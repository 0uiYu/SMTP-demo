package com.lhy.smtp.controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class QqSmtpDemo {

    public static void main(String[] args) {
        // 收件人邮箱（例如：someone@139.com）
        String to = "someone@139.com";

        // 发件人邮箱（你的QQ邮箱）
        String from = "your-qq-email@qq.com"; // 替换为你的完整QQ邮箱地址

        // QQ邮箱的SMTP服务器配置
        final String host = "smtp.qq.com";
        final String port = "587"; // 推荐使用 STARTTLS 端口
        final String username = from; // 使用你的完整QQ邮箱地址作为用户名
        final String password = "your-authorization-code"; // 使用你在QQ邮箱中生成的授权码

        // 设置邮件服务器属性
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // 启用STARTTLS加密

        // 创建会话
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 创建默认的 MimeMessage 对象
            Message message = new MimeMessage(session);

            // 设置发件人
            message.setFrom(new InternetAddress(from));

            // 设置收件人
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // 设置邮件主题
            message.setSubject("测试邮件 - 来自Java程序");

            // 设置邮件正文
            message.setText("你好！这是一封由Java程序通过QQ邮箱SMTP服务发送的测试邮件。");

            // 发送邮件
            Transport.send(message);

            System.out.println("邮件发送成功！");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}