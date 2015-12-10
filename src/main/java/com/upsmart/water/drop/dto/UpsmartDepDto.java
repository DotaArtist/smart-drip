package com.upsmart.water.drop.dto;

import java.io.Serializable;
import java.util.List;

import com.upsmart.water.drop.domain.UpsmartDep;

/**
 * @ClassName: UpsmartDepDto
 * @Description: 银联智慧部门dto
 * @author lihx
 * @date 2015年11月23日 下午1:56:32
 */
public class UpsmartDepDto implements Serializable {
    private static final long serialVersionUID = 9222534533975468534L;
    private Integer id;
    private String name;
    private List<UpsmartContactsDto> upsmartContacts;

    public UpsmartDepDto() {
        super();
    }

    public UpsmartDepDto(UpsmartDep upsmartDep) {
        super();
        if (null != upsmartDep) {
            this.id = upsmartDep.getId();
            this.name = upsmartDep.getName();
        }
    }

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

    public List<UpsmartContactsDto> getUpsmartContacts() {
        return upsmartContacts;
    }

    public void setUpsmartContacts(List<UpsmartContactsDto> upsmartContacts) {
        this.upsmartContacts = upsmartContacts;
    }

}
