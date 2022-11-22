package com.ggomsu.app.board.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.mybatis.config.MyBatisConfig;

public class FileDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public FileDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
}
