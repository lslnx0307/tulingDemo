package com.lslnx.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @program: tulingDemo
 * @description:
 * @author: v-jasperli
 * @create: 2019-02-21 11:03
 **/
@Data
@ToString
public class User {

    private Long id;

    private String username;

    private Integer age;

    private Long phone;

    private String desc;
}
