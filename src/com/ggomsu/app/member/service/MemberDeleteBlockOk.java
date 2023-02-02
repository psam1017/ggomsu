package com.ggomsu.app.member.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;

//작성자 : 손하늘

public class MemberDeleteBlockOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String nickname = (String)session.getAttribute("nickname");
		
		vo.setNickname(nickname);
		String blockList = req.getParameter("blockList");
		vo.setBlockedMember(blockList);
		
		//회원차단 delete
		if(nickname.equals(blockList)) {
			System.out.println("사용자는 차단해제 할수 없습니다!");
			forward.setPath(req.getContextPath() + "/member/member-get-block?code=nicknameFail");
		}
//		else if(!vo.getBlockedMember().equals(delBlockedMember) || vo.getBlockedMember() == null) {
//			System.out.println("차단 대상이 아닙니다!");
//			forward.setPath(req.getContextPath() + "/member/member-get-block?code=blockedMemberFail");
//		}
		else {
			dao.deleteBlock(vo); 
		}
		
		forward.setForward(false);
		return forward;
	}
}
