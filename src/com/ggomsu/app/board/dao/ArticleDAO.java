package com.ggomsu.app.board.dao;

import java.util.List;

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
	
}
