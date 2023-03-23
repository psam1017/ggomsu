package com.ggomsu.system.naver;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Map;

public interface NaverHelper {

	public String get(String apiUrl, Map<String, String> requestHeaders) throws Exception;
	public HttpURLConnection connect(String apiUrl) throws Exception;
	public String readBody(InputStream body);
}
