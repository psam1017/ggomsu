package com.ggomsu.app.my.service;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.upload.ProfileImageRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

//작성자 : 손하늘, 박성민
public class ProfileConfirm implements Action{
	
	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		ActionForward forward = new ActionForward();
		
		// 자바 객체 생성
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		HttpSession session = req.getSession();
		
		// parameter 저장
		String email = (String) session.getAttribute("email");
		String oldNickname = (String) session.getAttribute("nickname");
		String profileImageUrl = (String) session.getAttribute("profileImageUrl");
		String statusValue = (String) session.getAttribute("statusValue");
		String newNickname = req.getParameter("nickname");
		String isChangeProfileImage = req.getParameter("isChangeProfileImage");
		String statusURI = null;
		
		if(statusValue.equals("MEM") || statusValue.equals("SNS")) {
			statusURI = "my";
		}
		else if (statusValue.equals("ADM")) {
			statusURI = "admin";
		}
		
		// 잘못된 접근일 때
		if(!req.getMethod().equals("POST") || newNickname == null) {
			forward.setPath(req.getContextPath() + "/" + statusURI + "/profile?code=error");
			forward.setForward(false);
			return forward;
		}
		
		// 전송 받은 닉네임이 이전 닉네임과 다르면서 동시에 기존에 닉네임이 존재한다면...
		if(!newNickname.equals(session.getAttribute("nickname")) && dao.checkNickname(newNickname)) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/" + statusURI + "/profile?code=duplicate");
			return forward;
		}
		
		// multipart config
		// 프로필 이미지 크기는 25KB 이하 -> javascript에서 제어
		String category = "profile";
		String contextRoot = req.getServletContext().getRealPath("/");
		String fileRoot = contextRoot + "/uploads/" + category + "/";
		int fileSize  = 1024 * 25 + 1;
		String encoding = "UTF-8";
		FileRenamePolicy policy = new ProfileImageRenamePolicy(fileRoot, oldNickname, newNickname);
		
		// file 저장 경로 자동 생성
		File file = new File(fileRoot);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		// sql에 필요한 정보를 vo에 저장
		vo.setEmail(email);
		vo.setNickname(newNickname);
		session.setAttribute("nickname", newNickname);
		
		// 프로필 이미지를 삭제한 경우
		if(isChangeProfileImage.equals("delete")) {
			vo.setProfileImageUrl(null);
			session.setAttribute("profileImageUrl", null);
			
			File path = new File(fileRoot);
			File[] fileList = path.listFiles();
			
			if(fileList.length > 0) {
				for(File f : fileList) {
					String name = f.getName();
					int dot = name.lastIndexOf(".");
					String body = null;
					
					if(dot != -1) {
						body = name.substring(0, dot);
					}
					else {
						body = name;
					}
					
					if(body.equals(oldNickname)) {
						f.delete();
					}
				}
			}
		}
		// 새로운 이미지가 있다면
		else if(isChangeProfileImage.equals("new")) {
			try {
				MultipartRequest multi = new MultipartRequest(req, fileRoot, fileSize, encoding, policy);
				
				Enumeration<String> upload = multi.getFileNames();
				
				if(upload.hasMoreElements()) {
					String name = upload.nextElement();
					String systemName = multi.getFilesystemName(name);
					if(systemName != null) {
						profileImageUrl = "/uploads/" + category + "/" + systemName;
						vo.setProfileImageUrl(profileImageUrl);
						session.setAttribute("profileImageUrl", profileImageUrl);
					}
				}
			} catch (IOException e) {
				forward.setPath(req.getContextPath() + "/" + statusURI + "/profile?code=big-size");
				forward.setForward(false);
				
				return forward;
			}
			
		}
		// 변경이 없다면 session의 value를 저장
		else {
			vo.setProfileImageUrl(profileImageUrl);
		}
		
		// 닉네임과 프로필 이미지를 모두 vo에 저장한 이후 dao로 업데이트
		dao.updateProfile(vo);
		
		forward.setPath(req.getContextPath() + "/" + statusURI + "/profile?code=success");
		forward.setForward(false);
		
		return forward;
	}
}
