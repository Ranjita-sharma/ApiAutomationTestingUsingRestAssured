package com.pojo.request.signUpPojo;

import java.util.List;

public class RamQuestionA {
   private int optionId;
   private int userId;
   private  List<subOption> subOption;

    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<subOption> getSubOption() {
        return subOption;
    }

    public void setSubOption(List<subOption> subOption) {
        this.subOption = subOption;
    }

    @Override
    public String toString() {
        return "RamQuestionA{" +
                "optionId=" + optionId +
                ", userId=" + userId +
                ", subOption=" + subOption +
                '}';
    }
}
