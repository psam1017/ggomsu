package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;

public class MemberSignUpOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		System.out.println("실행2");
		
		// getParameter의 안에 String 값은 jsp에서 만든 name의 값과 같아야 한다!
		System.out.println("실행100");
		vo.setEmail(req.getParameter("email"));
		System.out.println("실행200");
		vo.setPassword(req.getParameter("password"));
		//vo.setNaverKey(req.getParameter("naverKey"));
		//vo.setKakaoKey(req.getParameter("kakaoKey"));
		//vo.setGoogleKey(req.getParameter("googleKey"));
		System.out.println("실행300");
		vo.setNickname(req.getParameter("nickname"));
		System.out.println("실행400");
		vo.setName(req.getParameter("name"));
		System.out.println("실행500");
		vo.setBirthDate(req.getParameter("birthDate"));
		System.out.println("실행600");
		vo.setSex(req.getParameter("sex"));
		System.out.println("실행700");
		vo.setTelecomValue(req.getParameter("telecomValue"));
		System.out.println("실행800");
		vo.setContact(req.getParameter("contact"));
		vo.setZipcode(req.getParameter("zipcode"));
		vo.setAddress(req.getParameter("address"));
		System.out.println("실행900");
		vo.setAddressDetail(req.getParameter("addressDetail"));
		vo.setProfileImageUrl(req.getParameter("profileImageUrl"));
		
		//req.setAttribute("user", vo);
		
		dao.signUp(vo);
		
		System.out.println("실행2!");
		
		forward.setForward(false);
		System.out.println(req.getContextPath());
		forward.setPath(req.getContextPath() + "/app/member/MemberSignUpOk.jsp");
		
		System.out.println("실행3!");
		
		return forward;
		
	}

}
