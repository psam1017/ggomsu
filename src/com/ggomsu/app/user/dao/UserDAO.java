package com.ggomsu.app.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.mybatis.config.MyBatisConfig;

public class UserDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public UserDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
//	public boolean checkEmail(String email) {
//		return (Integer)(sqlSession.selectOne("User.checkEmail", email)) == 1;
//	}
}
