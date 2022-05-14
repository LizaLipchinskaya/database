package repository.entity;

import repository.CommandTable;
import repository.TemplateTable;

import java.sql.*;
import java.util.ArrayList;

public class Object extends TemplateTable implements CommandTable {

    public Object(Connection connection) {
        super(connection, "object");
    }

    @Override
    public void insertRow(String[] row) throws SQLException {
        String sql = "insert into object (teacher, group_st, datefrom, dateto, term, discipline) values (?, ?, ?, " +
                "?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(row[0]));
        preparedStatement.setInt(2, Integer.parseInt(row[1]));
        preparedStatement.setDate(3, Date.valueOf(row[2]));
        preparedStatement.setDate(4, Date.valueOf(row[3]));
        preparedStatement.setInt(5, Integer.parseInt(row[4]));
        preparedStatement.setInt(6, Integer.parseInt(row[5]));

        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    @Override
    public void deleteRow(String id) throws SQLException {
        super.executeSqlStatement("delete from object where id = " + Integer.parseInt(id));
    }

    @Override
    public void deleteAllData() throws SQLException {
        super.executeSqlStatement("delete from object");
    }

    @Override
    public ArrayList<String[]> getAll() {
        ArrayList<String[]> data = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("""
                    select object.id, lastname, gs.name, datefrom, dateto, t2.name, d.name from object
                    inner join teacher t on t.id = object.teacher
                    inner join group_st gs on gs.id = object.group_st
                    inner join term t2 on t2.id = object.term
                    inner join discipline d on d.id = object.discipline""");

            while (resultSet.next()) {
                String[] row = {resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getString(6),
                        resultSet.getString(7)};
                data.add(row);
            }

            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return data;
    }
}
