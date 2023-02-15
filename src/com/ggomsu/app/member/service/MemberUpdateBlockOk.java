package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;

//작성자 : 손하늘

public class MemberUpdateBlockOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String nickname = (String)session.getAttribute("nickname");
		String blockedMember = req.getParameter("blockedMember");
		
		vo.setNickname(nickname);
		vo.setBlockedMember(blockedMember);
		
		//회원차단 insert
		if(nickname.equals(blockedMember)) {
			System.out.println("사용자는 차단할수 없습니다!");
			forward.setPath(req.getContextPath() + "/member/member-get-block?code=userNickname");
		}
		else {
			dao.updateBlock(vo); 
		}
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/member/member-get-block-ok");
		
		return forward;
	}
}
