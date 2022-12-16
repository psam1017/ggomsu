package com.ggomsu.app.board.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.mybatis.config.MyBatisConfig;

public class AttachmentDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public AttachmentDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public void insertAttachment(String systemName, int articleIndex) {
			HashMap<String, Object> hash = new HashMap<>();
			hash.put("systemName", systemName);
			hash.put("articleIndex", articleIndex);
			sqlSession.insert("Attachment.insertAttachment", hash);
		
	}
}
