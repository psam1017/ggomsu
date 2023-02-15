package com.ggomsu.app.member.service;

import java.io.*;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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

public class MemberUpdateMyPrivacyOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("utf-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		String email = (String)session.getAttribute("email");
		vo.setEmail(email);
		
		vo.setTelecomValue(req.getParameter("telecomValue"));
		String contact = req.getParameter("contact1") + req.getParameter("contact2") + req.getParameter("contact3");
		vo.setContact(contact);
		vo.setZipcode(req.getParameter("zipcode"));
		vo.setAddress(req.getParameter("address"));
		vo.setAddressDetail(req.getParameter("addressDetail"));
		
		dao.updataMemberMyPrivacy(vo);
		
		forward.setForward(true);
		forward.setPath("/member/member-view-my-info-ok?myPage=myPrivacy");
		
		return forward;
	}
}
