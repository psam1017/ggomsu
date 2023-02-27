package com.ggomsu.app.admin.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class Members implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		ActionForward forward = new ActionForward();
		MemberDAO dao = new MemberDAO();
		
		List<MemberVO> members = dao.getAllMember();
		
		req.setAttribute("members", members);
		
		forward.setForward(true);
		forward.setPath("/views/admin/Members.jsp");
		return forward;
	}

}
