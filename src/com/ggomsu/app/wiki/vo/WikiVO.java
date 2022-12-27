package com.ggomsu.app.wiki.vo;

// 작성자 : 박성민

public class WikiVO {
	
	private String subject;
	private int rvs;
	private int rvsIndex;
	private int preRvs;
	private int preRvsIndex;
	private String content;
	
	public WikiVO() { ; }
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public int getRvs() {
		return rvs;
	}
	public void setRvs(int rvs) {
		this.rvs = rvs;
	}
	public int getRvsIndex() {
		return rvsIndex;
	}
	public void setRvsIndex(int rvsIndex) {
		this.rvsIndex = rvsIndex;
	}
	public int getPreRvs() {
		return preRvs;
	}
	public void setPreRvs(int preRvs) {
		this.preRvs = preRvs;
	}
	public int getPreRvsIndex() {
		return preRvsIndex;
	}
	public void setPreRvsIndex(int preRvsIndex) {
		this.preRvsIndex = preRvsIndex;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof WikiVO) {
			WikiVO wiki = (WikiVO)obj;
//			System.out.print(this.getRvs() + ", " + wiki.getRvsIndex() + "/");
//			System.out.println(this.getPreRvs() + ", " + wiki.getPreRvsIndex());
			return (Integer.toString(this.getRvs()) + this.getRvsIndex()).equals((Integer.toString(wiki.getPreRvs()) + wiki.getPreRvsIndex()));
		}
		return false;
	}
	
	public boolean isSelf() {
		return (Integer.toString(this.getRvs()) + this.getRvsIndex()).equals((Integer.toString(this.getPreRvs()) + this.getPreRvsIndex()));
	}
}
