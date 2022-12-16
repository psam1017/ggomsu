package com.ggomsu.app.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.board.vo.CommentVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;

public class CommentDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public CommentDAO() {
		sqlSession = sessionFactory.openSession(true);
	}

}