package com.ggomsu.app.member.naver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.naver.CapchaHelper;
import com.ggomsu.system.naver.NaverInfo;

public class Capcha implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		CapchaHelper capchaHelper = new CapchaHelper();
		Cookie cookie = null;
		
		String clientId = NaverInfo.getClientId();
        String clientSecret = NaverInfo.getClientSecret();

        String apiURL = NaverInfo.CAPCHA_API_URL + "?code=0";

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = capchaHelper.get(apiURL, requestHeaders);

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject)parser.parse(responseBody);
        
        cookie = new Cookie("capchaKey", (String)obj.get("capchaKey"));
        cookie.setMaxAge(60 * 5);
        resp.addCookie(cookie);
        
    	forward.setForward(true);
		forward.setPath("/member/capcha/form");
		return forward;
        	
	}
}