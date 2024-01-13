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

    ServerSocket serverSocket;
    Socket socket;
    DataInputStream listenFromClient;
    PrintStream printedMessageToClient;
    static boolean isRunning = true;
    static ClientHandler clientHandler;

    public ServerSide() {

        try {
            serverSocket = new ServerSocket(2000);

            new Thread() {
                public void run() {
                    while (true) {
                        try {
                            socket = serverSocket.accept();
                            clientHandler = new ClientHandler(socket, "");
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

class ClientHandler extends Thread {

    DataInputStream listenFromClient;
    PrintStream printedMessageToClient;
    static Vector<ClientHandler> clientsVector = new Vector<ClientHandler>();
    String name;
    Socket socket;

    public ClientHandler(Socket clientSocket, String userName) {
        name = userName;
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

    public void run() {
        while (true) {
            try {
                String message = listenFromClient.readLine();
                String[] parts = message.split(" ");
                String username = null;
                String password = null;
                String playerName = null;
                String playerTargetName = null;
                String step = null;

                if (parts[0].equals("information")) {
                    getStatistics();
                } else if (parts[0].equals("login")) {
                    username = parts[1];
                    password = parts[2];

                    validateLogin(username, password);
                } else if (parts[0].equals("available")) {
                    System.out.println("fffffffffff");
                    username = parts[1];
                    System.out.println("user name" + username);
                    String available = displayAvailableList(username);
                    System.out.println(available);
                    printedMessageToClient.println(available);
                }
                else if (parts[0].equals("signUp")) {
                    username = parts[1];
                    password = parts[2];
                    boolean isExist = DataAccessObject.isUserExist(username);
                    if (isExist) {
                        System.out.println("user Exist");
                        printedMessageToClient.println("Existing " + username);
                    } else {
                        signUp(username, password);
                    }
                } else if (parts[0].equals("history")) {
                    username = parts[1];
                    //send username to next line
                    String newString = getHistory(username);
                    System.out.println(newString);
                    printedMessageToClient.println(newString);
                } else if (parts[0].equals("Logout")) {
                    username = parts[1];
                    DataAccessObject.updateOnlineState(username, false);
                    //DataAccessObject.updateAvailability(username,false);
                } else if (parts[0].equals("Available")) {
                    username = parts[1];
                    DataAccessObject.updateAvailability(username, true);
                } else if (parts[0].equals("play")) {
                    System.out.println("play moode");
                    playerName = parts[1];
                    playerTargetName = parts[2];
                    sendMessageToClient(playerName, playerTargetName, "play");
                } else if (parts[0].equals("accept")) {
                    playerName = parts[1];
                    playerTargetName = parts[2];
                    sendMessageToClient(playerName, playerTargetName, "accept");
                } else if (parts[0].equals("refuse")) {
                    playerName = parts[1];
                    playerTargetName = parts[2];
                    sendMessageToClient(playerName, playerTargetName, "refuse");
                } else if (parts[0].equals("cancel")) {
                    playerName = parts[1];
                    playerTargetName = parts[2];
                    sendMessageToClient(playerName, playerTargetName, "cansel");
                } else if (parts[0].equals("step")) {
                    playerTargetName = parts[1];
                    step = parts[2];
                    sendStepToClient(playerTargetName, "step", step);
                }

                /*if (message.equalsIgnoreCase("Close")) {
                    clientsVector.remove(clientsVector.size() - 1);
                    System.out.println(clientsVector.size());
                    if (clientsVector.size() == 0) {
                        try {
                            ServerSide.serverSocket.close();
                        } catch (IOException ex) {
                            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }*/
            } catch (IOException ex) {
                ex.getStackTrace();
            } catch (SQLException ex) {
                ex.getErrorCode();
                ex.getMessage();
            }
        }
    }

    public String displayAvailableList(String username) {
        Player player = new Player();
        String available = "";
        try {
            List<String> availableList = DataAccessObject.getAvailableList();

            for (int i = 0; i < availableList.size(); i++) {
                available = availableList.get(i) + ",";
                System.out.println(available);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return available;
    }

    public void validateLogin(String username, String password) throws SQLException {
        boolean isExist = DataAccessObject.isUserExist(username);
        if (isExist) {
            try {
                boolean isValid = DataAccessObject.isUserValid(username, password);
                if (isValid) {
                    printedMessageToClient.println("confirm " + username);
                    //mina
                    DataAccessObject.updateOnlineState(username, true);
                    System.out.println("con");
                    ClientHandler.clientsVector.add(this);
                } else {
                    printedMessageToClient.println("password " + username);
                }
            } catch (SQLException ex) {
                ex.getErrorCode();
                ex.getMessage();
            }
        } else {
            printedMessageToClient.println("username " + username);
        }
    }

    //method to add user in DB
    public void signUp(String userName, String password) {
        try {
            DataAccessObject.addUser(userName, password);
        } catch (SQLException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("user added");
        // DataAccessObject.addPassword(password);
        //System.out.println("password added");
        printedMessageToClient.println("confirm");
    }

    public String getHistory(String userName) {
        String newString = "";
        try {

            List<String> playerRecords = DataAccessObject.getRecords(userName);

            for (int i = 0; i < playerRecords.size(); i++) {
                String record = playerRecords.get(i);
                System.out.println(record);
                newString = newString + record + "*";
            }

        } catch (SQLException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newString;

    }

    public void getStatistics() {
        if (ServerSide.isRunning) {
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

    private void sendMessageToClient(String username, String targetUsername, String message) {
        System.out.println("call");
        for (ClientHandler client : clientsVector) {
            System.out.println("loop");
            System.out.println(client.name);
            System.out.println("aftername");
            if (client.name.equals(targetUsername)) {
                System.out.println("found");
                client.printedMessageToClient.println(message + " " + username + " " + targetUsername);
                return;
            } else {
                System.out.println("falseeeeeeeeeeeeee");
            }
        }
    }

    private void sendStepToClient(String targetUsername, String message, String step) {
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
            } else {
                System.out.println("falseeeeeeeeeeeeee");
            }
        }
    }
}
