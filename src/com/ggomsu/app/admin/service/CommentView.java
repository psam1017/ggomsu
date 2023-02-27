package com.ggomsu.app.admin.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.app.admin.dao.AdminDAO;
import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.app.board.vo.CommentDTO;
import com.ggomsu.app.report.vo.CommentReportVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

public class CommentView implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		CommentDAO commentDAO = new CommentDAO();
		AdminDAO adminDAO = new AdminDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()));
        StringBuilder builder = new StringBuilder();
        String buffer;
        while ((buffer = input.readLine()) != null) {
            if (builder.length() > 0) {
                builder.append("\n");
            }
            builder.append(buffer);
        }
        
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(builder.toString());
        
		String nickname = (String) jsonObject.get("nickname");
		int commentIndex = Integer.parseInt((String)jsonObject.get("commentIndex"));

		CommentReportVO commentReport = adminDAO.getCommentReport(nickname, commentIndex);
		CommentDTO comment = commentDAO.getCommentOne(commentIndex);
		
		if(commentReport != null) {
			json.put("reportStatus", "ok");
			json.put("commentIndex", commentIndex );
			json.put("reportMember", nickname);
			json.put("articleContent", comment.getContent() );
			json.put("reportedMember", comment.getNickname());
		}
		else {
			json.put("reportStatus", "not-ok");
		}
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
