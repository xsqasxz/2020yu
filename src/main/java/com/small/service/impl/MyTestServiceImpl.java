package com.small.service.impl;

import com.small.entity.MyTest;
import com.small.mapper.MyTestMapper;
import com.small.service.MyTestService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author xueshiqi
 * @since 2020/4/28
 */
@Service
public class MyTestServiceImpl implements MyTestService {

    @Resource
    private MyTestMapper myTestMapper;

    @Override
    public MyTest findById(Long id) {
        return myTestMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MyTest> findAll() {
        return myTestMapper.selectAll();
    }


}
