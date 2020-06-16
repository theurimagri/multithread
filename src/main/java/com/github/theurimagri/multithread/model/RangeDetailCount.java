
package com.github.theurimagri.multithread.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rangeDetailCount", propOrder = {
    "storeName",
    "detailedCount"
})
public class RangeDetailCount {

    @XmlElement(required = true)
    protected String storeName;

    protected List<DetailedCount> detailedCount;

    public RangeDetailCount() {
    }

    public RangeDetailCount(String storeName, List<DetailedCount> detailedCount) {
        this.storeName = storeName;
        this.detailedCount = detailedCount;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String value) {
        this.storeName = value;
    }

    public List<DetailedCount> getDetailedCount() {
        return this.detailedCount;
    }

    public void setDetailedCount(List<DetailedCount> detailedCount) {
        this.detailedCount = detailedCount;
    }

    @Override
    public String toString() {
        return String.format("[Store Name: %s, \nDetailed Count: [%s]]", storeName, detailedCount.stream().map(DetailedCount::toString).collect(Collectors.joining(System.lineSeparator())));
    }
}
