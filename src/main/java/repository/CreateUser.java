package repository;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class CreateUser implements Closeable {

    private final Connection connection;

    public CreateUser(Connection connection) {
        this.connection = connection;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createNewUser(String login, String password) throws SQLException {
        Savepoint savepoint = null;

        try {
            connection.setAutoCommit(false);
            savepoint = connection.setSavepoint("before");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            executeSqlStatement("CREATE USER " + login + " PASSWORD '" + password + "'");
            executeSqlStatement("GRANT myapp_readonly TO " + login);
            connection.commit();
            System.out.println("Пользователь с логином: " + login + " создан");
        } catch (SQLException err) {
            connection.rollback(savepoint);
            err.printStackTrace();
        }

        connection.setAutoCommit(true);
    }

    private void executeSqlStatement(String sql) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}
