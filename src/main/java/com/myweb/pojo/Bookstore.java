package com.myweb.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Bookstore {
    private int id;
    private Integer bookId;
    private Integer ownerId;
    private String location;
    private Integer stauts;
    private BigDecimal deposit;
    private BigDecimal fee;
    private Integer days;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bookId", nullable = true)
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "ownerId", nullable = true)
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 255)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "stauts", nullable = true)
    public Integer getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }

    @Basic
    @Column(name = "deposit", nullable = true, precision = 2)
    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    @Basic
    @Column(name = "fee", nullable = true, precision = 2)
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "days", nullable = true)
    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookstore bookstore = (Bookstore) o;

        if (id != bookstore.id) return false;
        if (bookId != null ? !bookId.equals(bookstore.bookId) : bookstore.bookId != null) return false;
        if (ownerId != null ? !ownerId.equals(bookstore.ownerId) : bookstore.ownerId != null) return false;
        if (location != null ? !location.equals(bookstore.location) : bookstore.location != null) return false;
        if (stauts != null ? !stauts.equals(bookstore.stauts) : bookstore.stauts != null) return false;
        if (deposit != null ? !deposit.equals(bookstore.deposit) : bookstore.deposit != null) return false;
        if (fee != null ? !fee.equals(bookstore.fee) : bookstore.fee != null) return false;
        if (days != null ? !days.equals(bookstore.days) : bookstore.days != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        result = 31 * result + (ownerId != null ? ownerId.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (stauts != null ? stauts.hashCode() : 0);
        result = 31 * result + (deposit != null ? deposit.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        result = 31 * result + (days != null ? days.hashCode() : 0);
        return result;
    }
}
