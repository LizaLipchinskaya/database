package repository.request;

import repository.TemplateTable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DepartmentForClasses extends TemplateTable {

    public DepartmentForClasses(String tableName, Connection connection) {
        super(connection, tableName);
    }

    @Override
    public ArrayList<String[]> executeRequest() {
        ArrayList<String[]> rows = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    select object.id, department, gs.name, t2.name, faculty, datefrom, dateto from object
                    inner join teacher t on t.id = object.teacher
                    inner join discipline d on d.id = object.discipline
                    inner join term t2 on t2.id = object.term
                    inner join group_st gs on gs.id = object.group_st""");

            while (resultSet.next()) {
                String[] row = {
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)
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
