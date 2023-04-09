
package com.pojo.request.AdminPojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminAddInvestmentTerms {

    @SerializedName("riskCategory")
    @Expose
    private String riskCategory;
    @SerializedName("successFees")
    @Expose
    private String successFees;
    @SerializedName("loanTenure")
    @Expose
    private String loanTenure;
    @SerializedName("isIcCodes")
    @Expose
    private List<String> isIcCodes = null;
    @SerializedName("minCap")
    @Expose
    private String minCap;
    @SerializedName("maxCap")
    @Expose
    private String maxCap;
    @SerializedName("corporateId")
    @Expose
    private String corporateId;

    public String getRiskCategory() {
        return riskCategory;
    }

    public void setRiskCategory(String riskCategory) {
        this.riskCategory = riskCategory;
    }

    public String getSuccessFees() {
        return successFees;
    }

    public void setSuccessFees(String successFees) {
        this.successFees = successFees;
    }

    public String getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(String loanTenure) {
        this.loanTenure = loanTenure;
    }

    public List<String> getIsIcCodes() {
        return isIcCodes;
    }

    public void setIsIcCodes(List<String> isIcCodes) {
        this.isIcCodes = isIcCodes;
    }

    public String getMinCap() {
        return minCap;
    }

    public void setMinCap(String minCap) {
        this.minCap = minCap;
    }

    public String getMaxCap() {
        return maxCap;
    }

    public void setMaxCap(String maxCap) {
        this.maxCap = maxCap;
    }

    public String getCorporateId() {
        return corporateId;
    }

    public void setCorporateId(String corporateId) {
        this.corporateId = corporateId;
    }

}
