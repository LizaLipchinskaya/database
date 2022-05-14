package controller;

import repository.CreateUser;
import repository.entity.*;
import repository.entity.Object;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Controller {

    public static final String URL = "jdbc:postgresql://localhost:5432/university";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "Hello2015";
    public static final String JDBC_POSTGRES_DRIVER = "org.postgresql.Driver";

    private Connection connection;
    private CreateUser createUser;

    private String user;
    private String password;

    private Student studentRepository;
    private Teacher teacherRepository;
    private Object objectRepository;
    private CategoryTeacher categoryTeacherRepository;
    private Protect protectRepository;
    private Discipline disciplineRepository;
    private Term termRepository;
    private Session sessionRepository;
    private Group groupRepository;
    private Diploma diplomaRepository;

    public Controller() {
        try {
            Class.forName(JDBC_POSTGRES_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void login(String user, String password) throws SQLException {
        this.user = user;
        this.password = password;

        if (getConnection() == null) {
            JOptionPane.showMessageDialog(new JFrame(),"Неправильный логин или пароль");
            return;
        }

        this.createUser = new CreateUser(getConnection());

        studentRepository = new Student(getConnection());
        teacherRepository = new Teacher(getConnection());
        objectRepository = new Object(getConnection());
        categoryTeacherRepository = new CategoryTeacher(getConnection());
        protectRepository = new Protect(getConnection());
        disciplineRepository = new Discipline(getConnection());
        termRepository = new Term(getConnection());
        sessionRepository = new Session(getConnection());
        groupRepository = new Group(getConnection());
        diplomaRepository = new Diploma(getConnection());
    }

    public Connection getConnection()  {
        if (connection != null) {
            return connection;
        }

        try {
            connection = DriverManager.getConnection(URL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            return connection = null;
        }

        return connection;
    }

    public Student getStudentRepository() {
        return studentRepository;
    }

    public Teacher getTeacherRepository() {
        return teacherRepository;
    }

    public Object getObjectRepository() {
        return objectRepository;
    }

    public CategoryTeacher getCategoryTeacherRepository() {
        return categoryTeacherRepository;
    }

    public Protect getProtectRepository() {
        return protectRepository;
    }

    public Discipline getDisciplineRepository() {
        return disciplineRepository;
    }

    public Term getTermRepository() {
        return termRepository;
    }

    public Session getSessionRepository() {
        return sessionRepository;
    }

    public Group getGroupRepository() {
        return groupRepository;
    }

    public Diploma getDiplomaRepository() {
        return diplomaRepository;
    }

    public CreateUser getCreateUser() {
        return createUser;
    }
}
