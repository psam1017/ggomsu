package com.ggomsu.app.report.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.ggomsu.app.report.dao.ReportDAO;
import com.ggomsu.app.report.vo.ArticleReportVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 손하늘
public class ArticleConfirm implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 자바 객체 생성
		ReportDAO dao = new ReportDAO();
		ArticleReportVO vo = new ArticleReportVO();
		HttpSession session = req.getSession();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		// 파라미터 저장
		String nickname = (String) session.getAttribute("nickname");
		String statusValue = (String) session.getAttribute("statusValue");
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		String articleReportReason = req.getParameter("articleReportReason");
		
		if(statusValue == null || statusValue.equals("TMP")) {
			json.put("status", "tmp");
		}
		else if(statusValue.equals("MEM") || statusValue.equals("ADM")) {
			// 게시글 신고 제출
			vo.setNickname(nickname);
			vo.setArticleIndex(articleIndex);
			vo.setArticleReportReason(articleReportReason);
			dao.replaceArticleReport(vo);
			
			json.put("status", "ok");
		}
		else {
			json.put("status", "not-ok");
		}
		
		out.print(json.toJSONString());
		out.close();
		return null;
	}
}
