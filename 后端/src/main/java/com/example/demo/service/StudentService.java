package com.example.demo.service;

import com.example.demo.entity.student;
import com.example.demo.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

@Service
@Slf4j
public class StudentService {
    @Resource
    private StudentMapper studentMapper;

    public Boolean deletebyid(Integer id){
        return studentMapper.deletebyid(id);
    }

    public ArrayList<student> findall(){
        return studentMapper.findall();
    }

    public Integer insertone(student giao){
        return studentMapper.insert(giao);
    }

    public Integer  isdone(String name,String qq){
        return studentMapper.isdone(name, qq);
    }
}
