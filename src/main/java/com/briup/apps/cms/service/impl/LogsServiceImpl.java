package com.briup.apps.cms.service.impl;

import com.briup.apps.cms.bean.Logs;
import com.briup.apps.cms.bean.LogsExample;
import com.briup.apps.cms.dao.LogsMapper;
import com.briup.apps.cms.service.ILogsService;
import com.briup.apps.cms.utils.CustomerException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LogsServiceImpl implements ILogsService {
    @Resource
    private LogsMapper mapper;
    @Override
    public List<Logs> findAll() {
        return mapper.selectByExample(new LogsExample());
    }

    @Override
    public void insertLogs(Logs logs) {
        if (logs!=null){
            if (logs.getContent()!=null){
                mapper.insert(logs);
                return;
            }
        }
        throw new CustomerException("插入失败!");
    }

    @Override
    public void updateLogsById(Logs logs) {
        if (logs!=null){
            if (logs.getId()!=null){
                Logs logs1 = mapper.selectByPrimaryKey(logs.getId());
                if (logs1!=null){
                    mapper.updateByPrimaryKey(logs);
                    return;
                }
            }
        }
        throw new CustomerException("更新失败");
    }

    @Override
    public void deleteLogsById(Long id) {
        if (id!=null){
            mapper.deleteByPrimaryKey(id);
            return;
        }
        throw new CustomerException("删除失败");
    }

    @Override
    public Logs findLogsById(Long id) {
        return mapper.selectByPrimaryKey(id);
    }
}
