package com.upsmart.water.drop.dto;

import java.io.Serializable;

/**
 * @ClassName: UpsmartContactsFilterDto
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lihx
 * @date 2015年11月23日 下午8:04:28
 */
public class UpsmartContactsFilterDto implements Serializable {
    private static final long serialVersionUID = -874221168453735053L;
    private String name;
    private Integer depId;
    private boolean vaild;

    public UpsmartContactsFilterDto() {
        super();
    }

    public UpsmartContactsFilterDto(String name, Integer depId) {
        this.name = name;
        this.depId = depId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }

    public boolean isVaild() {
        return vaild;
    }

    public void setVaild(boolean vaild) {
        this.vaild = vaild;
    }

}
