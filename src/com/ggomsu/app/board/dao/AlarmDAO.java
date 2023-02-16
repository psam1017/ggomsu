package com.ggomsu.app.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ggomsu.app.board.vo.ArticleAlarmVO;
import com.ggomsu.app.board.vo.ArticleVO;
import com.ggomsu.app.board.vo.CommentAlarmVO;
import com.ggomsu.app.board.vo.CommentVO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.mybatis.config.MyBatisConfig;

	// 작성자 : 김지혜
public class AlarmDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public AlarmDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	//알림

	public int checkArticleAlarm(int articleIndex) {
		return sqlSession.selectOne("Alarm.checkArticleAlarm", articleIndex);
	}
	
	public int checkCommentAlarm(int refIndex) {
		return sqlSession.selectOne("Alarm.checkCommentAlarm", refIndex);
	}
	
	public void insertCommentAlarm(ArticleAlarmVO articleAlarmVo) {
		sqlSession.insert("Alarm.insertCommentAlarm", articleAlarmVo);
	}
	
	public void insertRefCommentAlarm(CommentAlarmVO commentAlarmVo) {
		sqlSession.insert("Alarm.insertRefCommentAlarm", commentAlarmVo);
	}
	
	public int getCommentAlarmCount(String nickname){
		return sqlSession.selectOne("Alarm.getCommentAlarmCount", nickname);
	}
	
	public List<CommentVO> getCommentAlarmList(String nickname){
		return sqlSession.selectList("Alarm.getCommentAlarmList", nickname);
	}
	public List<CommentVO> getRefCommentAlarmList(String nickname){
		return sqlSession.selectList("Alarm.getRefCommentAlarmList", nickname);
	}
	
	public List<CommentVO> getCommentContent(String nickname) {
		return sqlSession.selectList("Alarm.getCommentContent", nickname);
	}
	
	public List<ArticleVO> getArticleTitle(String nickname) {
		return sqlSession.selectList("Alarm.getArticleTitle", nickname);
	}
	
	
	public void deleteCommentAlarm(int commentIndex){
		sqlSession.update("Alarm.deleteCommentAlarm", commentIndex);
	}
	public void deleteRefCommentAlarm(int commentIndex){
		sqlSession.update("Alarm.deleteRefCommentAlarm", commentIndex);
	}
		
}
