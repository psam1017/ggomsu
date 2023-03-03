package com.ggomsu.app.admin.service;

import java.io.PrintWriter;
import java.util.LinkedList;
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
		
		if(info.getDeletedAt() == null) {
			
			List<WikiContentVO> pastList = new LinkedList<>();
			pastList.addAll(dao.getContentPast(subject, rvs));
			List<WikiContentVO> currentList = (LinkedList<WikiContentVO>)dao.getContentOne(subject, rvs);
			wikiHelper.setContentFromPast(currentList, pastList);
			int length = currentList.size();
			String[] array = new String[length];
			
			for(int i = 0; i < array.length; i++) {
				array[i] = currentList.get(i).getContent();
			}
			
			json.put("status", "ok");
			json.put("subject", subject);
			json.put("rvs", rvs);
			json.put("nickname", info.getNickname());
			json.put("profileImageUrl", info.getProfileImageUrl());
			json.put("ip", info.getIp());
			json.put("revisedAt", info.getRevisedAt());
			json.put("array", array);
		}
		else {
			json.put("status", "not-ok");
		}
		out.print(json.toJSONString());
		out.close();
		
		return null;
	}
}
