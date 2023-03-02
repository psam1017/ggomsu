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
		
		if(!req.getMethod().equals("POST")) {
			forward.setForward(false);
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
		
		// 초기화
		vo = dao.getMemberInfo(email);
		password = vo.getPassword();
		salt = vo.getSalt();
		
		// 비밀번호 일치 검사
		if(encryptionHelper.compare(inserted, password, salt)) {
			session.setAttribute("myPasswordAuth", "success");
			forward.setForward(false);
			if(statusValue.equals("MEM")) {
				forward.setPath(req.getContextPath() + "/my/password/form");
			}
			else if(statusValue.equals("ADM")) {
				forward.setPath(req.getContextPath() + "/admin/password/form");
			}
		}
		else {
			forward.setForward(false);
			if(statusValue.equals("MEM")) {
				forward.setPath(req.getContextPath() + "/my/password/check?code=fail");
			}
			else if(statusValue.equals("ADM")) {
				forward.setPath(req.getContextPath() + "/admin/password/check?code=fail");
			}
		}
		return forward;
	}
}
