package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 손하늘, 박성민

public class PersonalConfirm implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// personal 목록 : telecomValue, contact, zipcode, address, addressDetail 
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		HttpSession session = req.getSession();
		String statusValue = (String)session.getAttribute("statusValue");
		String statusURI = null;
		
		if(statusValue.equals("MEM")) {
			statusURI = "my";
		}
		else if(statusValue.equals("ADM")) {
			statusURI = "admin";
		}
		
		if(!req.getMethod().equals("POST")) {
			forward.setPath(req.getContextPath() + "/" + statusURI + "/personal?code=error");
			forward.setForward(false);
			return forward;
		}
		
		vo.setEmail((String)session.getAttribute("email"));
		vo.setTelecomValue(req.getParameter("telecomValue"));
		vo.setContact(req.getParameter("contact"));
		vo.setZipcode(req.getParameter("zipcode"));
		vo.setAddress(req.getParameter("address"));
		vo.setAddressDetail(req.getParameter("addressDetail"));
		
		dao.updatePersonal(vo);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/" + statusURI + "/personal?code=success");
		return forward;
	}
}
