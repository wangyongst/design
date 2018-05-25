package com.myweb.pojo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "bookstore")
public class Bookstore {
    private int id;
    private Integer bookid;
    private Integer ownerid;
    private Integer userid;
    private Integer stauts;
    private BigDecimal deposit;
    private BigDecimal fee;
    private Integer days;
    private Book book;

    @Basic
    @Column(name = "userid")
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @OneToOne
    @MapsId
    @JoinColumn(name = "bookid" , insertable = false,updatable = false)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bookid", insertable = false,updatable = false)
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    @Basic
    @Column(name = "ownerid")
    public Integer getOwnerid() {
        return ownerid;
    }

    public void setOwnerid(Integer ownerid) {
        this.ownerid = ownerid;
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
                Objects.equals(bookid, bookstore.bookid) &&
                Objects.equals(ownerid, bookstore.ownerid) &&
                Objects.equals(stauts, bookstore.stauts) &&
                Objects.equals(deposit, bookstore.deposit) &&
                Objects.equals(fee, bookstore.fee) &&
                Objects.equals(days, bookstore.days);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, bookid, ownerid, stauts, deposit, fee, days);
    }
}
