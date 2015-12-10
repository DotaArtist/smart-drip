package com.upsmart.water.drop.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.upsmart.water.drop.domain.CompanyFile;

/** 
* @ClassName: CompanyFileDto 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author lihx 
* @date 2015年11月24日 下午2:27:25 
*/
public class CompanyFileDto implements Serializable {
    private static final long serialVersionUID = 5297557231197751775L;
    private Integer id;
    private String name;
    private String path;
    private Integer type;
    private boolean update=false;

    public CompanyFileDto() {
        super();
    }

    public CompanyFileDto(CompanyFile companyFile) {
        super();
        if (null != companyFile) {
            this.id = companyFile.getId();
            this.name = companyFile.getName();
            this.path = companyFile.getPath();
            this.type = companyFile.getType();
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public static List<CompanyFileDto> getCompanyFileDtos(Iterable<CompanyFile> companyFiles){
        List<CompanyFileDto> companyFileDtos = new ArrayList<>();
        if (null != companyFiles) {
            for (CompanyFile companyFile : companyFiles) {
                companyFileDtos.add(new CompanyFileDto(companyFile));
            }
        }
        return companyFileDtos;
    }
}
