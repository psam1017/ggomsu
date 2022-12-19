package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;

//작성자 : 손하늘

public class MemberGetBlockOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String nickname = req.getParameter("nickname"); 
		
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		
		req.setAttribute("blockList", dao.getList(nickname));
		
		forward.setForward(true);
		forward.setPath("/app/member/MemberBlock.jsp");
		
		return forward;
	}
}
