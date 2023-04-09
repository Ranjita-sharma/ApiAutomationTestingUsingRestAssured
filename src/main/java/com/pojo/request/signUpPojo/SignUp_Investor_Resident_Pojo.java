package com.pojo.request.signUpPojo;

public class SignUp_Investor_Resident_Pojo {
    private String email;
    private String iqamaId;
    private boolean isSaudi;
    private String nationality;
    private String password;
    private String phoneNumber;
    private String userType;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIqamaId() {
        return iqamaId;
    }

    public void setIqamaId(String iqamaId) {
        this.iqamaId = iqamaId;
    }

    public boolean getIsSaudi() {
        return isSaudi;
    }

    public void setIsSaudi(boolean isSaudi) {
        this.isSaudi = isSaudi;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
