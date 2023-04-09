package com.pojo.request.autoInvestPojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostSaveAutoBid {

private List<BidReqDtoList> bidReqDtoList ;

public List<BidReqDtoList> getBidReqDtoList() {
return bidReqDtoList;
}

public void setBidReqDtoList(List<BidReqDtoList> bidReqDtoList) {
this.bidReqDtoList = bidReqDtoList;
}

}


		
	//	public void setInvestorId(Integer investorId) {
//		this.investorId = investorId;
//	}
//
//	public Boolean getIsAutoInvest() {
//		return isAutoInvest;
//	}
//
//	public void setIsAutoInvest(Boolean isAutoInvest) {
//		this.isAutoInvest = isAutoInvest;
//	}
//
//	public Integer getLoanId() {
//		return loanId;
//	}
//
//	public void setLoanId(Integer loanId) {
//		this.loanId = loanId;
//	}
//
//	public Integer getOpportunityAmount() {
//		return opportunityAmount;
//	}
//
//	public void setOpportunityAmount(Integer opportunityAmount) {
//		this.opportunityAmount = opportunityAmount;
//	}
//
//	public Integer getOpportunityId() {
//		return opportunityId;
//	}
//
//	public void setOpportunityId(Integer opportunityId) {
//		this.opportunityId = opportunityId;
//	}
//
//	public Integer getParticipationAmount() {
//		return participationAmount;
//	}
//
//	public void setParticipationAmount(Integer participationAmount) {
//		this.participationAmount = participationAmount;
//	}
//
//	public Integer getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}



