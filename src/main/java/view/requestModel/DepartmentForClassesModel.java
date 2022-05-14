package view.requestModel;

import repository.CommandTable;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class DepartmentForClassesModel extends AbstractTableModel {

    private static final int COLUMN_COUNT = 7;

    private final ArrayList<String[]> dataArrayList;

    public DepartmentForClassesModel() {
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
            case 0 -> "ИД предмета";
            case 1 -> "Кафедра";
            case 2 -> "Группа";
            case 3 -> "Курс";
            case 4 -> "Факультет";
            case 5 -> "Начало занятий";
            case 6 -> "Конец занятий";
            default -> "";
        };
    }

    public void addData(String[] row) {
        dataArrayList.add(row);
    }

    public void addData(CommandTable table) {
        ArrayList<String[]> data = table.getAll();

        for (String[] row : data){
            addData(row);
        }
    }

    public void addData(ArrayList<String[]> data){
        for (String[] row : data) {
            addData(row);
        }
    }
}
