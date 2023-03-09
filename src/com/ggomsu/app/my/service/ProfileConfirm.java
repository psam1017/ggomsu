package com.ggomsu.app.my.service;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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
		
		if(!req.getMethod().equals("POST")) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/my/profile?code=error");
			
			return forward;
		}
		
		// 자바 객체 생성
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		HttpSession session = req.getSession();
		
		// parameter 저장
		String email = (String) session.getAttribute("email");
		String oldNickname = (String) session.getAttribute("nickname");
		String newNickname = req.getParameter("nickname");
		String statusValue = (String) session.getAttribute("statusValue");
		Part profile = req.getPart("profileImageUrl");
		
		// multipart config
		// 프로필 이미지 크기는 160 * 160 pixel 및 25KB 이하
		String category = "profile";
		String contextRoot = req.getServletContext().getRealPath("/");
		String fileRoot = contextRoot + "\\uploads\\" + category + "\\";
		int fileSize  = 160 * 160 * 3 + 1;
		String encoding = "UTF-8";
		FileRenamePolicy policy = new ProfileImageRenamePolicy(fileRoot, oldNickname, newNickname);
		
		// profile image의 크기 검사
		if(profile != null && profile.getSize() >= fileSize) {
			forward.setForward(false);
			if(statusValue.equals("MEM")) {
				forward.setPath(req.getContextPath() + "/my/profile?code=big-size");
			}
			else if(statusValue.equals("ADM")) {
				forward.setPath(req.getContextPath() + "/admin/profile?code=big-size");
			}
			return forward;
		}
		
		// file 저장 경로 자동 생성
		File file = new File(fileRoot);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		vo.setProfileImageUrl(null);
		
		// 업로드한 파일이 있다면...
		if(profile != null) {
			// 파일업로드
			MultipartRequest multi = new MultipartRequest(req, fileRoot, fileSize, encoding, policy);
			
			Enumeration<String> upload = multi.getFileNames();
			
			if(upload.hasMoreElements()) {
				String name = upload.nextElement();
				String systemName = multi.getFilesystemName(name);
				if(systemName != null) {
					String profileImageUrl = "/uploads/" + category + "/" + systemName;
					vo.setProfileImageUrl(profileImageUrl);
					session.setAttribute("profileImageUrl", profileImageUrl);
				}
			}
		}
		
		vo.setNickname(newNickname);
		vo.setEmail(email);
		
		dao.updateProfile(vo);
		
		forward.setForward(false);
		if(statusValue.equals("MEM")) {
			forward.setPath(req.getContextPath() + "/my/profile?code=success");
		}
		else if(statusValue.equals("ADM")) {
			forward.setPath(req.getContextPath() + "/admin/profile?code=success");
		}
		
		return forward;
	}
}
