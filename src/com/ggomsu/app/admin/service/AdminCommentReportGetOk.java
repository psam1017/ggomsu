package com.ggomsu.app.admin.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.admin.dao.ArticleReportDAO;
import com.ggomsu.app.admin.dao.CommentReportDAO;
import com.ggomsu.app.admin.vo.ArticleReportVO;
import com.ggomsu.app.admin.vo.CommentReportVO;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.vo.ArticleVO;
import com.ggomsu.app.board.vo.CommentVO;

public class AdminCommentReportGetOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ArticleDAO aDao = new ArticleDAO();
		CommentReportDAO crDao = new CommentReportDAO();
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

		CommentReportVO crVO = crDao.getCommentReport(nickname, commentIndex);
		CommentVO cVO = aDao.getComment(commentIndex);
		
		if(crVO != null) {
			json.put("reportStatus", "ok");
			json.put("commentIndex", commentIndex );
			json.put("reportContent", crVO.getCommentReportReason() );
			json.put("reportMember", nickname);
			json.put("articleContent", cVO.getContent() );
			json.put("reportedMember", cVO.getNickname());
		}
		else {
			json.put("reportStatus", "not-ok");
		}
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
