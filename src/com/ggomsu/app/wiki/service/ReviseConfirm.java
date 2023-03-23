package com.ggomsu.app.wiki.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiContentVO;
import com.ggomsu.app.wiki.vo.WikiInfoDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.wiki.WikiHelper;

// 작성자 : 박성민
public class ReviseConfirm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// java 객체 생성
		WikiHelper wikiHelper = new WikiHelper();
		WikiDAO dao = new WikiDAO();
		WikiInfoDTO info = new WikiInfoDTO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		// parameter 저장
		String subject = req.getParameter("subject");
		String contentText = req.getParameter("contents");
		String nickname = session.getAttribute("nickname") != null ? (String) session.getAttribute("nickname") : "noname";
		
		// 위키 작성 정보를 저장하면서 rvs를 얻어온다.
		info.setSubject(subject);
		info.setNickname(nickname);
		info.setIp(nickname.equals("noname") ? req.getRemoteAddr() : null);
		
		// 프로시저 호출 : 새로운 info를 INSERT하고, 새롭게 개정된 버전의 rvs를 가져온다.
		int rvs = dao.reviseWikiInfo(info);
		int preRvs = rvs - 1;
		
		// wiki 생성
		// 직전 버전과 먼저 비교하여 검색 대상을 최소화함.
		List<WikiContentVO> rvsList = (ArrayList<WikiContentVO>) wikiHelper.paragraphToList(subject, rvs, contentText);
		List<WikiContentVO> preRvsList = (ArrayList<WikiContentVO>) dao.getContentOne(subject, preRvs);
		List<WikiContentVO> allPastList = (ArrayList<WikiContentVO>) dao.getContentPast(subject, preRvs);
		wikiHelper.setContentFromPast(preRvsList, allPastList);
		
		// 위키 콘텐츠 저장
		wikiHelper.reviseContent(rvsList, preRvsList, allPastList);
		dao.insertWikiContents(rvsList);
		
		String subjectEncoded = URLEncoder.encode(subject, "UTF-8");
		
		// redirect
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/wiki/view?subject=" + subjectEncoded + "&rvs=" + rvs);
		
		return forward;
	}
}
