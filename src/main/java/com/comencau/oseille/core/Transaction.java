package com.comencau.oseille.core;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * TODO
 *
 * @author Nicolas FABRE
 * @since 2018-03-27
 */
public class Transaction {

    private LocalDate date;

    private String label;

    private BigDecimal amount;

    private Long id;

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(label).append(amount).append(date).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != getClass()) return false;
        Transaction rhs = (Transaction) obj;
        return new EqualsBuilder().appendSuper(super.equals(obj)).append(date, rhs.date).append(label, rhs.label).append(amount, rhs.amount).isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
