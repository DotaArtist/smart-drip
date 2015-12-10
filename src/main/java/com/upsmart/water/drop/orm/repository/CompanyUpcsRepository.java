package com.upsmart.water.drop.orm.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.upsmart.water.drop.orm.domain.CompanyUpcs;

/**
 * @ClassName: CompanyUpcsRepository
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lihx
 * @date 2015年11月24日 下午3:20:30
 */
public interface CompanyUpcsRepository extends CrudRepository<CompanyUpcs, Integer> {
    List<CompanyUpcs> findByComIdAndValid(Integer comId, boolean valid);
}
