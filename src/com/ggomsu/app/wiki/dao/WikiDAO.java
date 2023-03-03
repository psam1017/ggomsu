package com.ggomsu.app.wiki.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.wiki.vo.*;
import com.ggomsu.system.mybatis.config.MyBatisConfig;

// 작성자 : 박성민
public class WikiDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public WikiDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public List<WikiInfoDTO> getAllRvsBySubject(String subject){
		return sqlSession.selectList("Wiki.getAllRvsBySubject", subject);
	}
	
	public List<WikiInfoDTO> getRecentSubject(){
		return sqlSession.selectList("Wiki.getRecentSubject");
	}
	
	public WikiInfoDTO findWikiInfo(String subject, int rvs) {
		Map<String, Object> map = new HashMap<>();
		map.put("subject", subject);
		map.put("rvs", rvs);
		return sqlSession.selectOne("Wiki.findWikiInfo", map);
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
	
	public void insertWikiInfo(WikiInfoDTO info) {
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
	
	public void confirmWikiAbuse(String nickname, String ip) {
		Map<String, String> map = new HashMap<>();
		map.put("nickname", nickname);
		map.put("ip", ip);
		sqlSession.insert("Wiki.confirmWikiAbuse", map);
	}
	
	public WikiAbuseVO getAbuseInfo(String nickname, String ip) {
		Map<String, String> map = new HashMap<>();
		map.put("nickname", nickname);
		map.put("ip", ip);
		return sqlSession.selectOne("Wiki.getAbuseInfo", map);
	}
	
	public void confirmWikiDelete(String subject, int rvs) {
		Map<String, Object> map = new HashMap<>();
		map.put("subject", subject);
		map.put("rvs", rvs);
		sqlSession.update("Wiki.confirmWikiDelete", map);
	}
	
	public boolean checkExistBySubject(String subject) {
		return Integer.parseInt(sqlSession.selectOne("Wiki.checkExistBySubject", subject)) == 1;
	}
	
	public int reviseWikiInfo(WikiInfoDTO info) {
		return sqlSession.selectOne("Wiki.reviseWikiInfo", info);
	}
}
