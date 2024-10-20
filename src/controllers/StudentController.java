package controllers;

import errors.BusinessLogicException;
import interfaces.IController;
import models.Student;
import services.StudentService;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentController implements IController<Student> {
    private final ArrayList<Student> students;
    private StudentService studentService;

    public StudentController(StudentService studentService) throws SQLException {
        this.studentService = studentService;
        students = studentService.findAll();
    }

    @Override
    public void display() {
        System.out.printf(Student.toStringHeader());
        for (Student student : this.students) {
            System.out.printf(student.toString());
        }
    }

    private Student findStudentById(long id) {
        Student student = null;
        for (int i = 0; i < this.students.size(); i++) {
            if (this.students.get(i).getId() == id) {
                student = this.students.get(i);
                break;
            }
        }
        return student;
    }

    private int findIndexByStudentId(long id) {
        int index = -1;
        for (int i = 0; i < this.students.size(); i++) {
            if (this.students.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void removeItem(long id) throws SQLException {
        Student student = this.findStudentById(id);
        if (student == null) {
            throw new BusinessLogicException(this.getClass(), "Không tìm thấy sinh viên với id đã cho");
        }
        this.students.remove(student);
        this.studentService.remove(id);
    }

    @Override
    public void update(Student student) throws SQLException {
        int index = this.findIndexByStudentId(student.getId());
        if (index == -1) {
            throw new BusinessLogicException(this.getClass(), "Không tìm thấy sinh viên với id đã cho");
        }
        this.students.set(index, student);
        this.studentService.update(student);
    }

    @Override
    public void insert(Student student) throws SQLException {
        this.students.add(student);
        this.studentService.save(student);
    }
}
