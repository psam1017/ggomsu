package com.ggomsu.app.wiki.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.mybatis.config.MyBatisConfig;
import com.ggomsu.app.wiki.vo.*;

// 작성자 : 박성민
public class WikiDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public WikiDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public List<WikiInfoVO> getRecentSubject(){
		return sqlSession.selectList("Wiki.getRecentSubject");
	}
	
	public WikiInfoVO getWikiInfo(String subject, int rvs) {
		Map<String, Object> map = new HashMap<>();
		map.put("subject", subject);
		map.put("rvs", rvs);
		return sqlSession.selectOne("Wiki.getWikiInfo", map);
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
	
	public void insertWikiInfo(WikiInfoVO info) {
		sqlSession.insert("Wiki.insertWikiInfo", info);
	}
	
	public void insertWikiContents(List<WikiContentVO> contents) {
		sqlSession.insert("Wiki.insertWikiContents", contents);
	}
	
	public boolean checkBlockedIp(String ip) {
		return Integer.parseInt(sqlSession.selectOne("Wiki.checkBlockedIp", ip)) == 1;
	}
	
	public boolean checkBlockedNickname(String nickname) {
		return Integer.parseInt(sqlSession.selectOne("Wiki.checkBlockedNickname", nickname)) == 1;
	}
	
	public WikiAbuseVO getAbuseInfo(String nickname, String ip) {
		Map<String, String> map = new HashMap<>();
		map.put("nickname", nickname);
		map.put("ip", ip);
		return sqlSession.selectOne("Wiki.getAbuseInfo", map);
	}
	
	public boolean checkExistBySubject(String subject) {
		return Integer.parseInt(sqlSession.selectOne("Wiki.checkExistBySubject", subject)) == 1;
	}
	
	public int reviseWikiInfo(WikiInfoVO info) {
		return sqlSession.selectOne("Wiki.reviseWikiInfo", info);
	}
}
