package tictactoe;

import javafx.animation.KeyFrame;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.util.Duration;

public class CheckVar extends AnchorPane {

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
    protected final ImageView imageView0;
    protected final Text player1;
    protected final Text player2;
    protected final ImageView imageView1;
    protected final Text timeAndDate;
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
    String StepsString = "";
    String player1Name;
    String player2Name;
    String dateAndTime;
    String[] steps;
    Timeline timeline;
    Thread moveThread;
    Button[][] gameBoard = new Button[3][3];
    boolean flag = true;

    //mahmoud-mina 13/1/2024 01:30.X00,O01,X10,O11,X21,O20,X02,O12,X22
    public CheckVar(Stage s) {
        this.steps = new String[]{"", "", "", "", "", "", "", "", ""};

        StepsString = HistoryScreen.stepsString;
        player1Name = HistoryScreen.player1;
        player2Name = HistoryScreen.player2;
        dateAndTime = HistoryScreen.dateAndTime;
        steps = StepsString.split(",");
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
        imageView0 = new ImageView();
        player1 = new Text();
        player2 = new Text();
        imageView1 = new ImageView();
        timeAndDate = new Text();
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
        text.setLayoutX(400.0);
        text.setLayoutY(161.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("H");
        text.setFont(new Font("Cooper Black", 80.0));

        text0.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text0.setLayoutX(464.0);
        text0.setLayoutY(161.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("E");
        text0.setFont(new Font("Cooper Black", 80.0));

        text1.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text1.setLayoutX(342.0);
        text1.setLayoutY(161.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("C");
        text1.setFont(new Font("Cooper Black", 80.0));

        text2.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text2.setLayoutX(519.0);
        text2.setLayoutY(162.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("C");
        text2.setFont(new Font("Cooper Black", 80.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text3.setLayoutX(577.0);
        text3.setLayoutY(161.0);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("K");
        text3.setFont(new Font("Cooper Black", 80.0));

        text4.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text4.setLayoutX(653.0);
        text4.setLayoutY(163.0);
        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.setText("V");
        text4.setFont(new Font("Cooper Black", 80.0));

        text5.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text5.setLayoutX(705.0);
        text5.setLayoutY(162.0);
        text5.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text5.setStrokeWidth(0.0);
        text5.setText("A");
        text5.setFont(new Font("Cooper Black", 80.0));

        text6.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text6.setLayoutX(772.0);
        text6.setLayoutY(161.0);
        text6.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text6.setStrokeWidth(0.0);
        text6.setText("R");
        text6.setFont(new Font("Cooper Black", 80.0));

        imageView0.setFitHeight(53.0);
        imageView0.setFitWidth(102.0);
        imageView0.setLayoutX(14.0);
        imageView0.setLayoutY(14.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("images/xoTopIcon.png").toExternalForm()));

        player1.setFill(javafx.scene.paint.Color.valueOf("#fcfcfc"));
        player1.setLayoutX(102.0);
        player1.setLayoutY(237.0);
        player1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        player1.setStrokeWidth(0.0);
        player1.setText(player1Name);
        player1.setWrappingWidth(190.3984339237213);
        player1.setFont(new Font("Cooper Black", 25.0));

        player2.setFill(javafx.scene.paint.Color.valueOf("#fcfcfc"));
        player2.setLayoutX(1010.0);
        player2.setLayoutY(237.0);
        player2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        player2.setStrokeWidth(0.0);
        player2.setText(player2Name);
        player2.setWrappingWidth(190.3984339237213);
        player2.setFont(new Font("Cooper Black", 25.0));

        imageView1.setFitHeight(150.0);
        imageView1.setFitWidth(150.0);
        imageView1.setLayoutX(977.0);
        imageView1.setLayoutY(43.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("images/playero.png").toExternalForm()));

        timeAndDate.setFill(javafx.scene.paint.Color.WHITE);
        timeAndDate.setLayoutX(498.0);
        timeAndDate.setLayoutY(203.0);
        timeAndDate.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        timeAndDate.setStrokeWidth(0.0);
        timeAndDate.setText(dateAndTime);
        timeAndDate.setWrappingWidth(204.603515625);
        timeAndDate.setFont(new Font("Berlin Sans FB", 30.0));

        exitButton.setFitHeight(134.0);
        exitButton.setFitWidth(174.0);
        exitButton.setLayoutX(93.0);
        exitButton.setLayoutY(623.0);
        exitButton.setPickOnBounds(true);
        exitButton.setPreserveRatio(true);
        exitButton.setImage(new Image(getClass().getResource("images/exit.png").toExternalForm()));
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                Welcome.navScreens(new HistoryScreen(s), s);
            }
        });
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
        GridPane.setMargin(button00, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button01, 1);
        button01.setMnemonicParsing(false);
        button01.setPrefHeight(150.0);
        button01.setPrefWidth(174.0);
        button01.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button01, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button02, 2);
        button02.setMnemonicParsing(false);
        button02.setPrefHeight(150.0);
        button02.setPrefWidth(174.0);
        button02.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button02, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setRowIndex(button10, 1);
        button10.setMnemonicParsing(false);
        button10.setPrefHeight(150.0);
        button10.setPrefWidth(174.0);
        button10.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button10, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button11, 1);
        GridPane.setRowIndex(button11, 1);
        button11.setMnemonicParsing(false);
        button11.setPrefHeight(150.0);
        button11.setPrefWidth(174.0);
        button11.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button11, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button12, 2);
        GridPane.setRowIndex(button12, 1);
        button12.setMnemonicParsing(false);
        button12.setPrefHeight(150.0);
        button12.setPrefWidth(174.0);
        button12.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button12, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setRowIndex(button20, 2);
        button20.setMnemonicParsing(false);
        button20.setPrefHeight(150.0);
        button20.setPrefWidth(174.0);
        button20.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button20, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button21, 1);
        GridPane.setRowIndex(button21, 2);
        button21.setMnemonicParsing(false);
        button21.setPrefHeight(150.0);
        button21.setPrefWidth(174.0);
        button21.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button21, new Insets(0.0, 0.0, 0.0, 15.0));

        GridPane.setColumnIndex(button22, 2);
        GridPane.setRowIndex(button22, 2);
        button22.setMnemonicParsing(false);
        button22.setPrefHeight(150.0);
        button22.setPrefWidth(174.0);
        button22.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button22, new Insets(0.0, 0.0, 0.0, 15.0));

        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(text);
        anchorPane.getChildren().add(text0);
        anchorPane.getChildren().add(text1);
        anchorPane.getChildren().add(text2);
        anchorPane.getChildren().add(text3);
        anchorPane.getChildren().add(text4);
        anchorPane.getChildren().add(text5);
        anchorPane.getChildren().add(text6);
        anchorPane.getChildren().add(imageView0);
        anchorPane.getChildren().add(player1);
        anchorPane.getChildren().add(player2);
        anchorPane.getChildren().add(imageView1);
        anchorPane.getChildren().add(timeAndDate);
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

