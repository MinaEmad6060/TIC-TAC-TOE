/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AvailableUsers extends BorderPane {

    protected final Label label;
    protected final ScrollPane scrollPane;
    protected final ListView<MyData> listView;
   String AvailableRequest;
    Socket serverSide;
    DataInputStream listenFromServer;
    PrintStream sendMessageToServer;
    public AvailableUsers(Stage s) {
        label = new Label();
        scrollPane = new ScrollPane();
        listView = new ListView<>();
        //
        AvailableRequest="available " + "Maha";
        System.out.println(AvailableRequest);
            try {
                serverSide = new Socket("127.0.0.1", 2000);
                listenFromServer = new DataInputStream(serverSide.getInputStream());
                sendMessageToServer = new PrintStream(serverSide.getOutputStream());
                sendMessageToServer.println(AvailableRequest);
                    new Thread(){
                        @Override
                        public void run(){
                            while(true)
                            {
                                try {
                                     System.out.println("uuuuuuuuuuuuuu");
                                    String msg = listenFromServer.readLine();
                                    System.out.println(msg);
                                    String[] allAvailables = msg.split(",");
                                    //maha:50,mina:30,sals:40,
                       for (int i = 0; i < allAvailables.length; i++) {
                        String[] availablePlayer = allAvailables[i].split(":");
                        //if (availablePlayer.length == 2) {
                        System.out.println("Availllllllll");
                            String playerName = availablePlayer[i];
                            String PlayerScore = availablePlayer[i+1];

                            Platform.runLater(() -> {
                                // change ui
                               
                                addDataToListView(new MyData(playerName, PlayerScore));
                                
                                });
                        //}
                    }

                                }catch (IOException ex) {
                                    break;
                                }
                            }


                        }
                    }.start();

                        s.setOnCloseRequest(new EventHandler<WindowEvent>(){
                            @Override
                            public void handle(WindowEvent event) {
                                sendMessageToServer.println("Close");
                                try {
                                    sendMessageToServer.close();
                                    listenFromServer.close();
                                    serverSide.close();                        
                                } catch (IOException ex) {
                                    System.out.println("Erorr");
                                }
                            }
                        }); 

                }catch (IOException ex) {
                        System.out.println("error in creating socket");                    
                }
            ///
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        setStyle("-fx-background-color: #1d1e3d;");

        BorderPane.setAlignment(label, javafx.geometry.Pos.TOP_LEFT);
        label.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label.setText("Available Players");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        label.setFont(new Font("Cooper Black", 48.0));
        BorderPane.setMargin(label, new Insets(10.0));
        label.setPadding(new Insets(10.0, 0.0, 0.0, 20.0));
        setTop(label);

        BorderPane.setAlignment(scrollPane, javafx.geometry.Pos.CENTER);
        scrollPane.setStyle("-fx-background-color: #1d1e3d;");

        listView.setPrefHeight(784.0);
        listView.setPrefWidth(1181.0);
        listView.setStyle("-fx-background-color: #1d1e3d;");
        scrollPane.setContent(listView);
        setCenter(scrollPane);
        listView.setCellFactory(param -> new ItemLayoutForAvailableCell());

        // Add 20 test data
        //add20TestData();
        
    }

//    private void add20TestData() {
//        for (int i = 1; i <= 50; i++) {
//            addDataToListView(new MyData("Test Data " + i));
//        }
//    }

    private class ItemLayoutForAvailableCell extends javafx.scene.control.ListCell<MyData> {
        @Override
        protected void updateItem(MyData item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                itemLayoutForAvailable itemLayout = new itemLayoutForAvailable();
                itemLayout.dataLabel.setText(item.getText());
                itemLayout.label.setText(item.getText2());
                setGraphic(itemLayout);
            }
        }
    }

     public class MyData {
         String text1;
         String text2;
        public MyData(String text1,String text2) {
            this.text1 = text1;
            this.text2 = text2;
        }
        public String getText2() {
            return text2;
        }

        public void setText2(String text2) {
            this.text2 = text2;
        }
        public void setText(String text1) {
            this.text1 = text1;
        }
        public String getText() {
            return text1;
        }
    }
    public void updateDataInListView() {
        listView.refresh();
    }

    public void addDataToListView(MyData newData) {
        listView.getItems().add(newData);
        listView.scrollTo(newData);
        updateDataInListView();
    }
}
