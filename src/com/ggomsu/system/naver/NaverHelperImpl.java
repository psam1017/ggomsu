package com.ggomsu.system.naver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class NaverHelperImpl implements NaverHelper {

	@Override
	public String get(String apiUrl, Map<String, String> requestHeaders) throws Exception{
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			if(requestHeaders != null && requestHeaders.size() > 0) {
				for(Map.Entry<String, String> header : requestHeaders.entrySet()) {
					con.setRequestProperty(header.getKey(), header.getValue());
				}
			}
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상
				return readBody(con.getInputStream());
			} else { // 실패
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}
	
	@Override
	public HttpURLConnection connect(String apiUrl) throws Exception{
		URL url = new URL(apiUrl);
		return (HttpURLConnection)url.openConnection();
	}
	
	@Override
	public String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);
		
		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();
			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}
			return responseBody.toString();
		}
		catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}
}
