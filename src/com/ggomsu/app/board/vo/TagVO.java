package com.ggomsu.app.board.vo;

// 작성자 : 박성민

public class TagVO {
	
	private int articleIndex;
	private String tagValue;
	
	public TagVO() { ; }

	public int getArticleIndex() {
		return articleIndex;
	}

	public void setArticleIndex(int articleIndex) {
		this.articleIndex = articleIndex;
	}

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
	}
	
}
