package com.ggomsu.app.board.dao;

import java.util.HashMap;
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
	
	public List<CommentVO> getCommentList(int articleIndex, String blockedList){
		HashMap<String, Object> hash = new HashMap<>();
		hash.put("articleIndex", articleIndex);
		hash.put("blockedList", blockedList);
		return sqlSession.selectList("Comment.getCommentList", hash);
	}
	
	public void insertComment(CommentVO commentVo) {
		sqlSession.update("Comment.insertComment", commentVo);
	}
	
	public void deleteComment() {
		
	}
}