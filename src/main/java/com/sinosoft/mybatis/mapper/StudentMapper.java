package com.sinosoft.mybatis.mapper;

import com.sinosoft.mybatis.entity.Student;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: wanghuangpeng
 * @time: 2019/11/15 13:46
 */
public interface StudentMapper {

    @Insert("<script>insert into student values <foreach item='student' index='index' collection='students' separator=',' > (#{student.id},#{student.name},#{student.birthday}) </foreach> </script> ")
    int saveStudent(@Param("students") List<Student> students);

    @Select("select * from student")
    List<Student> listStudent();

    @DeleteProvider(type = StudentMapperProvider.class, method = "deleteStudent")
    int deleteStudent(@Param("ids") List<Integer> ids);

    int updateStudent(@Param("students") Map<Integer, Student> students);

    @Insert("insert into student (name,birthday) values (#{student.name},#{student.birthday})")
    int scheduledSave(@Param("student") Student student);
}
