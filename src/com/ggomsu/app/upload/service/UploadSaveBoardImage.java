package com.ggomsu.app.upload.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.upload.UUIDFileRenamePolicy;
import com.ggomsu.system.upload.UploadHelper;
import com.oreilly.servlet.MultipartRequest;

// 작성자 : 박성민
public class UploadSaveBoardImage implements Action {

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// cos, json-simple jar 필요
		JSONObject json = new JSONObject();
		UploadHelper uploadHelper = new UploadHelper();
		
		// multipart config
		// 필요 시 category만 변경
		String category = "board";
		String contextRoot = req.getSession().getServletContext().getRealPath("/");
		String fileRoot = contextRoot + "/uploads/" + category + "/";
		int fileSize = 1024 * 1024 * 5;
		String encoding = "UTF-8";
		
		// file 저장 경로 자동 생성
		File file = new File(fileRoot);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		uploadHelper.deleteOldFile(file);
		
		// 파일 저장. 파일 이름 정책은 com.ggomsu.app.upload.policy 참고
		MultipartRequest multi = new MultipartRequest(req, fileRoot, fileSize, encoding, new UUIDFileRenamePolicy());
		
		Enumeration<String> upload = multi.getFileNames();
		
		// 파일 n개에 대하여 js에서 n번의 ajax 요청을 수행하므로 한 번의 요청에 하나의 파일만 전송됨.
		if(upload.hasMoreElements()) {
			// 사용자가 업로드한 파일 태그의 name
			String name = upload.nextElement();
			
			// 자동으로 변경하여 저장하는 이름
			String systemName = multi.getFilesystemName(name);
			
			// 저장한 파일의 경로는 content 영역에 img 태그로 저장되므로 DB 저장이 필요 없음.
			// 대신 content의 img 태그에 src에 정확하게 이름을 적어줘야 함.
			if(systemName != null) {
				json.put("url", "/uploads/" + category + "/" + systemName);
				json.put("responseCode", "success");
			}
			else {
				json.put("responseCode", "error");
			}
		}
		else {
			json.put("responseCode", "error");
		}
		
		PrintWriter out = resp.getWriter();
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}

}
