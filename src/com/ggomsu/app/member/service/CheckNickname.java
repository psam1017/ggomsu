package com.ggomsu.app.member.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

	//작성자 : 박성민
public class CheckNickname implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String nickname = req.getParameter("nickname");
		MemberDAO dao = new MemberDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		if(dao.checkNickname(nickname)) {
			json.put("nicknameStatus", "not-ok");
		}
		else {
			json.put("nicknameStatus", "ok");
		}
		
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
