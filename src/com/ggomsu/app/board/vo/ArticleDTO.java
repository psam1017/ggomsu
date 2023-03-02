package com.ggomsu.app.board.vo;

// 작성자 : 박성민

public class ArticleDTO {
	
	private int articleIndex;
	private String boardValue;
	private String nickname;
	private String title;
	private String content;
	private int viewCount;
	private String writtenAt;
	private String deletedAt;
	private String articleDeleteReason;
	private int articleLikeCount;
	private String profileImageUrl;
	private String tagString;
	private String[] tagArray;

	public ArticleDTO() { ; }

	public int getArticleIndex() {
		return articleIndex;
	}

	public void setArticleIndex(int articleIndex) {
		this.articleIndex = articleIndex;
	}

	public String getBoardValue() {
		return boardValue;
	}

	public void setBoardValue(String boardValue) {
		this.boardValue = boardValue;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String memberNickname) {
		this.nickname = memberNickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public String getWrittenAt() {
		return writtenAt;
	}

	public void setWrittenAt(String writtenAt) {
		this.writtenAt = writtenAt;
	}

	public String getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(String deletedAt) {
		this.deletedAt = deletedAt;
	}
	
	public String getArticleDeleteReason() {
		return articleDeleteReason;
	}
	
	public void setArticleDeleteReason(String articleDeleteReason) {
		this.articleDeleteReason = articleDeleteReason;
	}

	public int getArticleLikeCount() {
		return articleLikeCount;
	}

	public void setArticleLikeCount(int articleLikeCount) {
		this.articleLikeCount = articleLikeCount;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
	
	public String getTagString() {
		return tagString;
	}

	public void setTagString(String tagString) {
		this.tagString = tagString;
	}
	
	public String[] getTagArray() {
		return tagArray;
	}

	public void setTagArray(String[] tagArray) {
		this.tagArray = tagArray;
	}
}
