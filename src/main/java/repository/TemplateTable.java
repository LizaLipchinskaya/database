package repository;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TemplateTable implements Closeable {

    protected Connection connection;
    protected String tableName;

    public TemplateTable(Connection connection, String tableName) {
        this.connection = connection;
        this.tableName = tableName;
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeSqlStatement(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
    }

    public ArrayList<String[]> executeRequest(){
        return new ArrayList<>();
    }
}
