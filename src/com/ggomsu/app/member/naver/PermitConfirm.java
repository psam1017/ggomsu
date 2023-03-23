package com.ggomsu.app.member.naver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.encrypt.EncryptionInfo;
import com.ggomsu.system.encrypt.EncryptionHelper;

//작성자 : 박성민
public class PermitConfirm implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		EncryptionHelper encryptionHelper = new EncryptionHelper();
		EncryptionInfo info = new EncryptionInfo();
		HttpSession session = req.getSession();
		
		String inserted = req.getParameter("password");
		info = encryptionHelper.encrypt(inserted);
		
		String contact = req.getParameter("contact");
		
		// 중복되는 값들은 한 번 더 검사. js의 취약성을 한 번 더 보완
		if(dao.checkContact(contact)) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/error/error");
			return forward;
		}
		
		vo.setEmail((String) session.getAttribute("email"));
		vo.setPassword(info.getPassword());
		vo.setSalt(info.getSalt());
		vo.setName(req.getParameter("name"));
		vo.setBirthDate(req.getParameter("birthDate"));
		vo.setSex(req.getParameter("sex"));
		vo.setTelecomValue(req.getParameter("telecomValue"));
		vo.setContact(contact);
		vo.setZipcode(req.getParameter("zipcode"));
		vo.setAddress(req.getParameter("address"));
		
		dao.integrateAccount(vo);
		session.setAttribute("statusValue", "MEM");
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/my/personal?code=permit");
		return forward;
	}
}
