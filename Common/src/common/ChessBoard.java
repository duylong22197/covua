/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;

public class ChessBoard implements Serializable{
    
    public static final int BLACK = -1;
    public static final int WHITE = 1;
    
      public static final int TOT_DEN = -1;
    public static final int XE_DEN = -2;
    public static final int MA_DEN = -3;
    public static final int TINH_DEN = -4;
    public static final int HAU_DEN = -5;
    public static final int VUA_DEN = -6;
    
    public static final int TOT_TRANG = 1;
    public static final int XE_TRANG = 2;
    public static final int MA_TRANG = 3;
    public static final int TINH_TRANG = 4;
    public static final int HAU_TRANG = 5;
    public static final int VUA_TRANG = 6;
    
    public static final int TRANG = 1;
    public static final int DEN = -1;
    
    private int turn;
    private int board[][];
    
    public ChessBoard(){
        turn = DEN;
        
        board = new int[8][8];
        
        for (int i = 0; i < 8; i++){
            board[1][i] = TOT_DEN;
            board[6][i] = TOT_TRANG;
        }
        
        board[0][0] = XE_DEN;
        board[0][7] = XE_DEN;
        board[0][1] = MA_DEN;
        board[0][6] = MA_DEN;
        board[0][2] = TINH_DEN;
        board[0][5] = TINH_DEN;
        board[0][3] = HAU_DEN;
        board[0][4] = VUA_DEN;
        
        board[7][0] = XE_TRANG;
        board[7][7] = XE_TRANG;
        board[7][1] = MA_TRANG;
        board[7][6] = MA_TRANG;
        board[7][2] = TINH_TRANG;
        board[07][5] = TINH_TRANG;
        board[07][3] = HAU_TRANG;
        board[07][4] = VUA_TRANG;
    }
    
    public int getSmth(int x, int y){
        return board[x][y];
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }
    
