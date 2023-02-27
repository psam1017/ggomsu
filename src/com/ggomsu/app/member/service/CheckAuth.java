package com.ggomsu.app.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

	//작성자 : 박성민
public class CheckAuth implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 객체
		MemberDAO dao = new MemberDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		
		// 파라미터
		String tempEmail = (String) session.getAttribute("tempEmail");
		String memberKey = req.getParameter("memberKey");
		String authKey = (String)session.getAttribute("authKey");
		String authFailFlag = (String) session.getAttribute("authFailCount");
		
		// 초기화
		int authFailCount = authFailFlag != null ? Integer.parseInt(authFailFlag) : 0;
		
		// 사용 불가능인지 검사
		// 중복검사를 3번 모두 실패한 직후 사용 불가능 이메일로 등록되기 때문에 중복검사 단계에서 실패함
		if(dao.checkEmail(tempEmail)) {
			json.put("auth", "fail");
			return null;
		}
		
		if(!memberKey.equals(authKey) && authFailCount < 2) {
			authFailCount++;
			json.put("auth", "not-ok");
			json.put("authFailCount", authFailCount);
			session.setAttribute("authFailCount", authFailCount);
		}
		else if(!memberKey.equals(authKey)) {
			json.put("auth", "fail");
			session.removeAttribute("authFailCount");
			dao.insertAuthFailedEmail(tempEmail);
		}
		else if(memberKey.equals(authKey) && authFailCount < 2) {
			json.put("auth", "ok");
			session.removeAttribute("authKey");
			session.removeAttribute("tempEmail");
			session.removeAttribute("authFailCount");
			session.setAttribute("auth", "ok");
		}
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
