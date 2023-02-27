package com.ggomsu.app.board.dao;

import java.util.List;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.app.board.vo.CommentDTO;
import com.ggomsu.system.mybatis.config.MyBatisConfig;

// 작성자 : 김지혜
public class CommentDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	public CommentDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public List<CommentDTO> getCommentList(int articleIndex, List<String> blindList){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("articleIndex", articleIndex);
		map.put("blindList", blindList);
		return sqlSession.selectList("Comment.getCommentList", map);
	}
	
	public CommentDTO doInsertCommentProcedure(CommentDTO commentVO) {
		return sqlSession.selectOne("Comment.doInsertCommentProcedure", commentVO);
	}
	
	public CommentDTO doInsertRefCommentProcedure(CommentDTO commentVO) {
		return sqlSession.selectOne("Comment.doInsertRefCommentProcedure", commentVO);
	}
	
	public void deleteCommentByCommentIndex(int commentIndex) {
		sqlSession.delete("Comment.deleteCommentByCommentIndex", commentIndex);
	}

	// 관리자 신고 처리
	public CommentDTO getCommentOne(int commentIndex) {
		return sqlSession.selectOne("Comment.getCommentOne", commentIndex);
	}

	public void processReportComment(int commentIndex, String commentDeleteReason) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("commentIndex", commentIndex);
		hash.put("commentDeleteReason", commentDeleteReason);
		sqlSession.update("Comment.processReportComment", hash);
	}
	
	// 마이 페이지
	public int findCommentLikeTotalByNickname(String nickname) {
		return sqlSession.selectOne("Comment.findCommentLikeTotalByNickname", nickname);
	}

	public List<ArticleDTO> getCommentLikeList(String nickname, int page) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("page", page);
		return sqlSession.selectList("Comment.getCommentLikeList", hash);
	}
}