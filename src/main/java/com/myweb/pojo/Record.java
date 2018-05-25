package com.myweb.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "record")
public class Record {
    private int id;
    private Integer bookid;
    private Integer userid;
    private Integer status;
    private String time;
//    private Book book;
//    private User user;
//
//    @ManyToOne
//    @MapsId
//    @JoinColumn(name = "userid" , insertable = false,updatable = false)
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    @ManyToOne
//    @MapsId
//    @JoinColumn(name = "bookid" , insertable = false,updatable = false)
//    public Book getBook() {
//        return book;
//    }
//
//    public void setBook(Book book) {
//        this.book = book;
//    }

    @Basic
    @Column(name = "time")
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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
    @Column(name = "bookid",insertable = false,updatable = false)
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    @Basic
    @Column(name = "userid",insertable = false,updatable = false)
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id == record.id &&
                Objects.equals(bookid, record.bookid) &&
                Objects.equals(userid, record.userid) &&
                Objects.equals(time, record.time) &&
                Objects.equals(status, record.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, bookid, userid, status,time);
    }
}
