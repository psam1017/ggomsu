package com.ggomsu.app.member.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.board.vo.ArticleVO;
import com.ggomsu.app.board.vo.CommentVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;

//작성자 : 손하늘

public class HistoryDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public HistoryDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public List<ArticleVO> selectMyHistoryarticle(String nickname) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("nickname",nickname);
		return sqlSession.selectList("Article.selectMyHistoryarticle", map);
	}
	
	public List<CommentVO> selectMyHistorycomment(String nickname) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("nickname",nickname);
		return sqlSession.selectList("Comment.selectMyHistorycomment", map);
	}
	
}