package com.upsmart.water.drop.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.upsmart.water.drop.orm.domain.UpsmartContacts;

/**
 * @ClassName: UpsmartContactsRepository
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lihx
 * @date 2015年11月24日 下午2:28:59
 */
public interface UpsmartContactsRepository extends PagingAndSortingRepository<UpsmartContacts, Integer>, JpaSpecificationExecutor<UpsmartContacts> {
    @Query(value = "select * from upsmart_contacts u where u.dep_id = ?1 and u.valid =1", nativeQuery = true)
    List<UpsmartContacts> findByDepId(Integer id);
}