    public int checkWin(){        
        boolean check = false;
        
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++){
                if (board[i][j] == VUA_DEN) check = true;
            }
        }
        
        if (!check) return TRANG;        
        
        check = false;
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j ++){
                if (board[i][j] == VUA_TRANG) check = true;
            }
        }
        
        if (!check) return DEN;
        
        return 0;
    }
    
    public void move(int x0, int y0, int x, int y){
        switch (board[x0][y0]){
            case TOT_TRANG: {
                if (x == x0-1){
                    if (y == y0 && board[x][y] == 0){
                        board[x][y] = TOT_TRANG;
                        board[x0][y0] = 0;
                        break;
                    }
                    if (y == y0+1 && board[x][y] != 0){
                        board[x][y] = TOT_TRANG;
                        board[x0][y0] = 0;
                        break;
                    }  
                    if (y == y0-1 && board[x][y] != 0){
                        board[x][y] = TOT_TRANG;
                        board[x0][y0] = 0;
                        break;
                    } 
                }
                break;
            }
            
            case TOT_DEN: {
                if (x == x0+1){
                    if (y == y0 && board[x][y] == 0){
                        board[x][y] = TOT_DEN;
                        board[x0][y0] = 0;
                        break;
                    }
                    if (y == y0+1 && board[x][y] != 0){
                        board[x][y] = TOT_DEN;
                        board[x0][y0] = 0;
                        break;
                    }  
                    if (y == y0-1 && board[x][y] != 0){
                        board[x][y] = TOT_DEN;
                        board[x0][y0] = 0;
                        break;
                    } 
                }
                break;
            }
            
            case XE_TRANG: {
                if (x == x0){
                    int check = 0;
                    for (int i = y0+1; i <y; i++){
                        if (board[x][i] != 0) {
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    }                        
                    break;
                }
                if (y == y0){
                    int check = 0;
                    for (int i = x0+1; i <x; i++){
                        if (board[i][y] != 0) {
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    }  
                    break;
                }
                break;
            }
            
            case XE_DEN: {
                if (x == x0){
                    int check = 0;
                    for (int i = y0+1; i <y; i++){
                        if (board[x][i] != 0) {
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    }                        
                    break;
                }
                if (y == y0){
                    int check = 0;
                    for (int i = x0+1; i <x; i++){
                        if (board[i][y] != 0) {
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    }  
                    break;
                }
                break;
            }
            
            case MA_TRANG: {
                if (((x == x0+1 && y == y0+2) || (x == x0+1 && y == y0-2) ||
                        (x == x0-1 && y == y0+2) || (x == x0-1 && y == y0-2) ||
                        (x == x0+2 && y == y0+1) || (x == x0+2 && y == y0-1) ||
                        (x == x0-2 && y == y0+1) || (x == x0-2 && y == y0-1)) 
                        && board[x][y]*board[x0][y0] <= 0){
                    board[x][y] = board[x0][y0];
                    board[x0][y0] = 0;
                }
            }
            
            case MA_DEN: {
                if (((x == x0+1 && y == y0+2) || (x == x0+1 && y == y0-2) ||
                        (x == x0-1 && y == y0+2) || (x == x0-1 && y == y0-2) ||
                        (x == x0+2 && y == y0+1) || (x == x0+2 && y == y0-1) ||
                        (x == x0-2 && y == y0+1) || (x == x0-2 && y == y0-1)) 
                        && board[x][y]*board[x0][y0] <= 0){
                    board[x][y] = board[x0][y0];
                    board[x0][y0] = 0;
                }
            }
            
            case TINH_DEN: {
                if (x-y == x0-y0){
                    boolean check = false;
                    if (x > x0){
                        for (int i = x0+1; i < x; i++){
                            if (board[x0+i][y0+i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    } else {
                        for (int i = x+1; i < x0; i++){
                            if (board[x+i][y+i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    } 
                }
                if (x+y == x0+y0){
                    boolean check = false;
                    if (x > x0){
                        for (int i = x0+1; i < x; i++){
                            System.out.println((x0+i)+" "+(y0-i)+" "+i);
                            if (board[x0+i][y0-i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    } else {
                        for (int i = x+1; i < x0; i++){
                            if (board[x+i][y-i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    } 
                }
                break;
            }
            case TINH_TRANG: {
                if (x-y == x0-y0){
                    boolean check = false;
                    if (x > x0){
                        for (int i = x0+1; i < x; i++){
                            if (board[x0+i][y0+i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    } else {
                        for (int i = x+1; i < x0; i++){
                            if (board[x+i][y+i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    } 
                }
                if (x+y == x0+y0){
                    boolean check = false;
                    if (x > x0){
                        for (int i = x0+1; i < x; i++){
                            System.out.println((x0+i)+" "+(y0-i)+" "+i);
                            if (board[x0+i][y0-i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    } else {
                        for (int i = x+1; i < x0; i++){
                            if (board[x+i][y-i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    } 
                }
                break;
            }
            
            case HAU_DEN: {
                if (x == x0){
                    int check = 0;
                    for (int i = y0+1; i <y; i++){
                        if (board[x][i] != 0) {
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    }                        
                    break;
                }
                if (y == y0){
                    int check = 0;
                    for (int i = x0+1; i <x; i++){
                        if (board[i][y] != 0) {
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    }  
                    break;
                }
                
                if (x-y == x0-y0){
                    boolean check = false;
                    if (x > x0){
                        for (int i = x0+1; i < x; i++){
                            if (board[x0+i][y0+i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    } else {
                        for (int i = x+1; i < x0; i++){
                            if (board[x+i][y+i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    } 
                }
                if (x+y == x0+y0){
                    boolean check = false;
                    if (x > x0){
                        for (int i = x0+1; i < x; i++){
                            System.out.println((x0+i)+" "+(y0-i)+" "+i);
                            if (board[x0+i][y0-i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    } else {
                        for (int i = x+1; i < x0; i++){
                            if (board[x+i][y-i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    } 
                }
                break;
            }
            
            case HAU_TRANG: {
                if (x == x0){
                    int check = 0;
                    for (int i = y0+1; i <y; i++){
                        if (board[x][i] != 0) {
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    }                        
                    break;
                }
                if (y == y0){
                    int check = 0;
                    for (int i = x0+1; i <x; i++){
                        if (board[i][y] != 0) {
                            check = 1;
                            break;
                        }
                    }
                    if (check == 1) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    }  
                    break;
                }
                
                if (x-y == x0-y0){
                    boolean check = false;
                    if (x > x0){
                        for (int i = x0+1; i < x; i++){
                            if (board[x0+i][y0+i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    } else {
                        for (int i = x+1; i < x0; i++){
                            if (board[x+i][y+i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    } 
                }
                if (x+y == x0+y0){
                    boolean check = false;
                    if (x > x0){
                        for (int i = x0+1; i < x; i++){
                            System.out.println((x0+i)+" "+(y0-i)+" "+i);
                            if (board[x0+i][y0-i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    } else {
                        for (int i = x+1; i < x0; i++){
                            if (board[x+i][y-i] != 0) {
                                check = true;
                                break;
                            }
                        }
                    }
                    if (check) break;
                    if (board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                    } 
                }
                break;
            }
            
            case VUA_DEN: {
                if (x == x0){
                    if (y == y0+1 && board[x][y]*board[x0][y0] <= 0) {
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                    if (y == y0-1 && board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                }
                
                if (y == y0) {
                    if (x == x0+1 && board[x][y]*board[x0][y0] <= 0) {
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                    if (x == x0-1 && board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                }
                
                if (x-y == x0-y0 || x+y == x0+y0){
                    if (x == x0+1 && board[x][y]*board[x0][y0] <= 0) {
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                    if (x == x0-1 && board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                }
            }
            
            case VUA_TRANG: {
                if (x == x0){
                    if (y == y0+1 && board[x][y]*board[x0][y0] <= 0) {
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                    if (y == y0-1 && board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                }
                
                if (y == y0) {
                    if (x == x0+1 && board[x][y]*board[x0][y0] <= 0) {
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                    if (x == x0-1 && board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                }
                
                if (x-y == x0-y0 || x+y == x0+y0){
                    if (x == x0+1 && board[x][y]*board[x0][y0] <= 0) {
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                    if (x == x0-1 && board[x][y]*board[x0][y0] <= 0){
                        board[x][y] = board[x0][y0];
                        board[x0][y0] = 0;
                        break;
                    }
                }
            }
            
        }
        if (board[x0][y0] == 0)turn = -turn;
    }
    
}
