/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

import controllers.ServerMainController;
import java.rmi.RemoteException;


public class ServerRun {
    public static void main(String[] args) throws RemoteException{
        
        new ServerMainController(); 
    }
}
