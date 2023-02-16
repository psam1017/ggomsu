package com.ggomsu.app.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;


	//작성자 : 손하늘
public class MemberFindIdOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
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
		
		MemberVO findEmail = dao.findId(vo);
		vo = dao.findId(vo);
		
		if(findEmail != null || vo.getName().equals(name) && vo.getBirthDate().equals(birthDate) 
		   && vo.getSex().equals(sex) && vo.getTelecomValue().equals(telecomValue) && vo.getContact().equals(contact)) {
			req.setAttribute("message","아이디는 " + vo.getEmail() + " 입니다.");
		}
		else {
			req.setAttribute("message","회원정보가 존재하지 않습니다.");
		}
		
		forward.setForward(true);
		forward.setPath("/app/member/MemberFIndIdOkTest.jsp");
		return forward;

		}
	}
