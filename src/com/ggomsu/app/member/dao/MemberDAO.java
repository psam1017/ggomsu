package com.ggomsu.app.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.ggomsu.app.member.vo.MemberSnsVO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.mybatis.config.MyBatisConfig;

// 작성자 : 박성민, 손하늘, 이성호

public class MemberDAO {
	SqlSessionFactory sessionFactory = MyBatisConfig.getSqlSession_f();
	SqlSession sqlSession;
	
	// openSession(true) : 오토 커밋을 true로 설정.
	public MemberDAO() {
		sqlSession = sessionFactory.openSession(true);
	}
	
	// 회원가입
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
	
	public void insertAuthFailedEmail(String email) {
		sqlSession.insert("Member.insertAuthFailedEmail", email);
	}
	
	// 회원 정보 조회
	public MemberVO getMemberInfo(String email) {
		return sqlSession.selectOne("Member.getMemberInfo", email);
	}
	
	// 로그인
	public int updateSignAt(String email) {
		return sqlSession.update("Member.updateSignAt", email);
	}
	
	public boolean checkPasswordRenew(String email) {
		return (Integer.parseInt(sqlSession.selectOne("Member.checkPasswordRenew", email)) > 0);
	}
	
	public boolean checkTermExpired(String email) {
		return (Integer.parseInt(sqlSession.selectOne("Member.checkTermExpired", email)) > 0);
	}
	
	public void updatePasswordAlertAtByEmail(String email) {
		sqlSession.update("Member.updatePasswordAlertAtByEmail", email);
	}
	
	public void updateAgreedTermAtByEmail(String email) {
		sqlSession.update("Member.updateAgreedTermAtByEmail", email);
	}
	
	// 로그인 불가능 관련 -> HelpController 참고.
	public void restoreInvalid(String email) {
		sqlSession.update("Member.restoreInvalid", email);
	}
	
	public String findLostEmail(MemberVO vo) {
		return sqlSession.selectOne("Member.findLostEmail", vo);
	}
	
	// 마이페이지 관련
	public void updateProfile(MemberVO vo) {
		sqlSession.update("Member.updateProfile", vo);
	} 

	public void updatePersonal(MemberVO vo) {
		sqlSession.update("Member.updatePersonal", vo);
	} 
	
	public void updatePassword(MemberVO vo) {
		sqlSession.update("Member.updatePassword", vo);
	} 
	
	public void updateTerm(MemberVO vo) {
		sqlSession.update("Member.updateTerm", vo);
	} 
	
	public List<String> getBlindList(String nickname) {
		return sqlSession.selectList("Member.getBlindList", nickname);
	}
	
	public void insertBlindMember(String nickname, String blindMember) {
		Map<String, String> map = new HashMap<>();
		map.put("nickname", nickname);
		map.put("blindMember", blindMember);
		sqlSession.insert("Member.insertBlindMember", map);
	}
	
	public void deleteBlindMember(String nickname, List<String> deleteList) {
		Map<String, Object> map = new HashMap<>();
		map.put("nickname", nickname);
		map.put("deleteList", deleteList);
		sqlSession.delete("Member.deleteBlindMember", map);
	}

	public void updateConfig(MemberVO vo) {
		sqlSession.update("Member.updateConfig", vo);
	}
	
	public void withdraw(String email) {
		sqlSession.update("Member.withdraw", email);
	}
	
	public void updateDarkMode(String email, boolean darkModeFlag) {
		Map<String, Object> map = new HashMap<>();
		map.put("email", email);
		map.put("darkModeFlag", darkModeFlag);
		sqlSession.update("Member.updateDarkMode", map);
	}
	
	// 이성호 : admin, sns 관련
	public List<MemberVO> getAllMember() {
		return sqlSession.selectList("Member.getAllMember");
	}
	
	public void increaseAbuseCount(String nickname) {
		sqlSession.update("Member.increaseAbuseCount", nickname);
	}
	
	public void snsSignUp(MemberSnsVO vo) {
		sqlSession.insert("Member.snsSignUp", vo);
	}
}
