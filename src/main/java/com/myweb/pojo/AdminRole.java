package com.myweb.pojo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "admin_role")
public class AdminRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private AdminUser adminUser;
    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;
    @Basic
    @Column(name = "createtime", nullable = true, length = 255)
    private String createtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AdminUser getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }
}
