
package com.github.theurimagri.multithread.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "detailedCount", propOrder = {
    "productName",
    "soldCount"
})
public class DetailedCount {

    @XmlElement(required = true)
    protected String productName;

    protected Long soldCount;

    public DetailedCount() {
    }

    public DetailedCount(String productName, Long soldCount) {
        this.productName = productName;
        this.soldCount = soldCount;
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

    @Override
    public String toString() {
        return String.format("[Product Name: %s, Sold Count: %d]", productName, soldCount);
    }
}
