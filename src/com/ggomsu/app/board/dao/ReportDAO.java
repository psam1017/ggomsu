package com.ggomsu.app.board.dao;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.board.vo.ReportVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;

// 작성자 : 손하늘

public class ReportDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public ReportDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public void insertArticleReport(ReportVO aVo) {
		sqlSession.insert("Report.insertArticleReport", aVo);
	}
	
	public void insertCommentReport(ReportVO aVo) {
		sqlSession.insert("Report.insertCommentReport", aVo);
	}
	
}
