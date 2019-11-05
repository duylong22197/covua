/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import common.User;
import java.net.Socket;

public class UserData {    
    private User userInfo;
    private Socket userSoket;

    public UserData(User userInfo, Socket userSoket) {
        this.userInfo = userInfo;
        this.userSoket = userSoket;
    }

    public User getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(User userInfo) {
        this.userInfo = userInfo;
    }

    public Socket getUserSoket() {
        return userSoket;
    }

    public void setUserSoket(Socket userSoket) {
        this.userSoket = userSoket;
    }

    public UserData() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserData){
            UserData ud = (UserData) obj;
            return ud.getUserInfo().equals(this.userInfo);
        }
        return false;
    }
}
