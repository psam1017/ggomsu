package com.ggomsu.app.report.service;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.ggomsu.app.report.dao.ReportDAO;
import com.ggomsu.app.report.vo.CommentReportVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;

// 작성자 : 손하늘
public class CommentConfirm implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// java 객체 생성
		ReportDAO dao = new ReportDAO();
		CommentReportVO vo = new CommentReportVO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		
		// 파라미터 저장
		String nickname = (String) session.getAttribute("nickname");
		String statusValue = (String) session.getAttribute("statusValue");
		int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));
		
		if(statusValue == null || statusValue.equals("TMP")) {
			json.put("status", "tmp");
		}
		else if(statusValue.equals("MEM") || statusValue.equals("ADM")) {
			// 댓글 신고 제출
			vo.setNickname(nickname);
			vo.setCommentIndex(commentIndex);
			dao.replaceCommentReport(vo);
			
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
