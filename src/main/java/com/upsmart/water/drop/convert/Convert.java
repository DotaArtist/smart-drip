package com.upsmart.water.drop.convert;

import com.upsmart.water.drop.dto.CompanyContactsDto;
import com.upsmart.water.drop.dto.CompanyDto;
import com.upsmart.water.drop.dto.UpsmartContactsDto;
import com.upsmart.water.drop.dto.UpsmartDepDto;
import com.upsmart.water.drop.orm.domain.Company;
import com.upsmart.water.drop.orm.domain.CompanyContacts;
import com.upsmart.water.drop.orm.domain.UpsmartContacts;
import com.upsmart.water.drop.orm.domain.UpsmartDep;

/**
 * @ClassName: Convert
 * @Description: Dto转换domain
 * @author lihx
 * @date 2015年11月23日 下午3:43:11
 */
public class Convert {
    public static UpsmartDep convertToUpsmartDep(UpsmartDepDto upsmartDepDto) {
        UpsmartDep upsmartDep = null;
        if (null != upsmartDepDto) {
            upsmartDep = new UpsmartDep();
            upsmartDep.setId(upsmartDepDto.getId());
            upsmartDep.setName(upsmartDepDto.getName());
        }
        return upsmartDep;
    }

    public static UpsmartContacts convertToUpsmartContacts(UpsmartContactsDto upsmartContactsDto) {
        UpsmartContacts upsmartContacts = null;
        if (null != upsmartContactsDto) {
            upsmartContacts = new UpsmartContacts();
            upsmartContacts.setId(upsmartContactsDto.getId());
            UpsmartDepDto upsmartDepDto = upsmartContactsDto.getUpsmartDepDto();

            upsmartContacts.setUpsmartDep(convertToUpsmartDep(upsmartDepDto));
            upsmartContacts.setName(upsmartContactsDto.getName());
            upsmartContacts.setMobile(upsmartContactsDto.getMobile());
            upsmartContacts.setMail(upsmartContactsDto.getMail());
            upsmartContacts.setCreatedAt(upsmartContactsDto.getCreatedAt());
            upsmartContacts.setUpdatedAt(upsmartContactsDto.getUpdatedAt());
            upsmartContacts.setValid(upsmartContactsDto.isValid());
        }
        return upsmartContacts;
    }

    public static CompanyContacts convertToCompanyContacts(CompanyContactsDto companyContactsDto) {
        CompanyContacts companyContacts = null;
        if (null != companyContactsDto) {
            companyContacts = new CompanyContacts();
            companyContacts.setId(companyContactsDto.getId());
            companyContacts.setComId(companyContactsDto.getComId());
            companyContacts.setName(companyContactsDto.getName());
            companyContacts.setMobile(companyContactsDto.getMobile());
            companyContacts.setTelephone(companyContactsDto.getTelephone());
            companyContacts.setMail(companyContactsDto.getMail());
            companyContacts.setCreatedAt(companyContactsDto.getCreatedAt());
            companyContacts.setUpdatedAt(companyContactsDto.getUpdatedAt());
            companyContacts.setValid(companyContactsDto.isValid());
        }
        return companyContacts;
    }

    public static Company convertToCompany(CompanyDto companyDto) {
        Company company = null;
        if (null != companyDto) {
            company = new Company();
            company.setId(companyDto.getId());
            company.setName(companyDto.getName());
            company.setAbbreviation(companyDto.getAbbreviation());
            company.setCity(companyDto.getCity());
            company.setCommerce(companyDto.getCommerce());
            company.setContract(companyDto.isContract());
            company.setServiceContent(companyDto.getServiceContent());
            company.setTrade(companyDto.getTrade());
            company.setUrl(companyDto.getUrl());
            company.setBusId(companyDto.getBusId());
            company.setOrgId(companyDto.getOrgId());
            company.setTaxId(companyDto.getTaxId());
            company.setCreatedAt(companyDto.getCreatedAt());
            company.setUpdatedAt(companyDto.getUpdatedAt());
            company.setValid(companyDto.isValid());
        }
        return company;
    }
}
