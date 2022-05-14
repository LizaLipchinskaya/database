package repository.entity;

import repository.CommandTable;
import repository.TemplateTable;

import java.sql.*;
import java.util.ArrayList;

public class Session extends TemplateTable implements CommandTable {

    public Session(Connection connection) {
        super(connection, "session");
    }

    @Override
    public void insertRow(String[] row) throws SQLException {
        String sql = "insert into session (student, teacher, discipline, estimate) values (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(row[0]));
        preparedStatement.setInt(2, Integer.parseInt(row[1]));
        preparedStatement.setInt(3, Integer.parseInt(row[2]));
        preparedStatement.setInt(4, Integer.parseInt(row[3]));

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public void deleteRow(String id) throws SQLException {
        super.executeSqlStatement("delete from session where id = " + Integer.parseInt(id));
    }

    @Override
    public void deleteAllData() throws SQLException {
        super.executeSqlStatement("delete from session");
    }

    @Override
    public ArrayList<String[]> getAll() {
        ArrayList<String[]> data = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    select session.id, s.last_name, t.lastname, name, estimate from session
                    inner join student s on s.id = session.student
                    inner join teacher t on t.id = session.teacher
                    inner join object o on o.id = session.discipline
                    inner join discipline d on d.id = o.discipline""");

            while (resultSet.next()) {
                String[] row = {resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5)};
                data.add(row);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
