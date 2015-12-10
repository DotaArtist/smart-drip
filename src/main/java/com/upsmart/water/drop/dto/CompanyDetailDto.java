package com.upsmart.water.drop.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: CompanyDetailDto
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author lihx
 * @date 2015年11月24日 下午2:34:04
 */
public class CompanyDetailDto implements Serializable {
    private static final long serialVersionUID = 3422688140912215794L;
    private CompanyDto company;
    private List<CompanyFileDto> companyFiles;
    private List<CompanyContactsDto> companyContacts;
    private List<UpsmartContactsDto> upsmartContacts;

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

    public List<CompanyFileDto> getCompanyFiles() {
        return companyFiles;
    }

    public void setCompanyFiles(List<CompanyFileDto> companyFiles) {
        this.companyFiles = companyFiles;
    }

    public List<CompanyContactsDto> getCompanyContacts() {
        return companyContacts;
    }

    public void setCompanyContacts(List<CompanyContactsDto> companyContacts) {
        this.companyContacts = companyContacts;
    }

    public List<UpsmartContactsDto> getUpsmartContacts() {
        return upsmartContacts;
    }

    public void setUpsmartContacts(List<UpsmartContactsDto> upsmartContacts) {
        this.upsmartContacts = upsmartContacts;
    }

}
