package com.pojo.request.autoInvestPojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostApplyAutoInvest {

	private Integer distributionA;
	private Integer distributionB;
	private Integer distributionC;
	private Integer distributionD;
	private Integer investorId;
	private Integer maxLoanDuration;
	private Integer maxTicketSize;
	private Integer minLoanDuration;
	private String riskConfig;
	
	public Integer getDistributionA() {
		return distributionA;
	}
	public void setDistributionA(Integer distributionA) {
		this.distributionA = distributionA;
	}
	public Integer getDistributionB() {
		return distributionB;
	}
	public void setDistributionB(Integer distributionB) {
		this.distributionB = distributionB;
	}
	public Integer getDistributionC() {
		return distributionC;
	}
	public void setDistributionC(Integer distributionC) {
		this.distributionC = distributionC;
	}
	public Integer getDistributionD() {
		return distributionD;
	}
	public void setDistributionD(Integer distributionD) {
		this.distributionD = distributionD;
	}
	public Integer getInvestorId() {
		return investorId;
	}
	public void setInvestorId(Integer investorId) {
		this.investorId = investorId;
	}
	public Integer getMaxLoanDuration() {
		return maxLoanDuration;
	}
	public void setMaxLoanDuration(Integer maxLoanDuration) {
		this.maxLoanDuration = maxLoanDuration;
	}
	public Integer getMaxTicketSize() {
		return maxTicketSize;
	}
	public void setMaxTicketSize(Integer maxTicketSize) {
		this.maxTicketSize = maxTicketSize;
	}
	public Integer getMinLoanDuration() {
		return minLoanDuration;
	}
	public void setMinLoanDuration(Integer minLoanDuration) {
		this.minLoanDuration = minLoanDuration;
	}
	public String getRiskConfig() {
		return riskConfig;
	}
	public void setRiskConfig(String riskConfig) {
		this.riskConfig = riskConfig;
	}
	

}
