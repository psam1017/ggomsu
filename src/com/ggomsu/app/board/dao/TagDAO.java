package com.ggomsu.app.board.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.system.mybatis.config.MyBatisConfig;

public class TagDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public TagDAO() {
		sqlSession = sessionFactory.openSession(true);
	}

	public void insertTag(int articleIndex, String tagValue) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("articleIndex", articleIndex);
		hash.put("tagValue", tagValue);
		sqlSession.update("Article.insertTag", hash);
	}
}
