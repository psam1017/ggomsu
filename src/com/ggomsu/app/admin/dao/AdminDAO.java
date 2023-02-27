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
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("memberNickname", memberNickname);
		hash.put("memberStatus", memberStatus);
		sqlSession.update("Admin.updateMemberStatus", hash);
	}
	
	// article
	public List<ArticleReportVO> getArticleReportList(){
		return sqlSession.selectList("Admin.getArticleReportList");
	}
	
	public ArticleReportVO getArticleReport(String nickname, int articleIndex) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("articleIndex", articleIndex);
		return sqlSession.selectOne("Admin.getArticleReport", hash);
	}
	
	public void updateArticleReport(String articleDeleteReason, int articleIndex) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("articleDeleteReason", articleDeleteReason);
		hash.put("articleIndex", articleIndex);
		sqlSession.update("Admin.updateArticleReport", hash);
	}
	
	public void deleteArticleReport(int articleIndex) {
		sqlSession.delete("Admin.updateArticleReport", articleIndex);
	}
	
	// comment
	public List<CommentReportVO> getCommentReportList(){
		return sqlSession.selectList("Admin.getCommentReportList");
	}
	
	public CommentReportVO getCommentReport(String nickname, int commentIndex) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("commentIndex", commentIndex);
		return sqlSession.selectOne("Admin.getCommentReport", hash);
	}
	
	public void updateCommentReport(String commentDeleteReason, int commentIndex) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("commentDeleteReason", commentDeleteReason);
		hash.put("commentIndex", commentIndex);
		sqlSession.update("Admin.updateCommentReport", hash);
	}
	
	public void deleteCommentReport(int commentIndex) {
		sqlSession.delete("Admin.deleteCommentReport", commentIndex);
	}
	
	// 박성민 추가 : wiki, add admin
	public List<WikiReportVO> findWikiReportList(){
		return sqlSession.selectList("Admin.findWikiReportList");
	}
	
	public void updateWikiReport(String subject, int rvs) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("subject", subject);
		map.put("rvs", rvs);
		sqlSession.update("Admin.updateWikiReport", map);
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
