import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class PnP_GUI_Components extends PnP_GUI {

    public static JPanel createJPanelComponents(JPanel panel, int widthSize, int heightSize, int LocX, int LocY,
                                                Color color){
        panel.setSize(widthSize, heightSize);
        panel.setLocation(LocX, LocY);
        panel.setBackground(color);
        return panel;
    }

    public static JComboBox createJComboBoxComponents(JComboBox comboBox, int widthSize, int heightSize,
                                                      int LocX, int LocY, boolean scrollable){
        comboBox.setSize(widthSize, heightSize);
        comboBox.setLocation(LocX, LocY);
        comboBox.setAutoscrolls(scrollable);
        return comboBox;
    }

    public static JLabel createJLabelComponents(JLabel label, int widthSize, int heightSize, int LocX, int LocY){
        label.setSize(widthSize, heightSize);
        label.setLocation(LocX, LocY);
        return label;
    }

    public static JTable createJTableComponents(JTable table, int widthSize, int heightSize, int LocX, int LocY, String Name, int row, int column){
        table.setSize(widthSize, heightSize);
        table.setLocation(LocX, LocY);
        table.setValueAt(Name, row, column);
        return table;
    }

    //This function is resizing each column width
    public void setJTableColumnsWidth(JTable table, int tablePreferredWidth, double... percentages){

        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int)
                    (tablePreferredWidth * (percentages[i] / total)));
        }
    }

}
