package com.ggomsu.app.member.service;

import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

// 작성자 : 박성민

public class MemberEncryptOk{
	
	// 반환된 info는 호출한 쪽에서 값을 담아서 DB에 저장.
	// 예외처리가 필요함.
	public MemberEncryptInfo encrypt(String password) throws Exception {
		
		MemberEncryptInfo info = new MemberEncryptInfo();
		
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);

		info.setSalt(salt);
		salt = info.getSalt().getBytes();
		
		// RFC 2898 - IETF와 포트폴리오로서의 성능을 고려하여 iteration을 1,000으로 설정
		// 단, OWASP 2015는 iteration을 10,000으로 설정하기를 권장하였음.
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 1000, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = factory.generateSecret(spec).getEncoded();
		
		info.setPassword(hash);
		
		return info;
	}
	
	// DB에서 origin과 salt를 불러와서 inserted와 비교
	// 예외처리가 필요함.
	public boolean compare(String inserted, String origin, String salt) throws Exception{
		
		KeySpec spec = new PBEKeySpec(inserted.toCharArray(), salt.getBytes(), 1000, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = factory.generateSecret(spec).getEncoded();
		
		String password = "";
		for(byte b : hash) {
			password += b;
		}
		
		// 로그인 성공 시 true
		return password.equals(origin);
	}
}
