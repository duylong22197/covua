/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import views.HistoryForm;
import common.ChessBoard;
import common.Match;
import common.Message;
import common.RMIInterface;
import common.User;
import views.ChangeInfoForm;
import views.ChangePasswordForm;
import views.HistoryForm2;
import views.HomeForm;
import views.LoginForm;
import views.NewJFrame;
import views.ScoreBoardForm;
import views.SignUpForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class ClientMainController {
    private LoginForm loginForm;
    private SignUpForm signupForm;
    private HomeForm homeForm;
    private ChangeInfoForm changeInfoForm;
    private ChangePasswordForm changePasswordForm;
    private HistoryForm2 historyForm;
    private ScoreBoardForm scoreBoardForm;
    private NewJFrame sb2;
    
    private User user;
    
    private RMIInterface rmiServer;
    private Registry registry;
    
    private String rmiHostName;
    private int rmiPort;
    private String rmiService;
    
    private int tcpPort;
    private Socket tcpSocket;
    
    private TCPThread listeningThread;
    
    private ChessBoard chessBoard;
    
    public ClientMainController() {
        initVariablesWithoutJSON();
        initRMI();
        initForms();       
    }
    
    private void initForms(){
        loginForm = new LoginForm();
        signupForm = new SignUpForm();
        homeForm = new HomeForm();
        changeInfoForm = new ChangeInfoForm();
        changePasswordForm = new ChangePasswordForm();
        historyForm = new HistoryForm2();
        scoreBoardForm = new ScoreBoardForm();
        sb2 = new NewJFrame();
        
        changeInfoForm.addBtnChangeActionListener(new MainControllerActionListener());
        changePasswordForm.addBtnChangeActionListener(new MainControllerActionListener());
        signupForm.addBtnSignUpFormActionListener(new MainControllerActionListener());
        loginForm.addBtnsActionListener(new MainControllerActionListener());
        homeForm.addHomeFormActionListener(new MainControllerActionListener());
        
        loginForm.setVisible(true);
        
        changeInfoForm.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                homeForm.setVisible(true);
            }
        });
        
        changePasswordForm.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                homeForm.setVisible(true);
            }
        });
        
        historyForm.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                homeForm.setVisible(true);
            }
        });
        
        scoreBoardForm.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                homeForm.setVisible(true);
            }
        });
        
        sb2.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                homeForm.setVisible(true);
            }
        });
        
        homeForm.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    rmiServer.logOut(user.getUsername());
                    tcpSocket.close();
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void initRMI(){
        try{
            registry = LocateRegistry.getRegistry(rmiHostName, rmiPort);
            rmiServer = (RMIInterface) registry.lookup(rmiService);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initVariables(){
        String fileName = "data.json";
        
        JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(new FileReader(fileName));
            
            JSONObject jsonObject = (JSONObject) obj;
            
            tcpPort = Integer.parseInt((String) jsonObject.get("tcpPort"));
            rmiPort =Integer.valueOf((String)jsonObject.get("rmiPort"));
            rmiHostName = (String) jsonObject.get("rmiAddress");
            rmiService = (String) jsonObject.get("rmiService");
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
    }
    
    private void initVariablesWithoutJSON(){
        tcpPort = 9998;
        rmiPort = 9999;
        rmiHostName = "localhost";
        rmiService = "rmiService";
    }
    
    public void setUser(User user){
        this.user = user;
    }
    
    public User getUser(){
        return this.user;
    }
    
   
    
    private class MainControllerActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource().equals(loginForm.getBtnLogin())){
                String username = loginForm.getUsername();
                String password = loginForm.getPassword();
                if (!username.equals("") && !password.equals("")){
                    try {
                        user = rmiServer.checkLogin(username, password);
                        
                        if (user != null){
                            loginForm.setVisible(false);
                            homeForm.setUser(user);
                            homeForm.setListUsers(rmiServer.getAllOnlineUsers(), user.getUsername());
                            homeForm.setVisible(true);
                            
                            tcpSocket = new Socket(rmiHostName, tcpPort);
                            
                            System.out.println("Socket: "+tcpSocket.getLocalAddress());
                            new TCPThread(homeForm, user, tcpSocket).start();
                        } else JOptionPane.showMessageDialog(loginForm, "Sai tên đăng nhập hoặc mật khẩu rồi bạn ơiiiii :)");
                    } catch (RemoteException ex) {
                        JOptionPane.showMessageDialog(loginForm, "Có gì đó sai sai :)");
                        Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        
                } else {
                    JOptionPane.showMessageDialog(loginForm, "Nhập đủ tên đăng nhập và mật khẩu hộ cái bạn ơiiiii :)");
                } 
            }
            
            if(e.getSource().equals(loginForm.getBtnSignup())){
                loginForm.setVisible(false);
                signupForm.setVisible(true);
            }
            
            if(e.getSource().equals(signupForm.getBtnBack())){
                signupForm.setVisible(false);
                loginForm.setVisible(true);
            }
            
            if(e.getSource().equals(signupForm.getBtnSignup())){
                if (signupForm.validInformation()){
                    User user = signupForm.getUser();
                    
                    try {
                        if (rmiServer.insertUser(user)){
                            JOptionPane.showMessageDialog(signupForm, "Đăng ký OK r nhé. Giờ đăng nhập rồi chiến thôi :)");
                            signupForm.setVisible(false);
                            loginForm.setVisible(true);
                        } else JOptionPane.showMessageDialog(signupForm, "Trùng tên đăng nhập rồi bạn ơi thử tên đăng nhập khác đi!");
                    } catch (RemoteException ex) {
                        JOptionPane.showMessageDialog(signupForm, "Có gì đó sai sai -.-");                        
                        Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else {
                    JOptionPane.showMessageDialog(signupForm, "Cậu nhập sai mật khẩu hoặc cậu nhập thiếu thông tin nào đó");
                }
            }
            
            if (e.getSource().equals(homeForm.getBtnResetList())){
                try {
                    homeForm.setListUsers(rmiServer.getAllOnlineUsers(), getUser().getUsername());
                } catch (RemoteException ex) {
                    JOptionPane.showMessageDialog(homeForm, "Có gì đó sai sai :)");
                    Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (e.getSource().equals(homeForm.getBtnInvite())){
                String username = homeForm.getUsernameSelected();
                
                if(username == null) JOptionPane.showMessageDialog(homeForm, "Chọn đối thủ đã, gì vội thế, chọn một người đi :)");
                else {
                    try {
                        if(rmiServer.invite(user.getUsername(),username)){
//                            homeForm.setVisible(false);
                        } else JOptionPane.showMessageDialog(homeForm, "Người ta sợ không dám đấu lại rồi :)");
                    } catch (RemoteException ex) {
                        Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            if (e.getSource().equals(homeForm.getBtnChangeInfo())){
                changeInfoForm.setName(user.getName());
                homeForm.setVisible(false);
                changeInfoForm.setVisible(true);
            }
            
            if (e.getSource().equals(homeForm.getBtnChangePassword())){
                homeForm.setVisible(false);
                changePasswordForm.setVisible(true);
            }
            
            if (e.getSource().equals(changeInfoForm.getBtnChange())){
                try {
                    rmiServer.changeDisplayName(user.getUsername(), changeInfoForm.getName());
                    user.setName(changeInfoForm.getName());
                    JOptionPane.showMessageDialog(changeInfoForm, "Đổi tên hiển thị xong rồi nhé !!!");
                    changeInfoForm.setVisible(false);
                    homeForm.changeDisplayName(changeInfoForm.getName());
                    homeForm.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
            if (e.getSource().equals(changePasswordForm.getBtnChange())){
                String newPassword = changePasswordForm.getNewPassword();
                if (newPassword != null){
                    try {
                        if (rmiServer.changePassword(user.getUsername(), newPassword, changePasswordForm.getOldPassword())){
                            JOptionPane.showMessageDialog(changePasswordForm, "Đổi mật khẩu thành công");
                            changePasswordForm.setVisible(false);
                            homeForm.setVisible(true);
                        } else JOptionPane.showMessageDialog(changePasswordForm, "Sai mật khẩu cũ rồi bạn ơiiiii :)");
                    } catch (RemoteException ex) {
                        Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else JOptionPane.showMessageDialog(changePasswordForm, "Password nhập lại không trùng mới đau :)");
            }
            
            if (e.getSource().equals(homeForm.getBtnHistory())){
                
                try {
                    homeForm.setVisible(false);
                    ArrayList<Match> list = rmiServer.getListMatch(user.getUsername());
                    historyForm.setListMatches(list);
                    historyForm.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (e.getSource().equals(homeForm.getBtnHistory())){
                
                try {
                    homeForm.setVisible(false);
                    ArrayList<Match> list = rmiServer.getListMatch(user.getUsername());
                    historyForm.setListMatches(list);
                    historyForm.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (e.getSource().equals(homeForm.getBtnShowSB())){
                
                try {
                    homeForm.setVisible(false);
                    ArrayList<User> list = rmiServer.getScoreBoard();
                    sb2.setListMatches(list);
                    sb2.setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientMainController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }
    
}
