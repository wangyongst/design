package com.myweb.vo;

public class ThreeParameter {
    private Integer userid;
    private Integer helpid;
    private Integer touserid;
    private String message;
    private Integer page;

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
