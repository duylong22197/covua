package views;


import common.Match;
import views.StatusColumnCellRenderer;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class HistoryForm extends JFrame{
    private JScrollPane scrListMatch;
    private JTable tblListMatch;
    private DefaultTableModel mdlListMatch;
    
    public HistoryForm(){
        super("Match");
        
        JPanel content = (JPanel) getContentPane();
        
        setListMatchesContent();
        content.setLayout(new BorderLayout());
        
        content.add(scrListMatch, BorderLayout.CENTER);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void setListMatchesContent(){
        String[] columnNames = {"Match id", "Time", "Player 1", "Player 2", "Winner"};
        Object[][] data = {};
        
        tblListMatch = new JTable(){
            public boolean isCellEditable(int nRow, int nCol) {
                return false;
            }
        };
//        tblRoomList.setPreferredScrollableViewportSize(new Dimension(500, 300));
        tblListMatch.setFillsViewportHeight(true);
        scrListMatch = new JScrollPane(tblListMatch); 
        mdlListMatch = (DefaultTableModel) tblListMatch.getModel();
        
        tblListMatch.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        mdlListMatch.setColumnIdentifiers(columnNames);   
        tblListMatch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    public void setListMatches(ArrayList<Match> list){
        int rowCount = mdlListMatch.getRowCount();
        System.out.println(rowCount);
        for (int i = 0; i < rowCount; i++){
            mdlListMatch.removeRow(0);
        }
        
        for (Match m: list){
            System.out.println(m.getId()+" "+m.getTime()+" "+ m.getUser1()+" "+m.getUser2()+" "+ m.getUser1());
            if (m.getWinner() == 1){
                mdlListMatch.addRow(new Object[]{
                    m.getId(), m.getTime(), m.getUser1(), m.getUser2(), m.getUser1()
                });
            } else {
                mdlListMatch.addRow(new Object[]{
                    m.getId(), m.getTime(), m.getUser1(), m.getUser2(), m.getUser2()
                });
            }        
        }
    }
    
}
