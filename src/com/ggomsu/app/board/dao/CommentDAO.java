package com.ggomsu.app.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.board.vo.CommentVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;

// 작성자 : 김지혜
public class CommentDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public CommentDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public List<CommentVO> getCommentList(int articleIndex){
		return sqlSession.selectList("Comment.getCommentList", articleIndex);
	}
	
	public void insertComment(CommentVO commentVo) {
		sqlSession.insert("Comment.insertComment", commentVo);
	}
	
	public void insertRefComment(CommentVO commentVo) {
		sqlSession.insert("Comment.insertRefComment", commentVo);
	}
	
	public void deleteComment(CommentVO commentVo) {
		sqlSession.update("Comment.deleteComment", commentVo);
	}
	
	public int getCommentIndex() {
		return sqlSession.selectOne("Comment.getCommentIndex");
	}
}