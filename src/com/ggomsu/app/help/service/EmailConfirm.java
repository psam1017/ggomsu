package com.ggomsu.app.help.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 손하늘
public class EmailConfirm implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		
		String name = req.getParameter("name");
		String birthDate = req.getParameter("birthDate");
		String sex = req.getParameter("sex");
		String telecomValue = req.getParameter("telecomValue");
		String contact = req.getParameter("contact");
		
		vo.setName(name);
		vo.setBirthDate(birthDate);
		vo.setSex(sex);
		vo.setTelecomValue(telecomValue);
		vo.setContact(contact);
		
		String foundEmail = dao.findLostEmail(vo);
		
		if(foundEmail != null) {
			req.getSession().setAttribute("foundEmail", foundEmail);
			forward.setPath(req.getContextPath() + "/help/email/result");
		}
		else {
			forward.setPath(req.getContextPath() + "/help/email/result?code=fail");
		}
		forward.setForward(false);
		return forward;
	}
}
