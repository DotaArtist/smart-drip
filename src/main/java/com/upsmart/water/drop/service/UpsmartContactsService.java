package com.upsmart.water.drop.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsmart.water.drop.convert.Convert;
import com.upsmart.water.drop.domain.CompanyUpcs;
import com.upsmart.water.drop.domain.UpsmartContacts;
import com.upsmart.water.drop.dto.UpsmartContactsDto;
import com.upsmart.water.drop.dto.UpsmartContactsFilterDto;
import com.upsmart.water.drop.dto.UpsmartDepDto;
import com.upsmart.water.drop.repository.CompanyUpcsRepository;
import com.upsmart.water.drop.repository.UpsmartContactsRepository;
import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.response.MessageCode;
import com.upsmart.water.drop.specification.UpsmartContactsSpecification;

/**
 * @ClassName: UpsmartContactService
 * @Description: 银联智慧联系人Service
 * @author lihx
 * @date 2015年11月24日 上午11:21:24
 */
@Service
public class UpsmartContactsService {
    private Logger logger = LoggerFactory.getLogger(UpsmartContactsService.class);
    @Autowired
    private UpsmartContactsRepository upsmartContactRepository;
    @Autowired
    private CompanyUpcsRepository companyUpcsRepository;

    /**
     * 新增智慧联系人
     */
    @Transactional
    public BaseMessage saveUpsmartContact(UpsmartContactsDto upsmartContactsDto) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        UpsmartContacts upsmartContacts = Convert.convertToUpsmartContacts(upsmartContactsDto);
        Date now = new Date();
        upsmartContacts.setId(0);
        upsmartContacts.setCreatedAt(now);
        upsmartContacts.setUpdatedAt(now);
        upsmartContacts.setValid(true);
        upsmartContacts = upsmartContactRepository.save(upsmartContacts);
        baseMessage.setData(new UpsmartContactsDto(upsmartContacts));
        logger.info("新增智慧联系人 id:{} 成功", upsmartContacts.getId());
        return baseMessage;
    }

    /**
     * 修改智慧联系人
     */
    @Transactional
    public BaseMessage updateUpsmartContact(UpsmartContactsDto upsmartContactsDto) {
        BaseMessage baseMessage = null;
        UpsmartContacts upsmartContactsOld = upsmartContactRepository.findOne(upsmartContactsDto.getId());
        if (null != upsmartContactsOld) {
            UpsmartContacts upsmartContacts = Convert.convertToUpsmartContacts(upsmartContactsDto);
            Date now = new Date();
            upsmartContacts.setCreatedAt(upsmartContactsOld.getCreatedAt());
            upsmartContacts.setUpdatedAt(now);
            upsmartContacts.setValid(true);
            upsmartContacts = upsmartContactRepository.save(upsmartContacts);
            logger.info("修改智慧联系人 id:{} 成功", upsmartContacts.getId());
            baseMessage = new BaseMessage(MessageCode.SUCCESSED);
            baseMessage.setData(new UpsmartContactsDto(upsmartContacts));
        } else {
            baseMessage = new BaseMessage(MessageCode.FAILED);
            baseMessage.setDesc("要修改的智慧联系人不存在");
            baseMessage.setData(upsmartContactsDto.getId());
            logger.error("要修改的智慧联系人 id:{} 不存在", upsmartContactsDto.getId());
        }
        return baseMessage;
    }

    /**
     * 查询智慧联系人
     */
    @Transactional
    public BaseMessage queryUpsmartContacts(UpsmartContactsFilterDto filterDto, Pageable pageable) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        UpsmartContactsSpecification specification = new UpsmartContactsSpecification(filterDto);
        Page<UpsmartContacts> upsmartContacts = upsmartContactRepository.findAll(specification, pageable);
        Page<UpsmartContactsDto> page = new PageImpl<>(UpsmartContactsDto.getUpsmartContactsDtos(upsmartContacts), pageable, upsmartContacts.getTotalElements());
        baseMessage.setData(page);
        return baseMessage;
    }

    /**
     * 查询智慧联系人(不带分页)
     */
    @Transactional
    public BaseMessage queryUpsmartContacts(Integer depId, Integer comId) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        List<UpsmartContacts> upsmartContacts = upsmartContactRepository.findByDepId(depId);
        List<CompanyUpcs> companyUpcs = companyUpcsRepository.findByComIdAndValid(comId, true);
        Set<Integer> upIds = new HashSet<>();
        if (null != companyUpcs) {
            for (CompanyUpcs companyUpc : companyUpcs) {
                upIds.add(companyUpc.getUpcId());
            }
        }
        if(null!=upsmartContacts){
            Iterator<UpsmartContacts> iter=upsmartContacts.iterator();
            while(iter.hasNext()){
                UpsmartContacts upsmartContact=iter.next();
                if(upIds.contains(upsmartContact.getId()))
                    iter.remove();
            }
        }
        List<UpsmartContactsDto> page = UpsmartContactsDto.getUpsmartContactsDtos(upsmartContacts);
        baseMessage.setData(page);
        return baseMessage;
    }

    /**
     * 根据id查询智慧联系人
     */
    @Transactional
    public BaseMessage queryUpsmartContactById(Integer id) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        UpsmartContacts upsmartContact = upsmartContactRepository.findOne(id);
        UpsmartContactsDto upsmartContactsDto = new UpsmartContactsDto(upsmartContact);
        upsmartContactsDto.setUpsmartDepDto(new UpsmartDepDto(upsmartContact.getUpsmartDep()));
        baseMessage.setData(upsmartContactsDto);
        return baseMessage;
    }

    /**
     * 删除智慧联系人
     */
    @Transactional
    public BaseMessage deleteUpsmartContact(Integer id) {
        BaseMessage baseMessage = null;
        UpsmartContacts upsmartContacts = upsmartContactRepository.findOne(id);
        if (null != upsmartContacts) {
            upsmartContacts.setUpdatedAt(new Date());
            upsmartContacts.setValid(false);
            upsmartContacts = upsmartContactRepository.save(upsmartContacts);
            logger.info("删除智慧联系人 id:{} 成功", upsmartContacts.getId());
            baseMessage = new BaseMessage(MessageCode.SUCCESSED);
            baseMessage.setData(id);
        } else {
            baseMessage = new BaseMessage(MessageCode.FAILED);
            baseMessage.setDesc("要删除的智慧联系人不存在");
            baseMessage.setData(id);
            logger.error("要删除的智慧联系人 id:{} 不存在", id);
        }
        return baseMessage;
    }
}
