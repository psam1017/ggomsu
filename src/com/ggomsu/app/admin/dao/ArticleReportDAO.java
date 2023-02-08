package com.ggomsu.app.admin.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.admin.vo.ArticleReportVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;

public class ArticleReportDAO {
	
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	public ArticleReportDAO() {
		sqlSession = sessionFactory.openSession(true);
	}

	public List<ArticleReportVO> getArticleReportList(){
		return sqlSession.selectList("Admin.getArticleReportList");
	}
	
	public ArticleReportVO getArticleReport(String nickname, int articleIndex) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("articleIndex", articleIndex);
		return sqlSession.selectOne("Admin.getArticleReport", hash);
	}
	
	public void updateArticleReport(String articleDeleteReason, int articleDeleteCheck, int articleIndex) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("articleDeleteReason", articleDeleteReason);
		hash.put("articleDeleteCheck", articleDeleteCheck);
		hash.put("articleIndex", articleIndex);
		sqlSession.update("Admin.updateArticleReport", hash);
	}
	
	public void deleteArticleReport(int articleIndex) {
		sqlSession.update("Admin.updateArticleReport", articleIndex);
	}
	
}
