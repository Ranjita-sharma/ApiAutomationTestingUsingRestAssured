
package com.pojo.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanApplication {
    @SerializedName("collectionFeesPct")
    @Expose
    private Integer collectionFeesPct;
    @SerializedName("ROIForLoanPct")
    @Expose
    private Double rOIForLoanPct;
    @SerializedName("collectionMechanism")
    @Expose
    private String collectionMechanism;
    @SerializedName("repaymentAmount")
    @Expose
    private Double repaymentAmount;
    @SerializedName("rmComments")
    @Expose
    private String rmComments;
    @SerializedName("approvedLoanDurationDays")
    @Expose
    private Integer approvedLoanDurationDays;
    @SerializedName("autoInvest")
    @Expose
    private Boolean autoInvest;
    @SerializedName("publishDateTime")
    @Expose
    private PublishDateTime publishDateTime;
    @SerializedName("approvedLoanAmount")
    @Expose
    private Integer approvedLoanAmount;
    @SerializedName("financingStructure")
    @Expose
    private String financingStructure;
    @SerializedName("otherFeeFxd")
    @Expose
    private Integer otherFeeFxd;
    @SerializedName("purposeOfLoan")
    @Expose
    private String purposeOfLoan;
    @SerializedName("createdAt")
    @Expose
    private CreatedAt createdAt;
    @SerializedName("invoice_date_time")
    @Expose
    private InvoiceDateTime invoiceDateTime;
    @SerializedName("opportunityId")
    @Expose
    private Integer opportunityId;
    @SerializedName("requestedLoanDurationDays")
    @Expose
    private Integer requestedLoanDurationDays;
    @SerializedName("repaymentType")
    @Expose
    private String repaymentType;
    @SerializedName("loanStatus")
    @Expose
    private String loanStatus;
    @SerializedName("estimatedManagementFees")
    @Expose
    private Integer estimatedManagementFees;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("aprPct")
    @Expose
    private Integer aprPct;
    @SerializedName("creditTeamComments")
    @Expose
    private String creditTeamComments;
    @SerializedName("updatedAt")
    @Expose
    private UpdatedAt updatedAt;
    @SerializedName("requestedLoanAmount")
    @Expose
    private Integer requestedLoanAmount;
    @SerializedName("estimatedVatOnManagementFees")
    @Expose
    private Integer estimatedVatOnManagementFees;
    @SerializedName("borrowerId")
    @Expose
    private Integer borrowerId;
    @SerializedName("managementFeesPct")
    @Expose
    private Double managementFeesPct;
    @SerializedName("ticketSize")
    @Expose
    private Integer ticketSize;
    @SerializedName("estimatedVatOnOtherFees")
    @Expose
    private Double estimatedVatOnOtherFees;
    @SerializedName("successFeesPct")
    @Expose
    private Integer successFeesPct;
    @SerializedName("penaltyFeesFxd")
    @Expose
    private Integer penaltyFeesFxd;
    @SerializedName("buyerName")
    @Expose
    private String buyerName;
    @SerializedName("purposeOfUsage")
    @Expose
    private String purposeOfUsage;
    @SerializedName("riskRate")
    @Expose
    private String riskRate;
    @SerializedName("isProgress")
    @Expose
    private Boolean isProgress;
    @SerializedName("roiforLoanPct")
    @Expose
    private Double roiforLoanPct;
    @SerializedName("approvedInterestRateAnnualPct")
    @Expose
    private Double approvedInterestRateAnnualPct;

    public Integer getCollectionFeesPct() {
        return collectionFeesPct;
    }

    public void setCollectionFeesPct(Integer collectionFeesPct) {
        this.collectionFeesPct = collectionFeesPct;
    }

    public Double getROIForLoanPct() {
        return rOIForLoanPct;
    }

    public void setROIForLoanPct(Double rOIForLoanPct) {
        this.rOIForLoanPct = rOIForLoanPct;
    }

    public String getCollectionMechanism() {
        return collectionMechanism;
    }

    public void setCollectionMechanism(String collectionMechanism) {
        this.collectionMechanism = collectionMechanism;
    }

    public Double getRepaymentAmount() {
        return repaymentAmount;
    }

    public void setRepaymentAmount(Double repaymentAmount) {
        this.repaymentAmount = repaymentAmount;
    }

    public String getRmComments() {
        return rmComments;
    }

    public void setRmComments(String rmComments) {
        this.rmComments = rmComments;
    }

    public Integer getApprovedLoanDurationDays() {
        return approvedLoanDurationDays;
    }

    public void setApprovedLoanDurationDays(Integer approvedLoanDurationDays) {
        this.approvedLoanDurationDays = approvedLoanDurationDays;
    }

    public Boolean getAutoInvest() {
        return autoInvest;
    }

    public void setAutoInvest(Boolean autoInvest) {
        this.autoInvest = autoInvest;
    }

    public PublishDateTime getPublishDateTime() {
        return publishDateTime;
    }

    public void setPublishDateTime(PublishDateTime publishDateTime) {
        this.publishDateTime = publishDateTime;
    }

    public Integer getApprovedLoanAmount() {
        return approvedLoanAmount;
    }

    public void setApprovedLoanAmount(Integer approvedLoanAmount) {
        this.approvedLoanAmount = approvedLoanAmount;
    }

    public String getFinancingStructure() {
        return financingStructure;
    }

    public void setFinancingStructure(String financingStructure) {
        this.financingStructure = financingStructure;
    }

    public Integer getOtherFeeFxd() {
        return otherFeeFxd;
    }

    public void setOtherFeeFxd(Integer otherFeeFxd) {
        this.otherFeeFxd = otherFeeFxd;
    }

    public String getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public void setPurposeOfLoan(String purposeOfLoan) {
        this.purposeOfLoan = purposeOfLoan;
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAt createdAt) {
        this.createdAt = createdAt;
    }

    public InvoiceDateTime getInvoiceDateTime() {
        return invoiceDateTime;
    }

    public void setInvoiceDateTime(InvoiceDateTime invoiceDateTime) {
        this.invoiceDateTime = invoiceDateTime;
    }

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Integer opportunityId) {
        this.opportunityId = opportunityId;
    }

    public Integer getRequestedLoanDurationDays() {
        return requestedLoanDurationDays;
    }

    public void setRequestedLoanDurationDays(Integer requestedLoanDurationDays) {
        this.requestedLoanDurationDays = requestedLoanDurationDays;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Integer getEstimatedManagementFees() {
        return estimatedManagementFees;
    }

    public void setEstimatedManagementFees(Integer estimatedManagementFees) {
        this.estimatedManagementFees = estimatedManagementFees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAprPct() {
        return aprPct;
    }

    public void setAprPct(Integer aprPct) {
        this.aprPct = aprPct;
    }

    public String getCreditTeamComments() {
        return creditTeamComments;
    }

    public void setCreditTeamComments(String creditTeamComments) {
        this.creditTeamComments = creditTeamComments;
    }

    public UpdatedAt getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(UpdatedAt updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getRequestedLoanAmount() {
        return requestedLoanAmount;
    }

    public void setRequestedLoanAmount(Integer requestedLoanAmount) {
        this.requestedLoanAmount = requestedLoanAmount;
    }

    public Integer getEstimatedVatOnManagementFees() {
        return estimatedVatOnManagementFees;
    }

    public void setEstimatedVatOnManagementFees(Integer estimatedVatOnManagementFees) {
        this.estimatedVatOnManagementFees = estimatedVatOnManagementFees;
    }

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public Double getManagementFeesPct() {
        return managementFeesPct;
    }

    public void setManagementFeesPct(Double managementFeesPct) {
        this.managementFeesPct = managementFeesPct;
    }

    public Integer getTicketSize() {
        return ticketSize;
    }

    public void setTicketSize(Integer ticketSize) {
        this.ticketSize = ticketSize;
    }

    public Double getEstimatedVatOnOtherFees() {
        return estimatedVatOnOtherFees;
    }

    public void setEstimatedVatOnOtherFees(Double estimatedVatOnOtherFees) {
        this.estimatedVatOnOtherFees = estimatedVatOnOtherFees;
    }

    public Integer getSuccessFeesPct() {
        return successFeesPct;
    }

    public void setSuccessFeesPct(Integer successFeesPct) {
        this.successFeesPct = successFeesPct;
    }

    public Integer getPenaltyFeesFxd() {
        return penaltyFeesFxd;
    }

    public void setPenaltyFeesFxd(Integer penaltyFeesFxd) {
        this.penaltyFeesFxd = penaltyFeesFxd;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getPurposeOfUsage() {
        return purposeOfUsage;
    }

    public void setPurposeOfUsage(String purposeOfUsage) {
        this.purposeOfUsage = purposeOfUsage;
    }

    public String getRiskRate() {
        return riskRate;
    }

    public void setRiskRate(String riskRate) {
        this.riskRate = riskRate;
    }

    public Boolean getIsProgress() {
        return isProgress;
    }

    public void setIsProgress(Boolean isProgress) {
        this.isProgress = isProgress;
    }

    public Double getRoiforLoanPct() {
        return roiforLoanPct;
    }

    public void setRoiforLoanPct(Double roiforLoanPct) {
        this.roiforLoanPct = roiforLoanPct;
    }

    public Double getApprovedInterestRateAnnualPct() {
        return approvedInterestRateAnnualPct;
    }

    public void setApprovedInterestRateAnnualPct(Double approvedInterestRateAnnualPct) {
        this.approvedInterestRateAnnualPct = approvedInterestRateAnnualPct;
    }

}
