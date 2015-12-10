package com.upsmart.water.drop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.upsmart.water.drop.domain.UpsmartContacts;

/**
 * @ClassName: UpsmartContactsDto
 * @Description: 银联智慧联系人Dto
 * @author lihx
 * @date 2015年11月23日 下午3:07:41
 */
public class UpsmartContactsDto implements Serializable {

    private static final long serialVersionUID = -3978427987153651728L;
    private Integer id;
    private UpsmartDepDto upsmartDepDto;
    private Integer upcId;
    private String name;
    private String mobile;
    private String mail;
    private Date createdAt;
    private Date updatedAt;
    private boolean valid;

    public UpsmartContactsDto() {
        super();
    }

    public UpsmartContactsDto(UpsmartContacts upsmartContacts) {
        super();
        if (null != upsmartContacts) {
            this.id = upsmartContacts.getId();
            this.name = upsmartContacts.getName();
            this.mobile = upsmartContacts.getMobile();
            this.mail = upsmartContacts.getMail();
            this.createdAt = upsmartContacts.getCreatedAt();
            this.updatedAt = upsmartContacts.getUpdatedAt();
            this.valid = upsmartContacts.isValid();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UpsmartDepDto getUpsmartDepDto() {
        return upsmartDepDto;
    }

    public void setUpsmartDepDto(UpsmartDepDto upsmartDepDto) {
        this.upsmartDepDto = upsmartDepDto;
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

    public Integer getUpcId() {
        return upcId;
    }

    public void setUpcId(Integer upcsId) {
        this.upcId = upcsId;
    }

    public static List<UpsmartContactsDto> getUpsmartContactsDtos(Iterable<UpsmartContacts> upsmartContacts) {
        List<UpsmartContactsDto> upsmartContactsDtos = new ArrayList<>();
        if (null != upsmartContacts) {
            for (UpsmartContacts upsmartContact : upsmartContacts) {
                UpsmartContactsDto upsmartContactsDto = new UpsmartContactsDto(upsmartContact);
                upsmartContactsDto.setUpsmartDepDto(new UpsmartDepDto(upsmartContact.getUpsmartDep()));
                upsmartContactsDtos.add(upsmartContactsDto);
            }
        }
        return upsmartContactsDtos;
    }

}
