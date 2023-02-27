package com.ggomsu.app.report.dao;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.report.vo.ArticleReportVO;
import com.ggomsu.app.report.vo.CommentReportVO;
import com.ggomsu.app.report.vo.WikiReportVO;
import com.ggomsu.system.mybatis.config.MyBatisConfig;

// 작성자 : 손하늘, 박성민, 이성호

public class ReportDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public ReportDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public void replaceArticleReport(ArticleReportVO articleReport) {
		sqlSession.insert("Report.replaceArticleReport", articleReport);
	}
	
	public void replaceCommentReport(CommentReportVO commentReport) {
		sqlSession.insert("Report.replaceCommentReport", commentReport);
	}
	
	public void replaceWikiReport(WikiReportVO wikiReport) {
		sqlSession.insert("Report.replaceWikiReport", wikiReport);
	}
}
