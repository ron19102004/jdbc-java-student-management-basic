package views;

import conf.Database;
import controllers.GradeController;
import controllers.StudentController;
import interfaces.ICallbackVoid;
import models.Gender;
import models.Grade;
import models.Student;
import services.GradeService;
import services.StudentService;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.Function;

public class Main {
    public static void start(ICallbackVoid callback) throws SQLException {
        Database.connect(
                "localhost",
                3306,
                "root",
                "",
                "student_management"
        );
        callback.exec();
        Database.close();
    }

    public static void main(String[] args) throws SQLException {
        start(() -> {
            StudentService studentService = new StudentService();
            GradeService gradeService = new GradeService();

            StudentController studentController = new StudentController(studentService);
            GradeController gradeController = new GradeController(gradeService);

            Scanner scanner = new Scanner(System.in);
            int choice;
            while (true) {
                System.out.println("========== Menu ==========");
                System.out.println("1. Add Student");
                System.out.println("2. Remove Student");
                System.out.println("3. Update Student");
                System.out.println("4. Display All Students");
                System.out.println("5. Add Grade");
                System.out.println("6. Remove Grade");
                System.out.println("7. Update Grade");
                System.out.println("8. Display All Grades");
                System.out.println("0. Exit");
                System.out.println("==========================");
                System.out.print("Please choose an option (0-8): ");
                choice = scanner.nextInt();
                if (choice == 0) break;
                scanner.nextLine();
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Phone: ");
                        String phone = scanner.nextLine();
                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine();
                        System.out.print("Enter Year of Birth: ");
                        int yearOfBirth = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Gender (MALE = 0/FEMALE = 1): ");
                        int genderInput = scanner.nextInt();
                        Gender gender = Gender.getGender(genderInput);
                        System.out.print("Enter Grade ID: ");
                        long gradeId = scanner.nextLong();
                        Grade grade = new Grade(gradeId, "unknown");
                        Student student = new Student(0, name, phone, address, yearOfBirth, gender, grade);

                        studentController.insert(student);
                    }
                    case 2 -> {
                    }
                    case 3 -> {
                    }
                    case 4 -> {
                        studentController.display();
                    }
                    case 5 -> {
                    }
                    case 6 -> {
                    }
                    case 7 -> {
                    }
                    case 8 -> {
                        gradeController.display();
                    }
                }
            }
        });
    }
}
