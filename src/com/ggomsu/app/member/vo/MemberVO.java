package com.ggomsu.app.member.vo;

// 작성자 : 박성민

public class MemberVO {
	
	// 로그인
	private String email;
	private String password;
	private String salt;
	private String signAt;
	private String passwordAlertAt;
	
	// 게시판 이용
	private String nickname;
	private String profileImageUrl;
	
	// 개인정보
	private String name;
	private String birthDate;
	private String sex;
	private String telecomValue;
	private String contact;
	private String zipcode;
	private String address;
	private String addressDetail;
	
	// 이용사항 및 약관
	private String agreedTermAt;
	private String agreedMarketingAt;
	private String createdAt;
	
	// 회원상태
	private String statusValue;
	private int abuseCount;
	
	// 서비스 이용 설정
	private boolean alarmFlag;
	private boolean darkModeFlag;
	
	public MemberVO() { ; }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSignAt() {
		return signAt;
	}

	public void setSignAt(String signAt) {
		this.signAt = signAt;
	}

	public String getPasswordAlertAt() {
		return passwordAlertAt;
	}

	public void setPasswordAlertAt(String passwordAlertAt) {
		this.passwordAlertAt = passwordAlertAt;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelecomValue() {
		return telecomValue;
	}

	public void setTelecomValue(String telecomValue) {
		this.telecomValue = telecomValue;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getAgreedTermAt() {
		return agreedTermAt;
	}

	public void setAgreedTermAt(String agreedTermAt) {
		this.agreedTermAt = agreedTermAt;
	}

	public String getAgreedMarketingAt() {
		return agreedMarketingAt;
	}

	public void setAgreedMarketingAt(String agreedMarketingAt) {
		this.agreedMarketingAt = agreedMarketingAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	public int getAbuseCount() {
		return abuseCount;
	}

	public void setAbuseCount(int abuseCount) {
		this.abuseCount = abuseCount;
	}
	
	public boolean isAlarmFlag() {
		return alarmFlag;
	}
	
	public void setAlarmFlag(boolean alarmFlag) {
		this.alarmFlag = alarmFlag;
	}

	public boolean isDarkModeFlag() {
		return darkModeFlag;
	}

	public void setDarkModeFlag(boolean darkModeFlag) {
		this.darkModeFlag = darkModeFlag;
	}
}
