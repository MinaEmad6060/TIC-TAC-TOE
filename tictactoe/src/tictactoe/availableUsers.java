package tictactoe;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class availableUsers extends BorderPane {

    protected final Label label;
    protected final ScrollPane scroll;
    protected final ListView<MyData> listView;
    String loginRequest;
    boolean test = false;
    String targetPlayer;
    Alert waitingAlert;
    Alert invitationAlert;
    Stage stage;
    static int turn;
    static String player2Name;
    boolean running = true;

    public void ShowWaitingAlert(String nameee) {
        waitingAlert = new Alert(Alert.AlertType.NONE);
        waitingAlert.setTitle("Waiting");
        waitingAlert.setHeaderText("");
        waitingAlert.setContentText("Waiting...");
        DialogPane dialogPane = waitingAlert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: white;");
        dialogPane.getStyleClass().remove("alert");
        dialogPane.lookup(".content.label").setStyle("-fx-alignment: center;"
                + "-fx-pref-height: 73.0;"
                + "-fx-pref-width: 400.0;"
                + "-fx-text-fill: #d1a823;"
                + "-fx-font-family: \"Cooper Black\";"
                + "-fx-font-size: 33.0;"
                + "-fx-padding: 10.0;");

        ButtonType cancelButtonType = new ButtonType("Cansel");
        waitingAlert.getButtonTypes().addAll(cancelButtonType);

        Button cancelButton = (Button) waitingAlert.getDialogPane().lookupButton(cancelButtonType);
        cancelButton.setStyle("-fx-font-family: \"Cooper Black\"; -fx-font-size: 20.0;"
                + "-fx-background-color: red; -fx-background-radius: 10;"
                + "-fx-text-fill: white; -fx-pref-height: 50;");
        cancelButton.setTranslateX(-150);

        cancelButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                String canselRequest = "cansel " + SignIn.currentUser + " " + "sls";
                SignIn.sendMessageToServer.println(canselRequest);
            }
        });
        waitingAlert.showAndWait();
    }

    public void ShowInvitationAlert(String opponentPlayer) {
        invitationAlert = new Alert(Alert.AlertType.NONE);
        invitationAlert.setTitle("Invitation");
        invitationAlert.setHeaderText("");
        invitationAlert.setContentText(opponentPlayer + " Wants to play with you");
        DialogPane dialogPane = invitationAlert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: white;");
        dialogPane.getStyleClass().remove("alert");
        dialogPane.lookup(".content.label").setStyle("-fx-alignment: center;"
                + "-fx-pref-height: 73.0;"
                + "-fx-pref-width: 665.0;"
                + "-fx-text-fill: #d1a823;"
                + "-fx-font-family: \"Cooper Black\";"
                + "-fx-font-size: 33.0;"
                + "-fx-padding: 10.0;");

        ButtonType noButtonType = new ButtonType("Refuse");
        ButtonType yesButtonType = new ButtonType("Accept");
        invitationAlert.getButtonTypes().addAll(noButtonType, yesButtonType);

        Button noButton = (Button) invitationAlert.getDialogPane().lookupButton(noButtonType);
        noButton.setStyle("-fx-font-family: \"Cooper Black\"; -fx-font-size: 20.0;"
                + "-fx-background-color: red; -fx-background-radius: 10;"
                + "-fx-text-fill: white; -fx-padding: 10px 20px ; -fx-pref-width: 150; -fx-pref-height: 50;");
        noButton.setTranslateX(-230);

        Button yesButton = (Button) invitationAlert.getDialogPane().lookupButton(yesButtonType);
        yesButton.setStyle("-fx-font-family: \"Cooper Black\"; -fx-font-size: 20.0;"
                + "-fx-background-color: green; -fx-background-radius: 10;"
                + "-fx-text-fill: white; -fx-pref-height: 50;");
        yesButton.setTranslateX(-100);

        yesButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                String acceptRequest = "accept " + SignIn.currentUser + " " + opponentPlayer;
                SignIn.sendMessageToServer.println(acceptRequest);
                player2Name = opponentPlayer;
                turn = 2;
                Welcome.navScreens(new BoardOnline(stage), stage);
            }
        });
        noButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                String refuseRequest = "refuse " + SignIn.currentUser + " " + opponentPlayer;
                SignIn.sendMessageToServer.println(refuseRequest);
            }
        });
        invitationAlert.showAndWait();
    }

    public availableUsers(Stage s) {

            stage = s;
            label = new Label();
            scroll = new ScrollPane();
            listView = new ListView<>();
            String AvailableRequest;
            
            AvailableRequest = "AvUsers ";
            System.out.println(AvailableRequest);
            SignIn.sendMessageToServer.println(AvailableRequest);
            String msg;
        try {
            msg = SignIn.listenFromServer.readLine();
            String[] parts = msg.split(" ");
             System.out.println("false");
            for (int i = 0; i < parts.length; i++) {
                String[] availablePlayer = parts[i].split(":");
                //if (availablePlayer.length == 2) {
                System.out.println("Availllllllll");
                String playerName = availablePlayer[0];
                String PlayerScore = availablePlayer[1];
                System.out.println(playerName + " " + PlayerScore);
                Platform.runLater(() -> {
                    // change ui
                    addDataToListView(new MyData(playerName, PlayerScore));
                });
                //}
        }
            
           
            
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            System.out.println("uuuuuuuuuuuuuu");
                            String msg = SignIn.listenFromServer.readLine();
                            System.out.println(msg);
                            //String[] allAvailables = msg.split(" ");
                            //maha:50,mina:30,sals:40,
                            
                            String[] parts = msg.split(" ");
                            System.out.println(parts[0] + "testttttttttttttttt");
                            
                            if (parts[0].equals("play")) {
                                String opponentPlayer = parts[1];
                                System.out.println(opponentPlayer);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        ShowInvitationAlert(opponentPlayer);
                                    }
                                });
                                break;
                            }
                            if (parts[0].equals("accept")) {
                                String opponentPlayer = parts[1];
                                System.out.println(opponentPlayer);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        waitingAlert.close();
                                        turn = 1;
                                        running = false;
                                        
                                        Welcome.navScreens(new BoardOnline(s), s);
                                    }
                                });
                                break;
                            }
                            System.out.println(running);
                            if (parts[0].equals("refuse")) {
                                String opponentPlayer = parts[1];
                                System.out.println(opponentPlayer);
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        waitingAlert.close();
                                    }
                                });
                                break;
                                
                            }
                            if (parts[0].equals("cancel")) {
                                String opponentPlayer = parts[1];
                                System.out.println(opponentPlayer);
                                
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        invitationAlert.close();
                                    }
                                });
                                break;
                            } else {
                                /*System.out.println("false");
                                for (int i = 0; i < parts.length; i++) {
                                String[] availablePlayer = parts[i].split(":");
                                //if (availablePlayer.length == 2) {
                                System.out.println("Availllllllll");
                                String playerName = availablePlayer[0];
                                String PlayerScore = availablePlayer[1];
                                System.out.println(playerName + " " + PlayerScore);
                                Platform.runLater(() -> {
                                // change ui
                                addDataToListView(new MyData(playerName, PlayerScore));
                                });
                                //}
                                }*/
                                break;
                            }
                        } catch (IOException ex) {
                            break;
                        }
                    }
                    
                }
            }.start();
            
            setMaxHeight(USE_PREF_SIZE);
            setMaxWidth(USE_PREF_SIZE);
            setMinHeight(USE_PREF_SIZE);
            setMinWidth(USE_PREF_SIZE);
            setPrefHeight(784.0);
            setPrefWidth(1200.0);
            setStyle("-fx-background-color: #1d1e3d;");
            
            BorderPane.setAlignment(label, javafx.geometry.Pos.TOP_LEFT);
            label.setAlignment(javafx.geometry.Pos.TOP_LEFT);
            label.setText("Available Users");
            label.setTextFill(javafx.scene.paint.Color.valueOf("#ffd652"));
            label.setFont(new Font("Cooper Black", 48.0));
            BorderPane.setMargin(label, new Insets(10.0));
            label.setPadding(new Insets(10.0, 0.0, 0.0, 20.0));
            setTop(label);
            
            BorderPane.setAlignment(scroll, javafx.geometry.Pos.CENTER);
            scroll.setStyle("-fx-background-color: #1d1e3d;");
            
            listView.setPrefHeight(784.0);
            listView.setPrefWidth(1181.0);
            listView.setStyle("-fx-background-color: #1d1e3d;");
            scroll.setContent(listView);
            setCenter(scroll);
            listView.setCellFactory(param -> new ItemLayoutForAvailableCell());
            
            ObservableList<String> items = FXCollections.observableArrayList();
            items.add(0, "slsabel");
            items.add(1, "sls");
            //listView.setItems(items);
            
            listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    MyData data = listView.getSelectionModel().getSelectedItem();
                    //targetPlayer = data.text1;
                    targetPlayer = "nnn";
                    String playRequest = "play " + SignIn.currentUser + " " + targetPlayer;
                    SignIn.sendMessageToServer.println(playRequest);
                    ShowWaitingAlert(targetPlayer);
                    
                }
            });
            
            /*new Thread(){
            @Override
            public void run(){
            while(running)
            {
            System.out.println("while trueeee");
            String msg;
            System.out.println("string");
            try {
            System.out.println("msg available");
            msg = SignIn.listenFromServer.readLine();
            
            System.out.println("message");
            String[] parts = msg.split(" ");
            System.out.println(parts[0] + "testttttttttttttttt");
            if(parts[0].equals("play")){
            String opponentPlayer = parts[1];
            System.out.println(opponentPlayer);
            Platform.runLater(new Runnable() {
            @Override public void run() {
            ShowInvitationAlert(opponentPlayer);
            }
            });
            break;
            }
            if(parts[0].equals("accept")){
            String opponentPlayer = parts[1];
            System.out.println(opponentPlayer);
            Platform.runLater(new Runnable() {
            @Override public void run() {
            waitingAlert.close();
            turn = 1;
            running = false;
            
            Welcome.navScreens(new BoardOnline(s), s);
            }
            });
            break;
            }
            System.out.println(running);
            if(parts[0].equals("refuse")){
            String opponentPlayer = parts[1];
            System.out.println(opponentPlayer);
            Platform.runLater(new Runnable() {
            @Override public void run() {
            waitingAlert.close();
            }
            });
            break;
            
            }
            if(parts[0].equals("cancel")){
            String opponentPlayer = parts[1];
            System.out.println(opponentPlayer);
            
            Platform.runLater(new Runnable() {
            @Override public void run() {
            invitationAlert.close();
            }
            });
            break;
            }
            else{
            System.out.println("false");
            break;
            }
            } catch (IOException ex) {
            break;
            }
            }
            }
            }.start();*/
        } catch (IOException ex) {
            Logger.getLogger(availableUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                itemLayout.label.setText(item.getText2().toUpperCase());
                setGraphic(itemLayout);
            }
        }
    }

    public class MyData {

        String text1;
        String text2;

        public MyData(String text1, String text2) {
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
