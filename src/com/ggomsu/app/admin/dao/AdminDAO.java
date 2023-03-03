package com.ggomsu.app.admin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.app.report.vo.ArticleReportVO;
import com.ggomsu.app.report.vo.CommentReportVO;
import com.ggomsu.app.report.vo.WikiReportVO;
import com.ggomsu.system.mybatis.config.MyBatisConfig;

public class AdminDAO {

	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	public AdminDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	// member status
	public void updateMemberStatus(String memberNickname, String memberStatus) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberNickname", memberNickname);
		map.put("memberStatus", memberStatus);
		sqlSession.update("Admin.updateMemberStatus", map);
	}
	
	// article
	public List<ArticleReportVO> getArticleReportList(){
		return sqlSession.selectList("Admin.getArticleReportList");
	}

	public void confirmReportedArticle(int articleIndex, String articleDeleteReason) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleIndex", articleIndex);
		map.put("articleDeleteReason", articleDeleteReason);
		sqlSession.update("Admin.confirmReportedArticle", map);
	}
	
	public void deleteArticleReport(int articleIndex) {
		sqlSession.delete("Admin.deleteArticleReport", articleIndex);
	}
	
	// comment
	public List<CommentReportVO> getCommentReportList(){
		return sqlSession.selectList("Admin.getCommentReportList");
	}

	public void confirmReportedComment(int commentIndex, String commentDeleteReason) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commentIndex", commentIndex);
		map.put("commentDeleteReason", commentDeleteReason);
		sqlSession.update("Admin.confirmReportedComment", map);
	}
	
	public void deleteCommentReport(int commentIndex) {
		sqlSession.delete("Admin.deleteCommentReport", commentIndex);
	}
	
	// 박성민 추가 : wiki, add
	public List<WikiReportVO> findWikiReportList(){
		return sqlSession.selectList("Admin.findWikiReportList");
	}
	
	public void confirmReportedWiki(String subject, int rvs, String wikiDeleteReason) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subject", subject);
		map.put("rvs", rvs);
		map.put("wikiDeleteReason", wikiDeleteReason);
		sqlSession.update("Admin.confirmReportedWiki", map);
	}
	
	public void deleteWikiReport(String subject, int rvs) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subject", subject);
		map.put("rvs", rvs);
		sqlSession.delete("Admin.deleteWikiReport", map);
	}
	
	public void addNewAdmin(MemberVO member) {
		sqlSession.insert("Admin.addNewAdmin", member);
	}
}
