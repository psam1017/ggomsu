package com.ggomsu.app.wiki.service;

import java.util.List;

import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiVO;

public class WikiTest {
	public static void main(String[] args) {
		
		System.out.println("실행!");
		
		WikiDAO dao = new WikiDAO();
		
		String subject = "청춘예찬";
		int rvs = 2;
		
		List<WikiVO> list = dao.getList(subject, rvs);
		int count = 0;
		for(WikiVO wiki : list) {
			if(wiki.getRvs() == rvs) {
				if(wiki.isSelf()) {
					System.out.println(wiki.getRvs() + "" + wiki.getRvsIndex() + wiki.getContent());
					count++;
					System.out.println("실행" + wiki.getRvs() + wiki.getRvsIndex());
				}
				else {
					for(int i = 0; i < list.size(); i++) {
						count++;
						System.out.println("실행" + list.get(i).getRvs() + list.get(i).getRvsIndex());
						if(list.get(i).equals(wiki)) {
							System.out.println(wiki.getRvs() + "" + wiki.getRvsIndex() + list.get(i).getContent());
							list.remove(i);
							break;
						}
					}
				}
			}
		}
		System.out.println("실행횟수 : " + count);
	}
}
