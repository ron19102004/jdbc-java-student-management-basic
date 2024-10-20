package views;

import conf.Database;
import models.Grade;
import services.GradeService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Database.connect(
                "localhost",
                3306,
                "root",
                "",
                "student_management"
        );
        GradeService gradeService = new GradeService();

        Grade grade = new Grade(0, "24KIT");
        System.out.println(gradeService.save(grade));
        Database.close();
    }
}
