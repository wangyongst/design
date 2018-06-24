package com.myweb.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "help")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Help implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "userid", nullable = false)
    private Integer userid;
    @Basic
    @Column(name = "audience", nullable = true)
    private Integer audience;
    @Basic
    @Column(name = "title", nullable = true, length = 255)
    private String title;
    @Basic
    @Column(name = "image", nullable = true, length = 255)
    private String image;
    @Basic
    @Column(name = "description", nullable = true, length = 1024)
    private String description;
    @Basic
    @Column(name = "video", nullable = true, length = 255)
    private String video;
    @Basic
    @Column(name = "tag", nullable = true, length = 255)
    private String tag;
    @Basic
    @Column(name = "design", nullable = true, length = 255)
    private String design;
    @Basic
    @Column(name = "background", nullable = true)
    private Integer background;
    @Basic
    @Column(name = "set", nullable = true)
    private Integer set;
    @Basic
    @Column(name = "draft", nullable = true)
    private Integer draft;
    @Basic
    @Column(name = "studied", nullable = true)
    private Integer studied;
    @Basic
    @Column(name = "read", nullable = true)
    private Integer read;
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
    @Column(name = "recommend", nullable = true)
    private Integer recommend;
    @Basic
    @Column(name = "refertime", nullable = true, length = 255)
    private String refertime;
    @Basic
    @Column(name = "createtime", nullable = true, length = 255)
    private String createtime;

    public String getRefertime() {
        return refertime;
    }

    public void setRefertime(String refertime) {
        this.refertime = refertime;
    }

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

    public Integer getAudience() {
        return audience;
    }

    public void setAudience(Integer audience) {
        this.audience = audience;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public Integer getBackground() {
        return background;
    }

    public void setBackground(Integer background) {
        this.background = background;
    }

    public Integer getSet() {
        return set;
    }

    public void setSet(Integer set) {
        this.set = set;
    }

    public Integer getDraft() {
        return draft;
    }

    public void setDraft(Integer draft) {
        this.draft = draft;
    }

    public Integer getStudied() {
        return studied;
    }

    public void setStudied(Integer studied) {
        this.studied = studied;
    }

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
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

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
