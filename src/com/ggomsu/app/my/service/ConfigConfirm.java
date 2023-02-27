package com.ggomsu.app.my.service;

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
		boolean alarmFlag = req.getAttribute("alarmFlag").equals("on") ? true : false;
		boolean darkModeFlag = req.getAttribute("darkModeFlag").equals("on") ? true : false;
		
		if(!alarmFlag) {
			alarmDAO.deleteAlarmListByNickname(nickname);
		}
		
		vo.setEmail(email);
		vo.setAlarmFlag(alarmFlag);
		vo.setDarkModeFlag(darkModeFlag);
		memberDAO.updateConfig(vo);
		
		forward.setForward(false);
		if(statusValue.equals("MEM")) {
			forward.setPath(req.getContextPath() + "/my/config?code=success");
		}
		else if(statusValue.equals("ADM")) {
			forward.setPath(req.getContextPath() + "/admin/config?code=success");
		}
		
		return forward;
	}
}