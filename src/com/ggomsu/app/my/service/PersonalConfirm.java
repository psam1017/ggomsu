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
		
		if(!req.getMethod().equals("POST")) {
			if(statusValue.equals("MEM")) {
				forward.setPath(req.getContextPath() + "/my/personal?code=error");
			}
			else if(statusValue.equals("ADM")) {
				forward.setPath(req.getContextPath() + "/admin/personal?code=error");
			}
			else {
				forward.setPath(req.getContextPath() + "/error/error");
			}
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
		if(statusValue.equals("MEM")) {
			forward.setPath(req.getContextPath() + "/my/personal?code=success");
		}
		else if(statusValue.equals("ADM")) {
			forward.setPath(req.getContextPath() + "/admin/personal?code=success");
		}
		
		return forward;
	}
}
