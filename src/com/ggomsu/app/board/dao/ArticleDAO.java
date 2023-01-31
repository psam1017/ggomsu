package com.ggomsu.app.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.admin.vo.ArticleReportVO;
import com.ggomsu.app.board.vo.ArticleVO;
import com.ggomsu.app.board.vo.CommentVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;
	// 작성자 : 이성호
public class ArticleDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public ArticleDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public int getTotal(String boardValue, String blockedList) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("boardValue", boardValue);
		hash.put("blockedList", blockedList);
		return sqlSession.selectOne("Article.getTotal",hash);
	}
	
	public List<ArticleVO> getList(int page, String boardValue, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("page", page);
		hash.put("boardValue", boardValue);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getArticleList", hash);
	}
	
	public List<ArticleVO> getBestList(int page, String boardValue, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("page", page);
		hash.put("boardValue", boardValue);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getBestArticleList", hash);
	}
	
	public int getSearchTotalCount(String search, String searchPeriod, String blockedList) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectOne("Article.getSearchTotalCount", hash);
	}
	
	public int getSearchWriterCount(String search, String searchPeriod, String blockedList) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectOne("Article.getSearchWriterCount", hash);
	}
	
	public int getSearchTitleContentCount(String search, String searchPeriod, String blockedList) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectOne("Article.getSearchTitleContentCount", hash);
	}
	
	public int getSearchTagCount(String search, String searchPeriod, String blockedList) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectOne("Article.getSearchTagCount", hash);
	}
	
	public List<ArticleVO> getSearchTotalList(String search, int page, String searchPeriod, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getSearchTotalArticleList", hash);
	}
	
	public List<ArticleVO> getSearchWriterList(String search, int page, String searchPeriod, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getSearchWriterArticleList", hash);
	}
	
	public List<ArticleVO> getSearchTitleContentList(String search, int page, String searchPeriod, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getSearchTitleContentArticleList", hash);
	}
	
	public List<ArticleVO> getSearchTagList(String search, int page, String searchPeriod, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getSearchTagArticleList", hash);
	}
	
	public ArticleVO getArticle(int articleIndex) {
		return sqlSession.selectOne("Article.getArticle", articleIndex);
	}
	
	public void updateArticleViewCount(int articleIndex) {
		sqlSession.update("Article.updateArticleViewCount",articleIndex);
	}
	
	public List<ArticleVO> getViewedOrderList(int page, String boardValue, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("page", page);
		hash.put("boardValue", boardValue);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getViewedOrderArticleList", hash);
	}
	
	public void deleteArticle(int articleIndex) {
		sqlSession.update("Article.deleteArticle", articleIndex);
	}
	
	public void insertArticle(ArticleVO articleVo) {
		sqlSession.update("Article.insertArticle", articleVo);
	}
	
	public int getMaxIndex() {
		return sqlSession.selectOne("Article.getMaxIndex");
	}
	
	public boolean checkGood(String nickname, int articleIndex){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("articleIndex", articleIndex);
		return (Integer)(sqlSession.selectOne("Article.checkGood", hash)) == 1;
	}
	
	public void InsertArticleLike(String nickname, int articleIndex){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("articleIndex", articleIndex);
		sqlSession.update("Article.insertArticleLike",hash);
	}
	
	public void DeleteArticleLike(String nickname, int articleIndex){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("articleIndex", articleIndex);
		sqlSession.update("Article.deleteArticleLike",hash);
	}
	
	public int getLikeTotal(String nickname, String blockedList) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("blockedList", blockedList);
		return sqlSession.selectOne("Article.getLikeTotal",hash);
	}
	
	public List<ArticleVO> getLikeList(String nickname, String blockedList, int page) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("blockedList", blockedList);
		hash.put("page", page);
		return sqlSession.selectList("Article.getLikeList",hash);
	}
	
	public void processReportArticle(int articleIndex, String articleDeleteReason) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("articleIndex", articleIndex);
		hash.put("articleDeleteReason", articleDeleteReason);
		sqlSession.update("Article.processReportArticle", hash);
	}
	
	public CommentVO getComment(int commentIndex) {
		return sqlSession.selectOne("Article.getComment", commentIndex);
	}
	
	public void processReportComment(int commentIndex, String commentDeleteReason) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("commentIndex", commentIndex);
		hash.put("commentDeleteReason", commentDeleteReason);
		sqlSession.update("Article.processReportComment", hash);
	}
	
}
