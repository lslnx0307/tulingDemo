package com.lslnx.binding;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: tulingDemo
 * @description: mapper的注册中心
 * @author: v-jasperli
 * @create: 2019-02-21 11:16
 **/
@Getter
@Setter
public class MapperRegistry {
    private Map<String, MapperMethod>  knownMappers = new HashMap<>();
}
