package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.AlarmDAO;
import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.app.board.vo.CommentAlarmVO;
import com.ggomsu.app.board.vo.CommentVO;
//작성자: 김지혜
public class RefCommentWriteOk implements Action {
  @Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
	AlarmDAO alDao = new AlarmDAO();
    CommentDAO cDao = new CommentDAO();
    CommentVO cVo = new CommentVO();
    CommentAlarmVO caVo = new CommentAlarmVO();
    ActionForward forward = new ActionForward();
    
    HttpSession session = req.getSession();
    
    int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
    int commentIndex = cDao.getCommentIndex()+1;
    int refIndex = Integer.parseInt(req.getParameter("refIndex"));
    
    String nickname = (String)session.getAttribute("nickname");
    
    String content = req.getParameter("content");

    cVo.setArticleIndex(articleIndex);
    cVo.setCommentIndex(commentIndex);
    cVo.setRefIndex(refIndex);
    cVo.setNickname(nickname);
    cVo.setContent(content);
    
    String commentNickname = req.getParameter("commentNickname");
    String refNickname = req.getParameter("refNickname");
    
    cDao.insertRefComment(cVo);
    
    int checkCommentAlarm = alDao.checkCommentAlarm(refIndex);
//    System.out.println("대댓글달기 : "+checkCommentAlarm);
//    System.out.println("대댓글달기 : "+commentNickname);
//    System.out.println("대댓글달기 : "+refNickname);
//    System.out.println(commentNickname.equals(refNickname));
    
    caVo.setCommentIndex(commentIndex);
    caVo.setRefIndex(refIndex);
    caVo.setNickname(commentNickname);
    
	if(checkCommentAlarm == 0 && !(commentNickname.equals(refNickname))) {
    	alDao.insertRefCommentAlarm(caVo);
    }
    
    req.setCharacterEncoding("utf-8");
    resp.setCharacterEncoding("utf-8");
	resp.setContentType("text/html");
	
    forward.setForward(false);
	forward.setPath(req.getContextPath() + "/board/article-view-detail-ok?articleIndex=" + articleIndex);
    return forward;
  }
}