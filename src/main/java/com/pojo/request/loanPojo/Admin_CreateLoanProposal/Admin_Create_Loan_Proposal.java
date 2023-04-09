package com.pojo.request.loanPojo.Admin_CreateLoanProposal;

import java.util.List;

public class Admin_Create_Loan_Proposal {
 private String approvedLoanAmount;
 private int approvedLoanDuration;
 private String approvedInterestRatePct;
 private String riskRate;
 private String managementFeesPct;
 private int vatOnManagementFeesPct;
 private int successFeesPct;
 private int collectionFeesPct;
 private int otherFeeFxd;
 private String ticketSize;
 private String purposeOfLoan;
 private String purposeOfUsage;
 private String collectionMechanism;
 private String financingStructure;
 private String repaymentType;
 private String buyerName;
 private List<Object> gauranteeOthers = null;
 private String publishDateTime;

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getApprovedLoanAmount() {
        return approvedLoanAmount;
    }

    public void setApprovedLoanAmount(String approvedLoanAmount) {
        this.approvedLoanAmount = approvedLoanAmount;
    }

    public int getApprovedLoanDuration() {
        return approvedLoanDuration;
    }

    public void setApprovedLoanDuration(int approvedLoanDuration) {
        this.approvedLoanDuration = approvedLoanDuration;
    }

    public String getApprovedInterestRatePct() {
        return approvedInterestRatePct;
    }

    public void setApprovedInterestRatePct(String approvedInterestRatePct) {
        this.approvedInterestRatePct = approvedInterestRatePct;
    }

    public String getRiskRate() {
        return riskRate;
    }

    public void setRiskRate(String riskRate) {
        this.riskRate = riskRate;
    }

    public String getManagementFeesPct() {
        return managementFeesPct;
    }

    public void setManagementFeesPct(String managementFeesPct) {
        this.managementFeesPct = managementFeesPct;
    }

    public int getVatOnManagementFeesPct() {
        return vatOnManagementFeesPct;
    }

    public void setVatOnManagementFeesPct(int vatOnManagementFeesPct) {
        this.vatOnManagementFeesPct = vatOnManagementFeesPct;
    }

    public int getSuccessFeesPct() {
        return successFeesPct;
    }

    public void setSuccessFeesPct(int successFeesPct) {
        this.successFeesPct = successFeesPct;
    }

    public int getCollectionFeesPct() {
        return collectionFeesPct;
    }

    public void setCollectionFeesPct(int collectionFeesPct) {
        this.collectionFeesPct = collectionFeesPct;
    }

    public int getOtherFeeFxd() {
        return otherFeeFxd;
    }

    public void setOtherFeeFxd(int otherFeeFxd) {
        this.otherFeeFxd = otherFeeFxd;
    }

    public String getTicketSize() {
        return ticketSize;
    }

    public void setTicketSize(String ticketSize) {
        this.ticketSize = ticketSize;
    }

    public String getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public void setPurposeOfLoan(String purposeOfLoan) {
        this.purposeOfLoan = purposeOfLoan;
    }

    public String getPurposeOfUsage() {
        return purposeOfUsage;
    }

    public void setPurposeOfUsage(String purposeOfUsage) {
        this.purposeOfUsage = purposeOfUsage;
    }

    public String getCollectionMechanism() {
        return collectionMechanism;
    }

    public void setCollectionMechanism(String collectionMechanism) {
        this.collectionMechanism = collectionMechanism;
    }

    public String getFinancingStructure() {
        return financingStructure;
    }

    public void setFinancingStructure(String financingStructure) {
        this.financingStructure = financingStructure;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public List<Object> getGauranteeOthers() {
        return gauranteeOthers;
    }

    public void setGauranteeOthers(List<Object> gauranteeOthers) {
        this.gauranteeOthers = gauranteeOthers;
    }

    public String getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(String publishDateTime) {
        this.publishDateTime = publishDateTime;
    }
}
