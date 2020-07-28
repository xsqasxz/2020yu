package com.small.utils;

import com.small.constant.RedisUtilsData;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.genid.GenId;

/**
 * @author xueshiqi
 * @since 2020/5/15
 */
@Component
public class VestaGenId implements GenId<Integer> {

    @Override
    public Integer genId(String table, String column) {
        return RedisUtilsData.getId(table,column);
    }
}
