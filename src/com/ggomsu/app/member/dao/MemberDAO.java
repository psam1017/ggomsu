package com.ggomsu.app.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.member.vo.MemberSnsVO;
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
	
	public MemberVO getMemberInfo(String email) {
		return sqlSession.selectOne("Member.getMemberInfo", email);
	}
	
	public boolean withdrawal(MemberVO statusValue) {
		return (sqlSession.update("Member.withdrawal", statusValue)) == 1;
	}

	public List<String> getBlockedList(String nickname) {
		return sqlSession.selectList("Member.blockView", nickname);
	}
	
	public void updateAbuseCount(String nickname) {
		sqlSession.update("Member.updateAbuseCount", nickname);
	}
	
	public List<MemberVO> getAllMember() {
		return sqlSession.selectList("Member.getAllMember");
	}
	
	// 네이버
	public void snsSignUp(MemberSnsVO vo) {
		sqlSession.insert("Member.snsSignUp", vo);
	}
}
