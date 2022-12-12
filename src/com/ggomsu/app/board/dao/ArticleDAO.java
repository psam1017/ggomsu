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
	
	public int getSearchTotal(String search) {
		return sqlSession.selectOne("Article.getSearchTotal", search);
	}
	
	public List<ArticleVO> getSearchList(String search, int page){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("search", search);
		hash.put("page", page);
		return sqlSession.selectList("Article.getSearchArticleList", hash);
	}
	
	public ArticleVO getArticle(int index) {
		return sqlSession.selectOne("Article.getArticle", index);
	}
	
	public void updateArticleViewCount(int index) {
		sqlSession.update("Article.updateArticleViewCount",index);
	}
	
	public List<ArticleVO> getViewedOrderList(int page, String boardValue){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("page", page);
		hash.put("boardValue", boardValue);
		return sqlSession.selectList("Article.getViewedOrderArticleList", hash);
	}
	
	public void deleteArticle(int index) {
		sqlSession.update("Article.deleteArticle", index);
	}
	
	public void insertArticle(ArticleVO articleVo) {
		sqlSession.update("Article.insertArticle", articleVo);
	}
	
	public int getMaxIndex() {
		return sqlSession.selectOne("Article.getMaxIndex");
	}
	
}
