/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author minae
 */

import javafx.stage.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AvailableUsers extends BorderPane {

    protected final Label label;
    protected final ScrollPane scrollPane;
    protected final ListView<MyData> listView;
    String AvailableRequest;
    String targetPlayer;
    Alert waitingAlert;
    Alert invitationAlert;
    Stage stage;
    static int turn;
    static String player2Name;
    static boolean running = true;
    static Thread testThread;

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
                StartThread();
            }
        });
        invitationAlert.showAndWait();
    }

    public AvailableUsers(Stage s) {
        stage = s;
        label = new Label();
        scrollPane = new ScrollPane();
        listView = new ListView<>();

        AvailableRequest = "AvUsers ";
        System.out.println(AvailableRequest);
        SignIn.sendMessageToServer.println(AvailableRequest);
        System.out.println("uuuuuuuuuuuuuu");
        String msg;
        try {
            msg = SignIn.listenFromServer.readLine();
            System.out.println(msg);
            String[] allAvailables = msg.split(" ");
            //maha:50,mina:30,sals:40,
            for (int i = 0; i < allAvailables.length; i++) {
                String[] availablePlayer = allAvailables[i].split(":");
                //if (availablePlayer.length == 2) {
                System.out.println("Availllllllll");
                String playerName = availablePlayer[0];
                String PlayerScore = availablePlayer[1];

                Platform.runLater(() -> {
                    // change ui
                    addDataToListView(new MyData(playerName, PlayerScore));
                });
            }
        } catch (IOException ex) {
            Logger.getLogger(AvailableUsers.class.getName()).log(Level.SEVERE, null, ex);
        }

        s.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                SignIn.sendMessageToServer.println("Close");
                try {
                    SignIn.sendMessageToServer.close();
                    SignIn.listenFromServer.close();
                    SignIn.serverSide.close();
                } catch (IOException ex) {
                    System.out.println("Erorr");
                }
            }
        });

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
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MyData mydata = listView.getSelectionModel().getSelectedItem();
                String playRequest = "play " + SignIn.currentUser + " " + mydata.text1;
                player2Name = mydata.text1;
                SignIn.sendMessageToServer.println(playRequest);
                ShowWaitingAlert(targetPlayer);

            }
        });

        StartThread();

    }

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
>>>>>>> min-online
    
}
