package com.ggomsu.app.board.vo;

// 작성자 : 박성민

public class TagVO {
	
	private String value;
	private int tagCount;
	private int searchCount;
	
	public TagVO() { ; }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getTagCount() {
		return tagCount;
	}

	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}

	public int getSearchCount() {
		return searchCount;
	}

	public void setSearchCount(int searchCount) {
		this.searchCount = searchCount;
	}
}
