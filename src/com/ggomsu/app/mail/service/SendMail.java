package com.ggomsu.app.mail.service;

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

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.mail.domain.HostInfo;

public class SendMail implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		// javax.mail 라이브러리 필요, ajax 통신 필요
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		// 이메일 전송 정보. ★hostEmail은 SMTP 설정이 되어 있어야 함.
		// hostEmail과 password는 listener로 서블릿 초기화할 때 저장한 객체의 값을 사용
		String memberEmail = req.getParameter("email");
		String host = HostInfo.getHost();
		String hostEmail = HostInfo.getEmail();
		String password = HostInfo.getPassword();
		int port = 587; // 465로 할 때와 props 설정이 다름!
		
		// 이메일 인증 키 생성. 작성할 제목 및 내용과 함께 저장
		String authKey = String.valueOf(new Random().nextInt(888888) + 111111);
		String emailSubject = "꼼수닷컴 인증번호입니다";
		String emailContent = "html태그로 이메일 작성" + authKey;
		HttpSession saveKey = req.getSession();
		
		// 이메일 전송를 prpoerty로 저장
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.ssl.trust", "true");
//		props.put("mail.debug", "true");
		
		// 이메일 전송 세션 생성
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(hostEmail, password);
			}
		});
		
		// 이메일 전송 실행
		MimeMessage msg = new MimeMessage(session);
		
		try {
			msg.setFrom(new InternetAddress(hostEmail, "꼼수닷컴"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(memberEmail));
			
			msg.setSubject(emailSubject);
			msg.setContent(emailContent, "text/html; charset=UTF-8;");
			
			Transport.send(msg);
			
			// 성공. 세션에 인증키를 저장 후 사용자가 인증키를 올바르게 입력했는지 비교
			saveKey.setAttribute("authKey", authKey);
			json.put("status", "success");
		} catch (UnsupportedEncodingException e) { // 인코딩 문제로 실패
			json.put("status", "fail-encoding");
		} catch (AddressException e) { // 존재하지 않는 주소
			json.put("status", "fail-address");
		} catch (MessagingException e) { // SMTP를 사용할 수 없는 상태
			json.put("status", "fail-transport");
		}
		
		// api 방식으로 성공 여부를 전송
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
