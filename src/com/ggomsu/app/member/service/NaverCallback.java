package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.app.member.vo.MemberSnsVO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.github.scribejava.core.model.OAuth2AccessToken;

public class NaverCallback implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		ActionForward forward = new ActionForward();
		NaverLoginBO naverLoginBO = new NaverLoginBO();
		MemberVO mVo = new MemberVO();
		MemberSnsVO msVo = new MemberSnsVO();
		
		String apiResult = null;
		String code = req.getParameter("code"); // 단순 승인 코드
		String state = req.getParameter("state"); // 그냥 필요한 거
		
		OAuth2AccessToken oauthToken; // 네이버가 주는 개인정보를 모두 포함하는 암호화된 토큰
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		apiResult = naverLoginBO.getUserProfile(oauthToken);  //String형식의 json데이터
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		JSONObject response_obj = (JSONObject) jsonObj.get("response");

		// accessToken은 기간이 지나면 변할 수 있음? 확인 필요 -> 일단은 불변을 가정
		// accessToken은 불변을 가정한 상태에서
		// accessToken을 DB에서 조회해서 DB에 있다면 로그인으로 처리?
		// DB에 없다면 회원가입으로 처리?
		String accessToken = (String) response_obj.get("id"); // 이 토큰 자체가 개인에게 발급되는 snsKey, accessToken이다. 이걸 DB에 저장해야 한다.
		String name = (String) response_obj.get("nickname"); //이름
		String email = (String) response_obj.get("email");
		String gender = (String) response_obj.get("gender");
		String birthYear = (String) response_obj.get("birthyear");
		String birthDay = (String) response_obj.get("birthday");
		String birthDate = birthYear + "-" + birthDay;
		String mobile = (String) response_obj.get("mobile");
		mobile = mobile.replaceAll("-", "");
		 
		mVo.setName(name);
		mVo.setEmail(email);
		mVo.setContact(mobile);
		mVo.setBirthDate(birthDate);
		mVo.setSex(gender);
		mVo.setStatusValue("MEM");
		
		msVo.setAccessToken(accessToken);
		msVo.setEmail(email);

		
		// /member/sns/nickname으로 가려면 snsTemp가 session에 저장되어 있어야 한다.
		// SnsNickname.jsp에서 snsTemp가 null이면 405 error를 띄워야 한다.
		// /sns/confirm에서 처리가 완료되면 snsTemp를 remove
		
		req.setAttribute("memberVo", mVo);
		req.setAttribute("memberSnsVo", msVo);
		
		forward.setForward(true);	
		forward.setPath("/member/naver-nickname");
		return forward;
	}

}
