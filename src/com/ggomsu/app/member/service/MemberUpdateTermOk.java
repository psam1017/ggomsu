package com.ggomsu.app.member.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;

//작성자 : 손하늘

public class MemberUpdateTermOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String email = (String)session.getAttribute("email");
		String agreedMarketingAt = req.getParameter("agreedMarketingAt");
		String agreedTermAt = req.getParameter("agreedTermAt");
		
		Date now = new Date(); //현재날짜
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //출력형태
		Date date = sdf.parse(agreedTermAt); //형변환
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, 1); //vo.getAgreedTermAt() + 1year
		//저장된 필수약관날짜 + 1년 < 현재날짜(now)
		int result = sdf.format(now).compareTo(sdf.format(cal.getTime()));
		if(result >= 0) {
			dao.updateAgreedTermAt(email);
			//agreedMarketingAt가 null값이 아니라면 선택동의도 같이 업데이트
			if(agreedMarketingAt != null){
				vo.setAgreedMarketingAt(agreedMarketingAt);
				dao.updateAgreedMarketingAt(email);
			}
		}
		else if(agreedMarketingAt == null){
			vo.setAgreedMarketingAt(agreedMarketingAt);
			dao.deleteAgreedMarketingAt(email);
		}
		else{
			vo.setAgreedMarketingAt(agreedMarketingAt);
			dao.updateAgreedMarketingAt(email);
		}
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/member/member-view-term-ok");
		return forward;
	}
}
