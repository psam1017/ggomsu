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

public class MemberSignInOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		boolean isSignInOk = false;
		
		EncryptionController encrypt = new EncryptionController();
		
		vo = dao.getMemberInfo(email);
		
		String statusValue = vo.getStatusValue();
		
		try {
			isSignInOk = encrypt.compare(password, vo.getPassword(), vo.getSalt());
		} catch (Exception e) {
			System.out.println("암호화 로그인 예외 발생! " + e);
		}
		
		//회원상태가 MEM, ADM, DOR : 로그인 가능 (DOR 즉 휴면계정상태일때는 휴면계정해제 페이지로)
		if(isSignInOk && statusValue.equals("MEM") || statusValue.equals("ADM") || statusValue.equals("DOR")) {
			session.setAttribute("email", vo.getEmail());
			session.setAttribute("nickname", vo.getNickname());
			session.setAttribute("statusValue", vo.getStatusValue());
			session.setAttribute("signAt", vo.getSignAt());
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = new Date(); //현재날짜
			
			// 비밀번호를 변경할 때인가?(3개월) -> 변경 시 비밀번호 변경 일자를 다시 3개월 후로 갱신
			int result = sdf.format(now).compareTo(vo.getPasswordAlertAt());
			if(result >= 0) {
				System.out.println("비밀번호 변경 필요함");
			}
			
			// 약관에 다시 동의기간이 지났는가?(1년)
			String agreedTermAt = vo.getAgreedTermAt();
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = sdf.parse(agreedTermAt);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.YEAR, 1); //vo.getAgreedTermAt() + 1year
			result = sdf.format(now).compareTo(sdf.format(cal.getTime()));
			if(result >= 0) {
				System.out.println("재 약관동의 필요");
			}
			
			// 차단 유저 목록을 불러와서 세션에 저장해둘 것인가?(YES)
			session.setAttribute("blockedMember", vo.getBlockedMember());
			
			// 생일에 축하 메시지를 띄울 것인가?(NO?)
			
			if(session.getAttribute("statusValue").equals("DOR")) {
				//db이벤트 스케줄러로 하루에 한번 로그인시점 기준으로 휴면회원 업데이트 
				//휴면상태로 전환된 계정 로그인
				//휴면계정해제 페이지에서 해제 하도록
				resp.sendRedirect(req.getContextPath() + "/app/member/MemberWakeUp.jsp");
				return null;
			}
			
			// 로그인 성공 -> 현재 일자로 다시 signAt을 업데이트
			dao.signAt(email);
			
			// 세션으로 이전 페이지 값을 기억하여 값이 있으면 그 페이지로, 아니면 index로 redirect
			if(session.getAttribute("articleIndex") != null) {
				forward.setPath(req.getContextPath() + "/board/article-view-detail-ok?articleIndex=" + session.getAttribute("articleIndex"));
			}
			else if(session.getAttribute("boardValue") != null){
				forward.setPath(req.getContextPath() + "/board/article-get-list-ok?boardValue=" + session.getAttribute("boardValue"));
			}
			else {
				resp.sendRedirect(req.getContextPath() + "/");
				return null;
			}
		}
		else if(statusValue.equals("DEL") || statusValue.equals("SUS")) {
			//회원상태(자발적 탈퇴, 관리자에 의한 정지)로 인한 로그인 실패
			forward.setPath(req.getContextPath() + "/member/member-sign-in?status=" + statusValue);
		}
		else {
			// 로그인 실패
			forward.setPath(req.getContextPath() + "/member/member-sign-in?code=fail");
		}
		forward.setForward(false);
		
		return forward;
	}

}
