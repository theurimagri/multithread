
package com.github.theurimagri.multithread.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "saleCounter", propOrder = {
    "numberOfStores",
    "rangeCount",
    "rangeDetailCount"
})
@XmlRootElement
public class SaleCounter {

    protected int numberOfStores;

    @XmlElement(required = true)
    protected RangeCount rangeCount;

    protected List<RangeDetailCount> rangeDetailCount;

    public SaleCounter() {
        rangeCount = new RangeCount();
        rangeDetailCount = new ArrayList<>();
    }

    public SaleCounter(int numberOfStores, RangeCount rangeCount, List<RangeDetailCount> rangeDetailCount) {
        this.numberOfStores = numberOfStores;
        this.rangeCount = rangeCount;
        this.rangeDetailCount = rangeDetailCount;
    }

    public int getNumberOfStores() {
        return numberOfStores;
    }

    public void setNumberOfStores(int value) {
        this.numberOfStores = value;
    }

    public RangeCount getRangeCount() {
        return rangeCount;
    }

    public void setRangeCount(RangeCount value) {
        this.rangeCount = value;
    }

    public List<RangeDetailCount> getRangeDetailCount() {
        return this.rangeDetailCount;
    }

    public void setRangeDetailCount(List<RangeDetailCount> rangeDetailCount) {
        this.rangeDetailCount = rangeDetailCount;
    }

    @Override
    public String toString() {
        return String.format("[Number of Stores: %d,\nRange Count: [%s],\nRange Detailed Count: [%s]]", numberOfStores, rangeCount.toString(), rangeDetailCount.stream().map(RangeDetailCount::toString).collect(Collectors.joining(System.lineSeparator())));
    }
}
