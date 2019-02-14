package cn.wbnull.springbootdemo.dao;

import cn.wbnull.springbootdemo.model.TestV2;

public interface TestV2Mapper {
    int insert(TestV2 record);

    int insertSelective(TestV2 record);
}