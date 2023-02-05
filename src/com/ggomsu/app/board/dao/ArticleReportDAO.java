package com.ggomsu.app.board.dao;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.board.vo.ArticlereportVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;

// 작성자 : 손하늘

public class ArticleReportDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public ArticleReportDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public void articleReport(ArticlereportVO aVo) {
		sqlSession.insert("Report.insertArticleReport", aVo);
	}
	
}
