package com.ggomsu.app.alarm.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ggomsu.app.alarm.dao.AlarmDAO;
import com.ggomsu.app.alarm.vo.AlarmMessageDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 김지혜, 박성민
public class ListView implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// java 객체 생성
		AlarmDAO dao = new AlarmDAO();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		PrintWriter out = resp.getWriter();
		
		// parameter 저장
		String nickname = (String)req.getSession().getAttribute("nickname");
		
		// json으로 message list 전달
		List<AlarmMessageDTO> messageList = dao.findAlarmListByNickname(nickname);
		
		for(AlarmMessageDTO message : messageList) {
			JSONObject temp = new JSONObject();
			temp.put("original", message.getOriginal());
			temp.put("nickname", message.getNickname());
			temp.put("content", message.getContent());
			temp.put("writtenAt", message.getWrittenAt());
			array.add(temp);
		}
		
		json.put("size", messageList.size());
		json.put("list", array);
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
