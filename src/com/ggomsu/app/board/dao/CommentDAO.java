package com.ggomsu.app.board.dao;

import java.util.List;
import java.util.Map;
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
	
	public List<CommentDTO> getCommentList(int articleIndex, List<String> blindList, String nickname){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleIndex", articleIndex);
		map.put("blindList", blindList);
		map.put("nickname", nickname);
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

	public CommentDTO getCommentOne(int commentIndex) {
		return sqlSession.selectOne("Comment.getCommentOne", commentIndex);
	}

	// 관리자 신고 처리
	public void confirmCommentDelete(int commentIndex, String commentDeleteReason) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commentIndex", commentIndex);
		map.put("commentDeleteReason", commentDeleteReason);
		sqlSession.update("Comment.confirmCommentDelete", map);
	}
	
	// 마이 페이지
	public int findMyCommentLikeTotal(String nickname) {
		return sqlSession.selectOne("Comment.findMyCommentLikeTotal", nickname);
	}

	public List<ArticleDTO> getCommentLikeList(String nickname, int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickname", nickname);
		map.put("page", page);
		return sqlSession.selectList("Comment.getCommentLikeList", map);
	}
	
	public int findMyCommentHistoryTotal(String nickname) {
		return sqlSession.selectOne("Comment.findMyCommentHistoryTotal", nickname);
	}
	
	public List<ArticleDTO> getCommentHistoryList(String nickname, int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickname", nickname);
		map.put("page", page);
		return sqlSession.selectList("Comment.getCommentHistoryList", map);
	}
	
	public boolean checkLiked(String nickname, int commentIndex){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickname", nickname);
		map.put("commentIndex", commentIndex);
		return (Integer)(sqlSession.selectOne("Comment.checkLiked", map)) == 1;
	}

	public void doLike(String nickname, int commentIndex){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickname", nickname);
		map.put("commentIndex", commentIndex);
		sqlSession.update("Comment.doLike", map);
	}

	public void cancelLike(String nickname, int commentIndex){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickname", nickname);
		map.put("commentIndex", commentIndex);
		sqlSession.update("Comment.cancelLike", map);
	}
}