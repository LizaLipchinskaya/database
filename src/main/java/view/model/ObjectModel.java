package view.model;

import repository.CommandTable;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ObjectModel extends AbstractTableModel {

    private static final int COLUMN_COUNT = 7;

    private final ArrayList<String[]> data;

    public ObjectModel() {
        data = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = data.get(rowIndex);
        return rows[columnIndex];
    }

    public void addData(String[] row) {
        data.add(row);
    }

    public void addData(CommandTable commandTable) {
        ArrayList<String[]> data = commandTable.getAll();

        for (String[] row : data) {
            addData(row);
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ИД";
            case 1 -> "Преподаватель";
            case 2 -> "Группа";
            case 3 -> "Дата начала";
            case 4 -> "Дата окончания";
            case 5 -> "Семестр";
            case 6 -> "Дисциплина";
            default -> "";
        };
    }
}
