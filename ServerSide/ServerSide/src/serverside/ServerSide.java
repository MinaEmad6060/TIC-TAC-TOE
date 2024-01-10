/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverside;

import DAO.DataAccessObject;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Thread;
import java.util.Vector;

/**
 *
 * @author minae
 */
public class ServerSide {

    /**
     * @param args the command line arguments
     */
    
    static ServerSocket serverSocket;
    DataInputStream listenFromClient;
    PrintStream printedMessageToClient;
    
    public ServerSide(){
   
            try {
                serverSocket = new ServerSocket(2000);
                while(true){
                    Socket clientSocket = serverSocket.accept();
                    
                    listenFromClient = new DataInputStream(clientSocket.getInputStream());
                    printedMessageToClient = new PrintStream(clientSocket.getOutputStream());

                    String msg = listenFromClient.readLine();
                    String[] parts = msg.split(" ", 3);
                    if (parts.length == 3) {
                        String request = parts[0];
                        String username = parts[1];
                        String password = parts[2];
                        
                        if(request.equals("login")){
                        }
                    }
                    
                    
                    
                    new ChatHandler(clientSocket);
                }
            } catch (IOException ex) {
                System.out.println("not accepted");
            }finally {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    System.out.println("not closed");
                }
            }
          }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        new ServerSide();
    }
}


class ChatHandler extends Thread{
        DataInputStream listenFromClient;
        PrintStream printedMessageToClient;
        static Vector<ChatHandler> clientsVector =new Vector<ChatHandler>(); 
        
        public ChatHandler(Socket s){
            try {
                listenFromClient = new DataInputStream(s.getInputStream());
                printedMessageToClient = new PrintStream(s.getOutputStream());
                
                String msg = listenFromClient.readLine();
                String[] parts = msg.split(" ", 3);
                
                
                ChatHandler.clientsVector.add(this);
                start();
            } catch (IOException ex) {
                System.out.println("Erorr handle!");          
            }
        }

        public void run(){
            while(true){
                try {
                    printedMessageToClient.println("confirm data");
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
                        //sendMessageToAll(message);
                    }
                } catch (IOException ex) {
                     break;
                } 
            }
        } 
}