package serverside;

import DAO.DataAccessObject;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSide {

    
    static ServerSocket serverSocket;
    DataInputStream listenFromClient;
    PrintStream printedMessageToClient;
    boolean isRunning = true;
    
    public ServerSide(){
   
            try {
                serverSocket = new ServerSocket(2000);
                while(isRunning){
                    Socket clientSocket = serverSocket.accept();
                    
                    listenFromClient = new DataInputStream(clientSocket.getInputStream());
                    printedMessageToClient = new PrintStream(clientSocket.getOutputStream());

                    String msg = listenFromClient.readLine();
                    if(msg.equals("information")){
                        String info = "";
                        int allUsers = DataAccessObject.getAllUsers();
                        int onlineUsers = DataAccessObject.getOnlineUsers();
                        int availableUsers = DataAccessObject.getAvailableUsers();
                        info = String.valueOf(allUsers) + " " + String.valueOf(onlineUsers) 
                                + " " + String.valueOf(availableUsers);
                        printedMessageToClient.println(info);
                        
                    }
                    else{
                    String[] parts = msg.split(" ", 3);
                    String username;
                    String request;
                    String password;
                    if (parts.length == 3) {
                        request = parts[0];
                        username = parts[1];
                        password = parts[2];
                        
                        if(request.equals("login")){
                            boolean isExist = DataAccessObject.isUserExist(username);
                            if(isExist){
                                boolean isValid = DataAccessObject.isUserValid(username, password);
                                if(isValid){
                                    printedMessageToClient.println("confirm " + username);
                                    System.out.println("con");
                                    new ClientHandler(clientSocket , username);
                                }
                                else{
                                    printedMessageToClient.println("password " + username);
                                }
                            }
                            else{
                                printedMessageToClient.println("username " + username);
                            }
                        }
                        
                    }
                }
                }
            } catch (IOException ex) {
                System.out.println("not accepted");
            } catch (SQLException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    System.out.println("not closed");
                }
            }
          }
    
    public void closeServer() {
        isRunning = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        
    }
}


class ClientHandler extends Thread{
        DataInputStream listenFromClient;
        PrintStream printedMessageToClient;
        static Vector<ClientHandler> clientsVector =new Vector<ClientHandler>(); 
        String name;
        Socket socket;

        public ClientHandler(Socket clientSocket , String userName){
            name = userName;
            socket = clientSocket;
            try {
                listenFromClient = new DataInputStream(clientSocket.getInputStream());
                printedMessageToClient = new PrintStream(clientSocket.getOutputStream());
                
                String msg = listenFromClient.readLine();
                String[] parts = msg.split(" ", 3);
                
                
                ClientHandler.clientsVector.add(this);
                start();
            } catch (IOException ex) {
                System.out.println("Erorr handle!");          
            }
        }

        public void run(){
            while(true){
                try {
                    //printedMessageToClient.println("confirm " + name);
                    String message = listenFromClient.readLine();
                    System.out.println(message);
                    if(message.equalsIgnoreCase("Close")){
                        clientsVector.remove(clientsVector.size()-1);
                        System.out.println(clientsVector.size());
                        if(clientsVector.size()==0){
                            ServerSide.serverSocket.close();
                        }
                        break;
                    }else{
                        //
                    }
                } catch (IOException ex) {
                     break;
                } 
            }
        }
}