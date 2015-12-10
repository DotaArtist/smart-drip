package com.upsmart.water.drop.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsmart.water.drop.convert.Convert;
import com.upsmart.water.drop.dto.CompanyContactsDto;
import com.upsmart.water.drop.dto.CompanyDetailDto;
import com.upsmart.water.drop.dto.CompanyDto;
import com.upsmart.water.drop.dto.CompanyFileDto;
import com.upsmart.water.drop.dto.UpsmartContactsDto;
import com.upsmart.water.drop.orm.domain.Company;
import com.upsmart.water.drop.orm.domain.CompanyContacts;
import com.upsmart.water.drop.orm.domain.CompanyFile;
import com.upsmart.water.drop.orm.domain.CompanyUpcs;
import com.upsmart.water.drop.orm.domain.UpsmartContacts;
import com.upsmart.water.drop.orm.repository.CompanyContactsRepository;
import com.upsmart.water.drop.orm.repository.CompanyFileRepository;
import com.upsmart.water.drop.orm.repository.CompanyRepository;
import com.upsmart.water.drop.orm.repository.CompanyUpcsRepository;
import com.upsmart.water.drop.orm.repository.UpsmartContactsRepository;
import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.response.MessageCode;
import com.upsmart.water.drop.service.specification.CompanySpecification;

/**
 * @ClassName: CompanyService
 * @Description: 公司信息Service
 * @author lihx
 * @date 2015年11月24日 上午11:13:34
 */
@Service
public class CompanyService {
    private Logger logger = LoggerFactory.getLogger(CompanyService.class);
    @Autowired
    FileService fileService;
    @Autowired
    CompanyUpcsService companyUpcsService;
    @Autowired
    CompanyContactsService companyContactsService;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyFileRepository companyFileRepository;
    @Autowired
    private CompanyUpcsRepository companyUpcsRepository;
    @Autowired
    private UpsmartContactsRepository upsmartContactRepository;
    @Autowired
    private CompanyContactsRepository companyContactsRepositor;

    /**
     * 新增公司信息
     */
    @Transactional
    public BaseMessage saveCompany(CompanyDetailDto companyDetailDto) {
        BaseMessage baseMessage = null;
        List<CompanyFileDto> companyFileDtos = companyDetailDto.getCompanyFiles();
        if (null == companyFileDtos || companyFileDtos.size() < 3) {
            baseMessage = new BaseMessage(MessageCode.FAILED);
            baseMessage.setDesc("请上传全部三要素文件");
            logger.error("请上传全部三要素文件");
            return baseMessage;
        }
        CompanyDto companyDto = companyDetailDto.getCompany();
        Company company = Convert.convertToCompany(companyDto);
        Date now = new Date();
        company.setCreatedAt(now);
        company.setUpdatedAt(now);
        company.setValid(true);
        company = companyRepository.save(company);

        for (int i = 0; i < companyFileDtos.size(); i++) {
            int id = fileService.saveFiles(companyFileDtos.get(i), company.getId(), i + 1);
            switch (i) {
            case 0:
                company.setBusId(id);
                break;
            case 1:
                company.setOrgId(id);
                break;
            case 2:
                company.setTaxId(id);
                break;
            }
        }
        company = companyRepository.save(company);
        baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        baseMessage.setData(new CompanyDto(company));
        logger.info("新增公司 id:{} 成功", company.getId());
        return baseMessage;
    }

    /**
     * 修改公司信息
     */
    @Transactional
    public BaseMessage updateCompany(CompanyDetailDto companyDetailDto) {
        BaseMessage baseMessage = null;
        CompanyDto companyDto = companyDetailDto.getCompany();
        Company companyOld = companyRepository.findOne(companyDto.getId());
        if (null == companyOld) {
            baseMessage = new BaseMessage(MessageCode.FAILED);
            baseMessage.setDesc("要修改的公司不存在");
            baseMessage.setData(companyDto.getId());
            logger.error("要修改的公司 id:{} 不存在", companyDto.getId());
        }
        List<CompanyFileDto> companyFileDtos = companyDetailDto.getCompanyFiles();
        if (null == companyFileDtos || companyFileDtos.size() < 3) {
            baseMessage = new BaseMessage(MessageCode.FAILED);
            baseMessage.setDesc("请上传全部三要素文件");
            logger.error("请上传全部三要素文件");
            return baseMessage;
        }
        Company company = Convert.convertToCompany(companyDto);
        Date now = new Date();
        company.setCreatedAt(companyOld.getCreatedAt());
        company.setUpdatedAt(now);
        company.setValid(true);
        company = companyRepository.save(company);
        for (int i = 0; i < companyFileDtos.size(); i++) {
            if (companyFileDtos.get(i).isUpdate()) {
                fileService.updateFiles(companyFileDtos.get(i), company.getId(), i + 1);
            }
        }
        logger.info("修改公司 id:{} 成功", company.getId());
        baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        baseMessage.setData(new CompanyDto(company));

        return baseMessage;
    }

