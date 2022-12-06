package com.ggomsu.app.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.board.vo.ArticleVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;

public class ArticleDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public ArticleDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public int getTotal() {
		return sqlSession.selectOne("Article.getTotal");
	}
	
	public List<ArticleVO> getList(int page){
		return sqlSession.selectList("Article.getArticleList", page);
	}
	
	public List<ArticleVO> getBestList(int page){
		return sqlSession.selectList("Article.getBestArticleList", page);
	}
	
	public int getSearchTotal(String search) {
		return sqlSession.selectOne("Article.getSearchTotal", search);
	}
	
	public List<ArticleVO> getSearchList(String search){
		return sqlSession.selectList("Article.getSearchArticleList", search);
	}
	
	public ArticleVO getArticle(int index) {
		return sqlSession.selectOne("Article.getArticle", index);
	}
}
