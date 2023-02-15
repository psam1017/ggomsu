package com.ggomsu.app.member.service;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.app.member.vo.SimpleEncInfoVO;


	//작성자 : 손하늘
public class MemberCertificationOk implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		MemberVO vo = new MemberVO();
		MemberDAO dao = new MemberDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		StringBuffer temp =new StringBuffer();
        Random rnd = new Random();
        for(int i=0;i<10;i++)
        {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
            case 0:
                // a-z
                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                break;
            case 1:
                // A-Z
                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                break;
            case 2:
                // 0-9
                temp.append((rnd.nextInt(10)));
                break;
            }
        }
        String AuthenticationKey = temp.toString();
        
       session.setAttribute("AuthenticationKey", AuthenticationKey);
        System.out.println("인증번호 : " + AuthenticationKey);
		
        forward.setForward(true);
		forward.setPath("/app/member/MemberChangePw.jsp");
		return forward;

		}
	}
