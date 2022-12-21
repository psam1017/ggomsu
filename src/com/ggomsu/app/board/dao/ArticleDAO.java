package com.ggomsu.app.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.board.vo.ArticleVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;
	// 작성자 : 이성호
public class ArticleDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public ArticleDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public int getTotal(String boardValue) {
		return sqlSession.selectOne("Article.getTotal",boardValue);
	}
	
	public List<ArticleVO> getList(int page, String boardValue){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("page", page);
		hash.put("boardValue", boardValue);
		return sqlSession.selectList("Article.getArticleList", hash);
	}
	
	public List<ArticleVO> getBestList(int page, String boardValue){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("page", page);
		hash.put("boardValue", boardValue);
		return sqlSession.selectList("Article.getBestArticleList", hash);
	}
	
	public int getSearchTotalCount(String search, String searchPeriod) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("searchPeriod", searchPeriod);
		return sqlSession.selectOne("Article.getSearchTotalCount", hash);
	}
	
	public int getSearchWriterCount(String search, String searchPeriod) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("searchPeriod", searchPeriod);
		return sqlSession.selectOne("Article.getSearchWriterCount", hash);
	}
	
	public int getSearchTitleContentCount(String search, String searchPeriod) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("searchPeriod", searchPeriod);
		return sqlSession.selectOne("Article.getSearchTitleContentlCount", hash);
	}
	
	public List<ArticleVO> getSearchTotalList(String search, int page, String searchPeriod){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		hash.put("searchPeriod", searchPeriod);
		return sqlSession.selectList("Article.getSearchTotalArticleList", hash);
	}
	
	public List<ArticleVO> getSearchWriterList(String search, int page, String searchPeriod){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		return sqlSession.selectList("Article.getSearchWriterArticleList", hash);
	}
	
	public List<ArticleVO> getSearchTitleContentList(String search, int page, String searchPeriod){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		return sqlSession.selectList("Article.getSearchTitleContentArticleList", hash);
	}
	
	public ArticleVO getArticle(int articleIndex) {
		return sqlSession.selectOne("Article.getArticle", articleIndex);
	}
	
	public void updateArticleViewCount(int articleIndex) {
		sqlSession.update("Article.updateArticleViewCount",articleIndex);
	}
	
	public List<ArticleVO> getViewedOrderList(int page, String boardValue){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("page", page);
		hash.put("boardValue", boardValue);
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
	
}
