package com.ggomsu.app.wiki.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiContentVO;
import com.ggomsu.app.wiki.vo.WikiInfoDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.wiki.WikiHelper;

// 작성자 : 박성민
public class WriteConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// java 객체 생성
		WikiHelper wikiHelper = new WikiHelper();
		WikiDAO dao = new WikiDAO();
		WikiInfoDTO info = new WikiInfoDTO();
		ActionForward forward = new ActionForward();
		
		// parameter 저장
		String subject = req.getParameter("subject");
		int rvs = 1;
		String contentText = req.getParameter("contents");
		String nickname = (String) req.getSession().getAttribute("nickname");
		
		// 위키 작성 정보 저장
		// issue 1 : 작성 중간에 이미 누가 같은 제목의 주제를 생성했는가? -> 실패 안내
		// issue 2 : ip 정보 얻기 -> nickname이 "익명"이라면 ip를 저장하고 그렇지 않으면 ip = null
		// ★member에 있다면 nickname 저장, 아니라면 ip 저장으로 변경
		if(dao.checkExistBySubject(subject)) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/wiki/home?code=duplicate");
		}
		else {
			if(nickname == null) {
				nickname = "noname";
			}
			
			info.setSubject(subject);
			info.setRvs(rvs);
			info.setNickname(nickname);
			info.setIp(nickname.equals("noname") ? req.getRemoteAddr() : "");
			dao.insertWikiInfo(info);
			
			// 위키 콘텐츠 저장
			List<WikiContentVO> contents = (ArrayList<WikiContentVO>)wikiHelper.paragraphToList(subject, rvs, contentText);
			dao.insertWikiContents(contents);
			
			String subjectEncoded = URLEncoder.encode(subject, "UTF-8");
			
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/wiki/view?subject=" + subjectEncoded + "&rvs=" + rvs);
		}
		
		return forward;
	}
}
