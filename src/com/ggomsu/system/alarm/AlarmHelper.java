package com.ggomsu.system.alarm;

import java.util.List;

import com.ggomsu.app.alarm.dao.AlarmDAO;
import com.ggomsu.app.alarm.vo.ArticleAlarmVO;
import com.ggomsu.app.alarm.vo.CommentAlarmVO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.app.board.vo.CommentDTO;
import com.ggomsu.app.member.dao.MemberDAO;

public class AlarmHelper {

	public void replaceAlarmWithMyComment(CommentDTO myComment) {
		
		AlarmDAO alarmDao = new AlarmDAO();
		MemberDAO memberDAO = new MemberDAO();
		
		ArticleDTO referredArticle = alarmDao.findArticleByIndex(myComment.getArticleIndex());
		CommentDTO referredComment = alarmDao.findCommentByIndex(myComment.getRefIndex());
		
		// 댓글을 달았고, 내 게시글이 아닌 경우
		if(myComment.getCommentIndex() == myComment.getRefIndex()) {
			
			// 내가 단 게시글의 주인이 나를 블라인드 했다면
			List<String> ownerBlindList = memberDAO.getBlindList(referredArticle.getNickname());
			if(ownerBlindList != null && ownerBlindList.size() > 0) {
				for(String s : ownerBlindList) {
					if(s.equals(myComment.getNickname())) {
						return;
					}
				}
			}
			
			// 블라인드하지 않았다면
			if(!myComment.getNickname().equals(referredArticle.getNickname())) {
				ArticleAlarmVO articleAlarm = new ArticleAlarmVO();
				articleAlarm.setArticleIndex(referredArticle.getArticleIndex());
				articleAlarm.setCommentIndex(myComment.getCommentIndex());
				articleAlarm.setNickname(referredArticle.getNickname());
				alarmDao.replaceArticleAlarm(articleAlarm);
			}
		}
		// 대댓글을 달았고, 내 댓글이 아닌 경우
		else if(myComment.getCommentIndex() != myComment.getRefIndex()){
			
			// 내가 단 대댓글의 대상의 주인이 나를 블라인드 했다면
			List<String> ownerBlindList = memberDAO.getBlindList(referredComment.getNickname());
			if(ownerBlindList != null && ownerBlindList.size() > 0) {
				for(String s : ownerBlindList) {
					if(s.equals(myComment.getNickname())) {
						return;
					}
				}
			}
			
			// 블라인드하지 않았다면
			if(!myComment.getNickname().equals(referredComment.getNickname())) {
				CommentAlarmVO commentAlarm = new CommentAlarmVO();
				commentAlarm.setRefIndex(referredComment.getCommentIndex());
				commentAlarm.setCommentIndex(myComment.getCommentIndex());
				commentAlarm.setNickname(referredComment.getNickname());
				alarmDao.replaceCommentAlarm(commentAlarm);
			}
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
			for(CommentDTO comment : commentList) {
				if(commentAlarmIndexList.contains(comment.getCommentIndex())) {
					dao.deleteCommentAlarm(comment.getCommentIndex());
				}
			}
		}
	}
}
