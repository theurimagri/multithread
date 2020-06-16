
package com.github.theurimagri.multithread.model;

public class CompanyDetailedCount {

    private String companyName;

    private String productName;

    private Long soldCount;

    public CompanyDetailedCount(String companyName, String productName, Long soldCount) {
        this.companyName = companyName;
        this.productName = productName;
        this.soldCount = soldCount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String value) {
        this.productName = value;
    }

    public Long getSoldCount() {
        return soldCount;
    }

    public void setSoldCount(Long value) {
        this.soldCount = value;
    }

}
