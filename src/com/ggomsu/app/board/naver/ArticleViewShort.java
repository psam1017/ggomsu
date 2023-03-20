package com.ggomsu.app.board.naver;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.naver.NaverHelperImpl;
import com.ggomsu.system.naver.NaverInfo;

public class ArticleViewShort implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		NaverHelperImpl helperImpl = new NaverHelperImpl();
		PrintWriter out = resp.getWriter();
		
		String clientId = NaverInfo.getClientId(); 
		String clientSecret = NaverInfo.getClientSecret(); 
		
		String originalURL = req.getParameter("originalUrl");
		String apiURL = NaverInfo.SHORTEN_API_URL + "?url=" + originalURL;
		
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = helperImpl.get(apiURL,requestHeaders);
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(responseBody);
		
		out.print(obj.toJSONString());
		out.close();
		
		return null;
	}
}
