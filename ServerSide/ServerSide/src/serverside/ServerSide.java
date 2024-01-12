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

    
    ServerSocket serverSocket;
    Socket socket;
    DataInputStream listenFromClient;
    PrintStream printedMessageToClient;
    static boolean isRunning = true;
    static ClientHandler clientHandler;
    
    public ServerSide(){
   
        try {
            serverSocket = new ServerSocket(2000);

            new Thread(){
                public void run(){
                    while (true) {
                        try {
                            socket = serverSocket.accept();
                            clientHandler = new ClientHandler(socket , "");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }.start();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
          }
    
    public void closeServer() {
        ServerSide.isRunning = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        //new ServerSide();
    }
}


class ClientHandler extends Thread{
        DataInputStream listenFromClient;
        PrintStream printedMessageToClient;
        static Vector<ClientHandler> clientsVector =new Vector<ClientHandler>(); 
        String name;
        Socket socket;

        public ClientHandler(Socket clientSocket , String userName){
            //name = userName;
            socket = clientSocket;
            ServerSide.clientHandler = this;
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
                    String[] parts = message.split(" ");
                    String username = null;
                    String password = null;
                    String playerName = null;
                    String playerTargetName = null;
                    String step = null;
                    
                    if(parts[0].equals("information"))
                    {
                        getStatistics();
                    }
                    else if(parts[0].equals("login"))
                    {
                        username = parts[1];
                        password = parts[2];  
                        validateLogin(username , password);
                    }
                    else if(parts[0].equals("play"))
                    {
                        System.out.println("play moode");
                        playerName = parts[1];
                        playerTargetName = parts[2];
                        sendMessageToClient(playerName , playerTargetName , "play");
                    }
                    else if(parts[0].equals("accept"))
                    {
                        playerName = parts[1];
                        playerTargetName = parts[2];
                        sendMessageToClient(playerName ,playerTargetName , "accept");
                    }
                    else if(parts[0].equals("refuse"))
                    {
                        playerName = parts[1];
                        playerTargetName = parts[2];
                        sendMessageToClient(playerName , playerTargetName , "refuse");
                    }
                    else if(parts[0].equals("cancel"))
                    {
                        playerName = parts[1];
                        playerTargetName = parts[2];
                        sendMessageToClient(playerName , playerTargetName , "cansel");
                    }
                    else if(parts[0].equals("update"))
                    {
                        System.out.println("updateee");
                        String testUpdate = parts[1];
                        int test = DataAccessObject.updateOnlineState(testUpdate , true);
                        if(test > 0){
                            System.out.println("updated");
                        }
                        else
                        {
                            System.out.println("can't update");
                        }
                    }
                    else if(parts[0].equals("step"))
                    {
                        playerTargetName = parts[1];
                        step = parts[2];
                        sendStepToClient(playerTargetName , "step" , step);
                    }
                    else{
                        System.out.println("nonee");
                    }
                    
                    
                    /*
                    if(message.equalsIgnoreCase("Close")){
                        for(int i = 0 ; i < clientsVector.size(); i++)
                        {
                            clientsVector.remove(i);
                        }
                        if(clientsVector.size()==0){
                            serverSocket.close();
                            System.out.println(clientsVector.size());
                        }
                        
                    }*/
                } catch (IOException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
       public void validateLogin(String username , String password){
            try {
                boolean isExist = DataAccessObject.isUserExist(username);
                if(isExist){
                    try {
                        boolean isValid = DataAccessObject.isUserValid(username, password);
                        if(isValid){
                            printedMessageToClient.println("confirm " + username);
                            System.out.println("con");
                            name = username;
                            ClientHandler.clientsVector.add(ServerSide.clientHandler);
                        }
                        else{
                            printedMessageToClient.println("password " + username);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    printedMessageToClient.println("username " + username);
                }   } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
       public void getStatistics(){
           if(ServerSide.isRunning){
           try {
                String info = "";
                int allUsers = DataAccessObject.getAllUsers();
                int onlineUsers = DataAccessObject.getOnlineUsers();
                int availableUsers = DataAccessObject.getAvailableUsers();
                info = String.valueOf(allUsers) + " " + String.valueOf(onlineUsers) 
                        + " " + String.valueOf(availableUsers);
                printedMessageToClient.println(info);

            } catch (SQLException ex) {
                Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
            }
           }
       }
       private void sendMessageToClient(String username , String targetUsername , String message) {
           System.out.println("call");
        for (ClientHandler client : clientsVector) {
            System.out.println("loop");
            System.out.println(client.name);
            System.out.println("aftername");
            if (client.name.equals(targetUsername)) {
                System.out.println("found");
                client.printedMessageToClient.println(message + " " + username + " " + targetUsername);
                return;
            }
            else{
                System.out.println("falseeeeeeeeeeeeee");
            }
        }
    }
       private void sendStepToClient(String targetUsername , String message , String step) {
           System.out.println("call stepppp");
        for (ClientHandler client : clientsVector) {
            System.out.println("loop");
            System.out.println(client.name);
            System.out.println("aftername");
            if (client.name.equals(targetUsername)) {
                System.out.println("found");
                client.printedMessageToClient.println(message + " " + step);
                System.out.println(message + " " + step);
                return;
            }
            else{
                System.out.println("falseeeeeeeeeeeeee");
            }
        }
    }
}






