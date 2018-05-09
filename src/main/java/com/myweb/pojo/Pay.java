package com.myweb.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Pay {
    private int id;
    private Integer bookid;
    private Integer userid;
    private BigDecimal payfee;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bookid", nullable = true)
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    @Basic
    @Column(name = "userid", nullable = true)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "payfee", nullable = true, precision = 2)
    public BigDecimal getPayfee() {
        return payfee;
    }

    public void setPayfee(BigDecimal payfee) {
        this.payfee = payfee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pay pay = (Pay) o;

        if (id != pay.id) return false;
        if (bookid != null ? !bookid.equals(pay.bookid) : pay.bookid != null) return false;
        if (userid != null ? !userid.equals(pay.userid) : pay.userid != null) return false;
        if (payfee != null ? !payfee.equals(pay.payfee) : pay.payfee != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bookid != null ? bookid.hashCode() : 0);
        result = 31 * result + (userid != null ? userid.hashCode() : 0);
        result = 31 * result + (payfee != null ? payfee.hashCode() : 0);
        return result;
    }
}
