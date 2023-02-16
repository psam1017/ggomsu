package com.ggomsu.app.admin.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.admin.vo.ArticleReportVO;
import com.ggomsu.app.admin.vo.CommentReportVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;

public class CommentReportDAO {
	
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	public CommentReportDAO() {
		sqlSession = sessionFactory.openSession(true);
	}

	public List<CommentReportVO> getCommentReportList(){
		return sqlSession.selectList("Admin.getCommentReportList");
	}
	
	public CommentReportVO getCommentReport(String nickname, int commentIndex) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("commentIndex", commentIndex);
		return sqlSession.selectOne("Admin.getCommentReport", hash);
	}
	
	public void updateCommentReport(String commentDeleteReason, int commentDeleteCheck, int commentIndex) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("commentDeleteReason", commentDeleteReason);
		hash.put("commentDeleteCheck", commentDeleteCheck);
		hash.put("commentIndex", commentIndex);
		sqlSession.update("Admin.updateCommentReport", hash);
	}
	
	public void deleteCommentReport(int commentIndex) {
		sqlSession.update("Admin.deleteCommentReport", commentIndex);
	}

	
	
}
