package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberSnsVO;
import com.ggomsu.app.member.vo.MemberVO;

public class NaverNicknameOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		MemberDAO mDao = new MemberDAO();
		HttpSession session = req.getSession();
		
		MemberVO mVo = (MemberVO) session.getAttribute("memberVo");
		MemberSnsVO msVo = (MemberSnsVO)session.getAttribute("memberSnsVo");
		String nickname = (String)req.getParameter("nickname");
		
		session.removeAttribute("memberVo");
		session.removeAttribute("memberSnsVo");
		
		mVo.setNickname(nickname);
		
		mDao.signUp(mVo);
		mDao.snsSignUp(msVo);
		
		session.setAttribute("nickname", nickname);
		session.setAttribute("email", mVo.getEmail());
		session.setAttribute("satusValue", mVo.getStatusValue());
		
		forward.setForward(true);	
		forward.setPath("/");
		return forward;
	}

}
