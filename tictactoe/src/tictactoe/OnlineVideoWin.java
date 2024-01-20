package tictactoe;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class OnlineVideoWin extends BorderPane {

    protected final FlowPane flowPane;
    protected final Button winExitBtn;
    protected final Button winPlayAgainBtn;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final Label congratsLabel;
    protected final FlowPane flowPane0;
    protected final ImageView winIconImage;
    protected final Label playerNumber;
    protected final ImageView xoImage;
    protected final MediaView mediaView;
    String path;
    Thread thread;
    Alert invitationAlert;
    Alert waitingAlert;
    ButtonType noButtonTypeInvite;
    ButtonType cancelButtonType;
    Stage stage;
    //static String playerXScore = BoardOnline.xScore+"";
    //static String playerOScore = BoardOnline.oScore+"";

    public OnlineVideoWin(Stage s) {

        flowPane = new FlowPane();
        winExitBtn = new Button();
        winPlayAgainBtn = new Button();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        congratsLabel = new Label();
        flowPane0 = new FlowPane();
        winIconImage = new ImageView();
        playerNumber = new Label();
        xoImage = new ImageView();
        stage = s;

        playerNumber.setText(SignIn.currentUser);

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        setStyle("-fx-background-color: #1D1E3D;");
        String path;
        if (!BoardOnline.winner) {
            path = getClass().getResource("/tictactoe/videos/looser.mp4").toExternalForm();
            BoardOnline.winner = true;
        } else {
            path = getClass().getResource("/tictactoe/videos/winner.mp4").toExternalForm();
            BoardOnline.winner = true;
            if (AvailableUsers.turn == 1) {
                SignIn.sendMessageToServer.println("xScore " + AvailableUsers.player2Name + " " + BoardOnline.xScore + "");
            } else {
                SignIn.sendMessageToServer.println("oScore " + AvailableUsers.player2Name + " " + BoardOnline.oScore + "");

            }
        }

        Media media = new Media(path);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaView = new MediaView(mediaPlayer);
        mediaPlayer.play();

        BorderPane.setAlignment(flowPane, javafx.geometry.Pos.CENTER);
        flowPane.setPrefHeight(200.0);
        flowPane.setPrefWidth(1200.0);
        FlowPane.setMargin(winExitBtn, new Insets(0.0, 0.0, 50.0, 0.0));

        winExitBtn.setMnemonicParsing(false);
        winExitBtn.setPrefHeight(75.0);
        winExitBtn.setPrefWidth(250.0);
        winExitBtn.setStyle("-fx-background-color: #7949D0; -fx-background-radius: 15px;");
        winExitBtn.setText("Exit <<");
        winExitBtn.setTextFill(javafx.scene.paint.Color.WHITE);
        winExitBtn.setFont(new Font("Cooper Black", 35.0));
        winExitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.stop();
                SignIn.sendMessageToServer.println("exit " + SignIn.currentUser + " " + AvailableUsers.player2Name);
                thread.stop();
                Welcome.navScreens(new AvailableUsers(s), s);
            }
        });

        winPlayAgainBtn.setMnemonicParsing(false);
        winPlayAgainBtn.setPrefHeight(75.0);
        winPlayAgainBtn.setPrefWidth(250.0);
        winPlayAgainBtn.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 15px;");
        winPlayAgainBtn.setText("Play Again");
        winPlayAgainBtn.setTextFill(javafx.scene.paint.Color.valueOf("#7949d0"));
        winPlayAgainBtn.setFont(new Font("Cooper Black", 35.0));
        winPlayAgainBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.stop();
                SignIn.sendMessageToServer.println("playAgain " + SignIn.currentUser + " " + AvailableUsers.player2Name);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        ShowWaitingAlert("");
                        System.out.println("after send tie invitationn");

                    }
                });
            }
        });
        FlowPane.setMargin(winPlayAgainBtn, new Insets(0.0, 0.0, 50.0, 200.0));

        flowPane.setPadding(new Insets(80.0, 0.0, 0.0, 250.0));
        setBottom(flowPane);

        BorderPane.setAlignment(gridPane, javafx.geometry.Pos.CENTER);
        gridPane.setAlignment(javafx.geometry.Pos.CENTER);
        gridPane.setPrefHeight(200.0);
        gridPane.setPrefWidth(1200.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMaxWidth(589.0);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(77.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMaxWidth(1144.0);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(1123.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        GridPane.setColumnIndex(congratsLabel, 1);
        GridPane.setValignment(congratsLabel, javafx.geometry.VPos.CENTER);
        congratsLabel.setAlignment(javafx.geometry.Pos.CENTER);
        congratsLabel.setText("Congratulations!");
        congratsLabel.setTextFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        congratsLabel.setFont(new Font("Cooper Black", 100.0));
        congratsLabel.setPadding(new Insets(25.0, 0.0, 0.0, 130.0));

        GridPane.setColumnIndex(flowPane0, 1);
        GridPane.setRowIndex(flowPane0, 1);
        flowPane0.setAlignment(javafx.geometry.Pos.CENTER);
        flowPane0.setPrefHeight(200.0);
        flowPane0.setPrefWidth(200.0);

        winIconImage.setFitHeight(112.0);
        winIconImage.setFitWidth(129.0);
        winIconImage.setPickOnBounds(true);
        winIconImage.setPreserveRatio(true);
        winIconImage.setImage(new Image(getClass().getResource("/tictactoe/images/winnerIcon.png").toExternalForm()));

        playerNumber.setTextFill(javafx.scene.paint.Color.WHITE);
        playerNumber.setFont(new Font("Cooper Black", 35.0));
        flowPane0.setPadding(new Insets(50.0, 0.0, 0.0, -65.0));

        GridPane.setValignment(xoImage, javafx.geometry.VPos.TOP);
        xoImage.setFitHeight(47.0);
        xoImage.setFitWidth(77.0);
        xoImage.setPickOnBounds(true);
        xoImage.setPreserveRatio(true);
        xoImage.setImage(new Image(getClass().getResource("/tictactoe/images/xoTopIcon.png").toExternalForm()));
        GridPane.setMargin(xoImage, new Insets(5.0, 0.0, 0.0, 5.0));
        setTop(gridPane);

        BorderPane.setAlignment(mediaView, javafx.geometry.Pos.CENTER);
        mediaView.setFitHeight(400.0);
        mediaView.setFitWidth(600.0);
        BorderPane.setMargin(mediaView, new Insets(70.0, 0.0, 0.0, 0.0));
        setCenter(mediaView);

        flowPane.getChildren().add(winExitBtn);
        flowPane.getChildren().add(winPlayAgainBtn);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getChildren().add(congratsLabel);
        flowPane0.getChildren().add(winIconImage);
        flowPane0.getChildren().add(playerNumber);
        gridPane.getChildren().add(flowPane0);
        gridPane.getChildren().add(xoImage);

        thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("while trueeee");
                    String msg;
                    System.out.println("string");
                    try {
                        System.out.println("msggggggggggggg");
                        msg = SignIn.listenFromServer.readLine();

                        System.out.println("message");
                        System.out.println(msg);
                        String[] parts = {"", "", "", ""};
                        parts = msg.split(" ");

                        System.out.println(parts[0] + "tesssssss");
                        if (parts[0].equals("exit")) {

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Welcome.navScreens(new AvailableUsers(s), s);
                                }
                            });
                            break;

                        } else if (parts[0].equals("playAgain")) {
                            System.out.println("part 0 is " + parts[0]);
                            System.out.println("part 1 is " + parts[1]);
                            System.out.println("part 2 is " + parts[2]);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    ShowInvitationAlert("");
                                }
                            });
                            System.out.println("");
                            break;

                        } else if (parts[0].equals("cancel")) {
                            System.out.println("test cancel");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    invitationAlert.setResult(noButtonTypeInvite);
                                    Welcome.navScreens(new AvailableUsers(stage), stage);
                                }
                            });
                            System.out.println("test cancel after run later");
                            break;

                        } else if (parts[0].equals("no")) {

                            System.out.println("enterddddd");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println("before ref");
                                    waitingAlert.setResult(cancelButtonType);
                                    System.out.println("after reff");
                                    Welcome.navScreens(new AvailableUsers(stage), stage);

                                }
                            });
                            System.out.println("enter b3d runlaterrrr ref");
                            break;
                        } else if (parts[0].equals("yes")) {

                            System.out.println("enterddddd");
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println("before ref");
                                    waitingAlert.setResult(cancelButtonType);
                                    System.out.println("after reff");
                                    Welcome.navScreens(new BoardOnline(stage), stage);

                                }
                            });
                            System.out.println("enter b3d runlaterrrr");
                            break;
                        } else if (parts[0].equals("xScore")) {
                            System.out.println("inside xScoore");
                            String xScore = parts[1];
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    //playerXScore = xScore;
                                    BoardOnline.xScore = Integer.parseInt(xScore);
                                }
                            });
                            //break;
                        } else if (parts[0].equals("oScore")) {
                            System.out.println("inside oScoore");
                            String oScore = parts[1];
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    //playerOScore = oScore;
                                    BoardOnline.oScore = Integer.parseInt(oScore);
                                }
                            });
                            //break;
                        } else {
                            System.out.println("false");
                            //break;
                        }
                    } catch (IOException ex) {
                        System.out.println("catchhhhhhhhhh");
                        ex.printStackTrace();
                    }
                }
            }

        };
        thread.start();
    }

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

        cancelButtonType = new ButtonType("Cansel");
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
                thread.stop();
                Welcome.navScreens(new AvailableUsers(stage), stage);

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

        noButtonTypeInvite = new ButtonType("No");
        ButtonType yesButtonType = new ButtonType("Yes");
        invitationAlert.getButtonTypes().addAll(noButtonTypeInvite, yesButtonType);

        Button noButton = (Button) invitationAlert.getDialogPane().lookupButton(noButtonTypeInvite);
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
                String acceptRequest = "yes " + SignIn.currentUser + " " + AvailableUsers.player2Name;
                SignIn.sendMessageToServer.println(acceptRequest);
                thread.stop();
                Welcome.navScreens(new BoardOnline(stage), stage);
            }
        });
        noButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {

                String refuseRequest = "no " + SignIn.currentUser + " " + AvailableUsers.player2Name;
                SignIn.sendMessageToServer.println(refuseRequest);
                thread.stop();
                Welcome.navScreens(new AvailableUsers(stage), stage);

            }
        });
        invitationAlert.showAndWait();
    }
}
