package com.upsmart.water.drop.orm.domain;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;



/** 
* @ClassName: UpsmartContacts_ 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lihx 
* @date 2015年11月24日 下午2:27:09 
*/
@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UpsmartContacts.class)
public abstract class UpsmartContacts_ {
    public static volatile SingularAttribute<UpsmartContacts, Integer> id;
    public static volatile SingularAttribute<UpsmartContacts, UpsmartDep> upsmartDep;
    public static volatile SingularAttribute<UpsmartContacts, String> name;
    public static volatile SingularAttribute<UpsmartContacts, String> mobile;
    public static volatile SingularAttribute<UpsmartContacts, String> mail;
    public static volatile SingularAttribute<UpsmartContacts, Date> createdAt;
    public static volatile SingularAttribute<UpsmartContacts, Date> updatedAt;
    public static volatile SingularAttribute<UpsmartContacts, Boolean> valid;

}
