package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.ArticleReportDAO;
import com.ggomsu.app.board.vo.ArticlereportVO;

// 작성자 : 손하늘
public class ArticleReportOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		ArticleReportDAO dao = new ArticleReportDAO();
		ArticlereportVO aVo = new ArticlereportVO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
//		String nickname = (String)session.getAttribute("nickname");
//		int articleIndex = (Integer)session.getAttribute("articleIndex");
		String nickname = "psam1017";
		int articleIndex = 376;
		aVo.setNickname(nickname);
		aVo.setArticleIndex(articleIndex);
		aVo.setArticleReportReason(req.getParameter("declaration"));
		
		dao.articleReport(aVo);
			
		forward.setForward(true);
		forward.setPath("/app/board/ArticleViewList.jsp");
		
		return forward;
	}

}
