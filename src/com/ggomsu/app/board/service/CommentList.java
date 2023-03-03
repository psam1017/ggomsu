package com.ggomsu.app.board.service;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ggomsu.app.board.dao.CommentDAO;
import com.ggomsu.app.board.vo.CommentDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.alarm.AlarmHelper;

public class CommentList implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		JSONArray array = new JSONArray();
		PrintWriter out = resp.getWriter();
		AlarmHelper alarmHelper = new AlarmHelper();
		CommentDAO commentDAO = new CommentDAO();
		HttpSession session = req.getSession();
		
		int articleIndex = Integer.parseInt(req.getParameter("articleIndex"));
		String nickname = (String)session.getAttribute("nickname");
		List<String> blindList = (List<String>)session.getAttribute("blindList");
		boolean alarmFlag = (boolean) session.getAttribute("alarmFlag");
		
		List<CommentDTO> commentList = commentDAO.getCommentList(articleIndex, blindList, nickname);
		
		// 알람 삭제
		if(alarmFlag) {
			alarmHelper.deleteCommentAlarm(nickname, commentList);
		}
		
		// 댓글 전송
		for(CommentDTO comment : commentList) {
			JSONObject object = new JSONObject();
			object.put("commentIndex", comment.getCommentIndex());
			object.put("refIndex", comment.getRefIndex());
			object.put("articleIndex", comment.getArticleIndex());
			object.put("nickname", comment.getNickname());
			object.put("content", comment.getContent());
			object.put("writtenAt", comment.getWrittenAt());
			object.put("deletedAt", comment.getDeletedAt());
			object.put("commentDeleteReason", comment.getCommentDeleteReason());
			object.put("commentLikeCount", comment.getCommentLikeCount());
			object.put("profileImageUrl", comment.getProfileImageUrl());
			object.put("isLiked", comment.isLiked());
			array.add(object);
		}
		
		out.print(array.toJSONString());
		out.close();
		
		return null;
	}

}
