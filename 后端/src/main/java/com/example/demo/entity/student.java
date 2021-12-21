package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.sql.Timestamp;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@TableName("student")
public class student {
    @TableId(type = IdType.AUTO)
    Integer id;
    String name;
    String institute;
    String qq;
    String majorOne;
    String majorTwo;
    String introduce;
    Timestamp time;
    public student(String name, String institute, String qq, String majorOne, String majorTwo, String introduce) {
        this.name = name;
        this.institute = institute;
        this.qq = qq;
        this.majorOne = majorOne;
        this.majorTwo = majorTwo;
        this.introduce = introduce;
    }

    public student() {
    }
}
