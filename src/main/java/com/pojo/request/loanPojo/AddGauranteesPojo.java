package com.pojo.request.loanPojo;

public class AddGauranteesPojo {
    private String loanId;
    private String gauranteenameen;
    private String gauranteenamear;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getGauranteenameen() {
        return gauranteenameen;
    }

    public void setGauranteenameen(String gauranteenameen) {
        this.gauranteenameen = gauranteenameen;
    }

    public String getGauranteenamear() {
        return gauranteenamear;
    }

    public void setGauranteenamear(String gauranteenamear) {
        this.gauranteenamear = gauranteenamear;
    }
}
