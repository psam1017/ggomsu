package com.ggomsu.app.member.naver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.app.member.vo.MemberSnsVO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.naver.LoginHelper;
import com.ggomsu.system.naver.NaverInfo;

public class NaverConfirm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		ActionForward forward = new ActionForward();
		LoginHelper loginHelper = new LoginHelper();
		MemberVO memberVO = new MemberVO();
		MemberSnsVO memberSnsVO = new MemberSnsVO();
		
		String apiResult = null;
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		
		String apiUrl = NaverInfo.PROFILE_API_URL;
		String token = loginHelper.getAccessToken(session, code, state);
		String header = "Bearer " + token;
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Authorization", header);
		
		apiResult = loginHelper.get(apiUrl, requestHeaders);  //String형식의 json데이터
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(apiResult);
		JSONObject response_obj = (JSONObject) obj.get("response");

		String accessToken = (String) response_obj.get("id"); // DB에 저장해야 하는 accessToken
		String email = (String) response_obj.get("email");
		
		// key가 있다면
			// MEM 또는 ADM일 때 -> 통합회원으로 로그인
			// SNS일 때 -> sns회원으로 로그인
			// statusValue != null -> 휴면? 정지? 탈퇴?
		
		// key가 없다면
			// MEM 또는 ADM일 때 -> 통합하고 사용 가능
			// statusValue != null -> 휴면? 정지? 탈퇴?
			// statusValue == null -> 회원가입
		 
		memberVO.setEmail(email);
		memberSnsVO.setAccessToken(accessToken);
		
		forward.setForward(true);	
		forward.setPath("/member/naver-nickname");
		return forward;
	}

}
