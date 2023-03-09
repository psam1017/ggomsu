package com.ggomsu.app.wiki.service;

import java.util.LinkedList;
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
public class View implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// java 객체 생성
		WikiHelper wikiHelper = new WikiHelper();
		WikiDAO dao = new WikiDAO();
		WikiInfoDTO info = null;
		ActionForward forward = new ActionForward();
		
		// parameter 저장
		String subject = req.getParameter("subject");
		int rvs = Integer.parseInt(req.getParameter("rvs"));
		
		// 주제가 있는지 먼저 검색 COUNT(1) -> 없으면 no-subject, 있으면 계속 진행
		// /wiki/no-subject
		
		// 작성정보 가져오기
		info = dao.findWikiInfo(subject, rvs);
		
		// 오용으로 인해 삭제된 버전일 때
		if(info.getDeletedAt() != null) {
			forward.setForward(true);
			forward.setPath("/views/wiki/Deleted.jsp");
		}
		// 정상적으로 볼 수 있는 버전일 때
		else {
			// 콘텐츠 가져오기
			List<WikiContentVO> pastList = new LinkedList<>();
			pastList.addAll(dao.getContentPast(subject, rvs));
			List<WikiContentVO> currentList = (LinkedList<WikiContentVO>)dao.getContentOne(subject, rvs);
			wikiHelper.setContentFromPast(currentList, pastList);
			
			req.setAttribute("wikiInfo", info);
			req.setAttribute("wikiContents", currentList);
			
			req.setAttribute("subject", subject);
			req.setAttribute("rvs", rvs);
			
			forward.setForward(true);
			forward.setPath("/views/wiki/View.jsp");
		}
		
		return forward;
	}
}
