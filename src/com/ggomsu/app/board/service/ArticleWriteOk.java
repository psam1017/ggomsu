package com.ggomsu.app.board.service;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ggomsu.app.action.Action;
import com.ggomsu.app.action.ActionForward;
import com.ggomsu.app.board.dao.ArticleDAO;
import com.ggomsu.app.board.dao.AttachmentDAO;
import com.ggomsu.app.board.dao.BoardDAO;
import com.ggomsu.app.board.vo.ArticleVO;
import com.ggomsu.app.member.dao.MemberDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

public class ArticleWriteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
		ArticleDAO aDao = new ArticleDAO();
		AttachmentDAO atDao = new AttachmentDAO();
		ArticleVO aVo = new ArticleVO();
		BoardDAO bDao = new BoardDAO();
		ActionForward forward = new ActionForward();
		HttpSession session = req.getSession();

		String blockedString = "'',";
		try {
			List<String> blockedList = (List<String>)session.getAttribute("blockedList");
			for(String blockedMember : blockedList) {
				blockedString += ("'" + blockedMember + "',");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		blockedString = blockedString.substring(0, blockedString.length()-1);

		String saveFolder = "/Users/leesungho/project/workspace/ggomsu/WebContent/app/upload";
		int fileSize = 1024 * 1024 * 5;
		String encoding = "UTF-8";
		FileRenamePolicy frp = new DefaultFileRenamePolicy();
		MultipartRequest multi = null;
		multi = new MultipartRequest(req, saveFolder, fileSize, encoding, frp);
		
		String boardValue = multi.getParameter("boardValue");
		String title = multi.getParameter("title");
		String basic = multi.getParameter("basic"); // 해쉬태그 값
		String content = multi.getParameter("content");
		// String value = multi.getParameter("value");
		int articleIndex = aDao.getMaxIndex();
		
		session.setAttribute("boardValue", boardValue);
		session.setAttribute("articleIndex", articleIndex);
		
		aVo.setBoardValue(boardValue);
		aVo.setTitle(title);
		aVo.setContent(content);
		aVo.setNickname((String)session.getAttribute("nickname"));
		
		aDao.insertArticle(aVo);
		
		String temp = req.getParameter("page");
		int page = (temp == null) ? 1 : Integer.parseInt(temp);
		int pageSize = 10;
		int totalCount = aDao.getTotal("%" + boardValue, blockedString);
		
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		int realEndPage = (int)Math.ceil((double)totalCount/pageSize);
		
		endPage = (endPage > realEndPage) ? realEndPage : endPage;
		
		int prevPage = (int)((page - 10) / 10) * 10 + 1;
		int nextPage = (int)((page + 9) / 10) * 10 + 1;
		
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
		Enumeration<String> files = multi.getFileNames();
		while(files.hasMoreElements()) {
			String name = files.nextElement();
			String systemName = multi.getFilesystemName(name);

			if(systemName == null) {
				continue;
			}
			atDao.insertAttachment(systemName, aDao.getMaxIndex());
		}
		
		forward.setForward(false);
		forward.setPath(req.getContextPath() + "/board/article-get-list-ok?boardValue=" + boardValue);
			
		return forward;
	}

}
