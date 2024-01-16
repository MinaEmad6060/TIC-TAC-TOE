package tictactoe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
//import static tictactoe.EmptyBoard.turn;

public class BoardOnline extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final ImageView imageView;
    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Text text3;
    protected final Text text4;
    protected final Text text5;
    protected final Text text6;
    protected final Text text7;
    protected final ImageView imageView0;
    protected final Text player1Name;
    protected final Text player2Name;
    protected final ImageView imageView1;
    protected final ImageView exitButton;
    protected final GridPane gridPane;
    protected final ColumnConstraints columnConstraints;
    protected final ColumnConstraints columnConstraints0;
    protected final ColumnConstraints columnConstraints1;
    protected final RowConstraints rowConstraints;
    protected final RowConstraints rowConstraints0;
    protected final RowConstraints rowConstraints1;
    protected final Button button00;
    protected final Button button01;
    protected final Button button02;
    protected final Button button10;
    protected final Button button11;
    protected final Button button12;
    protected final Button button20;
    protected final Button button21;
    protected final Button button22;
    protected final Text text8;
    protected final Text text9;
    protected final Text scoreO;
    protected final Text scoreX;
    protected final ImageView recordBtn;
    Button[][] gameBoard = new Button[3][3];
    Stage stage;
    Timeline timeline;
    static int xScore = 0;
    static int oScore = 0;
    int drawCount = 0;
    static boolean winner = true;
    Thread thread;

    public BoardOnline(Stage s) {

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            Welcome.navScreens(new VideoWin(stage), stage);
        }));

        anchorPane = new AnchorPane();
        imageView = new ImageView();
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
        text4 = new Text();
        text5 = new Text();
        text6 = new Text();
        text7 = new Text();
        imageView0 = new ImageView();
        player1Name = new Text();
        player2Name = new Text();
        imageView1 = new ImageView();
        exitButton = new ImageView();
        gridPane = new GridPane();
        columnConstraints = new ColumnConstraints();
        columnConstraints0 = new ColumnConstraints();
        columnConstraints1 = new ColumnConstraints();
        rowConstraints = new RowConstraints();
        rowConstraints0 = new RowConstraints();
        rowConstraints1 = new RowConstraints();
        button00 = new Button();
        button01 = new Button();
        button02 = new Button();
        button10 = new Button();
        button11 = new Button();
        button12 = new Button();
        button20 = new Button();
        button21 = new Button();
        button22 = new Button();
        gameBoard[0][0] = button00;
        gameBoard[0][1] = button01;
        gameBoard[0][2] = button02;
        gameBoard[1][0] = button10;
        gameBoard[1][1] = button11;
        gameBoard[1][2] = button12;
        gameBoard[2][0] = button20;
        gameBoard[2][1] = button21;
        gameBoard[2][2] = button22;
        text8 = new Text();
        text9 = new Text();
        scoreO = new Text();
        scoreX = new Text();
        recordBtn = new ImageView();
        stage = s;

        initBoard();
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        setStyle("-fx-background-color: #1D1E3D;");

        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(1200.0);

        AnchorPane.setTopAnchor(imageView, 43.0);
        imageView.setFitHeight(150.0);
        imageView.setFitWidth(150.0);
        imageView.setLayoutX(88.0);
        imageView.setLayoutY(43.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("images/playerx.png").toExternalForm()));

        text.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text.setLayoutX(331.0);
        text.setLayoutY(152.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("I");
        text.setFont(new Font("Cooper Black", 100.0));

        text0.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text0.setLayoutX(371.0);
        text0.setLayoutY(151.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("C");
        text0.setFont(new Font("Cooper Black", 100.0));

        text1.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text1.setLayoutX(258.0);
        text1.setLayoutY(152.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("T");
        text1.setFont(new Font("Cooper Black", 100.0));

        text2.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text2.setLayoutX(473.0);
        text2.setLayoutY(151.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("T");
        text2.setFont(new Font("Cooper Black", 100.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text3.setLayoutX(539.0);
        text3.setLayoutY(151.0);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("A");
        text3.setFont(new Font("Cooper Black", 100.0));

        text4.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text4.setLayoutX(626.0);
        text4.setLayoutY(152.0);
        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.setText("C");
        text4.setFont(new Font("Cooper Black", 100.0));

        text5.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text5.setLayoutX(725.0);
        text5.setLayoutY(152.0);
        text5.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text5.setStrokeWidth(0.0);
        text5.setText("T");
        text5.setFont(new Font("Cooper Black", 100.0));

        text6.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text6.setLayoutX(798.0);
        text6.setLayoutY(152.0);
        text6.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text6.setStrokeWidth(0.0);
        text6.setText("O");
        text6.setFont(new Font("Cooper Black", 100.0));

        text7.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text7.setLayoutX(882.0);
        text7.setLayoutY(151.0);
        text7.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text7.setStrokeWidth(0.0);
        text7.setText("E");
        text7.setFont(new Font("Cooper Black", 100.0));

        imageView0.setFitHeight(53.0);
        imageView0.setFitWidth(102.0);
        imageView0.setLayoutX(14.0);
        imageView0.setLayoutY(14.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("images/xo.png").toExternalForm()));

        player1Name.setFill(javafx.scene.paint.Color.valueOf("#fcfcfc"));
        player1Name.setLayoutX(88.0);
        player1Name.setLayoutY(237.0);
        player1Name.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        player1Name.setStrokeWidth(0.0);
        player1Name.setText("player 1");
        player1Name.setWrappingWidth(190.3984339237213);
        player1Name.setFont(new Font("Cooper Black", 40.0));

        player2Name.setFill(javafx.scene.paint.Color.valueOf("#fcfcfc"));
        player2Name.setLayoutX(978.0);
        player2Name.setLayoutY(237.0);
        player2Name.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        player2Name.setStrokeWidth(0.0);
        player2Name.setText("player 2");
        player2Name.setWrappingWidth(190.3984339237213);
        player2Name.setFont(new Font("Cooper Black", 40.0));

        imageView1.setFitHeight(150.0);
        imageView1.setFitWidth(150.0);
        imageView1.setLayoutX(978.0);
        imageView1.setLayoutY(43.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("images/playero.png").toExternalForm()));

        exitButton.setFitHeight(134.0);
        exitButton.setFitWidth(174.0);
        exitButton.setLayoutX(93.0);
        exitButton.setLayoutY(623.0);
        exitButton.setPickOnBounds(true);
        exitButton.setPreserveRatio(true);
        exitButton.setImage(new Image(getClass().getResource("images/exit.png").toExternalForm()));

        AnchorPane.setRightAnchor(gridPane, 300.0);
        AnchorPane.setTopAnchor(gridPane, 238.0);
        gridPane.setLayoutX(300.0);
        gridPane.setLayoutY(238.0);
        gridPane.setPrefHeight(508.0);
        gridPane.setPrefWidth(600.0);

        columnConstraints.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints.setMinWidth(10.0);
        columnConstraints.setPrefWidth(100.0);

        columnConstraints0.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints0.setMinWidth(10.0);
        columnConstraints0.setPrefWidth(100.0);

        columnConstraints1.setHgrow(javafx.scene.layout.Priority.SOMETIMES);
        columnConstraints1.setMinWidth(10.0);
        columnConstraints1.setPrefWidth(100.0);

        rowConstraints.setMinHeight(10.0);
        rowConstraints.setPrefHeight(30.0);
        rowConstraints.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints0.setMinHeight(10.0);
        rowConstraints0.setPrefHeight(30.0);
        rowConstraints0.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        rowConstraints1.setMinHeight(10.0);
        rowConstraints1.setPrefHeight(30.0);
        rowConstraints1.setVgrow(javafx.scene.layout.Priority.SOMETIMES);

        button00.setMnemonicParsing(false);
        button00.setPrefHeight(150.0);
        button00.setPrefWidth(174.0);
        button00.setStyle("-fx-background-color: #7949D0;");
        button00.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (AvailableUsers.turn == 1) {
                    System.out.println(AvailableUsers.turn);
                    EnableBoard();
                    button00.setText("x");
                    String step = "step " + AvailableUsers.player2Name + " " + "x.0.0";
                    SignIn.sendMessageToServer.println(step);
                    button00.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 2;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                } else {
                    EnableBoard();
                    button00.setText("o");
                    String step = "step " + AvailableUsers.player2Name + " " + "o.0.0";
                    SignIn.sendMessageToServer.println(step);
                    button00.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 1;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        GridPane.setMargin(button00, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button01, 1);
        button01.setMnemonicParsing(false);
        button01.setPrefHeight(150.0);
        button01.setPrefWidth(174.0);
        button01.setStyle("-fx-background-color: #7949D0;");
        button01.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (AvailableUsers.turn == 1) {
                    EnableBoard();
                    button01.setText("x");
                    String step = "step " + AvailableUsers.player2Name + " " + "x.0.1";
                    SignIn.sendMessageToServer.println(step);
                    button01.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 2;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                } else {
                    EnableBoard();
                    button01.setText("o");
                    String step = "step " + AvailableUsers.player2Name + " " + "o.0.1";
                    SignIn.sendMessageToServer.println(step);
                    button01.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 1;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        GridPane.setMargin(button01, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button02, 2);
        button02.setMnemonicParsing(false);
        button02.setPrefHeight(150.0);
        button02.setPrefWidth(174.0);
        button02.setStyle("-fx-background-color: #7949D0;");
        button02.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (AvailableUsers.turn == 1) {
                    EnableBoard();
                    button02.setText("x");
                    String step = "step " + AvailableUsers.player2Name + " " + "x.0.2";
                    SignIn.sendMessageToServer.println(step);
                    button02.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 2;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                } else {
                    EnableBoard();
                    button02.setText("o");
                    String step = "step " + AvailableUsers.player2Name + " " + "o.0.2";
                    SignIn.sendMessageToServer.println(step);
                    button02.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 1;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        GridPane.setMargin(button02, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setRowIndex(button10, 1);
        button10.setMnemonicParsing(false);
        button10.setPrefHeight(150.0);
        button10.setPrefWidth(174.0);
        button10.setStyle("-fx-background-color: #7949D0;");
        button10.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (AvailableUsers.turn == 1) {
                    EnableBoard();
                    button10.setText("x");
                    String step = "step " + AvailableUsers.player2Name + " " + "x.1.0";
                    SignIn.sendMessageToServer.println(step);
                    button10.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 2;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                } else {
                    EnableBoard();
                    button10.setText("o");
                    String step = "step " + AvailableUsers.player2Name + " " + "o.1.0";
                    SignIn.sendMessageToServer.println(step);
                    button10.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 1;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        GridPane.setMargin(button10, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button11, 1);
        GridPane.setRowIndex(button11, 1);
        button11.setMnemonicParsing(false);
        button11.setPrefHeight(150.0);
        button11.setPrefWidth(174.0);
        button11.setStyle("-fx-background-color: #7949D0;");
        button11.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (AvailableUsers.turn == 1) {
                    EnableBoard();
                    button11.setText("x");
                    String step = "step " + AvailableUsers.player2Name + " " + "x.1.1";
                    SignIn.sendMessageToServer.println(step);
                    button11.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 2;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                } else {
                    EnableBoard();
                    button11.setText("o");
                    String step = "step " + AvailableUsers.player2Name + " " + "o.1.1";
                    SignIn.sendMessageToServer.println(step);
                    button11.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 1;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        GridPane.setMargin(button11, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button12, 2);
        GridPane.setRowIndex(button12, 1);
        button12.setMnemonicParsing(false);
        button12.setPrefHeight(150.0);
        button12.setPrefWidth(174.0);
        button12.setStyle("-fx-background-color: #7949D0;");
        button12.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (AvailableUsers.turn == 1) {
                    EnableBoard();
                    button12.setText("x");
                    String step = "step " + AvailableUsers.player2Name + " " + "x.1.2";
                    SignIn.sendMessageToServer.println(step);
                    button12.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 2;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                } else {
                    EnableBoard();
                    button12.setText("o");
                    String step = "step " + AvailableUsers.player2Name + " " + "o.1.2";
                    SignIn.sendMessageToServer.println(step);
                    button12.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 1;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        GridPane.setMargin(button12, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setRowIndex(button20, 2);
        button20.setMnemonicParsing(false);
        button20.setPrefHeight(150.0);
        button20.setPrefWidth(174.0);
        button20.setStyle("-fx-background-color: #7949D0;");
        button20.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (AvailableUsers.turn == 1) {
                    EnableBoard();
                    button20.setText("x");
                    String step = "step " + AvailableUsers.player2Name + " " + "x.2.0";
                    SignIn.sendMessageToServer.println(step);
                    button20.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 2;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                } else {
                    EnableBoard();
                    button20.setText("o");
                    String step = "step " + AvailableUsers.player2Name + " " + "o.2.0";
                    SignIn.sendMessageToServer.println(step);
                    button20.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 1;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        GridPane.setMargin(button20, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button21, 1);
        GridPane.setRowIndex(button21, 2);
        button21.setMnemonicParsing(false);
        button21.setPrefHeight(150.0);
        button21.setPrefWidth(174.0);
        button21.setStyle("-fx-background-color: #7949D0;");
        button21.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (AvailableUsers.turn == 1) {
                    EnableBoard();
                    button21.setText("x");
                    String step = "step " + AvailableUsers.player2Name + " " + "x.2.1";
                    SignIn.sendMessageToServer.println(step);
                    button21.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 2;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                } else {
                    EnableBoard();
                    button21.setText("o");
                    String step = "step " + AvailableUsers.player2Name + " " + "o.2.1";
                    SignIn.sendMessageToServer.println(step);
                    button21.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 1;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        GridPane.setMargin(button21, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button22, 2);
        GridPane.setRowIndex(button22, 2);
        button22.setMnemonicParsing(false);
        button22.setPrefHeight(150.0);
        button22.setPrefWidth(174.0);
        button22.setStyle("-fx-background-color: #7949D0;");
        button22.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (AvailableUsers.turn == 1) {
                    EnableBoard();
                    button22.setText("x");
                    String step = "step " + AvailableUsers.player2Name + " " + "x.2.2";
                    SignIn.sendMessageToServer.println(step);
                    button22.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 2;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                } else {
                    EnableBoard();
                    button22.setText("o");
                    String step = "step " + AvailableUsers.player2Name + " " + "o.2.2";
                    SignIn.sendMessageToServer.println(step);
                    button22.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    DisableBoard();
                    AvailableUsers.turn = 1;
                    if (availableToCheck()) {
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        GridPane.setMargin(button22, new Insets(0.0, 0.0, 0.0, 15.0));

        text8.setFill(javafx.scene.paint.Color.valueOf("#fcfcfc"));
        text8.setLayoutX(93.0);
        text8.setLayoutY(290.0);
        text8.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text8.setStrokeWidth(0.0);
        text8.setText("score: ");
        text8.setWrappingWidth(190.3984339237213);
        text8.setFont(new Font("Cooper Black", 40.0));

        text9.setFill(javafx.scene.paint.Color.valueOf("#fcfcfc"));
        text9.setLayoutX(982.0);
        text9.setLayoutY(289.0);
        text9.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text9.setStrokeWidth(0.0);
        text9.setText("score: ");
        text9.setWrappingWidth(190.3984339237213);
        text9.setFont(new Font("Cooper Black", 40.0));

        scoreO.setFill(javafx.scene.paint.Color.valueOf("#f5f5f5"));
        scoreO.setLayoutX(1106.0);
        scoreO.setLayoutY(289.0);
        scoreO.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        scoreO.setStrokeWidth(0.0);
        scoreO.setText("0");
        scoreO.setWrappingWidth(93.39843392372131);
        scoreO.setFont(new Font("Cooper Black", 40.0));

        scoreX.setFill(javafx.scene.paint.Color.valueOf("#f5f5f5"));
        scoreX.setLayoutX(220.0);
        scoreX.setLayoutY(290.0);
        scoreX.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        scoreX.setStrokeWidth(0.0);
        scoreX.setText("0");
        scoreX.setWrappingWidth(93.39843392372131);
        scoreX.setFont(new Font("Cooper Black", 40.0));

        recordBtn.setFitHeight(88.0);
        recordBtn.setFitWidth(123.0);
        recordBtn.setLayoutX(989.0);
        recordBtn.setLayoutY(646.0);
        recordBtn.setPickOnBounds(true);
        recordBtn.setPreserveRatio(true);
        //      recordBtn.setImage(new Image(getClass().getResource("../../../../../Users/slsabel/Downloads/recording%201.png").toExternalForm()));

        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(text);
        anchorPane.getChildren().add(text0);
        anchorPane.getChildren().add(text1);
        anchorPane.getChildren().add(text2);
        anchorPane.getChildren().add(text3);
        anchorPane.getChildren().add(text4);
        anchorPane.getChildren().add(text5);
        anchorPane.getChildren().add(text6);
        anchorPane.getChildren().add(text7);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(player1Name);
        anchorPane.getChildren().add(player2Name);
        anchorPane.getChildren().add(imageView1);
        getChildren().add(anchorPane);
        getChildren().add(exitButton);
        gridPane.getColumnConstraints().add(columnConstraints);
        gridPane.getColumnConstraints().add(columnConstraints0);
        gridPane.getColumnConstraints().add(columnConstraints1);
        gridPane.getRowConstraints().add(rowConstraints);
        gridPane.getRowConstraints().add(rowConstraints0);
        gridPane.getRowConstraints().add(rowConstraints1);
        gridPane.getChildren().add(button00);
        gridPane.getChildren().add(button01);
        gridPane.getChildren().add(button02);
        gridPane.getChildren().add(button10);
        gridPane.getChildren().add(button11);
        gridPane.getChildren().add(button12);
        gridPane.getChildren().add(button20);
        gridPane.getChildren().add(button21);
        gridPane.getChildren().add(button22);
        getChildren().add(gridPane);
        getChildren().add(text8);
        getChildren().add(text9);
        getChildren().add(scoreO);
        getChildren().add(scoreX);
        getChildren().add(recordBtn);

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
                        String[] parts = msg.split(" ");
                        System.out.println(parts[0] + "testttttttttttttttt");
                        if (parts[0].equals("step")) {
                            String step = parts[1];
                            String[] location = step.split("\\.");
                            System.out.println(location[0] + "  " + location[1] + "  " + location[2]);
                            String type = location[0];
                            int row = Integer.parseInt(location[1]);
                            int col = Integer.parseInt(location[2]);
                            System.out.println("type " + type);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    gameBoard[row][col].setText(type);
                                    if (type.equals("x")) {
                                        gameBoard[row][col].setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                                        AvailableUsers.turn = 2;
                                    } else {
                                        gameBoard[row][col].setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                                        AvailableUsers.turn = 1;
                                    }
                                    EnableBoard();
                                    System.out.println("row col printed");
                                }
                            });
                            //break;
                        } else if (parts[0].equals("win")) {
                            winner = false;
                            String step = parts[1];
                            String[] location = step.split("\\.");
                            int row1 = Integer.parseInt(location[0]);
                            int col1 = Integer.parseInt(location[1]);
                            int row2 = Integer.parseInt(location[2]);
                            int col2 = Integer.parseInt(location[3]);
                            int row3 = Integer.parseInt(location[4]);
                            int col3 = Integer.parseInt(location[5]);
                             Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    hilightWin(row1, col1, row2, col2, row3, col3);
                                    Welcome.navScreens(new OnlineVideoWin(s), s);
                                }});
                             break;
                        } else {
                            System.out.println("false");
                            //break;
                        }
                    } catch (IOException ex) {
                        //break;
                    }
                }
            }
        };
                thread.start();

    }

    void DisableBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //if (gameBoard[i][j].getText().equals(" ")) {
                gameBoard[i][j].setDisable(true);
                //}
            }
        }
    }

    void EnableBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameBoard[i][j].getText().equals(" ")) {
                    gameBoard[i][j].setDisable(false);
                }
            }
        }
    }

    public boolean checkDraw() {
        if (drawCount == 9) {
            return true;
        } else {
            return false;
        }
    }

    //initialize Board
    public void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j].setText(" ");
                gameBoard[i][j].setStyle("-fx-background-image: url('tictactoe/images/empty.png'); -fx-background-size: cover;");
                gameBoard[i][j].setDisable(false);
            }
        }
    }
    //indecates availability of check

    public boolean availableToCheck() {
        boolean avFlag = false;
        int count = 0;
        System.out.println("available method");

        for (int i = 0; i < 3; i++) {
            if (count > 4) {
                avFlag = true;
                break;
            }
            for (int j = 0; j < 3; j++) {
                if (!gameBoard[i][j].getText().equals(" ")) {
                    count++;
                    if (count > 4) {
                        avFlag = true;
                        break;
                    }
                }
            }
        }
        return avFlag;
    }

    public void checkWinner() {
        short checkWinnerRes;
        checkWinnerRes = checkOnGame();

        if (checkWinnerRes == 2) {
            updateScore();
            DisableBoard();
            timeline.play();
        } else {
            drawCount++;
        }
        if (checkWinnerRes == 1) {
            drawAlert();
        }
    }

    public short checkRows() {
        short result = 0;
        int j = 0;
        for (int i = 0; i < 3; i++) {

            if (gameBoard[i][j].getText().equals(gameBoard[i][j + 1].getText()) && gameBoard[i][j + 1].getText().equals(gameBoard[i][j + 2].getText()) && !gameBoard[i][i].getText().equals(" ")) {
                result = 2;
                hilightWin(i, j, i, j + 1, i, j + 2);
                System.out.println("check row");
                System.out.println("i" + i + " j" + j);
                System.out.println("i" + (i) + " j" + (j + 1));
                System.out.println("i" + i + " j" + (j + 2));
                String btn1 = Integer.toString(i) + "." + Integer.toString(j);
                String btn2 = Integer.toString(i) + "." + Integer.toString(j + 1);
                String btn3 = Integer.toString(i) + "." + Integer.toString(j + 2);
                String winReq = "win " + AvailableUsers.player2Name + " " + btn1 + "." + btn2 + "." + btn3;
                SignIn.sendMessageToServer.println(winReq);
                thread.stop();
                return result;
            }

        }
        return result;
    }

    public short checkColumns() {
        short result = 0;
        int j = 0;
        for (int i = 0; i < 3; i++) {

            if (gameBoard[j][i].getText().equals(gameBoard[j + 1][i].getText()) && gameBoard[j + 1][i].getText().equals(gameBoard[j + 2][i].getText()) && !gameBoard[i][i].getText().equals(" ")) {
                result = 2;
                hilightWin(j, i, j + 1, i, j + 2, i);
                //AvailableUsers.turn = 1;
                String btn1 = Integer.toString(j) + "." + Integer.toString(i);
                String btn2 = Integer.toString(j + 1) + "." + Integer.toString(i);
                String btn3 = Integer.toString(j + 2) + "." + Integer.toString(i);
                String winReq = "win " + AvailableUsers.player2Name + " " + btn1 + "." + btn2 + "." + btn3;
                SignIn.sendMessageToServer.println(winReq);
                System.out.println("check col");
                thread.stop();
                return result;
            }
        }
        return result;
    }

    public short checkDiagonals() {
        short result = 0;
        System.out.println("check daigonal");
        if (gameBoard[0][0].getText().equals(gameBoard[1][1].getText()) && gameBoard[1][1].getText().equals(gameBoard[2][2].getText()) && !gameBoard[0][0].getText().equals(" ")) {
            result = 2;
            System.out.println("daigonal Win");
            hilightWin(0, 0, 1, 1, 2, 2);
            System.out.println("check daig1");
            String btn1 = Integer.toString(0) + "." + Integer.toString(0);
            String btn2 = Integer.toString(1) + "." + Integer.toString(1);
            String btn3 = Integer.toString(2) + "." + Integer.toString(2);
            String winReq = "win " + AvailableUsers.player2Name + " " + btn1 + "." + btn2 + "." + btn3;
            SignIn.sendMessageToServer.println(winReq);
            thread.stop();
            return result;
        } else if (gameBoard[0][2].getText().equals(gameBoard[1][1].getText()) && gameBoard[1][1].getText().equals(gameBoard[2][0].getText()) && !gameBoard[1][1].getText().equals(" ")) {
            result = 2;
            hilightWin(0, 2, 1, 1, 2, 0);
            System.out.println("check daig2");
            String btn1 = Integer.toString(0) + "." + Integer.toString(2);
            String btn2 = Integer.toString(1) + "." + Integer.toString(1);
            String btn3 = Integer.toString(2) + "." + Integer.toString(0);
            String winReq = "win " + AvailableUsers.player2Name + " " + btn1 + "." + btn2 + "." + btn3;
            SignIn.sendMessageToServer.println(winReq);
            thread.stop();
            return result;
        }
        return result;
    }

    public short checkOnGame() {
        short result = 0;
        System.out.println("checkongame");

        result = checkRows();
        if (result == 2) {
            return result;
        }

        result = checkColumns();
        if (result == 2) {
            return result;
        }

        result = checkDiagonals();
        if (result == 2) {
            return result;
        }

        if (result != 2) {
            drawCount++;
            if (checkDraw()) {
                result = 1;
            }
        }

        return result;

    }

    public void hilightWin(int row1, int col1, int row2, int col2, int row3, int col3) {//change background of button to image
        if (AvailableUsers.turn == 2) {
            gameBoard[row1][col1].setStyle("-fx-background-image: url('tictactoe/images/xwin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
            gameBoard[row2][col2].setStyle("-fx-background-image: url('tictactoe/images/xwin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
            gameBoard[row3][col3].setStyle("-fx-background-image: url('tictactoe/images/xwin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
            gameBoard[row1][col1].setOpacity(1);
            gameBoard[row2][col2].setOpacity(1);
            gameBoard[row3][col3].setOpacity(1);

        } else {
            gameBoard[row1][col1].setStyle("-fx-background-image: url('tictactoe/images/owin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
            gameBoard[row2][col2].setStyle("-fx-background-image: url('tictactoe/images/owin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
            gameBoard[row3][col3].setStyle("-fx-background-image: url('tictactoe/images/owin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
            gameBoard[row1][col1].setOpacity(1);
            gameBoard[row2][col2].setOpacity(1);
            gameBoard[row3][col3].setOpacity(1);
            /*AvailableUsers.turn = 2;
            String btn1 = Integer.toString(row1) + "." + Integer.toString(col1);
            String btn2 = Integer.toString(row2) + "." + Integer.toString(col2);
            String btn3 = Integer.toString(row3) + "." + Integer.toString(col3);
            String winReq = "win " + AvailableUsers.player2Name + " " + btn1 + "." + btn2 + "." + btn3;
            SignIn.sendMessageToServer.println(winReq);*/
        }
    }

    public void updateScore() {
        if (AvailableUsers.turn == 2) {
            System.out.println("x win");
            xScore++;
            scoreX.setText((xScore) + "");//update x score
        } else {
            oScore++;
            scoreO.setText("" + oScore);
        }
    }

    public void drawAlert() {
        if (AvailableUsers.turn == 1) {
            AvailableUsers.turn = 2;
        } else {
            AvailableUsers.turn = 1;
        }
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Game Tie");
        alert.setHeaderText("");
        alert.setContentText("Game Tie, Do you want to play again?");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: white;");
        dialogPane.getStyleClass().remove("alert");
        dialogPane.lookup(".content.label").setStyle("-fx-alignment: center;"
                + "-fx-pref-height: 73.0;"
                + "-fx-pref-width: 665.0;"
                + "-fx-text-fill: #d1a823;"
                + "-fx-font-family: \"Cooper Black\";"
                + "-fx-font-size: 33.0;"
                + "-fx-padding: 10.0;");

        ButtonType noButtonType = new ButtonType("No");
        ButtonType yesButtonType = new ButtonType("Yes");
        alert.getButtonTypes().addAll(noButtonType, yesButtonType);

        Button noButton = (Button) alert.getDialogPane().lookupButton(noButtonType);
        noButton.setStyle("-fx-font-family: \"Cooper Black\"; -fx-font-size: 20.0;"
                + "-fx-background-color: red; -fx-background-radius: 10;"
                + "-fx-text-fill: white; -fx-padding: 10px 20px ; -fx-pref-width: 150; -fx-pref-height: 50;");
        noButton.setTranslateX(-230);

        Button yesButton = (Button) alert.getDialogPane().lookupButton(yesButtonType);
        yesButton.setStyle("-fx-font-family: \"Cooper Black\"; -fx-font-size: 20.0;"
                + "-fx-background-color: green; -fx-background-radius: 10;"
                + "-fx-text-fill: white; -fx-pref-height: 50;");
        yesButton.setTranslateX(-100);

        yesButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                Welcome.navScreens(new EmptyBoard(stage), stage);
            }
        });
        noButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                Welcome.navScreens(new Modes(stage), stage);
                xScore = 0;
                oScore = 0;
            }
        });
        alert.showAndWait();
    }

    public void exitAlert() {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle("Exit Game");
        alert.setHeaderText("");
        alert.setContentText("Do you want to Exit?");
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: white;");
        dialogPane.getStyleClass().remove("alert");
        dialogPane.lookup(".content.label").setStyle("-fx-alignment: center;"
                + "-fx-pref-height: 73.0;"
                + "-fx-pref-width: 665.0;"
                + "-fx-text-fill: #d1a823;"
                + "-fx-font-family: \"Cooper Black\";"
                + "-fx-font-size: 33.0;"
                + "-fx-padding: 10.0;");

        ButtonType noButtonType = new ButtonType("No");
        ButtonType yesButtonType = new ButtonType("Yes");
        alert.getButtonTypes().addAll(noButtonType, yesButtonType);

        Button noButton = (Button) alert.getDialogPane().lookupButton(noButtonType);
        noButton.setStyle("-fx-font-family: \"Cooper Black\"; -fx-font-size: 20.0;"
                + "-fx-background-color: red; -fx-background-radius: 10;"
                + "-fx-text-fill: white; -fx-padding: 10px 20px ; -fx-pref-width: 150; -fx-pref-height: 50;");
        noButton.setTranslateX(-230);

        Button yesButton = (Button) alert.getDialogPane().lookupButton(yesButtonType);
        yesButton.setStyle("-fx-font-family: \"Cooper Black\"; -fx-font-size: 20.0;"
                + "-fx-background-color: green; -fx-background-radius: 10;"
                + "-fx-text-fill: white; -fx-pref-height: 50;");
        yesButton.setTranslateX(-100);

        yesButton.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                Welcome.navScreens(new Modes(stage), stage);
                xScore = 0;
                oScore = 0;
            }
        });
        alert.showAndWait();
        //test
    }
}
