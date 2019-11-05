/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import common.User;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class HomeForm extends JFrame{
    private JPanel userInfoContent;
    private JLabel txtName;
    private JLabel txtScore;
    private JLabel txtUsername;
    
    private JScrollPane scrListUsers;
    private JTable tblListUsers;
    private DefaultTableModel mdlListUsers;
    
    private JPanel changeInfoContent;
    private JPanel listControllerContent;
    private JButton btnInvite;
    private JButton btnChangeInfo;
    private JButton btnChangePassword;
    private JButton btnResetList;
    private JButton btnHistory;
    private JButton btnShowSB;
    
    private GridBagConstraints c;

    public HomeForm() {
        super("Home");
        
        setUserInfoContent();
        setListUsersContent();
        setButtonContent();
        
        JPanel content = (JPanel) this.getContentPane();
        
        content.setLayout(new GridBagLayout());
        
        c = new GridBagConstraints();
        c.insets = new Insets(3, 3, 3, 3);
                
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridx = 0;
        c.gridy = 0;
        content.add(changeInfoContent, c);
        
        c.gridy = 1;
        content.add(userInfoContent, c);
        
        c.gridy = 2;
        content.add(scrListUsers, c);
                
        c.gridy = 3;
        content.add(listControllerContent, c);
        
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
    }
    
    private void setUserInfoContent(){
        userInfoContent = new JPanel(new GridBagLayout());
        txtName = new JLabel();
        txtScore = new JLabel();
        txtUsername = new JLabel();
                
        c = new GridBagConstraints();
        
        c.insets = new Insets(3, 3, 3, 3);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.ipadx = 70;
        c.ipady = 10;
        
        c.gridx = 0;
        c.gridy = 0;
        userInfoContent.add(new JLabel("Username:"),c);
        
        c.gridx = 0;
        c.gridy = 1;
        userInfoContent.add(new JLabel("Player name:"),c);
        
        c.gridx = 0;
        c.gridy = 2;
        userInfoContent.add(new JLabel("Player score:"),c);
        
        c.ipadx = 200;
        c.gridx = 1;
        c.gridy = 0;
        userInfoContent.add(txtUsername,c);
        
        c.gridx = 1;
        c.gridy = 1;
        userInfoContent.add(txtName,c);
        
        c.gridx = 1;
        c.gridy = 2;
        userInfoContent.add(txtScore,c);        
        
    }   
    
    private void setListUsersContent(){
        String[] columnNames = {"Username", "Name", "Score", "Status"};
        Object[][] data = {};
        
        tblListUsers = new JTable(){
            public boolean isCellEditable(int nRow, int nCol) {
                return false;
            }
        };
//        tblRoomList.setPreferredScrollableViewportSize(new Dimension(500, 300));
        tblListUsers.setFillsViewportHeight(true);
        
        scrListUsers = new JScrollPane(tblListUsers); 
        mdlListUsers = (DefaultTableModel) tblListUsers.getModel();
        
        mdlListUsers.setColumnIdentifiers(columnNames);   
        tblListUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblListUsers.getColumnModel().getColumn(3).setCellRenderer(new StatusColumnCellRenderer());
    }
    
    private void setButtonContent(){        
        changeInfoContent = new JPanel(new GridBagLayout());
        listControllerContent = new JPanel(new GridBagLayout());
        btnInvite = new JButton("Invite");
        btnChangeInfo = new JButton("Change Information");
        btnChangePassword = new JButton("Change Password");   
        btnResetList = new JButton("Reset List");
        btnHistory = new JButton("History");
        btnShowSB = new JButton("Score Board");
        
        c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.WEST;
        c.insets = new Insets(3, 3, 3, 3);
        c.gridx = 0;
        c.gridy = 0;
        changeInfoContent.add(btnChangeInfo, c);
        c.gridx = 1;
        changeInfoContent.add(btnChangePassword, c);
        c.gridx = 2;
        changeInfoContent.add(btnHistory, c);
        c.gridx = 3;
        changeInfoContent.add(btnShowSB, c);
        
        c.gridx = 0;
        listControllerContent.add(btnInvite, c);
        
        c.gridx = 1;
        listControllerContent.add(btnResetList, c);
        
    }
    
    public void setUser(User user){
        System.out.println(user.getName()+" "+user.getScore());
        txtName.setText(user.getName());
        txtUsername.setText(user.getUsername());
        txtScore.setText(String.valueOf(user.getScore()));
    }
    
    public void setListUsers(ArrayList<User> listUsers, String username){
        int rowCount = tblListUsers.getRowCount();
        
        for (int i = 0; i < rowCount; i ++) mdlListUsers.removeRow(0);
        
        for(User r: listUsers){
            if (!r.getUsername().equals(username))mdlListUsers.addRow(new Object[]{
                r.getUsername(), r.getName(), r.getScore(), r.getStatusString()
            });
        }
    }

    public JButton getBtnInvite() {
        return btnInvite;
    }

    public JButton getBtnChangeInfo() {
        return btnChangeInfo;
    }

    public JButton getBtnChangePassword() {
        return btnChangePassword;
    }

    public JButton getBtnResetList() {
        return btnResetList;
    }
    
    public void addHomeFormActionListener(ActionListener log){
        btnChangeInfo.addActionListener(log);
        btnInvite.addActionListener(log);
        btnChangePassword.addActionListener(log);
        btnResetList.addActionListener(log);
        btnHistory.addActionListener(log);
        btnShowSB.addActionListener(log);
    }
    
    public String getUsernameSelected(){
        int rowSelected = tblListUsers.getSelectedRow();
        if(rowSelected < 0) return null;
        String username = mdlListUsers.getValueAt(rowSelected, 0).toString();
        
        return username;
    }
    
    public void plusScore(){
        int score = Integer.parseInt(txtScore.getText());
        score += 10;
        txtScore.setText(String.valueOf(score));
    }
    
    public void changeDisplayName(String newName){
        txtName.setText(newName);
    }

    public JButton getBtnHistory() {
        return btnHistory;
    }

    public JButton getBtnShowSB() {
        return btnShowSB;
    }
    
}
