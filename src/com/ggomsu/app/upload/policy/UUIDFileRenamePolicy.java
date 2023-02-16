package com.ggomsu.app.upload.policy;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// 작성자 : 박성민
public class UUIDFileRenamePolicy implements FileRenamePolicy {

	// 아래 웹사이트에서 DefaultFileRenamePolicy 소스코드를 참고하여 작성
	// https://jar-download.com/artifacts/com.jfinal/cos/2020.4/source-code/com/oreilly/servlet/multipart/DefaultFileRenamePolicy.java
	
	@Override
	public File rename(File f) {
		
		String name = f.getName();
		String body = null; // 파일 이름
		String ext = null; // 확장자
		
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			body = name.substring(0, dot);
			ext = name.substring(dot);  // includes "."
		}
		else {
		  body = name;
		  ext = "";
		}
		
		do {
			String newName = body + "_" + String.valueOf(UUID.randomUUID()) + ext;
			f = new File(f.getParent(), newName);
		} while(!this.createNewFile(f));
		
		return f;
	}
	
	private boolean createNewFile(File f) {
		try {
		  return f.createNewFile();
		}
		catch (IOException ignored) {
		  return false;
		}
	}
}
