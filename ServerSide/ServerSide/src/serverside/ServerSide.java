package serverside;

import DAO.DataAccessObject;
import DTO.Player;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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
                clientHandler.sendMessageToAll("serverClosed");
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

class ClientHandler extends Thread {

    DataInputStream listenFromClient;
    PrintStream printedMessageToClient;
    static Vector<ClientHandler> clientsVector = new Vector<ClientHandler>();
    String name;
    Socket socket;

    public ClientHandler(Socket clientSocket, String userName) throws IOException {
        socket = clientSocket;
        ServerSide.clientHandler = this;
        listenFromClient = new DataInputStream(socket.getInputStream());
        printedMessageToClient = new PrintStream(socket.getOutputStream());
        start();

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
                String winBtns = null;

                String record = null;

                String oScore = null;
                String xScore = null;

                if (parts[0].equals("information")) {
                    getStatistics();
                } else if (parts[0].equals("login")) {
                    username = parts[1];
                    password = parts[2];

                    validateLogin(username, password);
                } else if (parts[0].equals("AvUsers")) {
                    System.out.println("fffffffffff");
                    String available = displayAvailableList();
                    System.out.println(available);
                    printedMessageToClient.println(available);
                } else if (parts[0].equals("signUp")) {
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
                } else if (parts[0].equals("record")) {
                    //"record curUser player1-player2 13/1/2024 01:30.X00,O01,X10,O11,X21,O20,X02,O12,X22"
                    String[] rec = message.split(" ", 3);
                    System.out.println(message);
                    username = rec[1];
                    record = rec[2];
                    if (record.endsWith(",")) {
                        //Remove the last (comma)
                        record = record.substring(0, record.length() - 1);
                    }
                    System.out.println(username);
                    System.out.println(record);
                    DataAccessObject.addRecord(username, record);
                } 
                else if (parts[0].equals("Logout")) {
                    username = parts[1];
                    removeClient(username);
                    DataAccessObject.updateOnlineState(username, false);
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
                    sendMessageToClient(playerName, playerTargetName, "cancel");

                } else if (parts[0].equals("sssssstep")) {
                    playerTargetName = parts[1];
                    step = parts[2];
                    sendStepToClient(playerTargetName, "sssssstep", step);
                } else if (parts[0].equals("win")) {
                    playerTargetName = parts[1];
                    winBtns = parts[2];
                    sendStepToClient(playerTargetName, "win", winBtns);
                } else if (parts[0].equals("tieNo")) {
                    playerName = parts[1];
                    playerTargetName = parts[2];
                    sendMessageToClient(playerName, playerTargetName, parts[0]);
                    if (parts[3].equals("record")) {
                        String[] rec = message.split(" ", 6);
                        System.out.println(message);
                        username = rec[4];

                        record = rec[5];

                        if (record.endsWith(",")) {
                            //Remove the last (comma)
                            record = record.substring(0, record.length() - 1);
                        }
                        System.out.println(username);
                        System.out.println(record);
                        DataAccessObject.addRecord(username, record);

                    }
                } else if (parts[0].equals("tieYes")) {
                    playerName = parts[1];
                    playerTargetName = parts[2];
                    System.out.println("tie yes inside");
                    sendMessageToClient(playerName, playerTargetName, parts[0]);
                    System.out.println("afterrrrrrr tie yes inside");
                    if (parts[3].equals("record")) {
                        String[] rec = message.split(" ", 6);
                        System.out.println(message);
                        username = rec[4];

                        record = rec[5];

                        if (record.endsWith(",")) {
                            record = record.substring(0, record.length() - 1);
                        }
                        System.out.println(username);
                        System.out.println(record);
                        DataAccessObject.addRecord(username, record);

                    }

                } else if (parts[0].equals("exit")) {
                    playerName = parts[1];
                    playerTargetName = parts[2];
                    sendMessageToClient(playerName, playerTargetName, "exit");
                } else if (parts[0].equals("playAgain")) {
                    playerName = parts[1];
                    playerTargetName = parts[2];
                    sendMessageToClient(playerName, playerTargetName, "playAgain");
                } else if (parts[0].equals("nno")) {
                    playerName = parts[1];
                    playerTargetName = parts[2];
                    System.out.println("part 0 is " + parts[0]);
                    sendMessageToClient(playerName, playerTargetName, "nno");
                } else if (parts[0].equals("yyes")) {
                    playerName = parts[1];
                    playerTargetName = parts[2];
                    System.out.println("part 0 is " + parts[0]);
                    sendMessageToClient(playerName, playerTargetName, "yyes");
                } else if (parts[0].equals("xScore")) {
                    playerTargetName = parts[1];
                    xScore = parts[2];
                    playerName = parts[3];
                    int score1 = 0;
                    sendStepToClient(playerTargetName, "xScore", xScore);
                    System.out.println("test score ");
                    try {
                        score1 = DataAccessObject.getScore(playerName);
                        System.out.println("score equaal " + score1);
                        System.out.println("before update scoree");
                        score1++;
                        int res = DataAccessObject.setScore(playerName, score1);
                        System.out.println("after update scoree " + res);
                    } catch (SQLException ex) {
                        System.out.println("exxxxx");
                        ex.printStackTrace();
                    }
                } else if (parts[0].equals("oScore")) {
                    playerTargetName = parts[1];
                    oScore = parts[2];
                    playerName = parts[3];
                    int score1 = 0;
                    sendStepToClient(playerTargetName, "oScore", oScore);
                    System.out.println("test score ");
                    try {
                        score1 = DataAccessObject.getScore(playerName);
                        System.out.println("score equaal " + score1);
                        System.out.println("before update scoree");
                        score1++;
                        int res = DataAccessObject.setScore(playerName, score1);
                        System.out.println("after update scoree " + res);
                    } catch (SQLException ex) {
                        System.out.println("exxxxx");
                        ex.printStackTrace();
                    }

                } else if (parts[0].equals("NotAvailable")) {
                    username = parts[1];
                    DataAccessObject.updateAvailability(username, false);

                }

            } catch (IOException ex) {
                ex.getStackTrace();
            } catch (SQLException ex) {
                ex.getErrorCode();
                ex.getMessage();
            }
        }
    }

    public String displayAvailableList() throws SQLException {
        Player player = new Player();
        String available = "";

        ResultSet resultSet = DataAccessObject.getAvailableList();
        List<String> availableList = new ArrayList<>();

        while (resultSet.next()) {

            String availablePlayers = resultSet.getString("Name") + ":" + resultSet.getString("score");
            availableList.add(availablePlayers);
        }

        for (int i = 0; i < availableList.size(); i++) {
            available = available + availableList.get(i) + " ";
            System.out.println(available);

        }

        available = "allAvailableUsers," + available;
        return available;
    }

    
    public void validateLogin(String username, String password) throws SQLException {
        boolean isExist = DataAccessObject.isUserExist(username);
        if (isExist) {
            boolean isValid = DataAccessObject.isUserValid(username, password);
            if (isValid) {
                printedMessageToClient.println("confirm " + username);
                DataAccessObject.updateOnlineState(username, true);
                System.out.println("con");
                name = username;
                ClientHandler.clientsVector.add(ServerSide.clientHandler);
            } else {
                printedMessageToClient.println("password " + username);
            }
        } else {
            printedMessageToClient.println("username " + username);
        }
    }

    //method to add user in DB
    public void signUp(String userName, String password) throws SQLException {
        DataAccessObject.addUser(userName, password);
        System.out.println("user added");
        printedMessageToClient.println("confirm");
    }

    public String getHistory(String userName) throws SQLException {
        String newString = "";

        List<String> playerRecords = DataAccessObject.getRecords(userName);

        for (int i = 0; i < playerRecords.size(); i++) {
            String record = playerRecords.get(i);
            System.out.println(record);
            newString = newString + record + "*";
        }

        return newString;

    }

    public void getStatistics() throws SQLException {
        if (ServerSide.isRunning) {

            String info = "";
            int allUsers = DataAccessObject.getAllUsers();
            int onlineUsers = DataAccessObject.getOnlineUsers();
            int availableUsers = DataAccessObject.getAvailableUsers();
            info = String.valueOf(allUsers) + " " + String.valueOf(onlineUsers)
                    + " " + String.valueOf(availableUsers);
            printedMessageToClient.println(info);

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
                System.out.println(message + " " + username + " " + targetUsername);
                client.printedMessageToClient.println(message + " " + username + " " + targetUsername);
                return;
            } else {
                System.out.println("falseeeeeeeeeeeeee");
            }
        }
    }

    private void removeClient(String username) {
        for (ClientHandler client : clientsVector) {
            if (client.name.equals(username)) {
                clientsVector.remove(client);
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
    public void sendMessageToAll(String msg) {
        for (int i = 0; i < clientsVector.size(); i++) {
            clientsVector.get(i).printedMessageToClient.println(msg);
        }
    }
}
