package repository.request;

import repository.TemplateTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeacherBusyness extends TemplateTable {

    public TeacherBusyness(String tableName, Connection connection) {
        super(connection, tableName);
    }

    @Override
    public ArrayList<String[]> executeRequest() {
        ArrayList<String[]> rows = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    select teacher.id, lastname, name, counthour, s.sumcount from teacher
                    inner join object o on teacher.id = o.teacher
                    inner join discipline d on d.id = o.discipline
                    inner join (select teacher.id, sum(counthour) sumcount from teacher
                                inner join object o on teacher.id = o.teacher
                                inner join discipline d on d.id = o.discipline
                                group by teacher.id) s on teacher.id = s.id""");

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