//        for (int i = 0; i < steps.length; i++) {
//            if (steps[i].contains("X")) {
//                if (steps[i].equals("X00")) {
//                    button00.setText("x");
//                    button00.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("X01")) {
//                    button01.setText("x");
//                    button01.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("X02")) {
//                    button02.setText("x");
//                    button02.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("X10")) {
//                    button10.setText("x");
//                    button10.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("X11")) {
//                    button11.setText("x");
//                    button11.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("X12")) {
//                    button12.setText("x");
//                    button12.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("X20")) {
//                    button20.setText("x");
//                    button20.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("X21")) {
//                    button21.setText("x");
//                    button21.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("X22")) {
//                    button22.setText("x");
//                    button22.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                }
//
//            } else if (steps[i].contains("O")) {
//                if (steps[i].equals("O00")) {
//                    button00.setText("O");
//                    button00.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("O01")) {
//                    button01.setText("O");
//                    button01.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("O02")) {
//                    button02.setText("O");
//                    button02.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("O10")) {
//                    button10.setText("O");
//                    button10.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("O11")) {
//                    button11.setText("O");
//                    button11.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("O12")) {
//                    button12.setText("O");
//                    button12.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("O20")) {
//                    button20.setText("O");
//                    button20.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("O21")) {
//                    button21.setText("O");
//                    button21.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                } else if (steps[i].equals("O22")) {
//                    button22.setText("O");
//                    button22.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
//                }
//            }
//        }
//        moveThread = new Thread(this::runMoves);
//        moveThread.start();
        moveThread = new Thread(() -> {
            Timeline timeline = new Timeline();

            for (int i = 0; i < steps.length; i++) {
                String move = steps[i];
                KeyFrame keyFrame = new KeyFrame(Duration.seconds(i + 1), event -> makeMove(getButtonByMove(move), move.contains("X") ? "X" : "O"));
                timeline.getKeyFrames().add(keyFrame);
            }

            timeline.play();
        });
        moveThread.start();
    }

    void runMoves() {
        for (int i = 0; i < steps.length; i++) {
            String move = steps[i];
            Platform.runLater(() -> makeMove(getButtonByMove(move), move.contains("X") ? "X" : "O"));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void makeMove(Button button, String move) {
        Platform.runLater(() -> {
            if (move.equals("X")) {
                button.setText("X");
                button.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                checkWinner();
            } else if (move.equals("O")) {
                button.setText("O");
                button.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                checkWinner();
            }
        });
    }

    Button getButtonByMove(String move) {
        switch (move) {
            case "X00":
                return button00;
            case "X01":
                return button01;
            case "X02":
                return button02;
            case "X10":
                return button10;
            case "X11":
                return button11;
            case "X12":
                return button12;
            case "X20":
                return button20;
            case "X21":
                return button21;
            case "X22":
                return button22;
            case "O00":
                return button00;
            case "O01":
                return button01;
            case "O02":
                return button02;
            case "O10":
                return button10;
            case "O11":
                return button11;
            case "O12":
                return button12;
            case "O20":
                return button20;
            case "O21":
                return button21;
            case "O22":
                return button22;
            default:
                return null;
        }

    }

    public void checkWinner() {
        short checkWinnerRes;
        checkWinnerRes = checkOnGame();

    }

    public short checkRows() {
        short result = 0;
        int j = 0;
        for (int i = 0; i < 3; i++) {

            if (gameBoard[i][j].getText().equals(gameBoard[i][j + 1].getText()) && gameBoard[i][j + 1].getText().equals(gameBoard[i][j + 2].getText()) && !gameBoard[i][i].getText().equals(" ")) {
                result = 2;
                if (gameBoard[0][0].getText().equals("X")) {
                    flag = true;
                } else {
                    flag = false;
                }
                hilightWin(i, j, i, j + 1, i, j + 2);
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
                if (gameBoard[0][0].getText().equals("X")) {
                    flag = true;
                } else {
                    flag = false;
                }
                hilightWin(j, i, j + 1, i, j + 2, i);
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
            if (gameBoard[0][0].getText().equals("X")) {
                flag = true;
            } else {
                flag = false;
            }
            hilightWin(0, 0, 1, 1, 2, 2);
            return result;
        } else if (gameBoard[0][2].getText().equals(gameBoard[1][1].getText()) && gameBoard[1][1].getText().equals(gameBoard[2][0].getText()) && !gameBoard[1][1].getText().equals(" ")) {
            result = 2;
            hilightWin(0, 2, 1, 1, 2, 0);
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
        return result;
    }

    public void hilightWin(int row1, int col1, int row2, int col2, int row3, int col3) {//change background of button to image
        if (flag) {
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
        }
    }
    public void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameBoard[i][j].setText(" ");
            }
        }
    }

}
