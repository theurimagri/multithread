package com.github.theurimagri.multithread.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"IDENTIFICATION_NUMBER", "COMPANY_NAME", "PRODUCT_NAME"}))
public class Sale {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID", nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(name = "IDENTIFICATION_NUMBER", nullable = false)
    private String identificationNumber;

    @Column(name = "COMPANY_NAME", nullable = false)
    private String company;

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String product;

    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    public Long getId() {
        return id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Sale other = (Sale) obj;

        return new EqualsBuilder()
                .append(this.id, other.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.id)
                .build();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", this.id)
                .append("identificationNumber", this.identificationNumber)
                .append("company", this.company)
                .append("product", this.product)
                .append("quantity", this.quantity)
                .toString();
    }
}
