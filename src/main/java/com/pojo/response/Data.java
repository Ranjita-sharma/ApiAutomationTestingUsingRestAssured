
package com.pojo.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    @SerializedName("loanApplications")
    @Expose
    private List<LoanApplication> loanApplications = null;
    @SerializedName("totalItems")
    @Expose
    private Integer totalItems;
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<LoanApplication> getLoanApplications() {
        return loanApplications;
    }

    public void setLoanApplications(List<LoanApplication> loanApplications) {
        this.loanApplications = loanApplications;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

}
