package com.ggomsu.app.board.dao;

import java.util.HashMap;
import java.util.Map;

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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleIndex", articleIndex);
		map.put("tagValue", tagValue);
		sqlSession.update("Tag.insertTag", map);
	}
	
	public void deleteTags(int articleIndex) {
		sqlSession.delete("Tag.deleteTags", articleIndex);
	}
}
