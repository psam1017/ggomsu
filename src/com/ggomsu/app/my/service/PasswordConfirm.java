package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.encrypt.EncryptionInfo;
import com.ggomsu.system.encrypt.EncryptionHelper;

//작성자 : 손하늘, 박성민
public class PasswordConfirm implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		EncryptionHelper encryptionHelper = new EncryptionHelper();
		EncryptionInfo info = new EncryptionInfo();
		
    	vo.setEmail((String)session.getAttribute("email"));
		String inserted = req.getParameter("password");
		String statusValue = (String)session.getAttribute("statusValue");
		String myPasswordAuth = (String)session.getAttribute("myPasswordAuth");
		String statusURI = null;
		
		if(statusValue.equals("MEM")) {
			statusURI = "my";
		}
		else if (statusValue.equals("ADM")) {
			statusURI = "admin";
		}
		
		// 잘못된 접근일 때
		if(!req.getMethod().equals("POST") || myPasswordAuth == null) {
			session.removeAttribute("myPasswordAuth");
			forward.setPath(req.getContextPath() + "/" + statusURI + "/password/check?code=error");
			forward.setForward(false);
			return forward;
		}
		
		info = encryptionHelper.encrypt(inserted);
		
		vo.setPassword(info.getPassword());
		vo.setSalt(info.getSalt());
		
    	dao.updatePassword(vo);
    	dao.updatePasswordAlertAtByEmail(vo.getEmail());
		session.removeAttribute("myPasswordAuth");
    	
    	forward.setForward(false);
		forward.setPath(req.getContextPath() + "/" + statusURI + "/password/check?code=success");
		return forward;
	}
}
