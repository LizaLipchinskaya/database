package view.requestModel;

import repository.CommandTable;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StudentTopicModel extends AbstractTableModel {
    private static final int COLUMN_COUNT = 5;

    private final ArrayList<String[]> dataArrayList;

    public StudentTopicModel() {
        dataArrayList = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ИД студента";
            case 1 -> "Студент";
            case 2 -> "Тема";
            case 3 -> "Кафедра";
            case 4 -> "Преподаватель";
            default -> "";
        };
    }

    public void addData(String[] row) {
        dataArrayList.add(row);
    }

    public void addData(CommandTable table){
        ArrayList<String[]> data = table.getAll();

        for (String[] row : data){
            addData(row);
        }
    }

    public void addData(ArrayList<String[]> data){
        for (String[] row : data){
            addData(row);
        }
    }
}
