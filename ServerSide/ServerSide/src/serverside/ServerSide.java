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
    Socket socket;
    DataInputStream listenFromClient;
    PrintStream printedMessageToClient;
    boolean isRunning = true;
    
    public ServerSide(){
   
        try {
            serverSocket = new ServerSocket(2000);

            new Thread(){
                public void run(){
                    while (true) {
                        try {
                            socket = serverSocket.accept();
                            new ClientHandler(socket , "");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }.start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*finally {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    System.out.println("not closed");
                }
            }*/
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
        new ServerSide();
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
                listenFromClient = new DataInputStream(socket.getInputStream());
                printedMessageToClient = new PrintStream(socket.getOutputStream());
                
                start();
            } catch (IOException ex) {
                System.out.println("Erorr handle!");          
            }
        }

        public void run(){
            while(true){
                try {
                    String message = listenFromClient.readLine();
                    System.out.println("listen");
                    String[] parts = message.split(" ");
                    String username = null;
                    String password = null;
                    if(parts[0].equals("information"))
                    {
                        //getStatistics();
                        
                    }
                    else if(parts[0].equals("login"))
                    {
                        username = parts[1];
                        password = parts[2];  
                        validateLogin(username , password);
                    }else if(parts[0].equals("Logout")){
                        username = parts[1];
                        DataAccessObject.updateOnlineState(username,false);
                        //DataAccessObject.updateAvailability(username,false);
                    }
                    else if(parts[0].equals("Available")){
                        username = parts[1];
                        DataAccessObject.updateAvailability(username,true);
                    }
                    if(message.equalsIgnoreCase("Close")){
                        clientsVector.remove(clientsVector.size()-1);
                        System.out.println(clientsVector.size());
                        if(clientsVector.size()==0){
                            ServerSide.serverSocket.close();
                        }
                        break;
                    }
                } catch (IOException ex) {
                    ex.getStackTrace();
                } catch (SQLException ex) {
                    ex.getErrorCode();
                    ex.getMessage();
                }
            }
        }
        
       public void validateLogin(String username , String password) throws SQLException{
        boolean isExist = DataAccessObject.isUserExist(username);
        if(isExist){
            try {
                boolean isValid = DataAccessObject.isUserValid(username, password);
                if(isValid){
                    printedMessageToClient.println("confirm " + username);
                    //mina
                    DataAccessObject.updateOnlineState(username,true);
                    System.out.println("con");
                    ClientHandler.clientsVector.add(this);
                }
                else{
                    printedMessageToClient.println("password " + username);
                }
            } catch (SQLException ex) {
                ex.getErrorCode();
                ex.getMessage();
            }
        }
        else{
            printedMessageToClient.println("username " + username);
        }
    }
}