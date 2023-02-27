package com.ggomsu.app.alarm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.alarm.vo.AlarmMessageDTO;
import com.ggomsu.app.alarm.vo.ArticleAlarmVO;
import com.ggomsu.app.alarm.vo.CommentAlarmVO;
import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.app.board.vo.CommentDTO;
import com.ggomsu.system.mybatis.config.MyBatisConfig;

	// 작성자 : 김지혜
public class AlarmDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	public AlarmDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public ArticleDTO findArticleByIndex(int articleIndex) {
		return sqlSession.selectOne("Alarm.findArticleByIndex", articleIndex);
	}
	
	public CommentDTO findCommentByIndex(int refIndex) {
		return sqlSession.selectOne("Alarm.findCommentByIndex", refIndex);
	}
	
	public void replaceArticleAlarm(ArticleAlarmVO articleAlarm) {
		sqlSession.insert("Alarm.replaceArticleAlarm", articleAlarm);
	}
	
	public void replaceCommentAlarm(CommentAlarmVO commentAlarm) {
		sqlSession.insert("Alarm.replaceCommentAlarm", commentAlarm);
	}
	
	public List<Integer> findArticleAlarmIndexListByNickname(String nickname){
		return sqlSession.selectList("Alarm.findArticleAlarmIndexListByNickname", nickname);
	}
	
	public List<Integer> findCommentAlarmIndexListByNickname(String nickname){
		return sqlSession.selectList("Alarm.findCommentAlarmIndexListByNickname", nickname);
	}
	
	public void deleteArticleAlarm(int index) {
		sqlSession.delete("Alarm.deleteArticleAlarm", index);
	}
	
	public void deleteCommentAlarm(int index) {
		sqlSession.delete("Alarm.deleteCommentAlarm", index);
	}
	
	public List<AlarmMessageDTO> findAlarmListByNickname(String nickname){
		return sqlSession.selectList("Alarm.findAlarmList", nickname);
	}
	
	public void deleteAlarmListByNickname(String nickname) {
		sqlSession.delete("Alarm.deleteAlarmListByNickname", nickname);
	}
}
