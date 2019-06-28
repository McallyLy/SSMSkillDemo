package com.mcally.cn.dao;

import com.mcally.cn.entity.Test;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TestDao {

    List<Map<String,Object>>  getDataList();

    Test getDataList2(Integer id);
}
