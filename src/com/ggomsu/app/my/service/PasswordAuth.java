package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.encrypt.EncryptionHelper;

// 작성자 : 박성민
public class PasswordAuth implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		forward.setForward(false);
		
		if(!req.getMethod().equals("POST")) {
			forward.setPath(req.getContextPath() + "/error/error");
			return forward;
		}
		
		// java 객체
		MemberVO vo;
		MemberDAO dao = new MemberDAO();
		EncryptionHelper encryptionHelper = new EncryptionHelper();
		HttpSession session = req.getSession();
		
		// 변수명
		String password;
		String salt;
		String email = (String) session.getAttribute("email");
		String inserted = req.getParameter("password");
		String statusValue = (String) session.getAttribute("statusValue");
		String statusURI = null;
		if(statusValue.equals("MEM")) {
			statusURI = "my";
		}
		else if(statusValue.equals("ADM")) {
			statusURI = "admin";
		}
		
		// 초기화
		vo = dao.getMemberInfo(email);
		password = vo.getPassword();
		salt = vo.getSalt();
		
		// 비밀번호 일치 검사
		if(encryptionHelper.compare(inserted, password, salt)) {
			session.setAttribute("myPasswordAuth", "success");
			forward.setPath(req.getContextPath() + "/" + statusURI + "/password/form");
		}
		else {
			forward.setPath(req.getContextPath() + "/" + statusURI + "/password/check?code=fail");
		}
		return forward;
	}
}
