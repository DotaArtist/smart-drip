package com.upsmart.water.drop.service.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.upsmart.water.drop.dto.UpsmartContactsFilterDto;
import com.upsmart.water.drop.orm.domain.UpsmartContacts;
import com.upsmart.water.drop.orm.domain.UpsmartContacts_;
import com.upsmart.water.drop.orm.domain.UpsmartDep;

/** 
* @ClassName: UpsmartContactsSpecification 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lihx 
* @date 2015年11月24日 下午2:29:57 
*/
public class UpsmartContactsSpecification implements Specification<UpsmartContacts> {
    private UpsmartContactsFilterDto upsmartContactsFilterDto;

    public UpsmartContactsSpecification(UpsmartContactsFilterDto upsmartContactsFilterDto) {
        this.upsmartContactsFilterDto = upsmartContactsFilterDto;
    }

    @Override
    public Predicate toPredicate(Root<UpsmartContacts> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = null;
        if (null != upsmartContactsFilterDto) {
            List<Predicate> whereClauses = new ArrayList<>();

            Integer depId = upsmartContactsFilterDto.getDepId();
            if (null != depId) {
                Path<UpsmartDep> column = root.get(UpsmartContacts_.upsmartDep);
                UpsmartDep upsmartDep = new UpsmartDep();
                upsmartDep.setId(depId);
                whereClauses.add(cb.equal(column, upsmartDep));
            }

            String name = upsmartContactsFilterDto.getName();
            if (StringUtils.isNotBlank(name)) {
                Path<String> column = root.get(UpsmartContacts_.name);
                whereClauses.add(cb.like(column, "%" + name + "%"));
            }

            boolean vaild = upsmartContactsFilterDto.isVaild();
            Path<Boolean> column = root.get(UpsmartContacts_.valid);
            whereClauses.add(cb.equal(column, vaild));

            if (!whereClauses.isEmpty()) {
                Predicate[] predicates = new Predicate[whereClauses.size()];
                predicate = cb.and(whereClauses.toArray(predicates));
            }
        }
        return predicate;
    }

}
