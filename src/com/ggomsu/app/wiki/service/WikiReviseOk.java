package com.ggomsu.app.wiki.service;

import java.util.ArrayList;

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
		int rvs = Integer.parseInt(req.getParameter("rvs"));
		int preRvs = rvs - 1;
		String contentText = req.getParameter("contents");
		
		// wiki 생성
		ArrayList<WikiContentVO> rvsList = (ArrayList<WikiContentVO>) wiki.paragraphToList(subject, rvs, contentText);
		ArrayList<WikiContentVO> preRvsList = (ArrayList<WikiContentVO>) dao.getContentOne(subject, preRvs);
		ArrayList<WikiContentVO> allPastList = (ArrayList<WikiContentVO>) dao.getContentPast(subject, preRvs);
		wiki.setContentFromPast(preRvsList, allPastList);
		
		// 위키 콘텐츠 저장
		wiki.reviseContent(rvsList, preRvsList, allPastList);
		dao.insertWikiContents(rvsList);
		
		// 위키 작성 정보 저장
		// issue : ip 정보 얻기 -> nickname이 "익명"이라면 ip를 저장하고 그렇지 않으면 ip = null
		infoVO.setSubject(subject);
		infoVO.setRvs(rvs);
		infoVO.setNickname(req.getParameter("nickname"));
		infoVO.setIp(infoVO.getNickname().equals("noname") ? req.getRemoteAddr() : null);
		dao.insertWikiInfo(infoVO);
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/wiki/wiki-view-ok?subject=" + subject + "&rvs=" + rvs);
		
		return forward;
	}
}