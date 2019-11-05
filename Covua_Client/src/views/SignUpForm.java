/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import common.User;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpForm extends JFrame{
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JPasswordField txtRePassword;
    private JTextField txtName;
    
    private JButton btnBack;
    private JButton btnSignup;

    public SignUpForm() {        
        super("Sign Up");
        
        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        txtRePassword = new JPasswordField(20);
        txtName = new JTextField(20);
        btnBack = new JButton("Back");
        btnSignup = new JButton("Sign Up");
        
        JPanel content = (JPanel) this.getContentPane();
        
        content.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.insets = new Insets(3, 3, 3, 3);
        
        c.ipadx = 50;
        c.ipady = 10;
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;
        
        c.gridx = 0;
        c.gridy = 0;
        content.add(new JLabel("Username:"), c);
        
        c.gridy = 1;
        content.add(new JLabel("Password:"), c);
        
        c.gridy = 2;
        content.add(new JLabel("RePassword:"), c);
        
        c.gridy = 3;
        content.add(new JLabel("Name"), c);
        
        c.gridx = 1;
        c.gridy = 0;
        content.add(txtUsername, c);
        
        c.gridy = 1;
        content.add(txtPassword, c);
        
        c.gridy = 2;
        content.add(txtRePassword, c);
        
        c.gridy = 3;
        content.add(txtName, c);
        
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        
        c.gridx = 0;
        c.gridy = 4;
        content.add(btnBack, c);
        
        c.gridx = 1;
        content.add(btnSignup, c);
        
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        
    }
    
    public User getUser(){
        User user = new User();
        
        user.setUsername(txtUsername.getText());
        user.setPassword(txtPassword.getText());
                
        user.setScore(0);
        user.setName(txtName.getText());
        
        return user;
    }
    
    public void addBtnSignUpFormActionListener(ActionListener log){
        btnBack.addActionListener(log);
        btnSignup.addActionListener(log);
    }

    public JButton getBtnBack() {
        return btnBack;
    }

    public JButton getBtnSignup() {
        return btnSignup;
    }
    
    public boolean validInformation(){
        return !(txtName.getText().equals("")||
                txtUsername.getText().equals("") ||
                !txtPassword.getText().equals(txtRePassword.getText()) ||
                txtPassword.getText().equals("") ||
                txtRePassword.getText().equals("")
                );
                
    }
}
