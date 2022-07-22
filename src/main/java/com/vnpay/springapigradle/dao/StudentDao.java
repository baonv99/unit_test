package com.vnpay.springapigradle.dao;

import com.vnpay.springapigradle.entitites.Student;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class StudentDao {
    private static final Map<String, Student> studentMap = new HashMap<String, Student>();
    static {
        initData();
    }

    public StudentDao() {

    }

    private static void initData() {
        Student std1 = new Student("01", "Bảo", 23, 1220);
        Student std2 = new Student("01", "Hùng", 24, 1221);
        Student std3 = new Student("01", "Duy", 25, 1222);
        studentMap.put(std1.getId(), std1);
        studentMap.put(std2.getId(), std2);
        studentMap.put(std3.getId(), std3);
    }
    public Student getStudent(String id) {
        return studentMap.get(id);
    }
    public Student createStudent(Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student updateStudent(Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }

    public List<Student> getAllStudent() {
        Collection<Student> c = studentMap.values();
        List<Student> list = new ArrayList<Student>();
        list.addAll(c);
        return list;
    }
}
