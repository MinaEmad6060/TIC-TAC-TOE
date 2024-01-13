package tictactoe;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static tictactoe.SignIn.serverSide;

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
    
    static Socket serverSide;
    static DataInputStream listenFromServer;
    static PrintStream sendMessageToServer;

    public BoardOnline(Stage s) {

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
        gameBoard[0][0]=button00;
        gameBoard[0][1]=button01;
        gameBoard[0][2]=button02;
        gameBoard[1][0]=button10;
        gameBoard[1][1]=button11;
        gameBoard[1][2]=button12;
        gameBoard[2][0]=button20;
        gameBoard[2][1]=button21;
        gameBoard[2][2]=button22;
        text8 = new Text();
        text9 = new Text();
        scoreO = new Text();
        scoreX = new Text();
        recordBtn = new ImageView();

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
                if(availableUsers.turn == 1){
                    gameBoard[0][0].setText("x");
                    String step = "step " + "nnn " + "x.0.0";
                    SignIn.sendMessageToServer.println(step);
                }
            }
        });
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
        /*try{
         serverSide = new Socket("127.0.0.1", 2000);
        listenFromServer = new DataInputStream(serverSide.getInputStream());
        sendMessageToServer = new PrintStream(serverSide.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(BoardOnline.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        new Thread(){
            @Override
            public void run(){
                while(true)
                {
                    System.out.println("while trueeee");
                    String msg;
                    System.out.println("string");
                    try {
                        System.out.println("msggggggggggggg");
                        msg = SignIn.listenFromServer.readLine();
                        System.out.println("message");
                        String[] parts = msg.split(" ");
                        System.out.println(parts[0] + "testttttttttttttttt");
                        if(parts[0].equals("step")){
                            String step = parts[1];
                            String[] location = step.split("\\.");
                            System.out.println(location[0] + "  " + location[1] + "  " + location[2]);
                            String type = location[0];
                            int row = Integer.parseInt(location[1]);
                            int col = Integer.parseInt(location[2]);
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                  gameBoard[row][col].setText(type);
                                    System.out.println("row col printed");
                                }
                            });
                            //break;
                        }
                        
                        else{
                            System.out.println("false");
                            //break;
                        }
                    } catch (IOException ex) {
                        //break;
                    }
                }
            }
        }.start();

    }
}
