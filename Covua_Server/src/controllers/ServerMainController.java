/*
   mo change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import common.Match;
import common.Message;
import common.RMIInterface;
import common.User;
import models.IODataCollection;
import models.UserData;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class ServerMainController extends UnicastRemoteObject implements RMIInterface{
    
    private int rmiPort;
    private String rmiAddress;
    private Registry registry;
    private String rmiService;
    private Connection conn;
    
    private int udpPort;    
    
    private int tcpPort;
    private ServerSocket tcpServerSocket;
    
    private HashMap<String, IODataCollection> mapOnlineUsers;
    
    private LoginHandlingThread loginHandlingThread;
    
    public ServerMainController() throws RemoteException{ 
        mapOnlineUsers = new HashMap<>();
        initVariablesWithoutJSON();
        createRegistry();
        createTCPServer();
        initConnection();
        createHandleLoginThread();
    }
    
    private void createHandleLoginThread(){
        loginHandlingThread = new LoginHandlingThread(mapOnlineUsers, tcpServerSocket);
        loginHandlingThread.run();
    }
    
    private void initVariables(){
        String fileName = "data.json";
        
        JSONParser parser = new JSONParser();
        
        try {
            Object obj = parser.parse(new FileReader(fileName));
            
            JSONObject jsonObject = (JSONObject) obj;
            
            tcpPort = Integer.parseInt((String) jsonObject.get("tcpPort"));
            rmiPort =Integer.valueOf((String)jsonObject.get("rmiPort"));
            rmiAddress = (String) jsonObject.get("rmiAddress");
            rmiService = (String) jsonObject.get("rmiService");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initVariablesWithoutJSON(){
        tcpPort = 9998;
        rmiPort = 9999;
        rmiAddress = "localhost";
        rmiService = "rmiService";
    }
    
    private void createRegistry() throws RemoteException{
        registry = LocateRegistry.createRegistry(rmiPort);
        registry.rebind(rmiService, this);
    }
    
    private void createTCPServer(){
        try {
            tcpServerSocket = new ServerSocket(tcpPort);
            System.out.println("Created TCP server");
        } catch (IOException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void initConnection(){
        String dbUrl = "jdbc:mysql://localhost:3306/covuadb";
        String dbClass = "com.mysql.jdbc.Driver";
        
        try {
            Class.forName(dbClass);
            
            conn = DriverManager.getConnection(dbUrl, "root", "");
            System.out.println("Database connected");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User getUser(String string) throws RemoteException {
        User user = null;
        String sql = "SELECT * FROM tbl_user WHERE username=?";
        
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, string);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()){
                user = new User();
                
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setScore(rs.getInt("score"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }

    @Override
    public boolean setUser(User user) throws RemoteException {
        String sql = "UPDATE tbl_user SET password=?, name=?, score=? WHERE username=?";
        
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setString(1, user.getPassword());
            stm.setString(2, user.getName());
            stm.setInt(3, user.getScore());
            stm.setString(4, user.getUsername());
            
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean insertUser(User user) throws RemoteException {
        System.out.println(user);
        System.out.println(user.getUsername()+" "+user.getPassword()+" "+user.getName()+" "+user.getScore());
        String sql = "INSERT INTO tbl_user(username, password, name, score) "+
                "VALUES(?, ?, ?, ?)";
        
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setString(1, user.getUsername());
            stm.setString(2, user.getPassword());
            stm.setString(3, user.getName());
            stm.setInt(4, user.getScore());
            
            
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public User checkLogin(String username, String password) throws RemoteException {
        User user = null;
        String sql = "SELECT * FROM tbl_user WHERE username=? AND password=?";
        
        try {
            PreparedStatement stm = conn.prepareStatement(sql);
            
            stm.setString(1, username);
            stm.setString(2, password);
            
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()){
                user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setName(rs.getString(3));
                user.setScore(rs.getInt(4));
                if (!mapOnlineUsers.containsKey(user)) {
                    return user;
                }
                else return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }

    @Override
    public void logOut(String username) throws RemoteException {
        IODataCollection ioData = mapOnlineUsers.get(username);
        try {
            ioData.getOis().close();
            ioData.getOos().close();
            ioData.getSocket().close();
        } catch (IOException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mapOnlineUsers.remove(username);
        
        System.out.println(username+" has loged out");
    }

    @Override
    public ArrayList<User> getAllOnlineUsers() throws RemoteException {
        ArrayList<User> list = new ArrayList<>();
        
        for (Map.Entry pair : mapOnlineUsers.entrySet()) {
            User user = ((IODataCollection) pair.getValue()).getUser();
            list.add(user);
        }
        
        return list;
    }

    @Override
    public boolean invite(String inviter, String username) throws RemoteException {
        IODataCollection player1IO = mapOnlineUsers.get(username);
        IODataCollection player2IO = mapOnlineUsers.get(inviter);
        System.out.println("Got invite to "+username);
        
        ObjectOutputStream oos1 = player1IO.getOos();
        ObjectInputStream ois1 = player1IO.getOis();
        
        
        try {
            oos1.writeObject(new Message("Invite", inviter));
            
            Object o = ois1.readObject();
            
            if (o instanceof Message){
                Message message = (Message) o;
                
                if (message.getTitle().equals("AC")) {
                    System.out.println("User accepted");
                    mapOnlineUsers.get(inviter).getUser().setStatus(User.BUSY);                    
                    mapOnlineUsers.get(username).getUser().setStatus(User.BUSY);
                    new MatchHandlingThread(player1IO, player2IO, username, inviter, conn, mapOnlineUsers).start();
                    return true;
                }
                else {
                    System.out.println("User declided");
                    return false;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    @Override
    public boolean changePassword(String username, String password, String oldPassword)throws RemoteException {
        String query = "UPDATE tbl_user SET password=? WHERE username=? AND password=?";
        
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, password);
            stm.setString(2, username);
            stm.setString(3, oldPassword);
            return stm.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public void changeDisplayName(String username, String name) throws RemoteException{
        String query = "UPDATE tbl_user SET name=? WHERE username=?";
        
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, name);
            stm.setString(2, username);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Match> getListMatch(String username) throws RemoteException {
        ArrayList<Match> list = new ArrayList<>();
        String query = "SELECT * FROM tbl_match WHERE user1=? OR user2=? ORDER BY id DESC";
        
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, username);
            stm.setString(2, username);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()){
                Match m = new Match(rs.getString("user1"), rs.getString("user2"));
                m.setTime(rs.getString("time"));
                m.setId(rs.getInt("id"));
                m.setWinner(rs.getInt("winner"));
                
                list.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }

    @Override
    public ArrayList<User> getScoreBoard() throws RemoteException {
        ArrayList<User> list = new ArrayList<>();
        String query = "SELECT * FROM tbl_user ORDER BY score DESC";
        
        try {
            PreparedStatement stm = conn.prepareStatement(query);
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()){
                User u = new User();
                u.setUsername(rs.getString("username"));
                u.setName(rs.getString("name"));
                u.setScore(rs.getInt("score"));
                
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
}
