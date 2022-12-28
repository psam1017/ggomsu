package com.ggomsu.app.wiki.service;

import java.util.ArrayList;
import java.util.List;

import com.ggomsu.app.wiki.dao.WikiDAO;
import com.ggomsu.app.wiki.vo.WikiVO;

public class WikiTestVer2 {
	public static void main(String[] args) {
		
		System.out.println("실행!");
		
		WikiDAO dao = new WikiDAO();
		
		String subject = "청춘예찬";
		int rvs = 3;
		
		List<WikiVO> rawList = dao.getList(subject, rvs);
		
		List<List<WikiVO>> sepList = new ArrayList<List<WikiVO>>();
		int sepIndex = 0;
		
		for(int i = 0; i < rawList.size(); i++) {
			sepList.get(sepIndex).add(i, rawList.get(i));
			if(rawList.get(i).getRvs() != rawList.get(i + 1).getRvs()) {
				
			}
		}
		
//		for(WikiVO wiki : list) {
//			if(wiki.getRvs() == rvs) {
//				if(wiki.isSelf()) {
//					System.out.println(wiki.getRvs() + "" + wiki.getRvsIndex() + wiki.getContent());
//					count++;
//					System.out.println("실행" + wiki.getRvs() + wiki.getRvsIndex());
//				}
//				else {
//					for(int i = 0; i < list.size(); i++) {
//						count++;
//						System.out.println("실행" + list.get(i).getRvs() + list.get(i).getRvsIndex());
//						if(list.get(i).equals(wiki)) {
//							System.out.println(wiki.getRvs() + "" + wiki.getRvsIndex() + list.get(i).getContent());
//							list.remove(i);
//							break;
//						}
//					}
//				}
//			}
//		}
//		System.out.println("실행횟수 : " + count);
	}
}
