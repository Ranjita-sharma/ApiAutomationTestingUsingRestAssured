package com.pojo.request.signUpPojo;

import java.util.List;

public class SignUP_Investor_ProfileCompletion_Pojo {
    private String annualIncome;
    private String companyName;
    private int dashboardStageStatus;
    private String dateOfBirth;
    private String employer;
    private String estimateAnnualDeposit;
    private String estimateAnnualInvestment;
    private String focalResponse;
    private String fullNameArab;
    private String fullNameEng;
    private String gender;
    private String idExpiryDate;
    private String iqamaId;
    private boolean isCreditedInvestor;
    private boolean isSaudi;
    private String jobStatus;
    private String jobTitle;
    private String nationalAddress;
    private String nationality;
    private String natureOfBusiness;
   // private HashMap<String, String> ramAnswers;
    private List<RamAnswerListDTO> ramAnswerListDTO;
    private RamAnswers ramAnswers ;
    private List<RamQuestionA> ramQuestionA;
    private String sourceOfInvestment;
    private int userId;

    public String getAnnualIncome() {
        return annualIncome;
    }
    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public int getDashboardStageStatus() {
        return dashboardStageStatus;
    }
    public void setDashboardStageStatus(int dashboardStageStatus) {
        this.dashboardStageStatus = dashboardStageStatus;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getEmployer() {
        return employer;
    }
    public void setEmployer(String employer) {
        this.employer = employer;
    }
    public String getEstimateAnnualDeposit() {
        return estimateAnnualDeposit;
    }
    public void setEstimateAnnualDeposit(String estimateAnnualDeposit) {
        this.estimateAnnualDeposit = estimateAnnualDeposit;
    }
    public String getEstimateAnnualInvestment() {
        return estimateAnnualInvestment;
    }
    public void setEstimateAnnualInvestment(String estimateAnnualInvestment) {
        this.estimateAnnualInvestment = estimateAnnualInvestment;
    }
    public String getFocalResponse() {
        return focalResponse;
    }
    public void setFocalResponse(String focalResponse) {
        this.focalResponse = focalResponse;
    }
    public String getFullNameArab() {
        return fullNameArab;
    }
    public void setFullNameArab(String fullNameArab) {
        this.fullNameArab = fullNameArab;
    }
    public String getFullNameEng() {
        return fullNameEng;
    }
    public void setFullNameEng(String fullNameEng) {
        this.fullNameEng = fullNameEng;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getIdExpiryDate() {
        return idExpiryDate;
    }
    public void setIdExpiryDate(String idExpiryDate) {
        this.idExpiryDate = idExpiryDate;
    }
    public String getIqamaId() {
        return iqamaId;
    }
    public void setIqamaId(String iqamaId) {
        this.iqamaId = iqamaId;
    }
    public boolean getIsCreditedInvestor() {
        return isCreditedInvestor;
    }
      public void setCreditedInvestor(boolean creditedInvestor) {
        isCreditedInvestor = creditedInvestor;
    }
    public boolean getIsSaudi() {
        return isSaudi;
    }
    public void setSaudi(boolean saudi) {
        isSaudi = saudi;
    }
    public String getJobStatus() {
        return jobStatus;
    }
    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getNationalAddress() {
        return nationalAddress;
    }
    public void setNationalAddress(String nationalAddress) {
        this.nationalAddress = nationalAddress;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getNatureOfBusiness() {
        return natureOfBusiness;
    }
    public void setNatureOfBusiness(String natureOfBusiness) {
        this.natureOfBusiness = natureOfBusiness;
    }
    public List<RamAnswerListDTO> getRamAnswerListDTO() {
        return ramAnswerListDTO;
    }
    public void setRamAnswerListDTO(List<RamAnswerListDTO>ramAnswerListDTO ) {
        this.ramAnswerListDTO = ramAnswerListDTO;
    }
    public RamAnswers getRamAnswers() {
        return ramAnswers;
    }
    public void setRamAnswers(RamAnswers ramAnswers) {
        this.ramAnswers = ramAnswers;
    }
    public List<RamQuestionA> getRamQuestionA() {
        return ramQuestionA;
    }
    public void setRamQuestionA(List<RamQuestionA> ramQuestionA) {
        this.ramQuestionA = ramQuestionA;
    }
    public String getSourceOfInvestment() {
        return sourceOfInvestment;
    }
    public void setSourceOfInvestment(String sourceOfInvestment) {
        this.sourceOfInvestment = sourceOfInvestment;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SignUP_Investor_ProfileCompletion_Pojo{" +
                "annualIncome='" + annualIncome + '\'' +
                ", companyName='" + companyName + '\'' +
                ", dashboardStageStatus=" + dashboardStageStatus +
                ", dateOfBirth=" + dateOfBirth +
                ", employer='" + employer + '\'' +
                ", estimateAnnualDeposit='" + estimateAnnualDeposit + '\'' +
                ", estimateAnnualInvestment='" + estimateAnnualInvestment + '\'' +
                ", focalResponse='" + focalResponse + '\'' +
                ", fullNameArab='" + fullNameArab + '\'' +
                ", fullNameEng='" + fullNameEng + '\'' +
                ", gender='" + gender + '\'' +
                ", idExpiryDate=" + idExpiryDate +
                ", iqamaId='" + iqamaId + '\'' +
                ", isCreditedInvestor=" + isCreditedInvestor +
                ", isSaudi=" + isSaudi +
                ", jobStatus='" + jobStatus + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", nationalAddress='" + nationalAddress + '\'' +
                ", nationality='" + nationality + '\'' +
                ", natureOfBusiness='" + natureOfBusiness + '\'' +
                ", ramAnswerListDTO=" + ramAnswerListDTO +
                ", ramAnswers=" + ramAnswers +
                ", ramQuestionA=" + ramQuestionA +
                ", sourceOfInvestment='" + sourceOfInvestment + '\'' +
                ", userId=" + userId +
                '}';
    }
}
