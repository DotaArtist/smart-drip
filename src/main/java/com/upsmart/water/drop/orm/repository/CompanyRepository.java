package com.upsmart.water.drop.orm.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.upsmart.water.drop.orm.domain.Company;

/** 
* @ClassName: CompanyRepository 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lihx 
* @date 2015年11月24日 下午2:28:55 
*/
public interface CompanyRepository extends PagingAndSortingRepository<Company, Integer> ,JpaSpecificationExecutor<Company>{

}
