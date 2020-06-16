
package com.github.theurimagri.multithread.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rangeCount", propOrder = {
    "detailedCount"
})
public class RangeCount {

    protected List<DetailedCount> detailedCount;

    public RangeCount() {
    }

    public RangeCount(List<DetailedCount> detailedCount) {
        this.detailedCount = detailedCount;
    }

    public List<DetailedCount> getDetailedCount() {
        if (detailedCount == null) {
            detailedCount = new ArrayList<>();
        }
        return this.detailedCount;
    }

    public void setDetailedCount(List<DetailedCount> detailedCount) {
        this.detailedCount = detailedCount;
    }

    @Override
    public String toString() {
        return detailedCount.stream().map(DetailedCount::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}
