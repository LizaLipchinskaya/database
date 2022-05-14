package repository.entity;

import repository.CommandTable;
import repository.TemplateTable;

import java.sql.*;
import java.util.ArrayList;

public class Protect extends TemplateTable implements CommandTable {

    public Protect(Connection connection) {
        super(connection, "protect");
    }

    @Override
    public void insertRow(String[] row) throws SQLException {
        String sql = "insert into protect (teacher, topic, date_of_protection) values (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(row[0]));
        preparedStatement.setString(2, row[1]);
        preparedStatement.setDate(3, Date.valueOf(row[2]));

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public void deleteRow(String id) throws SQLException {
        super.executeSqlStatement("delete from protect where id = " + Integer.parseInt(id));
    }

    @Override
    public void deleteAllData() throws SQLException {
        super.executeSqlStatement("delete from protect");
    }

    @Override
    public ArrayList<String[]> getAll() {
        ArrayList<String[]> data = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    SELECT protect.id, lastname, topic, date_of_protection from protect
                    inner join teacher t on t.id = protect.teacher""");

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
