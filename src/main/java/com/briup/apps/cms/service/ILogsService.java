package com.briup.apps.cms.service;

import com.briup.apps.cms.bean.Logs;

import java.util.List;

public interface ILogsService {
    List<Logs> findAll();
    void insertLogs(Logs logs);
    void updateLogsById(Logs logs);
    void deleteLogsById(Long id);
    Logs findLogsById(Long id);
}
