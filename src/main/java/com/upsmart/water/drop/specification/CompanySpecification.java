package com.upsmart.water.drop.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.upsmart.water.drop.domain.Company;
import com.upsmart.water.drop.domain.Company_;

/**
 * @ClassName: CompanySpecification
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lihx
 * @date 2015年11月24日 下午2:29:52
 */
public class CompanySpecification implements Specification<Company> {
    private String name;
    private boolean valid;

    public CompanySpecification(String name, boolean valid) {
        this.name = name;
        this.valid = valid;
    }

    @Override
    public Predicate toPredicate(Root<Company> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = null;

        List<Predicate> whereClauses = new ArrayList<>();
        if (StringUtils.isNotBlank(name)) {
            Path<String> column = root.get(Company_.name);
            whereClauses.add(cb.like(column, "%" + name + "%"));
        }
        Path<Boolean> columnValid = root.get(Company_.valid);
        whereClauses.add(cb.equal(columnValid, valid));

        if (!whereClauses.isEmpty()) {
            Predicate[] predicates = new Predicate[whereClauses.size()];
            predicate = cb.and(whereClauses.toArray(predicates));
        }

        return predicate;
    }

}
