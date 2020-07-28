package com.small.service;

import com.small.entity.MyTest;

import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/4/28
 */
public interface MyTestService{
    MyTest findById(Long id);

    List<MyTest> findAll();
}
