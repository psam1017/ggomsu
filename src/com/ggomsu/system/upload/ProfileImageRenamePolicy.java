package com.ggomsu.system.upload;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.oreilly.servlet.multipart.FileRenamePolicy;

// 작성자 : 박성민, 손하늘
// 박성민 : 손하늘 님 작업 결과물을 policy로 분리시켰습니다.
public class ProfileImageRenamePolicy implements FileRenamePolicy {
	
	private String fileRoot;
	private String oldNickname;
	private String newNickname;
	
	public ProfileImageRenamePolicy(String fileRoot, String oldNickname, String newNickname) {
		this.fileRoot = fileRoot;
		this.oldNickname = oldNickname;
		this.newNickname = newNickname;
	}

	@Override
	public File rename(File f) {
		
		this.deleteFileByNickname(f);
		
		String name = f.getName();
		String body = this.newNickname; // 파일 이름
		String ext = null; // 확장자
		
		int dot = name.lastIndexOf(".");
		if (dot != -1) {
			ext = name.substring(dot);  // includes "."
		}
		else {
		  ext = "";
		}
		
		// new nickname을 파일명으로
		String newName = body + ext;
		f = new File(f.getParent(), newName);
		
		// 만약 실패하는 경우 차선책으로 UUID를 생성
		while(!this.createNewFile(f)) {
			newName = body + "_" + String.valueOf(UUID.randomUUID()) + ext;
			f = new File(f.getParent(), newName);
		}

		return f;
	}
	
	private void deleteFileByNickname(File f) {
		try {
			// 예전 닉네임의 파일이 있다면 먼저 삭제
			File path = new File(this.fileRoot);
			File[] fileList = path.listFiles();
			
			if(fileList.length > 0) {
				for(File file : fileList) {
					String name = file.getName();
					int dot = name.lastIndexOf(".");
					String body = null;
					
					if(dot != -1) {
						body = name.substring(0, dot);
					}
					else {
						body = name;
					}
					
					if(body.equals(this.oldNickname)) {
						file.delete();
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
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
