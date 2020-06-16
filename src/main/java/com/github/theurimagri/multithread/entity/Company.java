package com.github.theurimagri.multithread.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
public class Company {

    @Id
    @Column(name = "NAME", nullable = false, unique = true, updatable = false)
    private String name;

    @ManyToMany(cascade = ALL, fetch = LAZY)
    @JoinTable(name = "COMPANY_PRODUCT",
            joinColumns = { @JoinColumn(name = "COMPANY_NAME") },
            inverseJoinColumns = { @JoinColumn(name = "PRODUCT_NAME") })
    private Set<Product> products;

    @OneToMany(fetch = LAZY)
    @JoinColumn(name="COMPANY_NAME")
    private Set<Sale> sales;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Company other = (Company) obj;

        return new EqualsBuilder()
                .append(this.name, other.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.name)
                .build();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", this.name)
                .toString();
    }
}
