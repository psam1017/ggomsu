package com.ggomsu.app.member.naver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.naver.CapchaHelper;
import com.ggomsu.system.naver.NaverInfo;

//작성자 : 손하늘
public class CapchaAuth implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		CapchaHelper capchaHelper = new CapchaHelper();
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		
		String clientId = NaverInfo.getClientId();
        String clientSecret = NaverInfo.getClientSecret();
        String capchaKey = null;
        String value = req.getParameter("capchaAuth"); // 사용자가 입력한 캡차 이미지 글자값
    	
        if(cookies != null && cookies.length > 0) {
        	for(Cookie c : cookies) {
        		if(c.getName().equals("capchaKey")) {
        			capchaKey = c.getValue();
        		}
        	}
        }
        
        if(capchaKey != null && value != null) {
        	String apiURL = NaverInfo.CAPCHA_API_URL + "?code=1" + "&key=" + capchaKey + "&value=" + value;
        	
        	Map<String, String> requestHeaders = new HashMap<>();
        	requestHeaders.put("X-Naver-Client-Id", clientId);
        	requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        	String responseBody = capchaHelper.get(apiURL, requestHeaders);
        	JSONParser parser = new JSONParser();
        	JSONObject obj = (JSONObject)parser.parse(responseBody);
        	
        	boolean result = (boolean) obj.get("result");
        	
        	// 임시 세션 발급
        	if(result) {
        		String articleRedirect = (String) session.getAttribute("articleRedirect");
    			
    			session.setAttribute("statusValue", "TMP");
				session.setAttribute("email", "noname@ggomsu.com");
				session.setAttribute("nickname", "noname");
				session.setAttribute("profileImageUrl", null);
				
				// 이전에 보던 페이지가 있는가?
				if(articleRedirect != null) {
					forward.setPath(req.getContextPath() + "/member/sign-in/board");
				}
				else {
					forward.setPath(req.getContextPath() + "/main");
				}
        	}
        	else {
        		forward.setPath(req.getContextPath() + "/member/sign-in?code=fail");
        	}
        }
        else {
        	forward.setPath(req.getContextPath() + "/member/sign-in?code=fail");
        }
        forward.setForward(false);
        
		return forward;
        	
	}
}