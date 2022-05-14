package repository;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommandTable {
    void insertRow(String[] row) throws SQLException;
    void deleteRow(String id) throws SQLException;
    void deleteAllData() throws SQLException;
    ArrayList<String[]> getAll();
}
