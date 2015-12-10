package com.upsmart.water.drop.orm.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/** 
* @ClassName: Company_ 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lihx 
* @date 2015年11月24日 下午2:26:39 
*/
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Company.class)
public abstract class Company_ {
    public static volatile SingularAttribute<Company, Integer> id;
    public static volatile SingularAttribute<Company, String> name;
    public static volatile SingularAttribute<Company, String> busId;
    public static volatile SingularAttribute<Company, String> orgId;
    public static volatile SingularAttribute<Company, String> taxId;
    public static volatile SingularAttribute<Company, Date> createdAt;
    public static volatile SingularAttribute<Company, Date> updatedAt;
    public static volatile SingularAttribute<Company, Boolean> valid;
}