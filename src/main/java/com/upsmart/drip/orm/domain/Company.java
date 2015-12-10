package com.upsmart.drip.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 公司信息表
 *
 * @author hekui
 * @since 2015年12月10日 下午4:16:21
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {
    private static final long serialVersionUID = 2102171910488189096L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    // 名称
    @Column(name = "name", nullable = false)
    private String name;
    // 简称
    @Column(name = "abb")
    private String abb;
    // 城市
    @Column(name = "city")
    private String city;
    // 商务
    @Column(name = "commerce")
    private String commerce;
    // 行业
    @Column(name = "trade")
    private String trade;
    // 公司网址
    @Column(name = "url")
    private String url;
    // 联系电话
    @Column(name = "telephone")
    private String telephone;
    // 联系邮箱
    @Column(name = "mail")
    private String mail;
    // 是否签约
    @Column(name = "contract", columnDefinition = "tinyint")
    private boolean contract;
    // 服务内容
    @Column(name = "service_content")
    private String serviceContent;
    // 联系人
    @Column(name = "contact_name")
    private String contactName;
    // 联系电话
    @Column(name = "contact_moblie")
    private String contactMoblie;
    // 创建时间
    @Column(name = "created_at", nullable = false)
    private Date createdAt;
    // 更新时间
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;
    // 状态
    @Column(name = "status", columnDefinition = "enum")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbb() {
        return abb;
    }

    public void setAbb(String abb) {
        this.abb = abb;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommerce() {
        return commerce;
    }

    public void setCommerce(String commerce) {
        this.commerce = commerce;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

//    public boolean isContract() {
//        return contract;
//    }
//
//    public void setContract(boolean contract) {
//        this.contract = contract;
//    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMoblie() {
        return contactMoblie;
    }

    public void setContactMoblie(String contactMoblie) {
        this.contactMoblie = contactMoblie;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
