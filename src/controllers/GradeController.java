package controllers;

import errors.BusinessLogicException;
import interfaces.IController;
import models.Grade;
import models.Student;
import services.GradeService;

import java.sql.SQLException;
import java.util.ArrayList;


public class GradeController implements IController<Grade> {
    private final ArrayList<Grade> grades;
    private GradeService gradeService;

    public GradeController(GradeService gradeService) throws SQLException {
        this.gradeService = gradeService;
        grades = gradeService.findAll();
    }

    @Override
    public void display() {
        System.out.printf(Grade.toStringHeader());
        for (Grade grade : this.grades) {
            System.out.printf(grade.toString());
        }
    }

    private Grade findGradeById(long id) {
        Grade grade = null;
        for (int i = 0; i < this.grades.size(); i++) {
            if (this.grades.get(i).getId() == id) {
                grade = this.grades.get(i);
                break;
            }
        }
        return grade;
    }

    private int findIndexByGradeId(long id) {
        int index = -1;
        for (int i = 0; i < this.grades.size(); i++) {
            if (this.grades.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public void removeItem(long id) throws SQLException {
        Grade grade = this.findGradeById(id);
        if (grade == null) {
            throw new BusinessLogicException(this.getClass(), "Không tìm thấy lớp với id đã cho");
        }
        this.grades.remove(grade);
        this.gradeService.remove(id);
    }

    @Override
    public void update(Grade grade) throws SQLException {
        int index = this.findIndexByGradeId(grade.getId());
        if (index == -1) {
            throw new BusinessLogicException(this.getClass(), "Không tìm thấy lớp với id đã cho");
        }
        this.grades.add(index, grade);
        this.gradeService.update(grade);
    }

    @Override
    public void insert(Grade grade) throws SQLException {
        this.grades.add(grade);
        this.gradeService.save(grade);
    }
}
