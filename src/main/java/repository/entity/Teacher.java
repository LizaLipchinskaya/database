package repository.entity;

import repository.CommandTable;
import repository.TemplateTable;

import java.sql.*;
import java.util.ArrayList;

public class Teacher extends TemplateTable implements CommandTable {

    public Teacher(Connection connection) {
        super(connection, "teacher");
    }

    @Override
    public void insertRow(String[] row) throws SQLException {
        String sql = "insert into teacher (lastname, firstname, department, faculty, category, gender, " +
                "date_of_birth, age, salary, count_baby) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, row[0]);
        preparedStatement.setString(2, row[1]);
        preparedStatement.setString(3, row[2]);
        preparedStatement.setString(4, row[3]);
        preparedStatement.setInt(5, Integer.parseInt(row[4]));
        preparedStatement.setString(6, row[5]);
        preparedStatement.setDate(7, Date.valueOf(row[6]));
        preparedStatement.setInt(8, Integer.parseInt(row[7]));
        preparedStatement.setInt(9, Integer.parseInt(row[8]));
        preparedStatement.setInt(10, Integer.parseInt(row[9]));

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public void deleteRow(String id) throws SQLException {
        super.executeSqlStatement("delete from teacher where id = " + Integer.parseInt(id));
    }

    @Override
    public void deleteAllData() throws SQLException {
        super.executeSqlStatement("delete from teacher");
    }

    @Override
    public ArrayList<String[]> getAll() {
        ArrayList<String[]> data = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    SELECT teacher.id, lastname, firstname, department, faculty, name, gender, date_of_birth,\040
                    age, salary, count_baby from teacher
                    inner join categoryteacher c on c.id = teacher.category""");

            while (resultSet.next()) {
                String[] row = {resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString(9), resultSet.getString(10),
                        resultSet.getString(11)};

                data.add(row);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
