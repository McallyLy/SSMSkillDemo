package com.mcally.cn.test;


import com.mcally.cn.dao.TestDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-service.xml","classpath:spring/spring-dao.xml"})
public class TestDemo {

    @Autowired
    private TestDao testDao;


    @Test
    public  void test1(){
       List<Map<String,Object>> datas= testDao.getDataList();
        System.out.println(datas);
        datas.stream().forEach(s ->{
            System.out.println(s.get("id"));
            System.out.println(s.get("name"));
            System.out.println(s.get("age"));
            System.out.println(s.get("sex"));

        });
    }



    @Test
    public  void test2(){
      com.mcally.cn.entity.Test datas= testDao.getDataList2(1);
        System.out.println(datas);

    }
}
