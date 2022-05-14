package view;

import controller.Controller;
import repository.CommandTable;
import repository.CreateUser;
import repository.request.*;
import view.model.*;
import view.requestModel.*;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.sql.SQLException;

public class Menu extends JFrame {

    private final Controller controller;

    public Menu(Controller controller) {
        super("Меню");
        this.controller = controller;

        JPanel menu = new JPanel();
        GridLayout layout = new GridLayout(6, 2, 15, 15);
        menu.setLayout(layout);
        addButtons(menu);
        setContentPane(menu);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(800, 350);
        setVisible(true);
    }

    public void addButtons(JPanel menu) {
        JButton studentButton = new JButton("Студенты");
        studentButton.addActionListener(event -> {
            StudentModel studentModel = new StudentModel();
            studentModel.addData(controller.getStudentRepository());
            showTable(studentModel, controller.getStudentRepository());
        });

        JButton teacherButton = new JButton("Преподаватели");
        teacherButton.addActionListener(event -> {
            TeacherModel teacherModel = new TeacherModel();
            teacherModel.addData(controller.getTeacherRepository());
            showTable(teacherModel, controller.getTeacherRepository());
        });

        JButton objectButton = new JButton("Предметы");
        objectButton.addActionListener(event -> {
            ObjectModel objectModel = new ObjectModel();
            objectModel.addData(controller.getObjectRepository());
            showTable(objectModel, controller.getObjectRepository());
        });

        JButton categoryTeacherButton = new JButton("Категории учителей");
        categoryTeacherButton.addActionListener(event -> {
            CategoryTeacherModel categoryTeacherModel = new CategoryTeacherModel();
            categoryTeacherModel.addData(controller.getCategoryTeacherRepository());
            showTable(categoryTeacherModel, controller.getCategoryTeacherRepository());
        });

        JButton protectButton = new JButton("Защита");
        protectButton.addActionListener(event -> {
            ProtectModel protectModel = new ProtectModel();
            protectModel.addData(controller.getProtectRepository());
            showTable(protectModel, controller.getProtectRepository());
        });

        JButton disciplineButton = new JButton("Дисциплины");
        disciplineButton.addActionListener(event -> {
            DisciplineModel disciplineModel = new DisciplineModel();
            disciplineModel.addData(controller.getDisciplineRepository());
            showTable(disciplineModel, controller.getDisciplineRepository());
        });

        JButton termButton = new JButton("Семестры");
        termButton.addActionListener(event -> {
            TermModel termModel = new TermModel();
            termModel.addData(controller.getTermRepository());
            showTable(termModel, controller.getTermRepository());
        });

        JButton sessionButton = new JButton("Сессии");
        sessionButton.addActionListener(event -> {
            SessionModel sessionModel = new SessionModel();
            sessionModel.addData(controller.getSessionRepository());
            showTable(sessionModel, controller.getSessionRepository());
        });

        JButton groupButton = new JButton("Группы");
        groupButton.addActionListener(event -> {
            GroupModel groupModel = new GroupModel();
            groupModel.addData(controller.getGroupRepository());
            showTable(groupModel, controller.getGroupRepository());
        });

        JButton diplomaButton = new JButton("Дипломы");
        diplomaButton.addActionListener(event -> {
            DiplomaModel diplomaModel = new DiplomaModel();
            diplomaModel.addData(controller.getDiplomaRepository());
            showTable(diplomaModel, controller.getDiplomaRepository());
        });

        JButton requestButton = new JButton("Запросы");
        requestButton.addActionListener(event -> createPanelForRequest());

        JButton newUser = new JButton("Добавить пользователя");
        newUser.addActionListener(e -> addUser(controller.getCreateUser()));

        menu.add(studentButton);
        menu.add(teacherButton);
        menu.add(objectButton);
        menu.add(categoryTeacherButton);
        menu.add(protectButton);
        menu.add(disciplineButton);
        menu.add(termButton);
        menu.add(sessionButton);
        menu.add(groupButton);
        menu.add(diplomaButton);
        menu.add(requestButton);
        menu.add(newUser);
    }

