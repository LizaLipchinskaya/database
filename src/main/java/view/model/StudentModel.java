package view.model;

import repository.CommandTable;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class StudentModel extends AbstractTableModel {

    private static final int COLUMN_COUNT = 11;

    private final ArrayList<String[]> data;

    public StudentModel() {
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

        for (String[] row : data){
            addData(row);
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "ИД";
            case 1 -> "Фамилия";
            case 2 -> "Имя";
            case 3 -> "Группа";
            case 4 -> "Курс";
            case 5 -> "Факультет";
            case 6 -> "Пол";
            case 7 -> "Дата рождения";
            case 8 -> "Возраст";
            case 9 -> "Есть дети";
            case 10 -> "Размер стипендии";
            default -> "";
        };
    }
}
