package com.ggomsu.app.member.naver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class SnsConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		HttpSession session = req.getSession();
		
		String nickname = (String)req.getParameter("nickname");
		String agreedMarketingAt = (String) req.getParameter("agreedMarketingAt");
		
		if(nickname == null || memberDAO.checkNickname(nickname)) {
			forward.setPath(req.getContextPath() + "/error/error");
		}
		else if((String) session.getAttribute("snsEmail") != null){
			String email = (String) session.getAttribute("snsEmail");
			String snsKey = (String) session.getAttribute("snsKey");
			String type = "naver";
			
			memberDAO.signUpWithSns(email, nickname, agreedMarketingAt);
			memberDAO.insertSnsKey(email, snsKey, type);
			
			session.removeAttribute("snsEmail");
			session.removeAttribute("snsKey");
			
			MemberVO memberVO = memberDAO.getMemberInfo(email);
			
			session.setAttribute("statusValue", memberVO.getStatusValue());
			session.setAttribute("profileImageUrl", memberVO.getProfileImageUrl());
			session.setAttribute("blindList", memberDAO.getBlindList(nickname));
			session.setAttribute("darkModeFlag", memberVO.isDarkModeFlag());
			session.setAttribute("alarmFlag", memberVO.isAlarmFlag());
			session.setAttribute("email", email);
			session.setAttribute("nickname", nickname);
			
			forward.setPath(req.getContextPath() + "/member/welcome");
		}
		else {
			forward.setPath(req.getContextPath() + "/error/error");
		}
		forward.setForward(false);
		
		return forward;
	}

}
