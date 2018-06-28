package com.myweb.vo;

import javax.persistence.Basic;
import javax.persistence.Column;

public class AdminOneParameter {
    private String helpids;
    private String name;
    private Integer settingid;
    private String content;
    private Integer type;
    private Integer operation;
    private String keyword;

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSettingid() {
        return settingid;
    }

    public void setSettingid(Integer settingid) {
        this.settingid = settingid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getHelpids() {
        return helpids;
    }

    public void setHelpids(String helpids) {
        this.helpids = helpids;
    }
}
