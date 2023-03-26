package com.ggomsu.system.upload;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class UploadHelper {
	
	// https://soulduse.tistory.com/10
	public void deleteOldFile(File path) {
		Calendar cal = Calendar.getInstance();
		long todayMil = cal.getTimeInMillis(); // 현재 시간(밀리 세컨드)
		
		cal.set(2023, 03, 01, 00, 00, 00);
		long aprilMil = cal.getTimeInMillis(); // 4월 1일
		
		long oneDayMil = 24 * 60 * 60 * 1000; // 1일마다
		
		Calendar fileCal = Calendar.getInstance();
		Date fileDate = null;
		
		File[] list = path.listFiles(); // 파일 리스트 가져오기
		
		for(int i = 0 ; i < list.length; i++){
			// 파일의 마지막 수정시간 가져오기
			fileDate = new Date(list[i].lastModified());
			// 현재시간과 파일 수정시간 시간차 계산(단위 : 밀리 세컨드)
			fileCal.setTime(fileDate);
			long fileMil = fileCal.getTimeInMillis();
			long diffMil = todayMil - fileMil;
			//날짜로 계산
			int diffDay = (int)(diffMil/oneDayMil);
			// 4월 이후에 생성되었으며, 1일이 지난 파일 삭제
			if(diffDay > 1 && fileMil > aprilMil && list[i].exists()){
				list[i].delete();
			}
		}
	}
}
