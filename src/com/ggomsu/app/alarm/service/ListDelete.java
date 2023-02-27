package com.ggomsu.app.alarm.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.alarm.dao.AlarmDAO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 박성민
public class ListDelete implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// java 객체 생성
		AlarmDAO dao = new AlarmDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		// parameter 저장
		String nickname = (String)req.getSession().getAttribute("nickname");
		
		// 알람 일괄 삭제
		dao.deleteAlarmListByNickname(nickname);
		
		json.put("status", "ok");
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
