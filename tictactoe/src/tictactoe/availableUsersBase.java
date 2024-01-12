package tictactoe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class availableUsersBase extends BorderPane {

    protected final Label label;
    protected final ScrollPane scroll;
    protected final ListView<String> listView;
    String loginRequest;
    //Socket serverSide;
    //DataInputStream listenFromServer;
    //PrintStream sendMessageToServer;
    boolean test=false;
    String targetPlayer;
    Alert waitingAlert;
    Alert invitationAlert;
    Stage stage;
    
   
    public void ShowWaitingAlert(String nameee) {
        waitingAlert = new Alert(Alert.AlertType.NONE);
        waitingAlert.setTitle("Waiting");
        waitingAlert.setHeaderText("");
        waitingAlert.setContentText("Waiting...");
        DialogPane dialogPane = waitingAlert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: white;");
        dialogPane.getStyleClass().remove("alert");
        dialogPane.lookup(".content.label").setStyle("-fx-alignment: center;" +
                "-fx-pref-height: 73.0;" +
                "-fx-pref-width: 400.0;" +
                "-fx-text-fill: #d1a823;" +
                "-fx-font-family: \"Cooper Black\";" +
                "-fx-font-size: 33.0;" +
                  "-fx-padding: 10.0;");


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
                String canselRequest = "cansel " + "slsabel " + "sls";
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
        dialogPane.lookup(".content.label").setStyle("-fx-alignment: center;" +
                "-fx-pref-height: 73.0;" +
                "-fx-pref-width: 665.0;" +
                "-fx-text-fill: #d1a823;" +
                "-fx-font-family: \"Cooper Black\";" +
                "-fx-font-size: 33.0;" +
                  "-fx-padding: 10.0;");

        ButtonType noButtonType = new ButtonType("Refuse");
        ButtonType yesButtonType = new ButtonType("Accept");
        invitationAlert.getButtonTypes().addAll(noButtonType , yesButtonType);

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
                String acceptRequest = "accept " + "sls " + opponentPlayer;
                SignIn.sendMessageToServer.println(acceptRequest);
                Welcome.navScreens(new EmptyBoard(stage), stage);
            }
        });
        noButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                String refuseRequest = "refuse " + "sls " + opponentPlayer;
                SignIn.sendMessageToServer.println(refuseRequest);
            }
        });
        invitationAlert.showAndWait();
    }
    public availableUsersBase(Stage s) {

        stage = s;
        label = new Label();
        scroll = new ScrollPane();
        listView = new ListView();

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
        ObservableList<String> items = FXCollections.observableArrayList();
        items.add(0 , "slsabel");
        items.add(1 , "sls");
        listView.setItems(items);
        /*try{
            //serverSide = new Socket("127.0.0.1", 2000);
            //listenFromServer = new DataInputStream(serverSide.getInputStream());
            //sendMessageToServer = new PrintStream(serverSide.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(availableUsersBase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            targetPlayer = listView.getSelectionModel().getSelectedItem();
            String playRequest = "play " + "slsabel " + targetPlayer;
            SignIn.sendMessageToServer.println(playRequest);
            ShowWaitingAlert(targetPlayer);   
                                             
        } 
    });
    
        
        new Thread(){
            @Override
            public void run(){
                while(true)
                {
                    System.out.println("while trueeee");
                    String msg;
                    System.out.println("string");
                    try {
                        System.out.println("msg");
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
                        }
                        if(parts[0].equals("accept")){
                            String opponentPlayer = parts[1];
                            System.out.println(opponentPlayer);
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                  waitingAlert.close();
                                  Welcome.navScreens(new EmptyBoard(s), s);
                                }
                            });
                        }
                        if(parts[0].equals("refuse")){
                            String opponentPlayer = parts[1];
                            System.out.println(opponentPlayer);
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                  waitingAlert.close();
                                }
                            });
                            
                        }
                        if(parts[0].equals("cancel")){
                            String opponentPlayer = parts[1];
                            System.out.println(opponentPlayer);
                            
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                  
                                    //System.out.println("close invitation");
                                    //invitationAlert.close();
                                    //System.out.println("close invitation222");
                                    if (invitationAlert.isShowing()) {
                                        invitationAlert.close();
                                        System.out.println("invitationAlert closed");
                                    } else {
                                        System.out.println("invitationAlert is not currently showing");
                                    }
                                }
                            });
                        }
                        else{
                            System.out.println("false");
                        }
                    } catch (IOException ex) {
                        break;
                    }
                }
            }
        }.start();
    }
}
