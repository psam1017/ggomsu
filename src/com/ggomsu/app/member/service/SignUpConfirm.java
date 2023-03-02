package com.ggomsu.app.member.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.encrypt.EncryptionInfo;
import com.ggomsu.system.encrypt.EncryptionHelper;

//작성자 : 박성민
public class SignUpConfirm implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		EncryptionHelper encryptionHelper = new EncryptionHelper();
		EncryptionInfo info = new EncryptionInfo();
		
		String inserted = req.getParameter("password");
		info = encryptionHelper.encrypt(inserted);
		
		String email = req.getParameter("email");
		String nickname = req.getParameter("nickname");
		String contact = req.getParameter("contact");
		
		// 중복되는 값들은 한 번 더 검사. js의 취약성을 한 번 더 보완
		if(dao.checkEmail(email) || dao.checkNickname(nickname) || dao.checkContact(contact)) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
			return forward;
		}
		// 이메일 인증을 통과했는지 검사
		if(!req.getSession().getAttribute("auth").equals("ok")) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
			return forward;
		}
		
		req.getSession().removeAttribute("auth");
		
		vo.setEmail(email);
		vo.setPassword(info.getPassword());
		vo.setSalt(info.getSalt());
		vo.setNickname(nickname);
		vo.setName(req.getParameter("name"));
		vo.setBirthDate(req.getParameter("birthDate"));
		vo.setSex(req.getParameter("sex"));
		vo.setTelecomValue(req.getParameter("telecomValue"));
		vo.setContact(contact);
		vo.setZipcode(req.getParameter("zipcode"));
		vo.setAddress(req.getParameter("address"));
		vo.setAddressDetail(req.getParameter("addressDetail"));
		vo.setAgreedMarketingAt(req.getParameter("agreedMarketingAt"));
		
		dao.signUp(vo);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/member/welcome");
		return forward;
	}
}
