package com.pojo.request.loginPojo;

public class loginOTP {
    private String mobile;
    private String otp;
    private String otpType;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getOtpType() {
        return otpType;
    }

    public void setOtpType(String otpType) {
        this.otpType = otpType;
    }

    @Override
    public String toString() {
        return "loginOTP{" +
                "mobile='" + mobile + '\'' +
                ", otp=" + otp +
                ", otpType='" + otpType + '\'' +
                '}';
    }
}
