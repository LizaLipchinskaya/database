package repository.entity;

import repository.CommandTable;
import repository.TemplateTable;

import java.sql.*;
import java.util.ArrayList;

public class Diploma extends TemplateTable implements CommandTable {

    public Diploma(Connection connection) {
        super(connection, "diploma");
    }

    @Override
    public void insertRow(String[] row) throws SQLException {
        String sql = "insert into diploma (student, teacher, topic) values (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(row[0]));
        preparedStatement.setInt(2, Integer.parseInt(row[1]));
        preparedStatement.setString(3, row[2]);

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public void deleteRow(String id) throws SQLException {
        super.executeSqlStatement("delete from diploma where id = " + Integer.parseInt(id));
    }

    @Override
    public void deleteAllData() throws SQLException {
        super.executeSqlStatement("delete from diploma");
    }

    @Override
    public ArrayList<String[]> getAll() {
        ArrayList<String[]> data = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    select diploma.id, s.last_name, t.lastname, topic from diploma
                    inner join student s on s.id = diploma.student
                    inner join teacher t on t.id = diploma.teacher""");

            while (resultSet.next()) {
                String[] row = {resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4)};

                data.add(row);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
