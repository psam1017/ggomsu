package com.ggomsu.app.wiki.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.mybatis.config.MyBatisConfig;
import com.ggomsu.app.wiki.vo.WikiInfoVO;
import com.ggomsu.app.wiki.vo.WikiContentVO;

// 작성자 : 박성민
public class WikiDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public WikiDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public int getLastRvs(String subject) {
		return sqlSession.selectOne("Wiki.getLastRvs", subject);
	}
	
	public List<String> getRecentSubject(){
		return sqlSession.selectList("Wiki.getRecentSubject");
	}

	public List<WikiContentVO> getContentOne(String subject, int rvs) {
		Map<String, Object> map = new HashMap<>();
		map.put("subject", subject);
		map.put("rvs", rvs);
		return sqlSession.selectList("Wiki.getContentOne", map);
	}
	
	public List<WikiContentVO> getContentPast(String subject, int rvs) {
		Map<String, Object> map = new HashMap<>();
		map.put("subject", subject);
		map.put("rvs", rvs);
		return sqlSession.selectList("Wiki.getContentPast", map);
	}
	
	public WikiInfoVO getWikiInfo(String subject, int rvs) {
		Map<String, Object> map = new HashMap<>();
		map.put("subject", subject);
		map.put("rvs", rvs);
		return sqlSession.selectOne("Wiki.getWikiInfo", map);
	}
	
	public void insertWikiInfo(WikiInfoVO info) {
		sqlSession.insert("Wiki.insertWikiInfo", info);
	}
	
	public void insertWikiContents(List<WikiContentVO> contents) {
		sqlSession.insert("Wiki.insertWikiContents", contents);
	}
}
