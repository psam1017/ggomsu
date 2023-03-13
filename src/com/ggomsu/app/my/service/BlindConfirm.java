package com.ggomsu.app.my.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 손하늘, 박성민

public class BlindConfirm implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// get 요청을 받음
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String nickname = (String)session.getAttribute("nickname");
		String editableMember = req.getParameter("editableMember");
		String statusValue = (String)session.getAttribute("statusValue");
		String edit = req.getParameter("edit");
		
		// 잘못된 접근일 때
		if(!req.getMethod().equals("POST") || edit == null) {
			if(statusValue.equals("MEM")) {
				forward.setPath(req.getContextPath() + "/my/blind?code=error");
			}
			else {
				forward.setPath(req.getContextPath() + "/error/error");
			}
			forward.setForward(false);
			
			return forward;
		}
		
		if(nickname.equals(editableMember)) { // 회원이 자기 닉네임을 제출한 경우
			forward.setPath(req.getContextPath() + "/my/blind?code=self");
		}
		else if(!dao.checkNickname(editableMember)) { // 회원이 존재하지 않는 닉네임을 제출한 경우
			forward.setPath(req.getContextPath() + "/my/blind?code=no-member");
		}
		else if(edit.equals("update")) {
			dao.insertBlindMember(nickname, editableMember);
			session.setAttribute("blindList", dao.getBlindList(nickname));
			forward.setPath(req.getContextPath() + "/my/blind?code=update");
		}
		else if(edit.equals("delete")) {
			dao.deleteBlindMember(nickname, editableMember);
			session.setAttribute("blindList", dao.getBlindList(nickname));
			forward.setPath(req.getContextPath() + "/my/blind?code=delete");
		}
		else {
			forward.setPath(req.getContextPath() + "/error/error");
		}
		forward.setForward(false);
		
		return forward;
	}
}
