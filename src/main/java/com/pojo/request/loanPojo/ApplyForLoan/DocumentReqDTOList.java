package com.pojo.request.loanPojo.ApplyForLoan;

import com.pojo.UploadDocument_Investors.FileList;

import java.util.List;

public class DocumentReqDTOList {
	//public String loanId;
	public String  documentType;

	public List<FileList> fileList;

	public String getDocumentType() {
	return documentType;
	}

	public void setDocumentType(String documentType) {
	this.documentType = documentType;
	}

	public List<FileList> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileList> fileList) {
		this.fileList = fileList;
	}

//	public String getLoanId() {
//		return loanId;
//	}
//
//	public void setLoanId(String loanId) {
//		this.loanId = loanId;
//	}
}
