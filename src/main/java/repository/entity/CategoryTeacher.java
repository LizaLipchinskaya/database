package repository.entity;

import repository.CommandTable;
import repository.TemplateTable;

import java.sql.*;
import java.util.ArrayList;

public class CategoryTeacher extends TemplateTable implements CommandTable {

    public CategoryTeacher(Connection connection) {
        super(connection, "categoryTeacher");
    }

    @Override
    public void insertRow(String[] row) throws SQLException {
        String sql = "insert into categoryTeacher (name) values (?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, row[0]);

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public void deleteRow(String id) throws SQLException {
        super.executeSqlStatement("delete from categoryTeacher where id = " + Integer.parseInt(id));
    }

    @Override
    public void deleteAllData() throws SQLException {
        super.executeSqlStatement("delete from categoryTeacher");
    }

    @Override
    public ArrayList<String[]> getAll() {
        ArrayList<String[]> data = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from categoryteacher");

            while (resultSet.next()) {
                String[] row = {resultSet.getString(1), resultSet.getString(2)};
                data.add(row);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
