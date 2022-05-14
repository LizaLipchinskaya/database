package repository.request;

import repository.TemplateTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TopicRhDForTeacher extends TemplateTable {

    public TopicRhDForTeacher(String tableName, Connection connection) {
        super(connection, tableName);
    }

    @Override
    public ArrayList<String[]> executeRequest() {
        ArrayList<String[]> rows = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    select protect.id, lastname, topic, department, faculty from protect
                    inner join teacher t on t.id = protect.teacher""");

            while (resultSet.next()) {
                String[] row = {
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                };
                rows.add(row);
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }
}
