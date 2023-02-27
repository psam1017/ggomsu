package com.ggomsu.app.my.service;

import java.util.Arrays;
import java.util.List;

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
		String newBlindMember = req.getParameter("newBlindMember");
		String[] deleteArray = req.getParameterValues("delete");
		List<String> deleteList = Arrays.asList(deleteArray);
		
		String edit = req.getParameter("edit");
		
		if(nickname.equals(newBlindMember)) { // 회원이 자기 닉네임을 제출한 경우
			forward.setPath(req.getContextPath() + "/my/blind?code=self");
		}
		else if(edit.equals("update") && !dao.checkNickname(nickname)) { // 회원이 존재하지 않는 닉네임을 제출한 경우
			forward.setPath(req.getContextPath() + "/my/blind?code=no-update");
		}
		else if(edit.equals("delete") && deleteList.size() == 0) { // 회원이 아무도 선택하지 않고 삭제를 요청한 경우
			forward.setPath(req.getContextPath() + "/my/blind?code=no-delete");
		}
		else if(edit.equals("update")) {
			dao.insertBlindMember(nickname, newBlindMember);
			forward.setPath(req.getContextPath() + "/my/blind?code=update");
		}
		else if(edit.equals("delete")) {
			dao.deleteBlindMember(nickname, deleteList);
			forward.setPath(req.getContextPath() + "/my/blind?code=delete");
		}
		else {
			forward.setPath(req.getContextPath() + "/error/error");
		}
		
		forward.setForward(false);
		return forward;
	}
}
