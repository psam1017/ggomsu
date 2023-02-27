package com.ggomsu.system.mybatis.config;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConfig {
	
	// 작성자 : 박성민
	// 환경설정과 관련된 부분이므로 절대로 건드리지 마세요.
	
	private static SqlSessionFactory sqlSession_f;
	
	static {
		try {
			String resource = "./com/ggomsu/system/mybatis/config/config.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSession_f = new SqlSessionFactoryBuilder().build(reader);
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException("초기화 문제 발생. MyBatisConfig.java");
		}
	}
	
	public static SqlSessionFactory getSqlSession_f() {
		return sqlSession_f;
	}
}
