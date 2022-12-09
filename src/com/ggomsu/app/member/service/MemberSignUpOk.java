package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;

public class MemberSignUpOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		MemberEncryptOk encrypt = new MemberEncryptOk();
		MemberEncryptInfo info = new MemberEncryptInfo();
		
		// getParameter의 안에 String 값은 jsp에서 만든 name의 값과 같아야 한다!
		vo.setEmail(req.getParameter("email"));
		String inserted = req.getParameter("password");
		
		info = encrypt.encrypt(inserted);

		vo.setPassword(info.getPassword());
		//vo.setSalt(info.getSalt());
		
		//vo.setNaverKey(req.getParameter("naverKey"));
		//vo.setKakaoKey(req.getParameter("kakaoKey"));
		//vo.setGoogleKey(req.getParameter("googleKey"));
		vo.setNickname(req.getParameter("nickname"));
		vo.setName(req.getParameter("name"));
		vo.setBirthDate(req.getParameter("birthDate"));
		vo.setSex(req.getParameter("sex"));
		vo.setTelecomValue(req.getParameter("telecomValue"));
		vo.setContact(req.getParameter("contact"));
		vo.setZipcode(req.getParameter("zipcode"));
		vo.setAddress(req.getParameter("address"));
		vo.setAddressDetail(req.getParameter("addressDetail"));
		vo.setProfileImageUrl(req.getParameter("profileImageUrl"));
		
		//req.setAttribute("user", vo);
		
		dao.signUp(vo);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/member/welcome");
		
		return forward;
		
	}

}
