/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Container;
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


public class LoginForm extends JFrame{
    
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnSignup;

    public LoginForm() {        
        super("Login");
        
        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnLogin = new JButton("Log in");
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
        
        c.gridx = 1;
        c.gridy = 0;
        content.add(txtUsername, c);
        
        c.gridy = 1;
        content.add(txtPassword, c);
        
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        
        c.gridx = 0;
        c.gridy = 2;
        content.add(btnSignup, c);
        
        c.gridx = 1;
        content.add(btnLogin, c);
        
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        
    }
    
    public String getUsername(){
        return txtUsername.getText();
    }
    
    public String getPassword(){
        return txtPassword.getText();
    }
    
    public void addBtnsActionListener(ActionListener log){
        btnLogin.addActionListener(log);
        btnSignup.addActionListener(log);
    }

    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnSignup() {
        return btnSignup;
    }
    
}
