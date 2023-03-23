package com.ggomsu.app.member.naver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.app.member.dao.MemberDAO;
import com.ggomsu.app.member.vo.MemberVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.naver.LoginHelper;
import com.ggomsu.system.naver.NaverInfo;

public class NaverConfirm implements Action{

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		HttpSession session = req.getSession();
		ActionForward forward = new ActionForward();
		LoginHelper loginHelper = new LoginHelper();
		MemberDAO memberDAO = new MemberDAO();
		MemberVO memberVO = null;
		
		String apiResult = null;
		String code = req.getParameter("code");
		String state = req.getParameter("state");
		
		String apiUrl = NaverInfo.PROFILE_API_URL;
		String token = loginHelper.getAccessToken(session, code, state);
		String header = "Bearer " + token; // 반드시 Bearer 뒤에 공백이 있어야 함!
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Authorization", header);
		
		apiResult = loginHelper.get(apiUrl, requestHeaders);  //String형식의 json데이터
		
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(apiResult);
		JSONObject response_obj = (JSONObject) obj.get("response");
		
		String snsKey = (String) response_obj.get("id"); // DB에 저장해야 하는 snsKey
		String email = (String) response_obj.get("email");
		String snsNickname = (String) response_obj.get("nickname");
		
		String linkRedirect = (String) session.getAttribute("linkRedirect");
		boolean isArticleRedirect = false;
		
		// 기존 회원이 마이 페이지에서 연동을 시도하는 경우
		if(linkRedirect != null && linkRedirect.equals("link")) {
			String statusValue = (String) session.getAttribute("statusValue");
			String path = null;
			String type= "naver";
			if(statusValue.equals("ADM")) {
				path = "admin";
			}
			else {
				path = "my";
			}
			session.removeAttribute("linkRedirect");
			memberDAO.insertSnsKey(email, snsKey, type);
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/" + path + "/link?code=success");
			return forward;
		}
		
		// key가 있다면
		//		vo != null -> MEM 또는 ADM 또는 SNS일 때 -> 로그인 / redirect: term, board, main
		//				   -> else -> 휴면? 정지? 탈퇴?
		//		vo == null -> 예외상황 
		
		// key가 없다면
		//		vo == null -> 회원가입 -> redirect: main (board X)
		//		vo != null -> sns에서 가입한 기존 회원 -> 계정 통합 필요(최대한 간단한 과정)
		
		// 참고로 최초로 로그인할 때 naver에서 자동으로 제공동의를 묻는다.
		// 실제 서비스에서는 'key가 없다' ~ '최초 로그인'
		
		if(memberDAO.checkEmail(email)) {
			memberVO = memberDAO.getMemberInfo(email);
		}
		
		boolean isSnsKeyExist = memberDAO.checkSnsByKey(email, snsKey);
		
		if(!isSnsKeyExist) {
			session.setAttribute("snsEmail", email);
			session.setAttribute("snsKey", snsKey);
			
			if(memberVO == null) {
				session.setAttribute("snsNickname", snsNickname);
				forward.setPath(req.getContextPath() + "/member/sns/form");
			}
			else {
				forward.setPath(req.getContextPath() + "/member/integration");
			}
		}
		else if(isSnsKeyExist) {
			if(memberVO != null) {
				String articleRedirect = (String) session.getAttribute("articleRedirect");
				String statusValue = memberVO.getStatusValue();
				String nickname = memberVO.getNickname();
				
				session.setAttribute("statusValue", statusValue);
				session.setAttribute("profileImageUrl", memberVO.getProfileImageUrl());
				session.setAttribute("blindList", memberDAO.getBlindList(nickname));
				session.setAttribute("darkModeFlag", memberVO.isDarkModeFlag());
				session.setAttribute("alarmFlag", memberVO.isAlarmFlag());
				
				memberDAO.updateSignAt(email);
				
				if(statusValue.equals("MEM") || statusValue.equals("ADM") || statusValue.equals("SNS")) {
					session.setAttribute("email", email);
					session.setAttribute("nickname", nickname);
					
					if(memberDAO.checkTermExpired(email)) {
						session.setAttribute("disagree", "true");
						forward.setPath(req.getContextPath() + "/member/term/expired");
					}
					else if(articleRedirect != null) {
						isArticleRedirect = true;
						forward.setPath(req.getContextPath() + "/member/sign-in/board");
					}
					else {
						forward.setPath(req.getContextPath() + "/main");
					}
				}
				else if(statusValue.equals("DEL") || statusValue.equals("SUS") || statusValue.equals("DOR")) {
					session.setAttribute("invalidEmail", email);
					session.setAttribute("invalidNickname", nickname);
					session.setAttribute("invalid", "true");
					session.setAttribute("originalStatusValue", "SNS");
					forward.setPath(req.getContextPath() + "/help/invalid");
				}
			}
			else {
				forward.setPath(req.getContextPath() + "/error/error");
			}
		}
		
		if(!isArticleRedirect) {
			session.removeAttribute("articleRedirect");
		}
		
		forward.setForward(false);	
		return forward;
	}

}
