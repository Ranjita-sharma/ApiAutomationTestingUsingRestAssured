package com.pojo.UploadDocument_Investors;

import java.util.List;

public class DocumentReqDTOList_inv {
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
}
