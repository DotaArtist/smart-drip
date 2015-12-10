package com.upsmart.water.drop.dto;

import java.io.Serializable;

/**
 * @ClassName: CompanyUpcsDto
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lihx
 * @date 2015年11月24日 下午9:45:56
 */
public class CompanyUpcsDto implements Serializable {
    private static final long serialVersionUID = 3763368244219764616L;
    private Integer comId;
    private Integer upcId;

    public Integer getComId() {
        return comId;
    }

    public void setComId(Integer comId) {
        this.comId = comId;
    }

    public Integer getUpcId() {
        return upcId;
    }

    public void setUpcId(Integer upcId) {
        this.upcId = upcId;
    }

}
