package com.myweb.vo;

public class ThreeParameter {
    private Integer messageid;
    private Integer userid;
    private Integer helpid;
    private Integer touserid;
    private String message;
    private Integer page;
    private Integer pagesize;
    private Integer type;

    public Integer getMessageid() {
        return messageid;
    }

    public void setMessageid(Integer messageid) {
        this.messageid = messageid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPagesize() {
        return pagesize;
    }

    public void setPagesize(Integer pagesize) {
        this.pagesize = pagesize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTouserid() {
        return touserid;
    }

    public void setTouserid(Integer touserid) {
        this.touserid = touserid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
}
