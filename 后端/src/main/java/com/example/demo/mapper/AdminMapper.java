package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper extends BaseMapper<admin> {
    @Select("SELECT COUNT(*) FROM admin WHERE username=#{username} AND password=#{password}")
    Integer login(String username,String password);
}
