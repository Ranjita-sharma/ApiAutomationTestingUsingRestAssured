package com.pojo.request.signUpPojo;

public class LoginPOJO {
	public String mobile;
	public Integer otp;
	public String otpType;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	public String getOtpType() {
		return otpType;
	}
	public void setOtpType(String otpType) {
		this.otpType = otpType;
	}
	public LoginPOJO(String mobile, int otp, String otpType) {
		this.mobile = mobile;
		this.otp = otp;
		this.otpType = otpType;
	}
	
	
}
