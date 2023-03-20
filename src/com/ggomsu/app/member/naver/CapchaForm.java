package com.ggomsu.app.member.naver;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.naver.CapchaHelper;
import com.ggomsu.system.naver.NaverInfo;

// 작성자 : 손하늘
public class CapchaForm implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		CapchaHelper capchaHelper = new CapchaHelper();
		
		String clientId = NaverInfo.getClientId();
		String clientSecret = NaverInfo.getClientSecret();

		String capchaKey = null;
		String apiURL = NaverInfo.CAPCHA_API_URL + "?key=" + capchaKey;
	  
		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		
		String fileDir = capchaHelper.getCapchaImage(req, apiURL, requestHeaders);
		
		json.put("filename", fileDir);
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
