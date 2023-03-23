package com.ggomsu.system.naver;

public class NaverInfo {
	
	private static String clientId;
	private static String clientSecret;
	
	public final static String SESSION_STATE = "oauth_state";
    public final static String LOGIN_REDIRECT_URI = "http://localhost:8087/member/naver/confirm";
    public final static String AUTHORIZATION_BASE_URL = "https://nid.naver.com/oauth2.0/authorize";
    public final static String ACCESS_TOKEN_END_POINT = "https://nid.naver.com/oauth2.0/token";
    public final static String PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
    
    public final static String CAPCHA_API_URL = "https://openapi.naver.com/v1/captcha/nkey";
    public final static String CAPCHA_IMAGE_URL = "https://openapi.naver.com/v1/captcha/ncaptcha.bin";
    public final static String SHORTEN_API_URL = "https://openapi.naver.com/v1/util/shorturl";
	
	public NaverInfo(String clientId, String clientSecret) {
		NaverInfo.clientId = clientId;
		NaverInfo.clientSecret = clientSecret;
	}
	public static String getClientId() {
		return clientId;
	}
	public static String getClientSecret() {
		return clientSecret;
	}
}
