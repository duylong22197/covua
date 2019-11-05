/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class StatusColumnCellRenderer extends DefaultTableCellRenderer {
  @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

    //Cells are by default rendered as a JLabel.
    JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

    //Get the status for the current row.
    DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
    if (tableModel.getValueAt(row, 3) == "Free") {
      l.setBackground(Color.GREEN);
    } else {
      l.setBackground(Color.RED);
    }

  //Return the JLabel which renders the cell.
  return l;

}
}