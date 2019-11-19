package com.sinosoft.mybatis.service;

import com.sinosoft.mybatis.entity.Student;
import com.sinosoft.mybatis.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: wanghuangpeng
 * @time: 2019/11/15 13:46
 */
@Service
@Slf4j
public class StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Resource
    private RedisTemplate redisTemplate;

    public int saveStudent(List<Student> students) {
        try {
            int saveResult = studentMapper.saveStudent(students);
            redisTemplate.opsForValue().set("students", students);
            return saveResult;
        } catch (Exception e) {
            log.error("插入数据发生异常" + e.getMessage());
        }
        return -1;
    }

    public List<Student> listStudent() {
        try {
            if (redisTemplate.hasKey("students")) {
                return (List<Student>) redisTemplate.opsForValue().get("student");
            }
            List<Student> students = studentMapper.listStudent();
            redisTemplate.opsForValue().set("students", students);
            return students;
        } catch (Exception e) {
            log.error("查询数据发生异常" + e.getMessage());
        }
        return null;
    }

    public int deleteStudent(List<Integer> ids) {
        try {
            redisTemplate.delete("students");
            return studentMapper.deleteStudent(ids);
        } catch (Exception e) {
            log.error("查询数据发生异常" + e.getMessage());
        }
        return -1;
    }

    public int updateStudent(Map<Integer, String> students) {
        try {
            int updateResult = studentMapper.updateStudent(students);
            List<Student> studentList = studentMapper.listStudent();
            redisTemplate.opsForValue().set("students", studentList);
            return updateResult;
        } catch (Exception e) {
            log.error("查询数据发生异常" + e.getMessage());
        }
        return -1;
    }

    public int scheduledSave(Student student) {
        try {
            int saveResult = studentMapper.scheduledSave(student);
            return saveResult;
        } catch (Exception e) {
            log.error("插入数据发生异常" + e.getMessage());
        }
        return -1;
    }
}
