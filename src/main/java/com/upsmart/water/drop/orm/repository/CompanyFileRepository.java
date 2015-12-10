package com.upsmart.water.drop.orm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.upsmart.water.drop.orm.domain.CompanyFile;

/**
 * @ClassName: CompanyFileRepository
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lihx
 * @date 2015年11月24日 下午2:28:50
 */
public interface CompanyFileRepository extends CrudRepository<CompanyFile, Integer> {
    @Query("FROM CompanyFile c WHERE c.id in ?1 AND c.valid = 1")
    Iterable<CompanyFile> findAllFiles(List<Integer> fileIds);
}
