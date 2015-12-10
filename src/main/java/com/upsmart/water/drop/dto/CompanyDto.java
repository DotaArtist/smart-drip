package com.upsmart.water.drop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.upsmart.water.drop.orm.domain.Company;

/**
 * @ClassName: CompanyDto
 * @Description: 公司信息Dto
 * @author lihx
 * @date 2015年11月24日 上午10:23:19
 */
public class CompanyDto implements Serializable {
    private static final long serialVersionUID = -439651545938490493L;
    private Integer id;
    private String name;
    private String abbreviation;
    private String city;
    private String commerce;
    private String trade;
    private String url;
    private boolean contract;
    private String serviceContent;
    private Integer busId = 0;
    private Integer orgId = 0;
    private Integer taxId = 0;
    private Date createdAt;
    private Date updatedAt;
    private boolean valid;

    public CompanyDto() {
        super();
    }

    public CompanyDto(Company company) {
        super();
        if (null != company) {
            this.id = company.getId();
            this.name = company.getName();
            this.abbreviation = company.getAbbreviation();
            this.city = company.getCity();
            this.commerce = company.getCommerce();
            this.trade = company.getTrade();
            this.url = company.getUrl();
            this.contract = company.isContract();
            this.serviceContent = company.getServiceContent();
            this.busId = company.getBusId();
            this.orgId = company.getOrgId();
            this.taxId = company.getTaxId();
            this.createdAt = company.getCreatedAt();
            this.updatedAt = company.getUpdatedAt();
            this.valid = company.isValid();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCommerce() {
        return commerce;
    }

    public void setCommerce(String commerce) {
        this.commerce = commerce;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isContract() {
        return contract;
    }

    public void setContract(boolean contract) {
        this.contract = contract;
    }

    public String getServiceContent() {
        return serviceContent;
    }

    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    public Integer getBusId() {
        return busId;
    }

    public void setBusId(Integer busId) {
        this.busId = busId;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public static List<CompanyDto> getCompanyDtos(Iterable<Company> companys) {
        List<CompanyDto> companyDtos = new ArrayList<>();
        if (null != companys) {
            for (Company company : companys) {
                companyDtos.add(new CompanyDto(company));
            }
        }
        return companyDtos;
    }
}
