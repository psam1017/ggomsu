package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.vo.MemberSnsVO;
import com.ggomsu.app.member.vo.MemberVO;
import com.github.scribejava.core.model.OAuth2AccessToken;

public class NaverLoginCallback implements Action{

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
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		
		OAuth2AccessToken oauthToken;
		oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		apiResult = naverLoginBO.getUserProfile(oauthToken);  //String형식의 json데이터
		
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
		JSONObject response_obj = (JSONObject) jsonObj.get("response");

		String accessToken = (String) response_obj.get("id");
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

		req.setAttribute("memberVo", mVo);
		req.setAttribute("memberSnsVo", msVo);
		
		forward.setForward(true);	
		forward.setPath("/member/naver-nickname");
		return forward;
	}

}
