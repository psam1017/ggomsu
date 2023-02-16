package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.AlarmDAO;
import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.app.board.vo.ArticleAlarmVO;
import com.ggomsu.app.board.vo.CommentVO;
//작성자: 김지혜
public class CommentWriteOk implements Action {
  @Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
  
    CommentDAO cDao = new CommentDAO();
    CommentVO cVo = new CommentVO();
    AlarmDAO alDao = new AlarmDAO();
    ArticleAlarmVO aaVo = new ArticleAlarmVO();
    ActionForward forward = new ActionForward();
    
    HttpSession session = req.getSession();
    
    int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
    //System.out.println(articleIndex);
    int commentIndex = cDao.getCommentIndex()+1;
    //System.out.println(commentIndex);
    int refIndex = commentIndex;
    
    String nickname = (String)session.getAttribute("nickname");
    String content = req.getParameter("content");
    
    //select로 먼저 확인한 후에 select
    cVo.setArticleIndex(articleIndex);
    cVo.setCommentIndex(commentIndex);
    cVo.setRefIndex(refIndex);
    cVo.setNickname(nickname);
    cVo.setContent(content);

    cDao.insertComment(cVo);
    
    int checkArticleAlarm = alDao.checkArticleAlarm(articleIndex);
    
    String articleWriter = req.getParameter("articleWriter");
    String commentWriter = req.getParameter("commentWriter");
    
//    System.out.println("댓글달기 : "+checkArticleAlarm);
//    System.out.println("댓글달기 : "+articleWriter);
//    System.out.println("댓글달기 : "+commentWriter);
//    System.out.println(articleWriter.equals(commentWriter));
    
    aaVo.setArticleIndex(articleIndex);
    aaVo.setCommentIndex(commentIndex);
    aaVo.setNickname(articleWriter);
    
    if( !(articleWriter.equals(commentWriter)) && checkArticleAlarm == 0 ) {
        alDao.insertCommentAlarm(aaVo);
    }
    
    req.setCharacterEncoding("utf-8");
    resp.setCharacterEncoding("utf-8");
	resp.setContentType("text/html");
	
    forward.setForward(false);
	forward.setPath(req.getContextPath() + "/board/article-view-detail-ok?articleIndex=" + articleIndex);
    return forward;
  }
}