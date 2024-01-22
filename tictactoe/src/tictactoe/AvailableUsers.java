package tictactoe;

import javafx.stage.WindowEvent;
import java.io.IOException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AvailableUsers extends AnchorPane {

    protected final ImageView refresh;
    protected final AnchorPane anchorPane;
    protected final ListView<MyData> listView;
    protected final Label label;
    protected final ImageView btnBack;
    String AvailableRequest;
    String targetPlayer;
    Alert waitingAlert;
    Alert invitationAlert;
    Stage stage;
    static int turn;
    static String player2Name;
    static boolean running = true;
    static Thread testThread;

    ButtonType noButtonType;

    String availableRequest;

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

        ButtonType cancelButtonType = new ButtonType("cancel");
        waitingAlert.getButtonTypes().addAll(cancelButtonType);

        Button cancelButton = (Button) waitingAlert.getDialogPane().lookupButton(cancelButtonType);
        cancelButton.setStyle("-fx-font-family: \"Cooper Black\"; -fx-font-size: 20.0;"
                + "-fx-background-color: red; -fx-background-radius: 10;"
                + "-fx-text-fill: white; -fx-pref-height: 50;");
        cancelButton.setTranslateX(-150);

        cancelButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                String cancelRequest = "cancel " + SignIn.currentUser + " " + AvailableUsers.player2Name;
                SignIn.sendMessageToServer.println(cancelRequest);

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

        noButtonType = new ButtonType("Refuse");
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
                testThread.stop();
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
        refresh = new ImageView();
        anchorPane = new AnchorPane();
        listView = new ListView();
        label = new Label();
        btnBack = new ImageView();
        stage = s;
        AvailableRequest = "AvUsers ";
        System.out.println(AvailableRequest);
        SignIn.sendMessageToServer.println(AvailableRequest);
        System.out.println("uuuuuuuuuuuuuu");
        String msg="";
        try {
            msg = SignIn.listenFromServer.readLine();
            System.out.println(msg);
            String[] availableMsg = msg.split(",");
            String[] allAvailables = availableMsg[1].split(" ");
            //maha:50,mina:30,sals:40,
            for (int i = 0; i < allAvailables.length; i++) {
                String[] availablePlayer = allAvailables[i].split(":");
                //if (availablePlayer.length == 2) {
                System.out.println("Availllllllll");
                String playerName = availablePlayer[0];
                String PlayerScore = availablePlayer[1];

                //if(!playerName.equals(SignIn.currentUser)){
                if(!SignIn.currentUser.contains(playerName)){

                    Platform.runLater(() -> {
                        // change ui
                        addDataToListView(new MyData(playerName, PlayerScore));
                    });
                }
            }
        } catch (IOException ex) {
            ex.getStackTrace();
            ex.getMessage();
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

        refresh.setFitHeight(78.0);
        refresh.setFitWidth(74.0);
        refresh.setLayoutX(1074.0);
        refresh.setLayoutY(30.0);
        refresh.setPickOnBounds(true);
        refresh.setPreserveRatio(true);
        refresh.setImage(new Image(getClass().getResource("images/refreshh.png").toExternalForm()));

        anchorPane.setPrefHeight(105.0);
        anchorPane.setPrefWidth(1020.0);

        listView.setLayoutX(82.0);
        listView.setLayoutY(117.0);
        listView.setPrefHeight(551.0);
        listView.setPrefWidth(1035.0);
        //listView.setStyle("-fx-background-color: #1d1e3d;");
        listView.setStyle(
                "-fx-background-color: #1d1e3d; "
                + "-fx-control-inner-background: #1d1e3d; "
                + "-fx-background: #1d1e3d; "
                + "-fx-padding: 10; "
                + "-fx-border-color: transparent; "
                + "-fx-scrollbar-face-color: transparent; "
                + "-fx-scrollbar-highlight-color: transparent; "
                + "-fx-scrollbar-shadow-color: transparent; "
                + "-fx-scrollbar-base-color: transparent; "
                + "-fx-background-insets: 0; "
                + "-fx-background-radius: 0;"
        );

        listView.setCellFactory(param -> new ItemLayoutForAvailableCell());

        listView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {

                MyData mydata = (AvailableUsers.MyData) listView.getSelectionModel().getSelectedItem();
                if (mydata != null) {
                    String playRequest = "play " + SignIn.currentUser + " " + mydata.text1;
                    player2Name = mydata.text1;
                    SignIn.sendMessageToServer.println(playRequest);
                    ShowWaitingAlert(targetPlayer);
                }
            }
        });

        StartThread();

        label.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label.setLayoutX(10.0);
        label.setLayoutY(10.0);
        label.setPrefHeight(65.0);
        label.setPrefWidth(1020.0);
        label.setText("Available Players");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        label.setFont(new Font("Cooper Black", 48.0));
        label.setPadding(new Insets(10.0, 0.0, 0.0, 20.0));

        refresh.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                AvailableRequest = "AvUsers ";
                System.out.println(AvailableRequest);
                SignIn.sendMessageToServer.println(AvailableRequest);
                System.out.println("agter");
            }
        });

        btnBack.setFitHeight(68.0);
        btnBack.setFitWidth(107.0);
        btnBack.setLayoutX(21.0);
        btnBack.setLayoutY(685.0);
        btnBack.setPickOnBounds(true);
        btnBack.setPreserveRatio(true);
        btnBack.setImage(new Image(getClass().getResource("images/back.png").toExternalForm()));
        btnBack.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                availableRequest = "NotAvailable " + SignIn.currentUser;
                SignIn.sendMessageToServer.println(availableRequest);
                Welcome.navScreens(new OnlineHome(s), s);
                testThread.stop();
            }
        });

        getChildren().add(refresh);
        getChildren().add(anchorPane);
        getChildren().add(listView);
        getChildren().add(label);
        getChildren().add(btnBack);

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

                itemLayout.label.setText(item.getText2());

                itemLayout.label.setText(item.getText2());

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

    public void RemoveList() {
        listView.getItems().clear();
    }

    public void StartThread() {
        testThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("while trueeee");
                    String msg;
                    System.out.println("string");
                    try {
                        System.out.println("msg available");
                        msg = SignIn.listenFromServer.readLine();
                        System.out.println("message");
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
                        } else if (parts[0].equals("accept")) {
                            String opponentPlayer = parts[1];
                            System.out.println(opponentPlayer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    waitingAlert.close();
                                    turn = 1;
                                    running = false;
                                    Welcome.navScreens(new BoardOnline(stage), stage);
                                }
                            });
                            break;
                        } else if (parts[0].equals("refuse")) {
                            String opponentPlayer = parts[1];
                            System.out.println(opponentPlayer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    waitingAlert.close();
                                    StartThread();
                                }
                            });
                            break;
                        } else if (parts[0].equals("cancel")) {
                            String opponentPlayer = parts[1];
                            System.out.println(opponentPlayer);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    invitationAlert.setResult(noButtonType);
                                }
                            });
                        } else if (parts[0].contains("vailable")) {
                            String[] availableMsg = msg.split(",");
                            String[] allAvailables = availableMsg[1].split(" ");
                            System.out.println("inside ");

                            
                            Platform.runLater(() -> {
                                RemoveList();
                            });

                            for (int i = 0; i < allAvailables.length; i++) {
                                String[] availablePlayer = allAvailables[i].split(":");
                                //if (availablePlayer.length == 2) {
                                System.out.println("Availllllllll");
                                String playerName = availablePlayer[0];
                                String PlayerScore = availablePlayer[1];
                                if (!playerName.equals(SignIn.currentUser)) {
                                    Platform.runLater(() -> {
                                        // change ui
                                        addDataToListView(new MyData(playerName, PlayerScore));
                                    });
                                }
                            }
                        } else {
                            System.out.println("false");
                            break;
                        }
                    } catch (IOException ex) {
                        break;
                    }
                }
            }
        };
        testThread.start();
    }

}