    public void createPanelForRequest() {
        JFrame frame = new JFrame("Запросы");
        JPanel requestsPanel = new JPanel();
        GridLayout layout = new GridLayout(3, 5, 15, 15);
        requestsPanel.setLayout(layout);

        JButton button1 = new JButton("Защищенные темы кандидатских");
        button1.addActionListener(event -> {
            TopicRhDForTeacherModel model = new TopicRhDForTeacherModel();
            model.addData(new TopicRhDForTeacher("TopicPhd", controller.getConnection()).executeRequest());
            createRequestTable(model);
        });

        JButton button2 = new JButton("Кафедры и группы, у которых проводят занятия");
        button2.addActionListener(event -> {
            DepartmentForClassesModel model = new DepartmentForClassesModel();
            model.addData(new DepartmentForClasses("Classes", controller.getConnection()).executeRequest());
            createRequestTable(model);
        });

        JButton button3 = new JButton("Преподавателей, проводящие занятия в группах");
        button3.addActionListener(event -> {
            TeacherClassesModel model = new TeacherClassesModel();
            model.addData(new TeacherClasses("TeacherClasses", controller.getConnection()).executeRequest());
            createRequestTable(model);
        });

        JButton button4 = new JButton("Сессия студентов");
        button4.addActionListener(event -> {
            StudentSessionModel model = new StudentSessionModel();
            model.addData(new StudentSession("StudentSession", controller.getConnection()).executeRequest());
            createRequestTable(model);
        });

        JButton button5 = new JButton("Отличники");
        button5.addActionListener(event -> {
            HonorStudentModel model = new HonorStudentModel();
            model.addData(new HonorStudent("HonorStudent", controller.getConnection()).executeRequest());
            createRequestTable(model);
        });

        JButton button6 = new JButton("Преподавателей, принимающие экзамены");
        button6.addActionListener(event -> {
            TeacherSessionModel model = new TeacherSessionModel();
            model.addData(new TeacherSession("TeacherSession", controller.getConnection()).executeRequest());
            createRequestTable(model);
        });

        JButton button7 = new JButton("Студенты и их научные руководители");
        button7.addActionListener(event -> {
            StudentTopicModel model = new StudentTopicModel();
            model.addData(new StudentTopic("StudentTopic", controller.getConnection()).executeRequest());
            createRequestTable(model);
        });

        JButton button8 = new JButton("Нагрузка преподавателей");
        button8.addActionListener(event -> {
            TeacherBusynessModel model = new TeacherBusynessModel();
            model.addData(new TeacherBusyness("TeacherBusyness", controller.getConnection())
                    .executeRequest());
            createRequestTable(model);
        });

        requestsPanel.add(button1);
        requestsPanel.add(button2);
        requestsPanel.add(button3);
        requestsPanel.add(button4);
        requestsPanel.add(button5);
        requestsPanel.add(button6);
        requestsPanel.add(button7);
        requestsPanel.add(button8);

        frame.setContentPane(requestsPanel);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 250);
        frame.setVisible(true);
    }

    public void createRequestTable(AbstractTableModel model) {
        JFrame frame = new JFrame("Вывод");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        JTable table = new JTable(model);
        JScrollPane tableScrollPage = new JScrollPane(table);
        tableScrollPage.setPreferredSize(new Dimension(750, 400));

        frame.add(tableScrollPage, new GridBagConstraints(0, 0, 2, 1, 1,
                1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(1, 1, 1,
                1), 0, 0));
        frame.setVisible(true);
        frame.pack();
    }

    public void showTable(AbstractTableModel tableModel, CommandTable commandTable) {
        JFrame frame = new JFrame("Данные таблицы");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPage = new JScrollPane(table);
        tableScrollPage.setPreferredSize(new Dimension(750, 400));

        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(event -> addRow(tableModel, commandTable, frame));

        JButton delButton = new JButton("Удалить");
        delButton.addActionListener(event -> deleteRow(commandTable, frame));

        frame.add(tableScrollPage, new GridBagConstraints(0, 0, 2, 1, 1,
                1, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));
        frame.add(addButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1),
                0, 0));
        frame.add(delButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, new Insets(1, 1, 1, 1),
                0, 0));
        frame.setVisible(true);
        frame.pack();
    }

    public void addRow(AbstractTableModel tableModel, CommandTable commandTable, JFrame table) {
        JFrame frame = new JFrame("Добавление новых данных");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel[] panels = new JPanel[tableModel.getColumnCount() + 2];
        JLabel[] labels = new JLabel[tableModel.getColumnCount()];
        JTextField[] fields = new JTextField[tableModel.getColumnCount()];

        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            JPanel horizontalPanel = new JPanel();
            horizontalPanel.setLayout(new BoxLayout(horizontalPanel, BoxLayout.X_AXIS));

            JLabel l = new JLabel(tableModel.getColumnName(i));

            horizontalPanel.add(l);
            horizontalPanel.add(Box.createHorizontalStrut(12));

            JTextField f = new JTextField(25);
            Dimension size = f.getPreferredSize();
            size.width = f.getMaximumSize().width;
            f.setMaximumSize(size);

            horizontalPanel.add(f);

            panels[i] = horizontalPanel;
            labels[i] = l;
            fields[i] = f;

            verticalPanel.add(horizontalPanel);
        }

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0));

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(event -> {
            String[] row = new String[tableModel.getColumnCount()];

            for (int i = 0; i < fields.length; i++) {
                row[i] = fields[i].getText();
            }

            if (tableModel.getColumnName(0).equals("ИД")) {
                System.arraycopy(row, 1, row, 0, fields.length - 1);
            }

            try {
                commandTable.insertRow(row);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            frame.dispose();
            table.dispose();
        });

        grid.add(okButton);
        flow.add(grid);

        panels[tableModel.getColumnCount()] = verticalPanel;
        panels[tableModel.getColumnCount() + 1] = flow;
        BoxUtils.setGroupAlignmentX(panels, Component.LEFT_ALIGNMENT);
        BoxUtils.setGroupAlignmentY(labels, Component.CENTER_ALIGNMENT);
        BoxUtils.makeSameSize(labels);
        verticalPanel.add(Box.createVerticalStrut(12));
        verticalPanel.add(Box.createVerticalStrut(17));
        verticalPanel.add(flow);

        frame.getContentPane().add(verticalPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void deleteRow(CommandTable commandTable, JFrame table) {
        JFrame frame = new JFrame("Удаление данных");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        JPanel verticalPanel = new JPanel();
        verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));
        verticalPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel name = new JPanel();
        name.setLayout(new BoxLayout(name, BoxLayout.X_AXIS));

        JLabel nameLabel = new JLabel("ИД :");
        name.add(nameLabel);
        name.add(Box.createHorizontalStrut(12));

        JTextField del = new JTextField(10);
        name.add(del);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0));

        JButton okButton = new JButton("Ok");
        okButton.setBackground(Color.RED);
        okButton.addActionListener(event -> {
            String key = del.getText();

            try {
                commandTable.deleteRow(key);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            table.dispose();
            frame.dispose();
        });

        grid.add(okButton);
        flow.add(grid);

        verticalPanel.add(name);
        verticalPanel.add(Box.createVerticalStrut(12));
        verticalPanel.add(Box.createVerticalStrut(17));
        verticalPanel.add(flow);
        frame.getContentPane().add(verticalPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void addUser(CreateUser createUser) {
        JFrame frame = new JFrame("Добавление нового пользователя");
        frame.setSize(new Dimension(600, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        JTextField tfLogin;
        JPasswordField tfPassword;

        JPanel panel = BoxUtils.createVerticalPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel name = BoxUtils.createHorizontalPanel();
        JLabel nameLabel = new JLabel("Логин:");
        name.add(nameLabel);
        name.add(Box.createHorizontalStrut(12));

        tfLogin = new JTextField(15);
        name.add(tfLogin);

        JPanel password = BoxUtils.createHorizontalPanel();
        JLabel passwrdLabel = new JLabel("Пароль:");
        password.add(passwrdLabel);
        password.add(Box.createHorizontalStrut(12));
        tfPassword = new JPasswordField(15);
        password.add(tfPassword);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0));

        JButton btnOk = new JButton("Ok");
        btnOk.addActionListener(event -> {
            try {
                createUser.createNewUser(tfLogin.getText(), new String(tfPassword.getPassword()));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            frame.dispose();
        });

        grid.add(btnOk);
        flow.add(grid);

        BoxUtils.setGroupAlignmentX(new JComponent[]{name, password, panel, flow},
                Component.LEFT_ALIGNMENT);
        BoxUtils.setGroupAlignmentY(new JComponent[]{tfLogin, tfPassword, nameLabel, passwrdLabel},
                Component.CENTER_ALIGNMENT);
        BoxUtils.makeSameSize(new JComponent[]{nameLabel, passwrdLabel});
        BoxUtils.createRecommendedMargin(new JButton[]{btnOk});
        BoxUtils.fixTextFieldSize(tfLogin);
        BoxUtils.fixTextFieldSize(tfPassword);

        panel.add(name);
        panel.add(Box.createVerticalStrut(12));
        panel.add(password);
        panel.add(Box.createVerticalStrut(17));
        panel.add(flow);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}
