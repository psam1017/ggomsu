package com.ggomsu.app.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.AlarmDAO;
//작성자: 김지혜
public class RefCommentAlarmDeleteOk implements Action {
	  @Override
		public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	  
	    AlarmDAO alDao = new AlarmDAO();
	    ActionForward forward = new ActionForward();
	    
	    int commentIndex = Integer.parseInt(req.getParameter("commentIndex"));

	    alDao.deleteRefCommentAlarm(commentIndex);

	    req.setCharacterEncoding("utf-8");
	    resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");

	    forward.setForward(false);
		forward.setPath(req.getContextPath() + "/app/index.jsp");
	    return forward;
	    
	  }
}
