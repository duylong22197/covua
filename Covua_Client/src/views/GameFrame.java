/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import common.ChessBoard;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameFrame extends JFrame{
    private GamePanel gamePanel;
    
    public GameFrame(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        
        JPanel content = (JPanel) this.getContentPane();
        
        content.setLayout(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        
        c.ipadx = 590;
        c.ipady = 590;
        
        c.gridx = 0;
        c.gridy = 0;
        
        content.add(gamePanel, c);
        this.addMouseListener(gamePanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
    
    
}
