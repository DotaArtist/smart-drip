package com.upsmart.water.drop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upsmart.water.drop.domain.UpsmartContacts;
import com.upsmart.water.drop.domain.UpsmartDep;
import com.upsmart.water.drop.dto.UpsmartContactsDto;
import com.upsmart.water.drop.dto.UpsmartDepDto;
import com.upsmart.water.drop.repository.UpsmartContactsRepository;
import com.upsmart.water.drop.repository.UpsmartDepRepository;
import com.upsmart.water.drop.response.BaseMessage;
import com.upsmart.water.drop.response.MessageCode;

/**
 * @ClassName: UpsmartDepService
 * @Description: 银联智慧部门Service
 * @author lihx
 * @date 2015年11月24日 上午11:20:58
 */
@Service
public class UpsmartDepService {
    @Autowired
    private UpsmartDepRepository upsmartDepRepository;
    @Autowired
    private UpsmartContactsRepository upsmartContactRepository;

    /**
     * 查找全部部门
     */
    @Transactional
    public BaseMessage getUpsmartDeps() {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        Iterable<UpsmartDep> iter = upsmartDepRepository.findAll();
        List<UpsmartDepDto> upsmartDepDtos = new ArrayList<>();
        if (null != iter) {
            for (UpsmartDep upsmartDep : iter) {
                upsmartDepDtos.add(new UpsmartDepDto(upsmartDep));
            }
        }
        baseMessage.setData(upsmartDepDtos);
        return baseMessage;
    }

    /**
     * 查找全部部门和联系人
     */
    @Transactional
    public BaseMessage getUpsmartDepsAndContacts() {
        BaseMessage baseMessage = new BaseMessage(MessageCode.SUCCESSED);
        Iterable<UpsmartDep> iter = upsmartDepRepository.findAll();
        List<UpsmartDepDto> upsmartDepDtos = new ArrayList<>();
        if (null != iter) {
            for (UpsmartDep upsmartDep : iter) {
                List<UpsmartContacts> upsmartContacts = upsmartContactRepository.findByDepId(upsmartDep.getId());
                UpsmartDepDto upsmartDepDto = new UpsmartDepDto(upsmartDep);
                upsmartDepDto.setUpsmartContacts(UpsmartContactsDto.getUpsmartContactsDtos(upsmartContacts));
                upsmartDepDtos.add(upsmartDepDto);
            }
        }
        baseMessage.setData(upsmartDepDtos);
        return baseMessage;
    }

}
