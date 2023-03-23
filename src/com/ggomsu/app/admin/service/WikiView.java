package com.ggomsu.app.admin.service;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiContentVO;
import com.ggomsu.app.wiki.vo.WikiInfoDTO;
import com.ggomsu.system.action.Action;
import com.ggomsu.system.action.ActionForward;
import com.ggomsu.system.wiki.WikiHelper;

// 작성자 : 이성호, 박성민
public class WikiView implements Action{

	@SuppressWarnings("unchecked")
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		WikiInfoDTO info;
		WikiHelper wikiHelper = new WikiHelper();
		WikiDAO dao = new WikiDAO();
		JSONObject json = new JSONObject();
		PrintWriter out = resp.getWriter();
		
		String subject = req.getParameter("subject");
		int rvs = Integer.parseInt(req.getParameter("rvs"));
		
		info = dao.findWikiInfo(subject, rvs);
		
		if(info == null) {
			json.put("status", "not-ok");
		}
		else if(info.getDeletedAt() == null) {
			
			List<WikiContentVO> pastList = new ArrayList<>();
			pastList.addAll(dao.getContentPast(subject, rvs));
			List<WikiContentVO> currentList = (ArrayList<WikiContentVO>)dao.getContentOne(subject, rvs);
			wikiHelper.setContentFromPast(currentList, pastList);
			
			String fullContents = "";
			
			for(WikiContentVO line : currentList) {
				fullContents += line.getContent();
			}
			
			json.put("status", "ok");
			json.put("subject", subject);
			json.put("rvs", rvs);
			json.put("writer", info.getNickname().equals("noname") ? info.getIp() : info.getNickname());
			json.put("revisedAt", info.getRevisedAt());
			json.put("content", fullContents);
		}
		else {
			json.put("status", "deleted");
		}
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
