package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface StudentMapper extends BaseMapper<student> {
    @Delete("DELETE FROM student WHERE id = #{id}")
    Boolean deletebyid(Integer id);

    @Select("SELECT * FROM student")
    ArrayList<student> findall();

    @Select("SELECT COUNT(*) FROM student WHERE name=#{name} AND qq=#{qq}")
    Integer isdone(String name,String qq);
}
