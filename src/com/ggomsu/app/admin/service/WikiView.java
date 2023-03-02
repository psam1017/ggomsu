package com.ggomsu.app.admin.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.app.admin.dao.AdminDAO;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.app.report.vo.ArticleReportVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 이성호
public class WikiView implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ArticleDAO articleDAO = new ArticleDAO();
		AdminDAO adminDAO = new AdminDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		// TODO 신고 받은 wiki 바로 보기
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

		ArticleReportVO articleReport = adminDAO.getArticleReport(nickname, articleIndex);
		ArticleDTO article = articleDAO.findArticle(articleIndex);
		
		if(articleReport != null) {
			json.put("reportStatus", "ok");
			json.put("articleIndex", articleIndex );
			json.put("reportContent", articleReport.getArticleReportReason() );
			json.put("reportMember", nickname);
			json.put("articleContent", article.getContent() );
			json.put("reportedMember", article.getNickname());
		}
		else {
			json.put("reportStatus", "not-ok");
		}
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
