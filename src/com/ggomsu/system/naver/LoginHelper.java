package com.ggomsu.system.naver;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

// 작성자 : 이성호
public class LoginHelper extends NaverHelperImpl {

    /* 네이버 아이디로 인증  URL 생성  Method */
    public String getAuthorizationUrl(HttpSession session) {

        /* 세션 유효성 검증을 위하여 난수를 생성 */
        String state = UUID.randomUUID().toString();;
        
        /* 생성한 난수 값을 session에 저장 */
        session.setAttribute(NaverInfo.SESSION_STATE, state);        

        String apiURL = NaverInfo.AUTHORIZATION_BASE_URL
        		+ "?response_type=code"
        		+ "&client_id=" + NaverInfo.getClientId()
        		+ "&redirect_uri=" + NaverInfo.LOGIN_REDIRECT_URI
        		+ "&state=" + state;

        return apiURL;
    }
    
    /* 네이버아이디로 Callback 처리 및  AccessToken 획득 Method */
    public String getAccessToken(HttpSession session, String code, String state) throws Exception{

        String apiURL = null;
        apiURL = NaverInfo.ACCESS_TOKEN_END_POINT
        			+ "?grant_type=authorization_code"
					+ "&client_id=" + NaverInfo.getClientId()
					+ "&client_secret=" + NaverInfo.getClientSecret()
					+ "&redirect_uri=" + NaverInfo.LOGIN_REDIRECT_URI
					+ "&code=" + code
					+ "&state=" + state;
        
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        String responseBody;
        
        if(responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
        	responseBody = readBody(con.getInputStream());
        }
        else {  // 에러 발생
        	responseBody = readBody(con.getErrorStream());
        }
        
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(responseBody);
        
    	return (String) json.get("access_token");
    }
}
