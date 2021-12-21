package com.example.demo.controller;

import com.example.demo.entity.student;
import com.example.demo.result.ResultFailure;
import com.example.demo.result.ResultModel;
import com.example.demo.result.ResultNull;
import com.example.demo.result.ResultSucess;
import com.example.demo.service.AdminService;
import com.example.demo.service.StudentService;
import com.example.demo.util.JWT;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.MARSHAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@Slf4j
@Validated
@RequestMapping(value = "/admin")
public class AdminController {
    @Resource
    private StudentService studentService;
    @Resource
    private AdminService adminService;
    @Autowired
    HttpServletRequest request;


    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public ResultModel login(@RequestParam(required = true)String username,
                             @RequestParam(required = true) String password){
        try {
            Integer flag=adminService.login(username, password);
            if(flag<=0){
                return new ResultFailure("登录失败，查无此用户");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("jwt", JWT.createJwt(username,username,10800000));
                return new ResultSucess("登录成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/getstudent")
    @ApiOperation(value = "获取学生")
    public ResultModel getstudent(){
        try {
            ArrayList<student> students=studentService.findall();
            if(students.size()==0){
                return new ResultNull("暂无人报名");
            }else {
                HashMap<String,Object> map=new HashMap<>();
                map.put("student",students);
                return new ResultSucess("查询成功",map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }

    @GetMapping("/deleteit")
    @ApiOperation(value = "删除学生")
    public ResultModel deleteit(@RequestParam(required = true)Integer id){
        try {
            Boolean flag=studentService.deletebyid(id);
            if(!flag){
                return new ResultFailure("删除失败");
            }else {
                return new ResultSucess("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultFailure("操作失败，系统故障");
        }
    }
}
