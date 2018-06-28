package com.myweb.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "advert")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Advert implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "adminuserid", nullable = true)
    private Integer adminuserid;
    @Basic
    @Column(name = "title", nullable = true, length = 255)
    private String title;
    @Basic
    @Column(name = "image", nullable = true, length = 255)
    private String image;
    @Basic
    @Column(name = "url", nullable = true, length = 255)
    private String url;
    @Basic
    @Column(name = "exposure", nullable = true)
    private Integer exposure;
    @Basic
    @Column(name = "forwarded", nullable = true)
    private Integer forwarded;
    @Basic
    @Column(name = "fans", nullable = true)
    private Integer fans;
    @Basic
    @Column(name = "clicked", nullable = true)
    private Integer clicked;
    @Basic
    @Column(name = "buy", nullable = true)
    private Integer buy;
    @Basic
    @Column(name = "refer", nullable = true)
    private Integer refer;
    @Basic
    @Column(name = "createtime", nullable = true, length = 255)
    private String createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminuserid() {
        return adminuserid;
    }

    public void setAdminuserid(Integer adminuserid) {
        this.adminuserid = adminuserid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getExposure() {
        return exposure;
    }

    public void setExposure(Integer exposure) {
        this.exposure = exposure;
    }

    public Integer getForwarded() {
        return forwarded;
    }

    public void setForwarded(Integer forwarded) {
        this.forwarded = forwarded;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getClicked() {
        return clicked;
    }

    public void setClicked(Integer clicked) {
        this.clicked = clicked;
    }

    public Integer getBuy() {
        return buy;
    }

    public void setBuy(Integer buy) {
        this.buy = buy;
    }

    public Integer getRefer() {
        return refer;
    }

    public void setRefer(Integer refer) {
        this.refer = refer;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
