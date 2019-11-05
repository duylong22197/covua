/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Match implements Serializable{
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;
    
    private int id;
    private String time;
    private String user1;
    private String user2;
    private int winner;

    public Match(String user1, String user2) {
        this.time = getDateString();
        this.user1 = user1;
        this.user2 = user2;
        this.winner = 0;
    }

    public String getTime() {
        return time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUser1() {
        return user1;
    }

    public void setUser1(String user1) {
        this.user1 = user1;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }
    
    public static String getDateString() {
        String pattern = "HH:mm:ss dd-MM-yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date());
    }
}
