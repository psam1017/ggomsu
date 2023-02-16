package com.ggomsu.app.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.member.vo.SettingVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;

//작성자 : 손하늘

public class SettingDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public SettingDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public SettingVO selectSetting(String email) {
		return sqlSession.selectOne("Setting.selectSetting", email);
	}
	
	public void updateSetting(SettingVO sVo) {
		sqlSession.update("Setting.updateSetting", sVo);
	}
	
}