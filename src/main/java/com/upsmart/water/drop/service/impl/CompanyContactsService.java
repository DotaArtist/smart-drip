package com.upsmart.water.drop.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsmart.water.drop.convert.Convert;
import com.upsmart.water.drop.dto.CompanyContactsDto;
import com.upsmart.water.drop.orm.domain.CompanyContacts;
import com.upsmart.water.drop.orm.repository.CompanyContactsRepository;
import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.response.MessageCode;

/**
 * @ClassName: CompanyContactsService
 * @Description: 公司联系人Service
 * @author lihx
 * @date 2015年11月24日 下午2:29:38
 */
@Service
public class CompanyContactsService {
    private Logger logger = LoggerFactory.getLogger(CompanyContactsService.class);
    @Autowired
    private CompanyContactsRepository companyContactRepository;

    /**
     * 新增公司联系人
     */
    @Transactional
    public BaseMessage saveCompanyContact(CompanyContactsDto companyContactsDto) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        CompanyContacts companyContacts = Convert.convertToCompanyContacts(companyContactsDto);
        Date now = new Date();
        companyContacts.setCreatedAt(now);
        companyContacts.setUpdatedAt(now);
        companyContacts.setValid(true);
        companyContacts = companyContactRepository.save(companyContacts);
        baseMessage.setData(new CompanyContactsDto(companyContacts));
        logger.info("新增公司联系人 id:{} 成功", companyContacts.getId());
        return baseMessage;
    }

    /**
     * 修改公司联系人
     */
    @Transactional
    public BaseMessage updateCompanyContact(CompanyContactsDto companyContactsDto) {
        BaseMessage baseMessage = null;
        CompanyContacts companyContactsOld = companyContactRepository.findOne(companyContactsDto.getId());
        if (null != companyContactsOld) {
            CompanyContacts companyContacts = Convert.convertToCompanyContacts(companyContactsDto);
            Date now = new Date();
            companyContacts.setCreatedAt(companyContactsOld.getCreatedAt());
            companyContacts.setUpdatedAt(now);
            companyContacts.setValid(true);
            companyContacts = companyContactRepository.save(companyContacts);
            logger.info("修改公司联系人 id:{} 成功", companyContacts.getId());
            baseMessage = new BaseMessage(MessageCode.SUCCESSED);
            baseMessage.setData(new CompanyContactsDto(companyContacts));
        } else {
            baseMessage = new BaseMessage(MessageCode.FAILED);
            baseMessage.setDesc("要修改的公司联系人不存在");
            baseMessage.setData(companyContactsDto.getId());
            logger.error("要修改的公司联系人 id:{} 不存在", companyContactsDto.getId());
        }
        return baseMessage;
    }

    /**
     * 根据id查询公司联系人
     */
    @Transactional
    public BaseMessage queryCompanyContactByID(Integer id) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        CompanyContacts companyContacts = companyContactRepository.findOne(id);
        baseMessage.setData(new CompanyContactsDto(companyContacts));
        return baseMessage;
    }

    /**
     * 查询公司全部联系人
     */
    @Transactional
    public BaseMessage queryCompanyContacts(Integer comId) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        List<CompanyContacts> companyContacts = companyContactRepository.findByComIdAndValid(comId, true);
        baseMessage.setData(CompanyContactsDto.getCompanyContactsDtos(companyContacts));
        return baseMessage;
    }

    /**
     * 删除公司联系人
     */
    @Transactional
    public BaseMessage deleteCompanyContact(Integer id) {
        BaseMessage baseMessage = null;
        CompanyContacts companyContacts = companyContactRepository.findOne(id);
        if (null != companyContacts) {
            companyContacts.setUpdatedAt(new Date());
            companyContacts.setValid(false);
            companyContacts = companyContactRepository.save(companyContacts);
            logger.info("删除公司联系人 id:{} 成功", companyContacts.getId());
            baseMessage = new BaseMessage(MessageCode.SUCCESSED);
            baseMessage.setData(id);
        } else {
            baseMessage = new BaseMessage(MessageCode.FAILED);
            baseMessage.setDesc("要删除的公司联系人不存在");
            baseMessage.setData(id);
            logger.error("要删除的公司联系人 id:{} 不存在", id);
        }
        return baseMessage;
    }

    /**
     * 根据公司id删除公司联系人
     */
    @Transactional
    public void deleteCompanyUpcsByComId(Integer comId) {
        List<CompanyContacts> companyContacts = companyContactRepository.findByComIdAndValid(comId, true);
        if (null != companyContacts) {
            for (CompanyContacts companyContact : companyContacts) {
                companyContact.setUpdatedAt(new Date());
                companyContact.setValid(false);
                companyContactRepository.save(companyContact);
            }
        }
    }
}
