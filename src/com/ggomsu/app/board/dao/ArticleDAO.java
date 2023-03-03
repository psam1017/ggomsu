package com.ggomsu.app.board.dao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.board.vo.ArticleDTO;
import com.ggomsu.system.mybatis.config.MyBatisConfig;

// 작성자 : 이성호, 박성민
public class ArticleDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public ArticleDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public int findTotal(String boardValue, List<String> blindList, String search, String category, String period) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardValue", boardValue);
		map.put("blindList", blindList);
		map.put("search", search);
		map.put("category", category);
		map.put("period", period);
		return sqlSession.selectOne("Article.findTotal", map);
	}
	
	public List<ArticleDTO> findList(String boardValue, List<String> blindList, String search, String category, String period, String criteria, int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("boardValue", boardValue);
		map.put("blindList", blindList);
		map.put("search", search);
		map.put("category", category);
		map.put("period", period);
		map.put("criteria", criteria);
		map.put("page", page);
		return sqlSession.selectList("Article.findList", map);
	}
	
	// 게시글 조회, 조회수 상승, 좋아요 여부, 좋아요 표시 또는 취소
	public ArticleDTO findArticle(int articleIndex) {
		return sqlSession.selectOne("Article.findArticle", articleIndex);
	}
	
	public void updateArticleViewCount(int articleIndex) {
		sqlSession.update("Article.updateArticleViewCount",articleIndex);
	}
	
	public boolean checkLiked(String nickname, int articleIndex){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickname", nickname);
		map.put("articleIndex", articleIndex);
		return (Integer)(sqlSession.selectOne("Article.checkLiked", map)) == 1;
	}

	public void doLike(String nickname, int articleIndex){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("articleIndex", articleIndex);
		sqlSession.update("Article.doLike",hash);
	}

	public void cancelLike(String nickname, int articleIndex){
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("articleIndex", articleIndex);
		sqlSession.update("Article.cancelLike",hash);
	}
	
	// 게시글 작성, 수정, 삭제
	public int doInsertArticleProcedure(ArticleDTO articleDTO) {
		return sqlSession.selectOne("Article.doInsertArticleProcedure", articleDTO);
	}
	
	public void updateArticle(ArticleDTO articleDTO) {
		sqlSession.update("Article.updateArticle", articleDTO);
	}
	
	public void deleteArticle(int articleIndex) {
		sqlSession.delete("Article.deleteArticle", articleIndex);
	}
	
	// 신고 받은 게시글을 삭제 처리하고 사유를 명시
	public void confirmArticleDelete(int articleIndex, String articleDeleteReason) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("articleIndex", articleIndex);
		map.put("articleDeleteReason", articleDeleteReason);
		sqlSession.update("Article.confirmArticleDelete", map);
	}

	// 마이 페이지 게시글 좋아요 목록
	public int findMyArticleLikeTotal(String nickname) {
		return sqlSession.selectOne("Article.findMyArticleLikeTotal", nickname);
	}

	public List<ArticleDTO> getArticleLikeList(String nickname, int page) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		hash.put("page", page);
		return sqlSession.selectList("Article.getLikeList", hash);
	}
	
	// index 좋아요, 조회수 베스트
	public List<ArticleDTO> findWeeklyMostLikedList(String boardValue) {
		return sqlSession.selectList("Article.findWeeklyMostLikedList", boardValue);
	}
	
	public List<ArticleDTO> findWeeklyMostViewedList(String boardValue) {
		return sqlSession.selectList("Article.findWeeklyMostViewedList", boardValue);
	}
}
