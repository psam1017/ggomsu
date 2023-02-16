package com.ggomsu.app.member.service;

import java.io.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.app.member.vo.SimpleEncInfoVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

//작성자 : 손하늘

public class MemberUpdataMyInfoOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		int maxSize  = 1024*1024*10;		
		String root = req.getSession().getServletContext().getRealPath("/");
		System.out.println("root : " + root);
		String savePath = root + "/app/" + "upload/" + "profile/";
		
		//파일이 존재하지않다면 파일 생성
//		File f = new File(savePath);
//		if(!f.exists()) {
//			f.mkdirs();
//		}
		
		// 업로드 파일명
		String uploadFile = "";
		// 실제 저장할 파일명
		String newFileName = "";
		int read = 0;
		byte[] buf = new byte[1024];
		FileInputStream fin = null;
		FileOutputStream fout = null;
//		long currentTime = System.currentTimeMillis();  
//		SimpleDateFormat simDf = new SimpleDateFormat("yyyyMMddHHmmss");  
		
		MultipartRequest multi = new MultipartRequest(req, savePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		
		String nickName = (String)session.getAttribute("nickname");
		
		try{
			// 파일업로드
			uploadFile = multi.getFilesystemName("profileImageUrl");
			//실제 저장할 파일명  ex) userNickname.zip 
			newFileName =  nickName +"."+ uploadFile.substring(uploadFile.lastIndexOf(".")+1);
			File oldFile = new File(savePath + uploadFile);
			File newFile = new File(savePath + newFileName);
			// 파일명 rename
			if(!oldFile.renameTo(newFile)){
				buf = new byte[1024];
//				fin = new FileInputStream(oldFile);
				fin = new FileInputStream(newFile);
				fout = new FileOutputStream(newFile);
				read = 0;
				while((read=fin.read(buf,0,buf.length))!=-1){
					fout.write(buf, 0, read);
				}
				fin.close();
				fout.close();
				oldFile.delete();
			}   

		}catch(Exception e){
			e.printStackTrace();
		}
		
		String email = (String)session.getAttribute("email");
		
		vo.setEmail(email);
		vo.setProfileImageUrl(savePath + newFileName);
		System.out.println("파일 경로 : " + savePath + newFileName);
		vo.setNickname(multi.getParameter("nickname"));
		vo.setTelecomValue(multi.getParameter("telecomValue"));
		vo.setContact(multi.getParameter("contact"));
		vo.setZipcode(multi.getParameter("zipcode"));
		vo.setAddress(multi.getParameter("address"));
		vo.setAddressDetail(multi.getParameter("addressDetail"));
		
		//dao.updataMemberMyInfo(vo);
		
		forward.setForward(true);
		forward.setPath("/member/member-view-my-info-ok");
		
		return forward;
	}
}
