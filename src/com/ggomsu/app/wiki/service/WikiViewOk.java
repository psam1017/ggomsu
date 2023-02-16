package com.ggomsu.app.wiki.service;

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
public class WikiViewOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		SimpleWiki wiki = new SimpleWiki();
		WikiDAO dao = new WikiDAO();
		WikiInfoVO infoVO = null;
		ActionForward forward = new ActionForward();
		
		String subject = req.getParameter("subject");
		int rvs = Integer.parseInt(req.getParameter("rvs"));
		
		// 주제가 있는지 먼저 검색 COUNT(1) -> 없으면 no-subject, 있으면 계속 진행
		
		// 작성정보 가져오기
		infoVO = dao.getWikiInfo(subject, rvs);
		
		// 오용으로 인해 삭제된 버전일 때
		if(infoVO.getDeletedAt() != null) {
			forward.setForward(true);
			forward.setPath("/app/wiki/WikiDeleted.jsp");
		}
		// 정상적으로 볼 수 있는 버전일 때
		else {
			// 콘텐츠 가져오기
			List<WikiContentVO> pastList = new LinkedList<>();
			pastList.addAll(dao.getContentPast(subject, rvs));
			List<WikiContentVO> currentList = (LinkedList<WikiContentVO>)dao.getContentOne(subject, rvs);
			wiki.setContentFromPast(currentList, pastList);
			
			// ★최근 변경 목록 가져오기 -> wiki home에서 필요로 하는 기능
			// List<String> recentSubjects = dao.getRecentSubject();
			// req.setAttribute("recentSubjects", recentSubjects);
			
			req.setAttribute("wikiInfo", infoVO);
			req.setAttribute("wikiContents", currentList);
			
			forward.setForward(true);
			forward.setPath("/app/wiki/WikiViewOk.jsp");
		}
		
		return forward;
	}
}
