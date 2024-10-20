package services;

import conf.Database;
import interfaces.IService;
import models.Student;
import models.Grade;
import models.Gender;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class StudentService implements IService<Student> {
    private LocalDateTime localDateTime;

    public StudentService() {
        this.localDateTime = LocalDateTime.now();
    }

    private Student studentMapper(ResultSet rs) throws SQLException {
        Grade grade = new Grade(
                rs.getInt("grade_id"),
                rs.getString("grade_name")
        );
        Student student = new Student(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("phone"),
                rs.getString("address"),
                localDateTime.getYear() - rs.getInt("age"),
                Gender.getGender(rs.getInt("gender")),
                grade
        );
        return student;
    }

    @Override
    public ArrayList<Student> findAll() throws SQLException {
        ArrayList<Student> students = new ArrayList<>();
        PreparedStatement stmt = Database.getConnection().prepareStatement(
                "SELECT s.id, s.name, s.phone, s.address, s.age, s.gender, g.id as grade_id, g.name as grade_name " +
                        "FROM students s JOIN grades g ON s.grade_id = g.id");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            students.add(studentMapper(rs));
        }
        return students;
    }

    @Override
    public boolean save(Student student) throws SQLException {
        PreparedStatement stmt = Database.getConnection().prepareStatement(
                "INSERT INTO students(name, phone, address, age, gender, grade_id) VALUES (?, ?, ?, ?, ?, ?)");
        stmt.setString(1, student.getName());
        stmt.setString(2, student.getPhone());
        stmt.setString(3, student.getAddress());
        stmt.setInt(4, this.localDateTime.getYear() - student.getYearOfBirth());
        stmt.setInt(5, student.getGender().getValue()); // Assuming gender is stored as a string
        stmt.setLong(6, student.getGrade().getId());
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    @Override
    public boolean remove(long id) throws SQLException {
        PreparedStatement stmt = Database.getConnection().prepareStatement("DELETE FROM students WHERE id = ?");
        stmt.setLong(1, id);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    @Override
    public boolean update(Student student) throws SQLException {
        PreparedStatement stmt = Database.getConnection().prepareStatement(
                "UPDATE students SET name = ?, address = ?, age = ?, gender = ?, grade_id = ? WHERE id = ?");
        stmt.setString(1, student.getName());
        stmt.setString(2, student.getAddress());
        stmt.setInt(3, this.localDateTime.getYear() - student.getYearOfBirth());
        stmt.setInt(4, student.getGender().getValue());
        stmt.setLong(5, student.getGrade().getId());
        stmt.setLong(6, student.getId());
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    @Override
    public Student findById(long id) throws SQLException {
        Student student = null;
        PreparedStatement stmt = Database.getConnection().prepareStatement(
                "SELECT s.id, s.name, s.phone, s.address, s.age, s.gender, g.id as grade_id, g.name as grade_name " +
                        "FROM students s JOIN grades g ON s.grade_id = g.id WHERE s.id = ?");
        stmt.setLong(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            student = studentMapper(rs);
        }
        return student;
    }
}
