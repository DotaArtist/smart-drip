package com.upsmart.water.drop.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsmart.water.drop.domain.CompanyUpcs;
import com.upsmart.water.drop.domain.UpsmartContacts;
import com.upsmart.water.drop.dto.CompanyUpcsDto;
import com.upsmart.water.drop.dto.UpsmartContactsDto;
import com.upsmart.water.drop.dto.UpsmartDepDto;
import com.upsmart.water.drop.repository.CompanyUpcsRepository;
import com.upsmart.water.drop.repository.UpsmartContactsRepository;
import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.response.MessageCode;

@Service
public class CompanyUpcsService {
    private Logger logger = LoggerFactory.getLogger(CompanyUpcsService.class);
    @Autowired
    private CompanyUpcsRepository companyUpcsRepository;
    @Autowired
    private UpsmartContactsRepository upsmartContactRepository;

    /**
     * 新增公司智慧联系人
     */
    @Transactional
    public BaseMessage saveCompanyUpcs(CompanyUpcsDto companyUpcsDto) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        CompanyUpcs companyUpcs = new CompanyUpcs();
        Date now = new Date();
        companyUpcs.setComId(companyUpcsDto.getComId());
        companyUpcs.setUpcId(companyUpcsDto.getUpcId());
        companyUpcs.setCreatedAt(now);
        companyUpcs.setUpdatedAt(now);
        companyUpcs.setValid(true);
        companyUpcs = companyUpcsRepository.save(companyUpcs);
        return baseMessage;
    }

    /**
     * 删除公司智慧联系人
     */
    @Transactional
    public BaseMessage deleteCompanyUpcs(Integer id) {
        BaseMessage baseMessage = null;
        CompanyUpcs companyUpcs = companyUpcsRepository.findOne(id);
        if (null != companyUpcs) {
            companyUpcs.setUpdatedAt(new Date());
            companyUpcs.setValid(false);
            companyUpcs = companyUpcsRepository.save(companyUpcs);
            baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        } else {
            baseMessage = new BaseMessage(MessageCode.FAILED);
            baseMessage.setDesc("要删除的公司智慧联系人不存在");
            baseMessage.setData(id);
            logger.error("要删除的公司智慧联系人 id:{} 不存在", id);
        }
        return baseMessage;
    }

    /**
     * 获取智慧联系人列表
     */
    @Transactional
    public BaseMessage queryUpcsByComId(Integer comId) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        List<UpsmartContactsDto> upsmartContactsDtos = new ArrayList<>();
        List<CompanyUpcs> companyUpcs = companyUpcsRepository.findByComIdAndValid(comId, true);
        if (null != companyUpcs) {
            for (CompanyUpcs companyUpc : companyUpcs) {
                UpsmartContacts upsmartContact = upsmartContactRepository.findOne(companyUpc.getUpcId());
                if (null != upsmartContact) {
                    UpsmartContactsDto upsmartContactsDto = new UpsmartContactsDto(upsmartContact);
                    upsmartContactsDto.setUpsmartDepDto(new UpsmartDepDto(upsmartContact.getUpsmartDep()));
                    upsmartContactsDto.setUpcId(companyUpc.getId());
                    upsmartContactsDtos.add(upsmartContactsDto);
                }
            }
        }
        baseMessage.setData(upsmartContactsDtos);
        return baseMessage;
    }

    /**
     * 根据公司id删除公司智慧联系人
     */
    @Transactional
    public void deleteCompanyUpcsByComId(Integer comId) {
        List<CompanyUpcs> companyUpcs = companyUpcsRepository.findByComIdAndValid(comId, true);
        if (null != companyUpcs) {
            for (CompanyUpcs companyUpc : companyUpcs) {
                companyUpc.setUpdatedAt(new Date());
                companyUpc.setValid(false);
                companyUpcsRepository.save(companyUpc);
            }
        }
    }
}
