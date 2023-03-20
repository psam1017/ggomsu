package com.ggomsu.system.naver;

import java.util.Map;

public interface NaverHelper {

	public String get(String apiUrl, Map<String, String> requestHeaders) throws Exception;
}
