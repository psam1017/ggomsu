package com.ggomsu.app.board.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		// upload의 경로 설정
		// 호스팅시에 파일 경로 변경해야한다.
		String saveFolder = "/Users/leesungho/project/workspace/ggomsu/WebContent/app/upload";
	
		// byte 단위의 크기 : 5Mb
		int fileSize = 1024 * 1024 * 5;
	
		// 인코딩 방식 
		String encoding = "UTF-8";
		
		// 동일한 파일명에 대한 처리 방식
		FileRenamePolicy frp = new DefaultFileRenamePolicy();
		
		// 게시글의 내용을 전달 받기 위해 MultipartRequest라는 객체를 선언한다. (+ cos.jar 라이브러리 필요)
		MultipartRequest multi = null;
		
		// multi = new MultiparatRequest(req, 경로, 파일크기, 인코딩방식, 정책 -> 사용자들의 파일명을 서버가 바꿔서 저장할 수 있도록)
		multi = new MultipartRequest(req, saveFolder, fileSize, encoding, frp);
		
		String boardValue = multi.getParameter("boardValue");
		String title = multi.getParameter("title");
		// String basic = multi.getParameter("basic"); // 해쉬태그 값
		String content = multi.getParameter("content");
		// String value = multi.getParameter("value");
		int articleIndex = aDao.getMaxIndex();
		
		// Session
		HttpSession session = req.getSession();
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
		int totalCount = aDao.getTotal(boardValue);
		
		int startPage = ((page - 1) / pageSize) * pageSize + 1;
		int endPage = startPage + 9;
		int realEndPage = (int)Math.ceil((double)totalCount/pageSize);
		
		endPage = (endPage > realEndPage) ? realEndPage : endPage;
		
		int prevPage = (int)((page - 10) / 10) * 10 + 1;
		int nextPage = (int)((page + 9) / 10) * 10 + 1;
		
		// 쿠키 생성
//		Cookie cBoardValue = new Cookie("boardValue",  URLEncoder.encode(boardValue, "UTF-8"));
		
		// 응답헤더에 쿠키 추가
//		resp.addCookie(cBoardValue);
	
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html");
		
//		req.setAttribute("totalCount", totalCount);
//		req.setAttribute("realEndPage", realEndPage);
//		req.setAttribute("startPage", startPage);
//		req.setAttribute("endPage", endPage);
//		req.setAttribute("nowPage", page);
//		req.setAttribute("articleList", aDao.getList((page-1)*10,"%"+boardValue));
//		req.setAttribute("prevPage", prevPage);
//		req.setAttribute("nextPage", nextPage);
//		req.setAttribute("boardValue", boardValue);
//		req.setAttribute("boardText", bDao.getBoardText(boardValue));
		
		Enumeration<String> files = multi.getFileNames();
		while(files.hasMoreElements()) {
			// 사용자가 업로드한 파일  태그의 name
			String name = files.nextElement();
			// 중복시 변경된 이름
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
