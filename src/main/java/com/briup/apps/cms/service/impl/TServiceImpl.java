package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Test;
import com.briup.apps.cms.bean.TestExample;
import com.briup.apps.cms.dao.TestMapper;
import com.briup.apps.cms.service.ITService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class TServiceImpl implements ITService {

    @Resource
    private TestMapper mapper;

    private TestExample example=new TestExample();

    @Override
    public List<Test> findAll() {
        return mapper.selectByExample(example);
    }
}
