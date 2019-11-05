/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

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

public class ChangePasswordForm extends JFrame{
    private JTextField txtOldPassword;
    private JTextField txtNewPassword;
    private JTextField txtConfirmNewPassword;
    private JButton btnChange;
    
    public ChangePasswordForm(){
        super("Change password");
        txtOldPassword = new JPasswordField(20);
        txtNewPassword = new JPasswordField(20);
        txtConfirmNewPassword = new JPasswordField(20);
        btnChange = new JButton("Change");
        
        JPanel content = (JPanel) this.getContentPane();
        content.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 3, 3, 3);
        c.anchor = GridBagConstraints.EAST;
        
        c.gridx = 0;
        c.gridy = 0;
        content.add(new JLabel("Old Password:"), c);
        
        c.gridy = 1;
        content.add(new JLabel("New Password:"), c);
        
        c.gridy = 2;
        content.add(new JLabel("Confirm new Password:"), c);
        
        c.gridx = 1;
        c.gridy = 0;
        content.add(txtOldPassword, c);
        
        c.gridy = 1;
        content.add(txtNewPassword, c);
        
        c.gridy = 2;
        content.add(txtConfirmNewPassword, c);
        
        c.anchor = GridBagConstraints.CENTER;
        c.gridy = 3;
        content.add(btnChange, c);
        
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    public String getOldPassword(){
        return txtOldPassword.getText();
    }
    
    public String getNewPassword(){
        if (txtNewPassword.getText().equals(txtConfirmNewPassword.getText()))
            return txtConfirmNewPassword.getText();
        return null;
    }
    
    public void addBtnChangeActionListener(ActionListener log){
        btnChange.addActionListener(log);
    }

    public JButton getBtnChange() {
        return btnChange;
    }
    
    
    
}
