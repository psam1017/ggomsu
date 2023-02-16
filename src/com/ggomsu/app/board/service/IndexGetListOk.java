package com.ggomsu.app.board.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.AlarmDAO;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.BoardDAO;
import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.app.board.vo.CommentVO;

	// 작성자 : 김지혜
public class IndexGetListOk implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		ActionForward forward = new ActionForward();
		
		//게시판
		ArticleDAO aDao = new ArticleDAO();
		BoardDAO bDao = new BoardDAO();
		AlarmDAO alDao = new AlarmDAO();
		
		req.setAttribute("articleLikeList", aDao.getBestLikeList());
		req.setAttribute("articleViewList", aDao.getBestViewList());
		
		HttpSession session = req.getSession();
		
		Cookie[] cookies = req.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(int i = 0; i <cookies.length; i++) {
				if(cookies[i].getName().equals("boardValue")) {
					String boardValue = cookies[i].getValue();
					//System.out.println("controller: "+boardValue);
					req.setAttribute("boardValue", boardValue);
					req.setAttribute("articleBestLikeOne", aDao.getBestLikeOne(boardValue));
					req.setAttribute("articleBestViewOne", aDao.getBestViewOne(boardValue));
				}
				else {String boardValue = null;
					Cookie cookie = new Cookie("boardValue", boardValue);
					resp.addCookie(cookie);
					//System.out.println("controller2: "+boardValue);
					req.setAttribute("boardValue", boardValue);}
			}
		}else {String boardValue = null;
			//System.out.println("controller3: "+boardValue);
			req.setAttribute("boardValue", boardValue);}
		
		req.setAttribute("articleBestLikeBoard", aDao.getBestLikeBoard());
		req.setAttribute("articleBestViewBoard", aDao.getBestViewBoard());
		
		//알림
	    
	    String nickname = (String)session.getAttribute("nickname");
	    //System.out.println(nickname);
	    req.setAttribute("alarmCount", alDao.getCommentAlarmCount(nickname));
	    //System.out.println("결과: "+cDao.getCommentAlarmCount(nickname));
	    
	    List<CommentVO> alarmList = new ArrayList<>();
	    alarmList.addAll(alDao.getCommentAlarmList(nickname));
	    alarmList.addAll(alDao.getRefCommentAlarmList(nickname));
	    
	    req.setAttribute("alarmList", alarmList);
		
	    req.setAttribute("commentContent", alDao.getCommentContent(nickname));
	    //System.out.println("CommentContent:" + alDao.getCommentContent(nickname));
	    req.setAttribute("articleTitle", alDao.getArticleTitle(nickname));
	    //System.out.println("ArticleTitle:" +alDao.getArticleTitle(nickname));
	    
		forward.setForward(true);
		forward.setPath("/app/index.jsp");
		
		return forward;
	}

}
