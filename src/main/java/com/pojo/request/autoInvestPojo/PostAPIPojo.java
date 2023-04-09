package com.pojo.request.autoInvestPojo;

public class PostAPIPojo {

	private int investorId;
	private int loanId;
	private int opportunityAmount;
	private int opportunityId;
	private int  participationAmount;
	
	public PostAPIPojo(int investorId, int loanId, int opportunityAmount, int opportunityId, int participationAmount) {
		this.investorId = investorId;
		this.loanId = loanId;
		this.opportunityAmount = opportunityAmount;
		this.opportunityId = opportunityId;
		this.participationAmount = participationAmount;
		
		
	}

	public int getInvestorId() {
		return investorId;
	}

	public void setInvestorId(int investorId) {
		this.investorId = investorId;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public int getOpportunityAmount() {
		return opportunityAmount;
	}

	public void setOpportunityAmount(int opportunityAmount) {
		this.opportunityAmount = opportunityAmount;
	}

	public int getOpportunityId() {
		return opportunityId;
	}

	public void setOpportunityId(int opportunityId) {
		this.opportunityId = opportunityId;
	}

	public int getParticipationAmount() {
		return participationAmount;
	}

	public void setParticipationAmount(int participationAmount) {
		this.participationAmount = participationAmount;
	}

	@Override
	public String toString() {
		return "PostAPIPojo [investorId=" + investorId + ", loanId=" + loanId + ", opportunityAmount="
				+ opportunityAmount + ", opportunityId=" + opportunityId + ", participationAmount="
				+ participationAmount + "]";
	}
	
	
	
}
