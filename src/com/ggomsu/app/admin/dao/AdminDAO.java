package com.ggomsu.app.admin.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.mybatis.config.MyBatisConfig;

public class AdminDAO {

	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	public AdminDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public void updateMemberStatus(String memberNickname, String memberStatus) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("memberNickname", memberNickname);
		hash.put("memberStatus", memberStatus);
		sqlSession.update("Admin.updateMemberStatus", hash);
	}
	

}
