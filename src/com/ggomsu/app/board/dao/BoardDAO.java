package com.ggomsu.app.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.mybatis.config.MyBatisConfig;

	// 작성자 : 이성호
public class BoardDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public BoardDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public String getBoardText(String boardValue) {
		return sqlSession.selectOne("Board.getBoardText",boardValue);
	}
}
