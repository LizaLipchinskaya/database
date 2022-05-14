package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class LoginSystem extends JDialog {

    public JTextField tfLogin;
    public JPasswordField tfPassword;
    public JButton btnOk, btnCancel, btnDefault;

    public LoginSystem(JFrame parent) {
        super(parent, "Вход в систему");
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });
        getContentPane().add(createGUI());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createGUI() {
        JPanel panel = BoxUtils.createVerticalPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JPanel name = BoxUtils.createHorizontalPanel();
        JLabel nameLabel = new JLabel("Логин:");
        name.add(nameLabel);
        name.add(Box.createHorizontalStrut(12));
        tfLogin = new JTextField(15);
        name.add(tfLogin);

        JPanel password = BoxUtils.createHorizontalPanel();
        JLabel passwordLabel = new JLabel("Пароль:");
        password.add(passwordLabel);
        password.add(Box.createHorizontalStrut(12));
        tfPassword = new JPasswordField(15);
        password.add(tfPassword);

        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        JPanel grid = new JPanel(new GridLayout(1, 2, 5, 0));
        btnOk = new JButton("Войти");
        btnCancel = new JButton("Отменить");
        btnDefault = new JButton("Админ");
        grid.add(btnOk);
        grid.add(btnCancel);
        grid.add(btnDefault);
        flow.add(grid);

        BoxUtils.setGroupAlignmentX(new JComponent[]{name, password, panel, flow}, Component.LEFT_ALIGNMENT);
        BoxUtils.setGroupAlignmentY(new JComponent[]{tfLogin, tfPassword, nameLabel, passwordLabel},
                Component.CENTER_ALIGNMENT);
        BoxUtils.makeSameSize(new JComponent[]{nameLabel, passwordLabel});
        BoxUtils.createRecommendedMargin(new JButton[]{btnDefault, btnOk, btnCancel});
        BoxUtils.fixTextFieldSize(tfLogin);
        BoxUtils.fixTextFieldSize(tfPassword);
        addButtonAction();

        panel.add(name);
        panel.add(Box.createVerticalStrut(12));
        panel.add(password);
        panel.add(Box.createVerticalStrut(17));
        panel.add(flow);

        return panel;
    }

    public void addButtonAction() {
        btnCancel.addActionListener(event -> {
            dispose();
            System.exit(0);
        });

        btnDefault.addActionListener(event -> {
            tfLogin.setText(Controller.USERNAME);
            tfPassword.setText(Controller.PASSWORD);
        });

        btnOk.addActionListener(event -> {
            Controller controller = new Controller();

            try {
                controller.login(tfLogin.getText(), new String(tfPassword.getPassword()));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            dispose();
            new Menu(controller);
        });
    }
}
