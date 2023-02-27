package com.ggomsu.app.alarm.service;

import java.util.ArrayList;
import java.util.List;

import com.ggomsu.app.alarm.dao.AlarmDAO;
import com.ggomsu.app.alarm.vo.ArticleAlarmVO;
import com.ggomsu.app.alarm.vo.CommentAlarmVO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.app.board.vo.CommentDTO;

public class AlarmService {

	public void replaceAlarmWithMyComment(CommentDTO myComment) {
		
		AlarmDAO dao = new AlarmDAO();
		
		ArticleDTO referredArticle = dao.findArticleByIndex(myComment.getArticleIndex());
		CommentDTO referredComment = dao.findCommentByIndex(myComment.getRefIndex());
		
		// 댓글을 달았고, 내 게시글이 아닌 경우
		if(myComment.getCommentIndex() == myComment.getRefIndex()
				&& !myComment.getNickname().equals(referredArticle.getNickname())) {
			ArticleAlarmVO articleAlarm = new ArticleAlarmVO();
			articleAlarm.setArticleIndex(referredArticle.getArticleIndex());
			articleAlarm.setCommentIndex(myComment.getCommentIndex());
			articleAlarm.setNickname(referredArticle.getNickname());
			dao.replaceArticleAlarm(articleAlarm);
		}
		// 대댓글을 달았고, 내 댓글이 아닌 경우
		else if(myComment.getCommentIndex() != myComment.getRefIndex()
				&& !myComment.getNickname().equals(referredComment.getNickname())) {
			CommentAlarmVO commentAlarm = new CommentAlarmVO();
			commentAlarm.setRefIndex(referredComment.getCommentIndex());
			commentAlarm.setCommentIndex(myComment.getCommentIndex());
			commentAlarm.setNickname(referredComment.getNickname());
			dao.replaceCommentAlarm(commentAlarm);
			
		}
	}
	
	public void deleteArticleAlarm(String nickname, int articleIndex) {
		
		AlarmDAO dao = new AlarmDAO();
		List<Integer> articleAlarmIndexList = dao.findArticleAlarmIndexListByNickname(nickname);
		
		// 게시글 알람 인덱스 리스트에서 현재 조회하는 게시글 index를 찾아서 있으면 삭제한다.
		if(articleAlarmIndexList != null && articleAlarmIndexList.size() > 0) {
			for(int index : articleAlarmIndexList) {
				if(index == articleIndex) {
					dao.deleteArticleAlarm(index);
				}
			}
		}
	}
	
	public void deleteCommentAlarm(String nickname, List<CommentDTO> commentList) {
		
		AlarmDAO dao = new AlarmDAO();
		List<Integer> commentAlarmIndexList = dao.findCommentAlarmIndexListByNickname(nickname);
		
		// 댓글 알람 인덱스 리스트에서 현재 조회하는 댓글들의 index를 비교해서 있으면 삭제한다.
		if(commentAlarmIndexList != null && commentAlarmIndexList.size() > 0
				&& commentList != null && commentList.size() > 0) {
			List<Integer> commentIndexList = new ArrayList<>();
			for(CommentDTO comment : commentList) {
				commentIndexList.add(comment.getCommentIndex());
			}
			
			for(int index : commentAlarmIndexList) {
				if(commentIndexList.contains(index)) {
					dao.deleteCommentAlarm(index);
				}
			}
		}
	}
}
