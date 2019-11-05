/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package views;

import common.Match;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Quoc Hung
 */
public class HistoryForm2 extends javax.swing.JFrame {

    /** Creates new form HistoryForm2 */
    DefaultTableModel mdlListMatch;
    public HistoryForm2() {
        initComponents();
        this.setDefaultCloseOperation(1);
        this.setLocationRelativeTo(null);
        mdlListMatch = (DefaultTableModel) jTable1.getModel();
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
                    m.getId(), m.getUser1(), m.getUser2(), m.getUser1()
                });
            } else {
                mdlListMatch.addRow(new Object[]{
                    m.getId(), m.getUser1(), m.getUser2(), m.getUser2()
                });
            }        
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Match id", "Player 1", "Player 2", "Winner"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(12);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 565, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
