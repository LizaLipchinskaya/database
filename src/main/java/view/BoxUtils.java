package view;

import javax.swing.*;
import java.awt.*;

public class BoxUtils {

    public static JPanel createVerticalPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        return panel;
    }

    public static JPanel createHorizontalPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        return panel;
    }

    public static void setGroupAlignmentX(JComponent[] components, float alignment) {
        for (JComponent component : components) component.setAlignmentX(alignment);
    }

    public static void setGroupAlignmentY(JComponent[] components, float alignment) {
        for (JComponent component : components) component.setAlignmentY(alignment);
    }

    public static void makeSameSize(JComponent[] components) {
        int[] array = new int[components.length];

        for (int i = 0; i < array.length; i++) {
            array[i] = components[i].getPreferredSize().width;
        }

        int maxSizePos = maximumElementPosition(array);
        Dimension maxSize = components[maxSizePos].getPreferredSize();

        for (JComponent component : components) {
            component.setPreferredSize(maxSize);
            component.setMinimumSize(maxSize);
            component.setMaximumSize(maxSize);
        }
    }

    private static int maximumElementPosition(int[] array) {
        int maxPos = 0;

        for (int i = 1; i < array.length; i++) {
            if (array[i] > array [maxPos]) {
                maxPos = i;
            }
        }

        return maxPos;
    }

    public static void createRecommendedMargin(JButton[] buttons) {
        for (JButton button : buttons) {
            Insets margin = button.getMargin();
            margin.left = 12;
            margin.right = 12;
            button.setMargin(margin);
        }
    }

    public static void fixTextFieldSize(JTextField field) {
        Dimension size = field.getPreferredSize();
        size.width = field.getMaximumSize().width;
        field.setMaximumSize(size);
    }
}
