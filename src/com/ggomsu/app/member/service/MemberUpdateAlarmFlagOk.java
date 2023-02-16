package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.SettingDAO;
import com.ggomsu.app.member.vo.SettingVO;

//작성자 : 손하늘

public class MemberUpdateAlarmFlagOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		SettingVO sVo = new SettingVO();
		SettingDAO sDao = new SettingDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String email = (String)session.getAttribute("email");
		int articleAlarmFlag = Integer.parseInt(req.getParameter("articleAlarmFlag"));
		int commentAlarmFlag = Integer.parseInt(req.getParameter("commentAlarmFlag"));
		int darkModeFlag = Integer.parseInt(req.getParameter("darkModeFlag"));
		
		sVo.setEmail(email);
		sVo.setArticleAlarmFlag(articleAlarmFlag);
		sVo.setCommentAlarmFlag(commentAlarmFlag);
		sVo.setDarkModeFlag(darkModeFlag);
		
		sDao.updateSetting(sVo);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/member/member-view-alarm-flag-ok");
		
		return forward;
	}
}