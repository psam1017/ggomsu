package com.ggomsu.app.wiki.service;

import java.util.ArrayList;
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
		int rvs = dao.getLastRvs(subject);
		int preRvs = rvs - 1;
		String contentText = req.getParameter("contents");
		
		// wiki 생성
		List<WikiContentVO> rvsList = (ArrayList<WikiContentVO>) wiki.paragraphToList(subject, rvs, contentText);
		List<WikiContentVO> preRvsList = (ArrayList<WikiContentVO>) dao.getContentOne(subject, preRvs);
		List<WikiContentVO> allPastList = (ArrayList<WikiContentVO>) dao.getContentPast(subject, preRvs);
		wiki.setContentFromPast(preRvsList, allPastList);
		
		// 위키 콘텐츠 저장
		wiki.reviseContent(rvsList, preRvsList, allPastList);
		dao.insertWikiContents(rvsList);
		
		// 위키 작성 정보 저장
		// issue : ip 정보 얻기 -> nickname이 "익명"이라면 ip를 저장하고 그렇지 않으면 ip = null
		
		// ★member에 있다면 nickname 저장, 아니라면 ip 저장으로 변경
		infoVO.setSubject(subject);
		infoVO.setRvs(rvs);
		infoVO.setNickname((String) req.getSession().getAttribute("nickname"));
		infoVO.setIp(infoVO.getNickname().equals("noname") ? req.getRemoteAddr() : null);
		dao.insertWikiInfo(infoVO);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/wiki/wiki-view-ok?subject=" + subject + "&rvs=" + rvs);
		
		return forward;
	}
}
