package serverside;

import DAO.DataAccessObject;
import DTO.Player;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;
import java.sql.SQLException;

import java.util.List;

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
                while(isRunning){
                    Socket clientSocket = serverSocket.accept();
                    
                    listenFromClient = new DataInputStream(clientSocket.getInputStream());
                    printedMessageToClient = new PrintStream(clientSocket.getOutputStream());

                    String msg = listenFromClient.readLine();
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
            } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
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
               listenFromClient = new DataInputStream(clientSocket.getInputStream());
                printedMessageToClient = new PrintStream(clientSocket.getOutputStream());
                
                String msg = listenFromClient.readLine();
                String[] parts = msg.split(" ", 3);
                
                
                ClientHandler.clientsVector.add(this);

                listenFromClient = new DataInputStream(socket.getInputStream());
                printedMessageToClient = new PrintStream(socket.getOutputStream());
                

                start();
            } catch (IOException ex) {
                System.out.println("Erorr handle!");          
            }
        }

        public void run(){
            while(true){

                try 
                {

                    String message = listenFromClient.readLine();
                    String[] parts = message.split(" ");
                    String username = null;
                    String password = null;
                    //String score=null;
                    if(parts[0].equals("information"))
                    {
                        //getStatistics();
                        
                    }
                    else if(parts[0].equals("login"))
                    {
                        username = parts[1];
                        password = parts[2];  
                      //  validateLogin(username , password);
                    }   else if(parts[0].equals("available")){
                        System.out.println("fffffffffff");
                        username = parts[1];
                    System.out.println("user name"+ username);
                        String available = displayAvailableList(username);
                        System.out.println(available);
                        printedMessageToClient.println(available);
                    }
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
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        
//       public void validateLogin(String username , String password){
//        boolean isExist = DataAccessObject.isUserExist(username);
//        if(isExist){
//            try 
//            {
//                boolean isValid = DataAccessObject.isUserValid(username, password);
//                if(isValid){
//                    printedMessageToClient.println("confirm " + username);
//                    System.out.println("con");
//                    ClientHandler.clientsVector.add(this);
//                }
//                else
//                {
//                    printedMessageToClient.println("password " + username);
//                }
//            } 
//            catch (SQLException ex) {
//                Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        else{
//            printedMessageToClient.println("username " + username);
//        }
//    }
        
      public  String displayAvailableList( String username){
     Player player=new Player();   String available="";
            try { 
                List<String> availableList=DataAccessObject.getAvailableList();
//     
   for (int i = 0; i < availableList.size(); i++) {
       available= availableList.get(i)+",";
       System.out.println(available);
      
        
   }
            } catch (SQLException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            return available;
        }
     

}