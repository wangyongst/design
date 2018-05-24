package com.myweb.pojo;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "follow")
public class Follow {
    private int id;
    private Integer userid;
    private Integer followid;

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
    @Column(name = "userid")
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "followid")
    public Integer getFollowid() {
        return followid;
    }

    public void setFollowid(Integer followid) {
        this.followid = followid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Follow follow = (Follow) o;
        return id == follow.id &&
                Objects.equals(userid, follow.userid) &&
                Objects.equals(followid, follow.followid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userid, followid);
    }
}
