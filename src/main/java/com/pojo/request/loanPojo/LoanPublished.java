package com.pojo.request.loanPojo;

import com.fasterxml.jackson.annotation.JsonFormat;

public class LoanPublished {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "Asia/Riyadh")
	public String publishDateTime;
	public Integer loanId;
	


public String getPublishDateTime() {
		return publishDateTime;
	}
	public void setPublishDateTime(String publishDateTime) {
		this.publishDateTime = publishDateTime;
	}
	//public DateTime getPublishDateTime() {
//		return publishDateTime;
//	}
//	public void setPublishDateTime(DateTime publishDateTime) {
//		this.publishDateTime = publishDateTime;
//	}
	//	public String getPublishDateTime() {
//		return publishDateTime;
//	}
//	public void setPublishDateTime(String publishDateTime) {
//		this.publishDateTime = publishDateTime;
//	}
	public Integer getLoanId() {
		return loanId;
	}
	public void setLoanId(Integer loanId) {
		this.loanId = loanId;
	}
//	@Override
//	public String toString() {
//		return "LoanPublished [publishDateTime=" + publishDateTime + ", loanId=" + loanId + "]";
//	}
	
	}
	
	
	


