package com.ggomsu.app.member.service;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.app.member.vo.SimpleEncInfoVO;


	//작성자 : 손하늘
public class MemberChangePwOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		EncryptionController encrypt = new EncryptionController();
		SimpleEncInfoVO info = new SimpleEncInfoVO();
		
		
		String AuthenticationKey = (String)session.getAttribute("AuthenticationKey");
        
	    String certification = req.getParameter("certification");
	    if(!AuthenticationKey.equals(certification)){
	           System.out.println("인증번호 일치하지 않음");
	           req.setAttribute("msg", "인증번호가 일치하지 않습니다");
	           forward.setForward(true);
	   		   forward.setPath("/app/member/MemberChangePw.jsp");
	      }
	    else {
	    	vo.setEmail(req.getParameter("email"));
			String inserted = req.getParameter("password");
			try {
				info = encrypt.encrypt(inserted);
			} catch (Exception e) {
				System.out.println("암호 생성 예외 발생! " + e);
			}
			vo.setPassword(info.getPassword());
			vo.setSalt(info.getSalt());
	    	dao.updateMemberPassword(vo);
	    	forward.setForward(false);
			forward.setPath(req.getContextPath() + "/app/member/MemberSignIn.jsp");
	    }
	    
		return forward;

		}
	}
