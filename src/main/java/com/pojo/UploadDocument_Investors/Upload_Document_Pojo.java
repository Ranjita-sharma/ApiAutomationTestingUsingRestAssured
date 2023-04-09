package com.pojo.UploadDocument_Investors;

import java.util.List;

public class Upload_Document_Pojo {
    private boolean isInvestor;
    private List<DocumentReqDTOList_inv> documentReqDTO;

    public boolean getIsInvestor() {
        return isInvestor;
    }
    public void setIsInvestor(boolean isInvestor) {
        this.isInvestor = isInvestor;
    }
    public List<DocumentReqDTOList_inv> getDocumentReqDTO() {
        return documentReqDTO;
    }
    public void setDocumentReqDTOList(List<DocumentReqDTOList_inv> documentReqDTO) {
        this.documentReqDTO = documentReqDTO;
    }

}
