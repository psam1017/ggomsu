package com.ggomsu.app.wiki.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.mybatis.config.MyBatisConfig;
import com.ggomsu.app.wiki.vo.WikiVO;

// 작성자 : 박성민

public class WikiDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public WikiDAO() {
		sqlSession = sessionFactory.openSession(true);
	}

	public List<WikiVO> getList(String subject, int rvs) {
		Map<String, Object> map = new HashMap<>();
		map.put("subject", subject);
		map.put("rvs", rvs);
		return sqlSession.selectList("Wiki.getWiki", map);
	}
}
