package com.pojo.request;

import com.pojo.request.loanPojo.ApplyForLoan.DocumentReqDTOList;

import java.util.List;

public class UploadDocPojo {
    public List<DocumentReqDTOList> documentReqDTOList;
    private String borrowerId;
    private String loanId;

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }
}
