package com.pojo.request.AdminPojo;

public class AdminDepositFund {
    public int userId;
    public String amount;
    public String type;
    public Boolean onHold;
    public String description;
    public Boolean sourceId;
    public String referenceId;
    public String sourceType;
    public String amountType;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getOnHold() {
        return onHold;
    }

    public void setOnHold(Boolean onHold) {
        this.onHold = onHold;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSourceId() {
        return sourceId;
    }

    public void setSourceId(Boolean sourceId) {
        this.sourceId = sourceId;
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }
}




