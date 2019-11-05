/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import common.ChessBoard;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements MouseListener{
    
   ChessBoard chessBoard;
    private int z = 60;
    private Position selectedBox;
    private int side;
    private int turn;
    
    public GamePanel(int side){
        chessBoard = new ChessBoard();
        this.setSize(600, 600);
        this.side = side;
        selectedBox = new Position();
        this.turn = ChessBoard.TRANG;
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        
        int[][] board = chessBoard.getBoard();
        
        g2.setColor(Color.GRAY);
        g2.fillRect(0, 0, 600, 600);
        
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                if ((i-j)%2 == 0){
                    g2.setColor(Color.GRAY);
                    g2.fillRect(z+i*z, z+j*z, z, z);
                } else {
                    g2.setColor(Color.BLACK);
                    g2.fillRect(z+i*z, z+j*z, z, z);
                }
            }
        }
        g2.setColor(Color.BLACK);
        g2.drawLine(z, z, z, z*9);
        g2.drawLine(z, z, z*9, z);
        g2.drawLine(z*9, z, z*9, z*9);
        g2.drawLine(z, z*9, z*9, z*9);
        
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
//                switch(board[i][j]){
//                    case ChessBoard.TOT_DEN:
//                
//                    
//                    break;
//                }
                if (selectedBox.x == i && selectedBox.y == j && selectedBox.isSelected) {
                    g2.setColor(Color.green);
                    g2.drawRect(z+j*z+3, z+i*z+3, z-6, z-6);
                }
                try {
                    String url = getImgUrl(board[i][j]);
                    if (url.equals("")) continue;
                    BufferedImage image = ImageIO.read(new File(url));
                    g2.drawImage(image,  z+j*z+15, z+i*z,null);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
    }
    
    public String getImgUrl(int quan){
        
        switch (quan){
            case ChessBoard.TOT_DEN:
                return "img/tot_xanh.png";
            case ChessBoard.TOT_TRANG:
                return "img/tot_vang.png";
            case ChessBoard.XE_DEN:
                return "img/xe_xanh.png";
            case ChessBoard.XE_TRANG:
                return "img/xe_vang.png";
            case ChessBoard.MA_DEN:
                return "img/ma_xanh.png";
            case ChessBoard.MA_TRANG:
                return "img/ma_vang.png";
            case ChessBoard.TINH_DEN:
                return "img/tuong_xanh.png";
            case ChessBoard.TINH_TRANG:
                return "img/tuong_vang.png";
            case ChessBoard.HAU_DEN:
                return "img/hau_xanh.png";
            case ChessBoard.HAU_TRANG:
                return "img/hau_vang.png";
            case ChessBoard.VUA_DEN:
                return "img/vua_xanh.png";
            case ChessBoard.VUA_TRANG:
                return "img/vua_vang.png";
            default:
                return "";
        }
    }

    

    @Override
    public void mouseClicked(MouseEvent e) {
        
        int x = e.getX()-10;
        int y = e.getY()-37;
        
        int a = -1, b = -1;
        
        if ( x < 30 || y < 30) return;
        
        for (int i = 50; i <375; i += z){
            if (i >= x){
                if (  x >= i-z/2) a = i/z -1;
                break;
            }
        }
        
        for (int i = 50; i <375; i += z){
            if (i >= y){
                if (  y >= i-z/2) b = i/z - 1;
                break;
            }
        }
        
        if (x >= 60 && x <= 540){
            a = x/60 - 1;
        }
        
        if (y >= 60 && y <= 540){
            b = y/60 - 1;
        }
        
        System.out.println("Clicked "+a+" "+b);
        
        if (a != -1 && b != -1){
            if (! selectedBox.isSelected){
                if (chessBoard.getSmth(b, a)*side > 0) selectedBox.setPos(b, a);
            } else{
                if (chessBoard.getTurn() == side) chessBoard.move(selectedBox.x, selectedBox.y, b, a);
                selectedBox.reset();
            }
            System.out.println(selectedBox.x+" "+selectedBox.y+" "+selectedBox.isSelected+" "+chessBoard.getSmth(b, a)+" "+side);
        }
        
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        repaint();
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }
    
    private class Position{
        public int x, y;
        public boolean isSelected;
        
        public Position(){
            x = -1;
            y = -1;
            isSelected = false;
        }
        
        public void reset(){
            x = -1;
            y = -1;
            isSelected = false;
        }
        
        public void setPos(int a, int b){
            x = a;
            y = b;
            isSelected = true;
        }
    }
    
    
    
}
