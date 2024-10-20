package services;


import conf.Database;
import interfaces.IService;
import models.Grade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GradeService implements IService<Grade> {
    public GradeService() {
    }

    @Override
    public ArrayList<Grade> findAll() throws SQLException {
        ArrayList<Grade> grades = new ArrayList<>();
        PreparedStatement stmt = Database.getConnection().prepareStatement("select * from grades");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Grade grade = new Grade(
                    rs.getInt("id"),
                    rs.getString("name")
            );
            grades.add(grade);
        }
        return grades;
    }

    @Override
    public boolean save(Grade grade) throws SQLException {
        PreparedStatement stmt = Database.getConnection().prepareStatement("insert into grades(name) values (?)");
        stmt.setString(1, grade.getName());
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    }

    @Override
    public boolean remove(long id) {
        return false;
    }

    @Override
    public boolean update(Grade grade) {
        return false;
    }
}
