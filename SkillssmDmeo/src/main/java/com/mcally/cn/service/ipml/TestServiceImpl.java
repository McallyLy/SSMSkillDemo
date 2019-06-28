package com.mcally.cn.service.ipml;

import com.mcally.cn.dao.TestDao;
import com.mcally.cn.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao  testDao;


    @Override
    public List<Map<String, Object>> getDataList() {
        return  testDao.getDataList();
    }
}
