package repository.request;

import repository.TemplateTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeacherClasses extends TemplateTable {

    public TeacherClasses(String tableName, Connection connection) {
        super(connection, tableName);
    }

    @Override
    public ArrayList<String[]> executeRequest() {
        ArrayList<String[]> rows = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    select teacher.id, d.name, gs.name, t.name, faculty from teacher
                    inner join object o on teacher.id = o.teacher
                    inner join discipline d on d.id = o.discipline
                    inner join group_st gs on gs.id = o.group_st
                    inner join term t on t.id = o.term""");

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
