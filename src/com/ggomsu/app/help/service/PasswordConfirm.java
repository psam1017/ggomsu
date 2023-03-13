package com.ggomsu.app.help.service;

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
		
		if(!req.getMethod().equals("POST") || !((String)session.getAttribute("helpPasswordAuth")).equals("success")) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
			return forward;
		}
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		EncryptionHelper encryptionHelper = new EncryptionHelper();
		EncryptionInfo info = new EncryptionInfo();
		
    	vo.setEmail((String)req.getSession().getAttribute("tempEmail"));
		String inserted = req.getParameter("password");
		
		info = encryptionHelper.encrypt(inserted);
		
		vo.setPassword(info.getPassword());
		vo.setSalt(info.getSalt());
		
    	dao.updatePassword(vo);
    	dao.updatePasswordAlertAtByEmail(vo.getEmail());
    	session.invalidate();
		
    	forward.setForward(false);
		forward.setPath(req.getContextPath() + "/member/sign-in?code=change");
	    
		return forward;
	}
}
