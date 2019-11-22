package com.sinosoft.mybatis.controller;

import com.sinosoft.mybatis.entity.Student;
import com.sinosoft.mybatis.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: wanghuangpeng
 * @time: 2019/11/15 13:46
 */
@RestController
@Api(tags = "增删改查")
public class StudentCtrl {
    @Autowired
    private StudentService studentService;

    @PostMapping("save")
    @ApiOperation(value = "新增", httpMethod = "POST")
    public String saveStudent(@RequestBody List<Student> students) {

        int saveResult = studentService.saveStudent(students);
        if (saveResult == -1) {
            return "新增失败";
        }
        return "新增了" + saveResult + "条数据";
    }

    @GetMapping("list")
    @ApiOperation(value = "查询", httpMethod = "GET")
    public List<Student> listStudent() {
        List<Student> students = studentService.listStudent();
        return students;
    }

    @DeleteMapping("delete")
    @ApiOperation(value = "删除", httpMethod = "DELETE")
    public String deleteStudent(@RequestBody List<Integer> ids) {
        int deleteResult = studentService.deleteStudent(ids);
        if (deleteResult == -1) {
            return "删除失败";
        }
        return "删除了" + deleteResult + "条数据";
    }

    @PostMapping("update")
    @ApiOperation(value = "更新", httpMethod = "POST")
    public String updateStudent(@RequestBody Map<Integer, Student> students) {
        int updateResult = studentService.updateStudent(students);
        if (updateResult == -1) {
            return "更新失败";
        }
        return "更新成功";
    }

    @Scheduled(fixedRate = 1000)
    @GetMapping("scheduled")
    @ApiOperation(value = "定时新增", httpMethod = "POST")
    public String scheduledSave() {
        Student student = new Student();
        student.setName("小明");
        student.setBirthday(new Date());
        int saveResult = studentService.scheduledSave(student);
        if (saveResult == 1) {
            String time = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            return time + "新增[" + saveResult + "]条数据:" + student;
        }
        return "定时新增出错";
    }

}
