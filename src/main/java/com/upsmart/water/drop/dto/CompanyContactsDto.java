package com.upsmart.water.drop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.upsmart.water.drop.orm.domain.CompanyContacts;

/**
 * @ClassName: CompanyContactsDto
 * @Description: 公司联系人Dto
 * @author lihx
 * @date 2015年11月24日 上午10:40:04
 */
public class CompanyContactsDto implements Serializable {
    private static final long serialVersionUID = -8182177794485541133L;
    private Integer id;
    private Integer comId;
    private String name;
    private String mobile;
    private String telephone;
    private String mail;
    private Date createdAt;
    private Date updatedAt;
    private boolean valid;

    public CompanyContactsDto() {
        super();
    }

    public CompanyContactsDto(CompanyContacts companyContacts) {
        super();
        if (null != companyContacts) {
            this.id = companyContacts.getId();
            this.comId = companyContacts.getComId();
            this.name = companyContacts.getName();
            this.mobile = companyContacts.getMobile();
            this.telephone = companyContacts.getTelephone();
            this.mail = companyContacts.getMail();
            this.createdAt = companyContacts.getCreatedAt();
            this.updatedAt = companyContacts.getUpdatedAt();
            this.valid = companyContacts.isValid();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
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

    public static List<CompanyContactsDto> getCompanyContactsDtos(Iterable<CompanyContacts> companyContacts) {
        List<CompanyContactsDto> companyContactsDtos = new ArrayList<>();
        if (null != companyContacts) {
            for (CompanyContacts companyContact : companyContacts) {
                companyContactsDtos.add(new CompanyContactsDto(companyContact));
            }
        }
        return companyContactsDtos;
    }
}
