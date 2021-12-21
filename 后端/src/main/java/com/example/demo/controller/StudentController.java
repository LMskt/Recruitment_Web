package com.example.demo.controller;

import com.alibaba.druid.sql.dialect.oracle.ast.OracleDataTypeIntervalYear;
import com.example.demo.entity.student;
import com.example.demo.result.ResultFailure;
import com.example.demo.result.ResultModel;
import com.example.demo.result.ResultSucess;
import com.example.demo.service.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@Validated
@RequestMapping(value = "/student")
public class StudentController {
    @Resource
    private StudentService studentService;
    @Autowired
    HttpServletRequest request;

    @PostMapping("/login")
    @ApiOperation(value = "报名")
    public ResultModel login(@RequestParam(required = true) String name,
                             @RequestParam(required = true) String institute,
                             @RequestParam(required = true) String qq,
                             @RequestParam(required = true) String majorOne,
                             @RequestParam(required = true) String majorTwo,
                             @RequestParam(required = true) String introduce
    ){
        try {
            Integer flag=studentService.isdone(name, qq);
            if (flag>0){
                return new ResultFailure("该同学已报名");
            }else {
                student one=new student(name, institute,qq,majorOne,majorTwo,introduce);
                Integer giao=studentService.insertone(one);
                if(giao<=0){
                    return new ResultFailure("报名失败");
                }else {
                    return new ResultSucess("报名成功");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }
}
