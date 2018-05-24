package com.myweb.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

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
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bookId")
    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "ownerId")
    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "stauts")
    public Integer getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }

    @Basic
    @Column(name = "deposit")
    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    @Basic
    @Column(name = "fee")
    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Basic
    @Column(name = "days")
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
        return id == bookstore.id &&
                Objects.equals(bookId, bookstore.bookId) &&
                Objects.equals(ownerId, bookstore.ownerId) &&
                Objects.equals(location, bookstore.location) &&
                Objects.equals(stauts, bookstore.stauts) &&
                Objects.equals(deposit, bookstore.deposit) &&
                Objects.equals(fee, bookstore.fee) &&
                Objects.equals(days, bookstore.days);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, bookId, ownerId, location, stauts, deposit, fee, days);
    }
}
