package com.lslnx.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: tulingDemo
 * @description: 把我们解析的sql（UserMapper）加载到这个类中
 * @author: v-jasperli
 * @create: 2019-02-21 11:11
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapperMethod<T> {
    private String sql;
    private Class<T> type;
}
