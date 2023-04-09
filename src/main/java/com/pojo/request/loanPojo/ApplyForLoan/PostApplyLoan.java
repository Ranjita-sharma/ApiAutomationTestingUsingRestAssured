package com.pojo.request.loanPojo.ApplyForLoan;

import java.util.List;

public class PostApplyLoan {
	public Integer requestedLoanAmount;
	public Integer requestDuration;
	public Integer productId;
	public String invoiceDate;
	public String buyerName;
	public String purposeOfUsage;
	public String borrowerId;
	public String invoiceDocument;
	public String invoiceDocumentUrl;
	public String deliveryNoteDocument;
	public String deliveryNoteDocumentUrl;
	public String contractDocument;
	public String contractDocumentUrl;
	public List<DocumentReqDTOList> documentReqDTOList;
	public Integer getRequestedLoanAmount() {
		return requestedLoanAmount;
	}
	public void setRequestedLoanAmount(Integer requestedLoanAmount) {
		this.requestedLoanAmount = requestedLoanAmount;
	}
	public Integer getRequestDuration() {
		return requestDuration;
	}
	public void setRequestDuration(Integer requestDuration) {
		this.requestDuration = requestDuration;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
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
	public String getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(String borrowerId) {
		this.borrowerId = borrowerId;
	}
	public String getInvoiceDocument() {
		return invoiceDocument;
	}
	public void setInvoiceDocument(String invoiceDocument) {
		this.invoiceDocument = invoiceDocument;
	}
	public String getInvoiceDocumentUrl() {
		return invoiceDocumentUrl;
	}
	public void setInvoiceDocumentUrl(String invoiceDocumentUrl) {
		this.invoiceDocumentUrl = invoiceDocumentUrl;
	}
	public String getDeliveryNoteDocument() {
		return deliveryNoteDocument;
	}
	public void setDeliveryNoteDocument(String deliveryNoteDocument) {
		this.deliveryNoteDocument = deliveryNoteDocument;
	}
	public String getDeliveryNoteDocumentUrl() {
		return deliveryNoteDocumentUrl;
	}
	public void setDeliveryNoteDocumentUrl(String deliveryNoteDocumentUrl) {
		this.deliveryNoteDocumentUrl = deliveryNoteDocumentUrl;
	}
	public String getContractDocument() {
		return contractDocument;
	}
	public void setContractDocument(String contractDocument) {
		this.contractDocument = contractDocument;
	}
	public String getContractDocumentUrl() {
		return contractDocumentUrl;
	}
	public void setContractDocumentUrl(String contractDocumentUrl) {
		this.contractDocumentUrl = contractDocumentUrl;
	}
	public List<DocumentReqDTOList> getDocumentReqDTOList() {
		return documentReqDTOList;
	}
	public void setDocumentReqDTOList(List<DocumentReqDTOList> documentReqDTOList) {
		this.documentReqDTOList = documentReqDTOList;
	}
	
}