    /**
     * 根据条件查询全部公司
     */
    @Transactional
    public BaseMessage queryCompanys(String name, Pageable pageable) {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        CompanySpecification specification = new CompanySpecification(name, true);
        Page<Company> companys = companyRepository.findAll(specification, pageable);
        List<CompanyDto> companyDtos = CompanyDto.getCompanyDtos(companys);
        Page<CompanyDto> page = new PageImpl<>(companyDtos, pageable, companys.getTotalElements());
        baseMessage.setData(page);
        return baseMessage;
    }

    /**
     * 根据id获取公司详细信息
     */
    public BaseMessage queryCompanyDetail(Integer id) {
        BaseMessage baseMessage = null;
        Company company = companyRepository.findOne(id);
        if (null == company) {
            baseMessage = new BaseMessage(MessageCode.FAILED);
            baseMessage.setDesc("要查询的公司不存在");
            baseMessage.setData(id);
            logger.error("要查询的公司 id:{} 不存在", id);
            return baseMessage;
        }
        // 获取公司基本信息
        CompanyDetailDto companyDetailDto = new CompanyDetailDto();
        companyDetailDto.setCompany(new CompanyDto(company));
        // 获取三要素文件
        Integer[] fileIds = { company.getBusId(), company.getOrgId(), company.getTaxId() };
        Iterable<CompanyFile> companyFiles = companyFileRepository.findAllFiles(Arrays.asList(fileIds));
        companyDetailDto.setCompanyFiles(CompanyFileDto.getCompanyFileDtos(companyFiles));
        // 获取公司联系人列表
        List<CompanyContacts> companyContacts = companyContactsRepositor.findByComIdAndValid(company.getId(), true);
        companyDetailDto.setCompanyContacts(CompanyContactsDto.getCompanyContactsDtos(companyContacts));
        // 获取智慧联系人列表
        List<UpsmartContactsDto> upsmartContactsDtos = new ArrayList<>();
        List<CompanyUpcs> companyUpcs = companyUpcsRepository.findByComIdAndValid(company.getId(), true);
        if (null != companyUpcs) {
            for (CompanyUpcs companyUpc : companyUpcs) {
                UpsmartContacts upsmartContact = upsmartContactRepository.findOne(companyUpc.getUpcId());
                if (null != upsmartContact) {
                    upsmartContactsDtos.add(new UpsmartContactsDto(upsmartContact));
                }
            }
        }
        companyDetailDto.setUpsmartContacts(upsmartContactsDtos);
        baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        baseMessage.setData(companyDetailDto);
        return baseMessage;
    }

    /**
     * 删除公司
     */
    @Transactional
    public BaseMessage deleteCompany(Integer id) {
        Date now = new Date();
        BaseMessage baseMessage = null;
        Company company = companyRepository.findOne(id);
        if (null == company) {
            baseMessage = new BaseMessage(MessageCode.FAILED);
            baseMessage.setDesc("要删除的公司不存在");
            baseMessage.setData(id);
            logger.error("要删除的公司 id:{} 不存在", id);
            return baseMessage;
        }
        company.setUpdatedAt(now);
        company.setValid(false);
        companyRepository.save(company);
        // 删除公司三要素文件
        int[] fileIds = { company.getBusId(), company.getOrgId(), company.getTaxId() };
        for (int fileId : fileIds) {
            fileService.deleteFile(fileId);
        }
        // 删除公司联系人
        companyContactsService.deleteCompanyUpcsByComId(id);
        // 删除公司智慧联系人
        companyUpcsService.deleteCompanyUpcsByComId(id);
        baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        baseMessage.setData(id);
        return baseMessage;
    }
}
