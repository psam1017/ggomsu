package com.ggomsu.app.home.service;

import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

//작성자 : 박성민
public class DarkMode implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberDAO dao = new MemberDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		
		String email = (String)session.getAttribute("email");
		boolean darkModeFlag = req.getParameter("darkModeFlag").equals("on") ? true : false;
		Cookie cookie = null;
		
		if(email != null) {
			dao.updateDarkMode(email, darkModeFlag);
		}
		
		session.setAttribute("darkModeFlag", darkModeFlag);
		
		cookie = new Cookie("darkModeFlag", darkModeFlag ? "on" : "off");
		cookie.setMaxAge(60 * 60 * 24 * 7);
		resp.addCookie(cookie);
		
		json.put("status", "ok");
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
