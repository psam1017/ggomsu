package com.ggomsu.system.naver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

// 작성자: 손하늘
public class CapchaHelper extends NaverHelperImpl {

	public String getCapchaImage(HttpServletRequest req, String apiUrl, Map<String, String> requestHeaders) throws Exception{
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}
			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상
				return makeCapchaImage(req, con.getInputStream());
			}
			else { // 실패
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}
	
	private static String makeCapchaImage(HttpServletRequest req, InputStream is) throws Exception{
		int read;
		byte[] bytes = new byte[1024];
		
		// 캡차 이미지가 저장될 디렉토리의 경로
		String category = "capcha";
		String contextRoot = req.getServletContext().getRealPath("/");
		String fileRoot = contextRoot + "\\uploads\\" + category + "\\";
		
		File dir = new File(fileRoot);
		if (!dir.exists() ) {
			dir.mkdirs();
		}
		  
		// 랜덤한 이름으로  파일 생성
		String filename = String.valueOf(UUID.randomUUID()) + ".jpg";
		
		// 선언한 경로와 이름으로 파일생성
		File f = new File(fileRoot, filename);
		
		try(OutputStream outputStream = new FileOutputStream(f)){
			f.createNewFile();
			while ((read = is.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			return "/uploads/" + category + "/" + filename;
		} catch (IOException e) {
			throw new RuntimeException("이미지 캡차 파일 생성에 실패 했습니다.", e);
		}
	}
}
