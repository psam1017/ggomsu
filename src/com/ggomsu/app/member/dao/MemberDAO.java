package com.ggomsu.app.member.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.app.mybatis.config.MyBatisConfig;

// 작성자 : 박성민, 손하늘

public class MemberDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public MemberDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	public boolean checkEmail(String email) {
		return (Integer)(sqlSession.selectOne("Member.checkEmail", email)) == 1;
	}
	
	public boolean checkNickname(String nickname) {
		return (Integer)(sqlSession.selectOne("Member.checkNickname", nickname)) == 1;
	}
	
	public boolean checkContact(String contact) {
		return (Integer)(sqlSession.selectOne("Member.checkContact", contact)) == 1;
	}
	
	public void signUp(MemberVO vo) {
		sqlSession.insert("Member.signUp", vo);
	}
	
	public void updateAgreedMarketingAt(String email) {
		sqlSession.update("Member.updateAgreedMarketingAt", email);
	}
	
	public MemberVO getMemberInfo(String email) {
		return sqlSession.selectOne("Member.getMemberInfo", email);
	}
	
	public int signAt(String email) {
		return sqlSession.update("Member.signAt", email);
	}
	
	public int updateWakeUp(String email) {
		return sqlSession.update("Member.updateWakeUp", email);
	}
	
	public MemberVO findId(MemberVO vo) {
		return sqlSession.selectOne("Member.findId", vo);
	}
	
	public void updateMemberPassword(MemberVO vo) {
		sqlSession.update("Member.updateMemberPassword", vo);
	} 
	
	public void updateMemberMyProfile(MemberVO vo) {
		sqlSession.update("Member.updateMemberMyProfile", vo);
	} 
	
	public void updateMemberMyNickname(MemberVO vo) {
		sqlSession.update("Member.updateMemberMyNickname", vo);
	} 
	
	public void updataMemberMyPrivacy(MemberVO vo) {
		sqlSession.update("Member.updataMemberMyPrivacy", vo);
	} 
	
	public void updateTerm(String email) {
		sqlSession.update("Member.updateTerm", email);
	} 
	
	public boolean withdrawal(MemberVO statusValue) {
		return (sqlSession.update("Member.withdrawal", statusValue)) == 1;
	}
	
	public void updateBlock(MemberVO vo) {
		sqlSession.insert("Member.updateBlock", vo);
	}
	
	public void deleteBlock(MemberVO vo) {
		sqlSession.delete("Member.deleteBlock", vo);
	}

	public List<MemberVO> viewBlock(String nickname) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		hash.put("nickname", nickname);
		return sqlSession.selectList("Member.viewBlock", hash);
	}
	
	public void updateAbuseCount(String nickname) {
		sqlSession.update("Member.updateAbuseCount", nickname);
	}
	
}
