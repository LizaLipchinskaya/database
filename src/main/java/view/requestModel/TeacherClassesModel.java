package view.requestModel;

import repository.CommandTable;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TeacherClassesModel extends AbstractTableModel {

    private static final int COLUMN_COUNT = 5;

    private final ArrayList<String[]> dataArrayList;

    public TeacherClassesModel() {
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
            case 0 -> "ИД учителя";
            case 1 -> "Дисциплина";
            case 2 -> "Группа";
            case 3 -> "Курс";
            case 4 -> "Факультет";
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
