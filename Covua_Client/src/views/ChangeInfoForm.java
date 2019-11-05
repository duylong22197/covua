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
import javax.swing.JTextField;


public class ChangeInfoForm extends JFrame{
    private JTextField txtDisplayName;
    private JButton btnChange;

    public ChangeInfoForm() {
        super("Change information");
        
        txtDisplayName = new JTextField(20);
        btnChange = new JButton("Change");
        
        JPanel content = (JPanel) this.getContentPane();
        content.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 3, 3, 3);
        
        c.gridx = 0;
        c.gridy = 0;
        content.add(new JLabel("Display Name"), c);
        
        c.gridx = 1;
        content.add(txtDisplayName, c);
        
        c.gridy = 1;
        content.add(btnChange, c);
        
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        
    }

    public JButton getBtnChange() {
        return btnChange;
    }
    
    public void setName(String name){
        txtDisplayName.setText(name);
    }
    
    public String getName(){
        return txtDisplayName.getText();
    }
    
    public void addBtnChangeActionListener(ActionListener log){
        btnChange.addActionListener(log);
    }
    
}
