package com.upsmart.water.drop.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @ClassName: UpsmartContacts
 * @Description: 银联智慧联系人
 * @author lihx
 * @date 2015年11月23日 下午2:35:56
 */
@Entity
@Table(name = "upsmart_contacts")
public class UpsmartContacts implements Serializable {
    private static final long serialVersionUID = -4486409610468164692L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.LAZY)
    @JoinColumn(name = "dep_id")
    private UpsmartDep upsmartDep;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "mobile", nullable = false)
    private String mobile;
    @Column(name = "mail", nullable = true)
    private String mail;
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
    @Column(name = "valid", columnDefinition = "tinyint", length = 1)
    private boolean valid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UpsmartDep getUpsmartDep() {
        return upsmartDep;
    }

    public void setUpsmartDep(UpsmartDep upsmartDep) {
        this.upsmartDep = upsmartDep;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

}
