package com.ggomsu.app.member.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;

//작성자 : 손하늘

public class MemberUpdateTermOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String email = (String)session.getAttribute("email");
		String agreedMarketingAt = req.getParameter("agreedMarketingAt");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String nowTime = sdf.format(now);
		if(agreedMarketingAt != null) {
			vo.setAgreedMarketingAt(nowTime);
			System.out.println(now + " 날짜로 마케팅 동의하였습니다!");
		}
		else {
			System.out.println(agreedMarketingAt);
			vo.setAgreedMarketingAt(agreedMarketingAt);
			System.out.println(now + " 날짜로 마케팅 취소하였습니다!");
		}
		dao.updateTerm(email);
		
		forward.setForward(true);
		forward.setPath("/member/member-view-term-ok");
		
		return forward;
	}
}
