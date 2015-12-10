package com.upsmart.water.drop.orm.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/** 
* @ClassName: upsmartDep 
* @Description: 银联智慧部门表
* @author lihx 
* @date 2015年11月23日 上午10:02:44 
*/
@Entity
@Table(name = "upsmart_dep")
public class UpsmartDep implements Serializable {
    private static final long serialVersionUID = -1659621268630308120L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "name")
    private String name;

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

}
