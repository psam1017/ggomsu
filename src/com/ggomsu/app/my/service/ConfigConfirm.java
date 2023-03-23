package com.ggomsu.app.my.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.alarm.dao.AlarmDAO;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 손하늘

public class ConfigConfirm implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberVO vo = new MemberVO();
		AlarmDAO alarmDAO = new AlarmDAO();
		MemberDAO memberDAO = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String email = (String)session.getAttribute("email");
		String nickname = (String)session.getAttribute("nickname");
		String statusValue = (String)session.getAttribute("statusValue");
		String alarmFlagText = req.getParameter("alarmFlag");
		String darkModeFlagText = req.getParameter("darkModeFlag");
		String statusURI = null;
		Cookie cookie = null;
		
		boolean alarmFlag = alarmFlagText != null && alarmFlagText.equals("on") ? true : false;
		boolean darkModeFlag = darkModeFlagText != null && req.getParameter("darkModeFlag").equals("on") ? true : false;
		
		if(statusValue.equals("MEM") || statusValue.equals("SNS")) {
			statusURI = "my";
		}
		else if (statusValue.equals("ADM")) {
			statusURI = "admin";
		}
		
		// 잘못된 접근일 때
		if(!req.getMethod().equals("POST")) {
			forward.setPath(req.getContextPath() + "/admin/config?code=error");
			forward.setForward(false);
			return forward;
		}
		
		if(!alarmFlag) {
			alarmDAO.deleteAlarmListByNickname(nickname);
		}
		
		vo.setEmail(email);
		vo.setAlarmFlag(alarmFlag);
		vo.setDarkModeFlag(darkModeFlag);
		memberDAO.updateConfig(vo);
		
		session.setAttribute("alarmFlag", alarmFlag);
		session.setAttribute("darkModeFlag", darkModeFlag);
		
		cookie = new Cookie("darkModeFlag", darkModeFlag ? "on" : "off");
		cookie.setMaxAge(60 * 60 * 24 * 7);
		resp.addCookie(cookie);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/" + statusURI + "/config?code=success");
		return forward;
	}
}