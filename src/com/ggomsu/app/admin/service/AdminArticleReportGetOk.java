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
import com.ggomsu.app.admin.vo.ArticleReportVO;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.vo.ArticleVO;

public class AdminArticleReportGetOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ArticleDAO aDao = new ArticleDAO();
		ArticleReportDAO arDao = new ArticleReportDAO();
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
		int articleIndex = Integer.parseInt((String)jsonObject.get("articleIndex"));

		ArticleReportVO arVO = arDao.getArticleReport(nickname, articleIndex);
		ArticleVO aVO = aDao.getArticle(articleIndex);
		
		if(arVO != null) {
			json.put("reportStatus", "ok");
			json.put("articleIndex", articleIndex );
			json.put("reportContent", arVO.getArticleReportReason() );
			json.put("reportMember", nickname);
			json.put("articleContent", aVO.getContent() );
			json.put("reportedMember", aVO.getNickname());
		}
		else {
			json.put("reportStatus", "not-ok");
		}
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
