package com.ggomsu.system.mail;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class SendMail implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// javax.mail 라이브러리 필요, ajax 통신 필요
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		// 이메일 전송 정보. *hostEmail은 SMTP 설정이 되어 있어야 함.
		// hostEmail과 password는 listener로 서블릿 초기화할 때 저장한 객체의 값을 사용
		String memberEmail = req.getParameter("email");
		String host = HostInfo.getHost();
		String hostEmail = HostInfo.getEmail();
		String password = HostInfo.getPassword();
		int port = 587; // 465로 할 때와 props 설정이 다름!
		
		// 이메일 인증 키 생성. 작성할 제목 및 내용과 함께 저장
		String authKey = String.valueOf(new Random().nextInt(888888) + 111111);
		String emailSubject = "꼼수닷컴 인증번호입니다";
		String emailContent = 
				"<!DOCTYPE html>\r\n" + 
				"<div style=\"width: 90%; height: 130px; border: 1px solid grey; padding: 10px; border-radius: 6px; flex-wrap: wrap; margin: 0 auto;\">\r\n" + 
				"  <div style=\"background-color: #0d6efd; width: 100%; height: 40%; border-radius: 6px; box-sizing: border-box; padding-top: 3px;\">\r\n" + 
				"    <span style=\"display: block; color: white; font-size: x-large; margin-left: 3%; margin-top: 8px;\">꼼수닷컴 인증번호입니다</span>\r\n" + 
				"  </div>\r\n" + 
				"  <div style=\"width: 100%; height: 60%; padding-top: 10px; padding-left: 2%; font-size: medium;\">\r\n" + 
				"    요청하신 인증번호는 <span style=\"font-weight: bold;\">" + authKey + "</span>입니다.<br>\r\n" + 
				"    타인에게 정보를 노출하지 마시고, 인증번호를 정확하게 입력해주십시오.\r\n" + 
				"  </div>\r\n" + 
				"</div>";
		HttpSession memberSession = req.getSession();
		
		// 이메일 전송를 prpoerty로 저장
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.ssl.trust", "true");
//		props.put("mail.debug", "true");
		
		// 이메일 전송 세션 생성
		Session mailSession = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(hostEmail, password);
			}
		});
		
		// 이메일 전송 실행
		MimeMessage msg = new MimeMessage(mailSession);
		
		try {
			msg.setFrom(new InternetAddress(hostEmail, "꼼수닷컴"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(memberEmail));
			
			msg.setSubject(emailSubject);
			msg.setContent(emailContent, "text/html; charset=UTF-8;");
			
			Transport.send(msg);
			
			// 성공. 세션에 인증키를 저장 후 사용자가 인증키를 올바르게 입력했는지 비교
			memberSession.setAttribute("authKey", authKey);
			memberSession.setAttribute("tempEmail", memberEmail);
			json.put("status", "success");
		} catch (UnsupportedEncodingException e) { // 인코딩 문제로 실패
			json.put("status", "fail-encoding");
		} catch (AddressException e) { // 존재하지 않는 주소
			json.put("status", "fail-address");
		} catch (MessagingException e) { // SMTP를 사용할 수 없는 상태
			json.put("status", "fail-transport");
		}
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
