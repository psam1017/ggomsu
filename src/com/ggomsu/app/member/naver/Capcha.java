package com.ggomsu.app.member.naver;

import java.io.PrintWriter;
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

// 작성자 : 손하늘
public class Capcha implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		CapchaHelper capchaHelper = new CapchaHelper();
		
		String clientId = NaverInfo.getClientId();
		String clientSecret = NaverInfo.getClientSecret();
		String capchaKey = this.getKey(req, resp);
		
		if(capchaKey != null) {
			String apiURL = NaverInfo.CAPCHA_IMAGE_URL + "?key=" + capchaKey;
			
			Map<String, String> requestHeaders = new HashMap<>();
			requestHeaders.put("X-Naver-Client-Id", clientId);
			requestHeaders.put("X-Naver-Client-Secret", clientSecret);
			
			String fileDir = capchaHelper.getCapchaImage(req, apiURL, requestHeaders);
			
			json.put("status", "success");
			json.put("filename", fileDir);
		}
		else {
			json.put("status", "fail");
		}
		
		out.print(json.toJSONString());
		out.close();
		return null;
	}
	
	private String getKey(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
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
        
        String key = (String)obj.get("key");
        
        cookie = new Cookie("capchaKey", key);
        cookie.setMaxAge(60 * 5);
        resp.addCookie(cookie);
        
        return key;
	}
}
