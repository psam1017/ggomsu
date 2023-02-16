package com.ggomsu.app.wiki.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiContentVO;
import com.ggomsu.app.wiki.vo.WikiInfoVO;

// 작성자 : 박성민
public class WikiReviseOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		// java 객체 생성
		SimpleWiki wiki = new SimpleWiki();
		WikiDAO dao = new WikiDAO();
		WikiInfoVO infoVO = new WikiInfoVO();
		ActionForward forward = new ActionForward();
		
		// parameter 저장
		String subject = req.getParameter("subject");
		String contentText = req.getParameter("contents");
		
		// 위키 작성 정보를 저장하면서 rvs를 얻어온다.
		infoVO.setSubject(subject);
		infoVO.setNickname((String) req.getSession().getAttribute("nickname"));
		infoVO.setIp(infoVO.getNickname().equals("noname") ? req.getRemoteAddr() : null);
		
		// 프로시저 호출 : 새로운 info를 INSERT하고, 새롭게 개정된 버전의 rvs를 가져온다.
		int rvs = dao.reviseWikiInfo(infoVO);
		int preRvs = rvs - 1;
		// wiki 생성
		List<WikiContentVO> rvsList = (ArrayList<WikiContentVO>) wiki.paragraphToList(subject, rvs, contentText);
		List<WikiContentVO> preRvsList = (LinkedList<WikiContentVO>) dao.getContentOne(subject, preRvs);
		List<WikiContentVO> allPastList = (LinkedList<WikiContentVO>) dao.getContentPast(subject, preRvs);
		wiki.setContentFromPast(preRvsList, allPastList);
		
		// 위키 콘텐츠 저장
		wiki.reviseContent(rvsList, preRvsList, allPastList);
		dao.insertWikiContents(rvsList);
		
		String subjectEncoded = URLEncoder.encode(subject, "UTF-8");
		
		// redirect
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/wiki/wiki-view-ok?subject=" + subjectEncoded + "&rvs=" + rvs);
		
		return forward;
	}
}
