/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import common.Match;
import common.User;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


public class ScoreBoardForm extends JFrame{
    private JScrollPane scrListMatch;
    private JTable tblListMatch;
    private DefaultTableModel mdlListMatch;
    
    public ScoreBoardForm(){
        super("ScoreBoard");
        
        JPanel content = (JPanel) getContentPane();
        
        setListMatchesContent();
        content.setLayout(new FlowLayout());
        
        content.add(scrListMatch);
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }
    
    private void setListMatchesContent(){
        String[] columnNames = {"No", "Username", "Name", "Score"};
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
    
    public void setListMatches(ArrayList<User> list){
        int rowCount = mdlListMatch.getRowCount();
        System.out.println(rowCount);
        for (int i = 0; i < rowCount; i++){
            mdlListMatch.removeRow(0);
        }
        int k = 1;
        for (User u: list){
            mdlListMatch.addRow(new Object[]{
                k, u.getUsername(), u.getName(), u.getScore()
            });    
            k++;
        }
    }
}
