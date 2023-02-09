package com.ggomsu.app.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;

public class AdminMemberBlockOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
	
		ActionForward forward = new ActionForward();
		MemberDAO mDao = new MemberDAO();
		
		List<MemberVO> members = mDao.getAllMember();
		
		req.setAttribute("members", members);
		
		forward.setForward(true);
		forward.setPath("/app/admin/AdminMemberBlock.jsp");
		return forward;
	}

}
