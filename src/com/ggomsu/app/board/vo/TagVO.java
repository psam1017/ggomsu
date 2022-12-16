package com.ggomsu.app.board.vo;

// 작성자 : 박성민

public class TagVO {
	
	private String tagValue;
	private int tagCount;
	private int searchCount;
	
	public TagVO() { ; }

	public String getTagValue() {
		return tagValue;
	}

	public void setTagValue(String tagValue) {
		this.tagValue = tagValue;
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
