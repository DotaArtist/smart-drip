package com.upsmart.water.drop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.upsmart.water.drop.domain.CompanyContacts;

/**
 * @ClassName: CompanyContactsRepository
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lihx
 * @date 2015年11月24日 下午2:28:46
 */
public interface CompanyContactsRepository extends CrudRepository<CompanyContacts, Integer> {
    List<CompanyContacts> findByComIdAndValid(Integer comId, boolean valid);

}
