package com.ggomsu.app.wiki.service;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiContentVO;
import com.ggomsu.app.wiki.vo.WikiInfoVO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.wiki.SimpleWiki;

// 작성자 : 박성민
public class View implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// java 객체 생성
		SimpleWiki wiki = new SimpleWiki();
		WikiDAO dao = new WikiDAO();
		WikiInfoVO infoVO = null;
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();
		
		// parameter 저장
		String subject = req.getParameter("subject");
		int rvs = Integer.parseInt(req.getParameter("rvs"));
		
		// 주제가 있는지 먼저 검색 COUNT(1) -> 없으면 no-subject, 있으면 계속 진행
		// /wiki/no-subject
		
		// 작성정보 가져오기
		infoVO = dao.getWikiInfo(subject, rvs);
		
		// 오용으로 인해 삭제된 버전일 때
		if(infoVO.getDeletedAt() != null) {
			forward.setForward(true);
			forward.setPath("/views/wiki/Deleted.jsp");
		}
		// 정상적으로 볼 수 있는 버전일 때
		else {
			// 콘텐츠 가져오기
			List<WikiContentVO> pastList = new LinkedList<>();
			pastList.addAll(dao.getContentPast(subject, rvs));
			List<WikiContentVO> currentList = (LinkedList<WikiContentVO>)dao.getContentOne(subject, rvs);
			wiki.setContentFromPast(currentList, pastList);
			
			req.setAttribute("wikiInfo", infoVO);
			req.setAttribute("wikiContents", currentList);
			
			session.setAttribute("subject", subject);
			session.setAttribute("rvs", rvs);
			
			forward.setForward(true);
			forward.setPath("/views/wiki/View.jsp");
		}
		
		return forward;
	}
}
