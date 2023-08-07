package com.mango.di;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 学生类
 *
 * @author mango
 * @since 2023/8/7
 */
@Data
@ToString
public class Student {
    private String name;
    private int age;
    private Date birthday;
    private Teacher teacher;
}
