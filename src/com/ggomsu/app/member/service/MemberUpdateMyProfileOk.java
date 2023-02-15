package com.ggomsu.app.member.service;

import java.io.*;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.oreilly.servlet.MultipartRequest;

//작성자 : 손하늘

public class MemberUpdateMyProfileOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		// 파일저장 경로	(웹서버 경로)
		String root = req.getServletContext().getRealPath("/");
		String savePath = root + "/app/" + "upload/" + "profile/";
		int maxSize  = 1024*1024*10;	
		
		// 업로드할 파일명
		String uploadFile = "";
		// 저장할 파일명
		String newFileName = "";
		
		int read = 0;
		byte[] buf = new byte[1024];
		FileInputStream fin = null;
		FileOutputStream fout = null;
		
		try{
			MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, "UTF-8");
			vo.setNickname(multi.getParameter("nickname"));
			// 파일업로드
			uploadFile = multi.getFilesystemName("profileImageUrl");
			String nickName = vo.getNickname();
			//실제 저장할 파일명 : 중복시 덮어쓰기, 프로필이미지 명(닉네임.확장자), ex) userNickname.zip 
			newFileName =  nickName + "."+ uploadFile.substring(uploadFile.lastIndexOf(".")+1);
			
			File oldFile = new File(savePath + uploadFile);
			File newFile = new File(savePath + newFileName);
			
			// 파일명 rename
			if(!oldFile.renameTo(newFile)){
				// rename이 되지 않을경우 강제로 파일을 복사하고 기존파일은 삭제
				buf = new byte[1024];
				fin = new FileInputStream(oldFile);
				fout = new FileOutputStream(newFile);
				read = 0;
				while((read=fin.read(buf,0,buf.length))!=-1){
					fout.write(buf, 0, read);
				}
				
				fin.close();
				fout.close();
				oldFile.delete();
			}   
			vo.setProfileImageUrl(savePath + newFileName);
			System.out.println("파일 경로 : " + savePath + newFileName);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String email = (String)session.getAttribute("email");
		vo.setEmail(email);
		
		dao.updateMemberMyProfile(vo);
		
		forward.setForward(true);
		forward.setPath("/member/member-view-my-info-ok");
		
		return forward;
	}
}
