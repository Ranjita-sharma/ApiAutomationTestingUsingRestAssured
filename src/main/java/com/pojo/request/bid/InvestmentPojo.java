package com.pojo.request.bid;

public class InvestmentPojo {
    public Integer investorId;
    public Integer loanId;
    public Integer opportunityAmount;
    public Integer opportunityId;
    public Integer participationAmount;

    public Integer getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Integer investorId) {
        this.investorId = investorId;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getOpportunityAmount() {
        return opportunityAmount;
    }

    public void setOpportunityAmount(Integer opportunityAmount) {
        this.opportunityAmount = opportunityAmount;
    }

    public Integer getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(Integer opportunityId) {
        this.opportunityId = opportunityId;
    }

    public Integer getParticipationAmount() {
        return participationAmount;
    }

    public void setParticipationAmount(Integer participationAmount) {
        this.participationAmount = participationAmount;
    }
}
