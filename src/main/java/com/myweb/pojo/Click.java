package com.myweb.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "click")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Click implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "userid", nullable = true)
    private Integer userid;
    @Basic
    @Column(name = "helpid", nullable = true)
    private Integer helpid;
    @Basic
    @Column(name = "createtime", nullable = true, length = 255)
    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getHelpid() {
        return helpid;
    }

    public void setHelpid(Integer helpid) {
        this.helpid = helpid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
