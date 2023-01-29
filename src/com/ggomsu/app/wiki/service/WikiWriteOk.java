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
public class WikiWriteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		// 데이터 유효성 검사는 js로
		
		// java 객체 생성
		SimpleWiki wiki = new SimpleWiki();
		WikiDAO dao = new WikiDAO();
		WikiInfoVO infoVO = new WikiInfoVO();
		ActionForward forward = new ActionForward();
		
		// parameter 저장
		String subject = req.getParameter("subject");
		int rvs = Integer.parseInt(req.getParameter("rvs"));
		String contentText = req.getParameter("contents");
		
		// 위키 콘텐츠 저장
		ArrayList<WikiContentVO> contents = (ArrayList<WikiContentVO>)wiki.paragraphToList(subject, rvs, contentText);
		dao.insertWikiContents(contents);
		
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
