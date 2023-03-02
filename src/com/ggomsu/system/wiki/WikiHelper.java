package com.ggomsu.system.wiki;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.ggomsu.app.wiki.vo.WikiContentVO;

// 작성자 : 박성민
public class WikiHelper {
	
	// 현재 작성한 위키의 content를 '일단' 저장해주기
	// issue1 : wiki는 외래키 참조 무결성을 지키기 위해 content보다 info를 먼저 저장해야 한다.
	// issue2 : contents와 rvs 저장하기 -> split()에 regex를 개행문자로 전달
	// 		방법1 : URLEncoder와 URLDecoder 객체 사용하고 아스키코드(%0D%0A)로 개행문자 식별하기
	//		방법2(채택) : System.getProperty("line.separator")로 개행문자 식별하기
	// 		방법3 : 정규표현식("(\r\n|\r|\n|\n\r)")으로 개행문자 식별하기
	// issue3 : contentArray만큼 INSERT하기 -> 동적 쿼리 사용
	// 참고로 : content가 null이 아닌 항목은 모두 R == PR, RI == PRI이다.
	public List<WikiContentVO> paragraphToList(String subject, int rvs, String contentText){
		
		List<WikiContentVO> contents = new ArrayList<>();
		String[] contentArray = contentText.split(System.getProperty("line.separator"), -1); 
		
		for(int i = 1; i <= contentArray.length; i++) {
			WikiContentVO contentVO = new WikiContentVO();
			contentVO.setSubject(subject);
			contentVO.setRvs(rvs);
			contentVO.setRvsIndex(i);
			contentVO.setPreRvs(rvs);
			contentVO.setPreRvsIndex(i);
			contentVO.setContent(contentArray[i - 1]);
			contents.add(contentVO);
		}
		
		return contents;
	}
	
	// content가 null, 즉 이미 content가 있는 경우에는 과거 버전에서 content를 찾아서 가져오기
	public void setContentFromPast(List<WikiContentVO> currentList, List<WikiContentVO> pastList) {
		for(WikiContentVO current : currentList) {
			if(current.getContent() == null) {
				int index = Collections.binarySearch(pastList, current, WIKI_RVS_COMPARATOR);
				WikiContentVO past = pastList.get(index);
				current.setContent(past.getContent());
				pastList.remove(past);
			}
		}
	}
	
	// 1. 개정 직전 버전과 비교하여 content가 같으면 content는 지우고 PR, PRI를 가져오기
	//     직전 버전 list를 모으는 Comparator는 (R, RI)와 (PR, PRI)를 비교하므로 탐색이 더 빠르다.
	// 2. 이전의 모든 버전과 비교하여 content가 같으면 content는 지우고 PR, PRI를 가져오기
	//     issue : 어떻게 탐색할 것인가
	//         선형 탐색(remove X) : 이중 반복문 -> 확실하게 O(n^2)
	//         선형 탐색(remove O) : O(n log n) + O(n), 최악의 경우(탐색을 모두 실패) O(n^2)
	//      -> 퀵 정렬 + 이진 탐색 : O(n log n) + O(log n)
	//             퀵 정렬은 최악의 경우 O(n^2)이지만 list가 이미 정렬되어 있을 가능성이 현저하게 낮음.
	// 3. 비교하여 같은 content가 없다면 content를 저장 -> R, RI는 paragraphToList()로 이미 저장되어 있음.
	public void reviseContent(List<WikiContentVO> currentList, List<WikiContentVO> preList, List<WikiContentVO> pastList) {
		setPRVSFromPre(currentList, preList);
		sortByContent(pastList);
		setPRVSFromPast(currentList, pastList);
	}
	
	// Iterator로 반복하지 않으면 remove 과정에서 ConcurrentModificationException 발생
	private void setPRVSFromPre(List<WikiContentVO> currentList, List<WikiContentVO> preList) {
		
		for(WikiContentVO currentContent : currentList) {
			Iterator<WikiContentVO> iter = preList.iterator();
			while(iter.hasNext()) {
				WikiContentVO preContent = iter.next();
				if(currentContent.getContent().equals(preContent.getContent())) {
					currentContent.setPreRvs(preContent.getPreRvs());
					currentContent.setPreRvsIndex(preContent.getPreRvsIndex());
					currentContent.setContent(null);
					preList.remove(preContent);
					break;
				}
			}
		}
	}
	
	private void sortByContent(List<WikiContentVO> list) {
		Collections.sort(list, WIKI_CONTENT_COMPARATOR);
	}
	
	private void setPRVSFromPast(List<WikiContentVO> currentList, List<WikiContentVO> pastList) {
		
		for(WikiContentVO currentContent : currentList) {
			if(currentContent.getContent() != null) {
				int index = Collections.binarySearch(pastList, currentContent, WIKI_CONTENT_COMPARATOR);
				if(index >= 0) {
					WikiContentVO pastContent = pastList.get(index);
					currentContent.setPreRvs(pastContent.getPreRvs());
					currentContent.setPreRvsIndex(pastContent.getPreRvsIndex());
					currentContent.setContent(null);
				}
			}
		}
	}
	
	private final Comparator<WikiContentVO> WIKI_RVS_COMPARATOR = new Comparator<WikiContentVO>() {
		
		// 현재 버전의 PR과 이전 버전의 R이 같은가?
		//     -> 같다면, 현재 버전의 PRI와 이전 버전의 RI가 같은가?
		@Override
		public int compare(WikiContentVO past, WikiContentVO current) {
			
			int PR = current.getPreRvs();
			int PRI = current.getPreRvsIndex();
			int R = past.getRvs();
			int RI = past.getRvsIndex();
			
			if(PR > R) { return -1; }
			else  if(PR < R){ return 1; }
			else{
				if(PRI > RI) { return -1; }
				else if(PRI < RI){ return 1; }
			}
			return 0;
		}
	};
	
	private final Comparator<WikiContentVO> WIKI_CONTENT_COMPARATOR = new Comparator<WikiContentVO>() {
		
		// 1. 문자열의 길이가 같은가?
		// 2. 각 char 배열의 값이 같은가?
		
		@Override
		public int compare(WikiContentVO w1, WikiContentVO w2) {
			
			char[] o1 = w1.getContent().toCharArray();
			char[] o2 = w2.getContent().toCharArray();
			
			char c1, c2;
			
			int length = o1.length > o2.length ? o2.length : o1.length;
			
			if(o1.length != o2.length) {
				for(int i = 0; i < length; i++) {
					c1 = o1[i];
					c2 = o2[i];
					
					if(c1 > c2) { return 1; }
					else if (c1 < c2){ return -1; }
				}
				return o1.length > o2.length ? 1 : -1;
			}
			else {
				for(int i = 0; i < length; i++) {
					c1 = o1[i];
					c2 = o2[i];
					
					if(c1 > c2) { return 1; }
					else if(c1 < c2) { return -1; }
				}
				return 0;
			}
		}
	};
}
