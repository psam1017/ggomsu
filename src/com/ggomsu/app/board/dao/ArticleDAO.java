package com.ggomsu.app.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.app.board.vo.CommentDTO;
import com.ggomsu.app.report.vo.ArticleReportVO;
import com.ggomsu.system.mybatis.config.MyBatisConfig;
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
	
	public List<ArticleDTO> getList(int page, String boardValue, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("page", page);
		hash.put("boardValue", boardValue);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getArticleList", hash);
	}
	
	public List<ArticleDTO> getBestList(int page, String boardValue, String blockedList){
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
	
	public int getSearchTitleContentCount(String search, String searchPeriod, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectOne("Article.getSearchTitleContentlCount", hash);
	}
	
	public int getSearchTagCount(String search, String searchPeriod, String blockedList) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectOne("Article.getSearchTagCount", hash);
	}
	
	public List<ArticleDTO> getSearchTotalList(String search, int page, String searchPeriod, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getSearchTotalArticleList", hash);
	}
	
	public List<ArticleDTO> getSearchWriterList(String search, int page, String searchPeriod, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getSearchWriterArticleList", hash);
	}
	
	public List<ArticleDTO> getSearchTitleContentList(String search, int page, String searchPeriod, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getSearchTitleContentArticleList", hash);
	}
	
	public List<ArticleDTO> getSearchTagList(String search, int page, String searchPeriod, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		hash.put("searchPeriod", searchPeriod);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getSearchTagArticleList", hash);
	}
	
	public ArticleDTO getArticle(int articleIndex) {
		return sqlSession.selectOne("Article.getArticle", articleIndex);
	}
	
	public void updateArticleViewCount(int articleIndex) {
		sqlSession.update("Article.updateArticleViewCount",articleIndex);
	}
	
	public List<ArticleDTO> getViewedOrderList(int page, String boardValue, String blockedList){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("page", page);
		hash.put("boardValue", boardValue);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Article.getViewedOrderArticleList", hash);
	}
	
	public void deleteArticle(int articleIndex) {
		sqlSession.update("Article.deleteArticle", articleIndex);
	}
	
	public void insertArticle(ArticleDTO articleVo) {
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

	public void processReportArticle(int articleIndex, String articleDeleteReason) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("articleIndex", articleIndex);
		hash.put("articleDeleteReason", articleDeleteReason);
		sqlSession.update("Article.processReportArticle", hash);
	}

	// 마이 페이지 게시글 좋아요 목록
	public int findArticleLikeTotalByNickname(String nickname) {
		return sqlSession.selectOne("Article.findArticleLikeTotalByNickname", nickname);
	}

	public List<ArticleDTO> getArticleLikeList(String nickname, int page) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("page", page);
		return sqlSession.selectList("Article.getLikeList", hash);
	}
	
	// index 좋아요, 조회수 베스트
	public List<ArticleDTO> findWeeklyMostLikedList(String boardValue) {
		return sqlSession.selectList("Article.findWeeklyMostLikedList", boardValue);
	}
	public List<ArticleDTO> findWeeklyMostViewedList(String boardValue) {
		return sqlSession.selectList("Article.findWeeklyMostViewedList", boardValue);
	}
}
