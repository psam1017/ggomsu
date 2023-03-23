package com.ggomsu.app.wiki.service;

import java.util.ArrayList;
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
		String statusValue = (String) req.getSession().getAttribute("statusValue");
		String subject = req.getParameter("subject");
		
		if(!dao.checkExistBySubject(subject)) {
			forward.setForward(false);
			forward.setPath(req.getContextPath() + "/wiki/no-subject");
			return forward;
		}
		
		int latestRvs = dao.getLatestRvs(subject);
		int rvs = req.getParameter("rvs") != null ? Integer.parseInt(req.getParameter("rvs")) : latestRvs;
		
		// 작성정보 가져오기
		info = dao.findWikiInfo(subject, rvs);
		
		// 삭제되거나 존재하지 않는다면
		if(info == null || info.getDeletedAt() != null) {
			forward.setForward(true);
			forward.setPath("/views/wiki/NotAvail.jsp");
		}
		// 정상적으로 볼 수 있는 버전일 때
		else {
			// 콘텐츠 가져오기
			List<WikiContentVO> pastList = new LinkedList<>();
			pastList.addAll(dao.getContentPast(subject, rvs));
			List<WikiContentVO> currentList = (ArrayList<WikiContentVO>)dao.getContentOne(subject, rvs);
			wikiHelper.setContentFromPast(currentList, pastList);
			
			String fullContents = "";
			
			for(WikiContentVO line : currentList) {
				fullContents += line.getContent();
			}
			
			if(statusValue == null || statusValue.equals("TMP")) {
				req.setAttribute("remoteAddr", req.getRemoteAddr());
			}
			if(latestRvs == rvs) {
				req.setAttribute("isLatestRvs", true);
			}
			req.setAttribute("wikiInfo", info);
			req.setAttribute("fullContents", fullContents);
			
			forward.setForward(true);
			forward.setPath("/views/wiki/View.jsp");
		}
		
		return forward;
	}
}
