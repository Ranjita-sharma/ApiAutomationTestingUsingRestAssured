package com.pojo.request.signUpPojo;

import java.util.List;

public class RamAnswerListDTO {
        public int userId;
        String quetionId;
        List<OptionSelectionDTO> optionSelectionDTO;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getQuetionId() {
        return quetionId;
    }

    public void setQuetionId(String quetionId) {
        this.quetionId = quetionId;
    }

    public List<OptionSelectionDTO> getOptionSelectionDTO() {
        return optionSelectionDTO;
    }

    public void setOptionSelectionDTO(List<OptionSelectionDTO> optionSelectionDTO) {
        this.optionSelectionDTO = optionSelectionDTO;
    }

    @Override
    public String toString() {
        return "RamAnswerListDTO{" +
                "userId=" + userId +
                ", quetionId='" + quetionId + '\'' +
                ", optionSelectionDTO=" + optionSelectionDTO +
                '}';
    }
}
