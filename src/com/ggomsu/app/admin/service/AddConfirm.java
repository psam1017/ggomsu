package com.ggomsu.app.admin.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.admin.dao.AdminDAO;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.encrypt.EncryptionInfo;
import com.ggomsu.system.encrypt.SimpleEncryptor;

//작성자 : 박성민
public class AddConfirm implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberVO vo = new MemberVO();
		MemberDAO memberDAO = new MemberDAO();
		AdminDAO adminDAO = new AdminDAO();
		ActionForward forward = new ActionForward();
		SimpleEncryptor encryptor = new SimpleEncryptor();
		EncryptionInfo info = new EncryptionInfo();
		
		String email = req.getParameter("email");
		String nickname = req.getParameter("nickname");
		String contact = req.getParameter("contact");
		String inserted = req.getParameter("password");
		info = encryptor.encrypt(inserted);
		
		// 중복되는 값들은 한 번 더 검사. js의 취약성을 한 번 더 보완
		if(memberDAO.checkEmail(email) || memberDAO.checkNickname(nickname) || memberDAO.checkContact(contact)) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
			return forward;
		}
		
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
		
		adminDAO.addNewAdmin(vo);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/admin/add?code=success");
		return forward;
	}
}
