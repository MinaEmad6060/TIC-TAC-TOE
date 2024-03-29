package tictactoe;

import javafx.event.ActionEvent;
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
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.util.Duration;


//implement interface
public class EmptyBoard extends AnchorPane implements BoardInterface{

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
    protected final Text text8;
    protected final Text text9;
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
    protected final Text text10;
    protected final Text text11;
    static Text scoreO;
    static Text scoreX;
    static int xScore=0;
    static int oScore=0;
    static boolean turn=true;
    Timeline timeline;
    Stage stage;
    int drawCount=0;   //counter for checkDraw  if checkwinner return false this counter increased by 1
    Button[][] gameBoard = new Button[3][3];//creat array of buttons
   
    public EmptyBoard(Stage s) {
        stage = s;
        
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
        text8 = new Text();
        text9 = new Text();
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
        text10 = new Text();
        text11 = new Text();
        scoreO = new Text();
        scoreX = new Text();
        gameBoard[0][0]=button00;
        gameBoard[0][1]=button01;
        gameBoard[0][2]=button02;
        gameBoard[1][0]=button10;
        gameBoard[1][1]=button11;
        gameBoard[1][2]=button12;
        gameBoard[2][0]=button20;
        gameBoard[2][1]=button21;
        gameBoard[2][2]=button22;
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

        text8.setFill(javafx.scene.paint.Color.valueOf("#fcfcfc"));
        text8.setLayoutX(88.0);
        text8.setLayoutY(237.0);
        text8.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text8.setStrokeWidth(0.0);
        text8.setText("player 1");
        text8.setWrappingWidth(190.3984339237213);
        text8.setFont(new Font("Cooper Black", 40.0));

        text9.setFill(javafx.scene.paint.Color.valueOf("#fcfcfc"));
        text9.setLayoutX(978.0);
        text9.setLayoutY(237.0);
        text9.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text9.setStrokeWidth(0.0);
        text9.setText("player 2");
        text9.setWrappingWidth(190.3984339237213);
        text9.setFont(new Font("Cooper Black", 40.0));

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
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                exitAlert();
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
        button00.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(turn){
                    button00.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    turn=false;
                    button00.setText("x");
                    button00.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }else{
                    button00.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
                    turn=true;
                    button00.setText("o");
                    button00.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        GridPane.setColumnIndex(button01, 1);
        button01.setMnemonicParsing(false);
        button01.setPrefHeight(150.0);
        button01.setPrefWidth(174.0);
        button01.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button01, new Insets(0.0, 0.0, 0.0, 15.0));
        button01.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(turn){
                    button01.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    turn=false;
                    button01.setText("x");
                    button01.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }else{
                    button01.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
                    turn=true;
                    button01.setText("o");
                    button01.setDisable(true);
                    gameBoard[0][1]=button01;
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });

        GridPane.setColumnIndex(button02, 2);
        button02.setMnemonicParsing(false);
        button02.setPrefHeight(150.0);
        button02.setPrefWidth(174.0);
        button02.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button02, new Insets(0.0, 0.0, 0.0, 15.0));
        button02.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(turn){
                    button02.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    turn=false;
                    button02.setText("x");
                    button02.setDisable(true);
                    gameBoard[0][2]=button02;
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }else{
                    button02.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
                    turn=true;
                    button02.setText("o");
                    button02.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });

        GridPane.setRowIndex(button10, 1);
        button10.setMnemonicParsing(false);
        button10.setPrefHeight(150.0);
        button10.setPrefWidth(174.0);
        button10.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button10, new Insets(0.0, 0.0, 0.0, 15.0));
        button10.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(turn){
                    button10.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    turn=false;
                    button10.setText("x");
                    button10.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }else{
                    button10.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
                    turn=true;
                    button10.setText("o");
                    button10.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });

        GridPane.setColumnIndex(button11, 1);
        GridPane.setRowIndex(button11, 1);
        button11.setMnemonicParsing(false);
        button11.setPrefHeight(150.0);
        button11.setPrefWidth(174.0);
        button11.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button11, new Insets(0.0, 0.0, 0.0, 15.0));
        button11.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(turn){
                    button11.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    turn=false;
                    button11.setText("x");
                    button11.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }else{
                    button11.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
                    turn=true;
                    button11.setText("o");
                    button11.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        
        GridPane.setColumnIndex(button12, 2);
        GridPane.setRowIndex(button12, 1);
        button12.setMnemonicParsing(false);
        button12.setPrefHeight(150.0);
        button12.setPrefWidth(174.0);
        button12.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button12, new Insets(0.0, 0.0, 0.0, 15.0));
        button12.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(turn){
                    button12.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    turn=false;
                    button12.setText("x");
                    button12.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }else{
                    button12.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
                    turn=true;
                    button12.setText("o");
                    button12.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
 
        GridPane.setRowIndex(button20, 2);
        button20.setMnemonicParsing(false);
        button20.setPrefHeight(150.0);
        button20.setPrefWidth(174.0);
        button20.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button20, new Insets(0.0, 0.0, 0.0, 15.0));
        button20.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(turn){
                    button20.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    turn=false;
                    button20.setText("x");
                    button20.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }else{
                    button20.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
                    turn=true;
                    button20.setText("o");
                    button20.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });

        GridPane.setColumnIndex(button21, 1);
        GridPane.setRowIndex(button21, 2);
        button21.setMnemonicParsing(false);
        button21.setPrefHeight(150.0);
        button21.setPrefWidth(174.0);
        button21.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button21, new Insets(0.0, 0.0, 0.0, 15.0));
        button21.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(turn){
                    button21.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    turn=false;
                    button21.setText("x");
                    button21.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }else{
                    button21.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
                    turn=true;
                    button21.setText("o");
                    button21.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });
        
        GridPane.setColumnIndex(button22, 2);
        GridPane.setRowIndex(button22, 2);
        button22.setMnemonicParsing(false);
        button22.setPrefHeight(150.0);
        button22.setPrefWidth(174.0);
        button22.setStyle("-fx-background-color: #7949D0;");
        GridPane.setMargin(button22, new Insets(0.0, 0.0, 0.0, 15.0));
        button22.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                if(turn){
                    button22.setStyle("-fx-background-image: url('tictactoe/images/x.png'); -fx-background-size: cover; -fx-text-fill: transparent;");
                    turn=false;
                    button22.setText("x");
                    button22.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }else{
                    button22.setStyle("-fx-background-image: url('tictactoe/images/o.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
                    turn=true;
                    button22.setText("o");
                    button22.setDisable(true);
                    if(availableToCheck()){
                        System.out.println("avaliaple to");
                        checkWinner();
                    }
                }
            }
        });

        text10.setFill(javafx.scene.paint.Color.valueOf("#fcfcfc"));
        text10.setLayoutX(93.0);
        text10.setLayoutY(290.0);
        text10.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text10.setStrokeWidth(0.0);
        text10.setText("score: ");
        text10.setWrappingWidth(190.3984339237213);
        text10.setFont(new Font("Cooper Black", 40.0));
        text11.setFill(javafx.scene.paint.Color.valueOf("#fcfcfc"));
        text11.setLayoutX(982.0);
        text11.setLayoutY(289.0);
        text11.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text11.setStrokeWidth(0.0);
        text11.setText("score: ");
        text11.setWrappingWidth(190.3984339237213);
        text11.setFont(new Font("Cooper Black", 40.0));
        scoreO.setFill(javafx.scene.paint.Color.valueOf("#f5f5f5"));
        scoreO.setLayoutX(1106.0);
        scoreO.setLayoutY(289.0);
        scoreO.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        scoreO.setStrokeWidth(0.0);
        scoreO.setText(oScore+"");
        scoreO.setWrappingWidth(93.39843392372131);
        scoreO.setFont(new Font("Cooper Black", 40.0));
        scoreX.setFill(javafx.scene.paint.Color.valueOf("#f5f5f5"));
        scoreX.setLayoutX(220.0);
        scoreX.setLayoutY(290.0);
        scoreX.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        scoreX.setStrokeWidth(0.0);
        scoreX.setText(xScore+"");
        scoreX.setWrappingWidth(93.39843392372131);
        scoreX.setFont(new Font("Cooper Black", 40.0));
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
        anchorPane.getChildren().add(text8);
        anchorPane.getChildren().add(text9);
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
        getChildren().add(text10);
        getChildren().add(text11);
        getChildren().add(scoreO);
        getChildren().add(scoreX);
    } 
                             
    @Override
      public void hilightWin(int row1 , int col1 , int row2 , int col2 , int row3 , int col3){//change background of button to image
      if(!turn){
          gameBoard[row1][col1].setStyle("-fx-background-image: url('tictactoe/images/xwin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
          gameBoard[row2][col2].setStyle("-fx-background-image: url('tictactoe/images/xwin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
          gameBoard[row3][col3].setStyle("-fx-background-image: url('tictactoe/images/xwin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
          gameBoard[row1][col1].setOpacity(1);
          gameBoard[row2][col2].setOpacity(1);
          gameBoard[row3][col3].setOpacity(1);
      }
      else{
          gameBoard[row1][col1].setStyle("-fx-background-image: url('tictactoe/images/owin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
          gameBoard[row2][col2].setStyle("-fx-background-image: url('tictactoe/images/owin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
          gameBoard[row3][col3].setStyle("-fx-background-image: url('tictactoe/images/owin.png'); -fx-background-size: cover;-fx-text-fill: transparent;");
          gameBoard[row1][col1].setOpacity(1);
          gameBoard[row2][col2].setOpacity(1);
          gameBoard[row3][col3].setOpacity(1);
      }
    }
    @Override
      public void updateScore(){
          if(!turn){
              System.out.println("x win");
              xScore++;
              scoreX.setText((xScore)+"");//update x score
          }
          else{
             oScore++;
             scoreO.setText(""+oScore);
          }
      }

    @Override
    public void drawAlert() {
        if(turn){
            turn = false;
        }
        else{
            turn = true;
        }
        Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("Game Tie");
                alert.setHeaderText("");
                alert.setContentText("Game Tie, Do you want to play again?");
                DialogPane dialogPane = alert.getDialogPane();
                dialogPane.setStyle("-fx-background-color: white;");
                dialogPane.getStyleClass().remove("alert");
                dialogPane.lookup(".content.label").setStyle("-fx-alignment: center;" +
                        "-fx-pref-height: 73.0;" +
                        "-fx-pref-width: 665.0;" +
                        "-fx-text-fill: #d1a823;" +
                        "-fx-font-family: \"Cooper Black\";" +
                        "-fx-font-size: 33.0;" +
                          "-fx-padding: 10.0;");

                ButtonType noButtonType = new ButtonType("No");
                ButtonType yesButtonType = new ButtonType("Yes");
                alert.getButtonTypes().addAll(noButtonType , yesButtonType);

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
                dialogPane.lookup(".content.label").setStyle("-fx-alignment: center;" +
                        "-fx-pref-height: 73.0;" +
                        "-fx-pref-width: 665.0;" +
                        "-fx-text-fill: #d1a823;" +
                        "-fx-font-family: \"Cooper Black\";" +
                        "-fx-font-size: 33.0;" +
                          "-fx-padding: 10.0;");

                ButtonType noButtonType = new ButtonType("No");
                ButtonType yesButtonType = new ButtonType("Yes");
                alert.getButtonTypes().addAll(noButtonType , yesButtonType);

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
    }
    @Override
    public boolean checkDraw(){
        if(drawCount == 9)
            return true;
        else
            return false;
    }

    //initialize Board
        public void initBoard(){
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                   gameBoard[i][j].setText(" "); 
                   gameBoard[i][j].setStyle("-fx-background-image: url('tictactoe/images/empty.png'); -fx-background-size: cover;");
                   gameBoard[i][j].setDisable(false);
                }
            }
        }
        //indecates availability of check
        public boolean availableToCheck(){
            boolean avFlag=false;
            int count=0;
            System.out.println("available method");
            
            for(int i=0; i<3; i++){
                if(count>4){
                    avFlag=true;
                    break;
                }
                for(int j=0; j<3; j++){
                    if(!gameBoard[i][j].getText().equals(" ")){
                        count++;
                        if(count>4){
                            avFlag=true;
                            break;
                        }
                    }
                }
            }
            return avFlag;
        }
    public void checkWinner(){
        short checkWinnerRes;
        checkWinnerRes = checkOnGame();
        
        if(checkWinnerRes == 2)
        {
            updateScore();
            disableBoard();
            timeline.play();
        }
        else
        {
            drawCount++;
        }
        if (checkWinnerRes == 1)
        {
            drawAlert();
        }
    }
    
    public short checkRows()
    {
        short result = 0;
        int j = 0;
        for (int i = 0; i < 3; i++) {
            
            if(gameBoard[i][j].getText().equals(gameBoard[i][j+1].getText()) && gameBoard[i][j+1].getText().equals(gameBoard[i][j+2].getText()) &&!gameBoard[i][i].getText().equals(" "))
            {
                result = 2;
                hilightWin(i , j , i , j+1 , i , j+2);
                System.out.println("check row");
                System.out.println("i"+i+" j"+j);
                System.out.println("i"+(i)+" j"+(j+1));
                System.out.println("i"+i+" j"+ (j+2));
                return result;
            }
            
        }
        return result;
    }
    
    public short checkColumns()
    {
        short result = 0;
        int j = 0;
        for (int i = 0; i < 3; i++) {


 

            if(gameBoard[j][i].getText().equals(gameBoard[j+1][i].getText()) && gameBoard[j+1][i].getText().equals(gameBoard[j+2][i].getText())&&!gameBoard[i][i].getText().equals(" "))

            {
                result = 2;
                hilightWin(j , i , j+1 , i , j+2 , i);
                System.out.println("check col");
                return result;
            }
        }
        return result;
    }
    
    public short checkDiagonals()
    {
        short result = 0;
        System.out.println("check daigonal");
        if(gameBoard[0][0].getText().equals(gameBoard[1][1].getText()) && gameBoard[1][1].getText().equals(gameBoard[2][2].getText()) &&!gameBoard[0][0].getText().equals(" "))
        {
            result = 2;
            System.out.println("daigonal Win");
            hilightWin(0 , 0 , 1 , 1 , 2 , 2);
            System.out.println("check daig1");
            return result;
        }
        else if(gameBoard[0][2].getText().equals(gameBoard[1][1].getText()) && gameBoard[1][1].getText().equals(gameBoard[2][0].getText())&&!gameBoard[1][1].getText().equals(" "))
        {
            result = 2;
            hilightWin(0 , 2 , 1 , 1 , 2 , 0);
            System.out.println("check daig2");
            return result;
        }
        return result;
    }
   
    public short checkOnGame(){
        short result = 0;
        System.out.println("checkongame");
        
        result = checkRows();
        if(result == 2)
        {
            return result;
        }
        
        result = checkColumns();
        if(result == 2)
        {
            return result;
        }
        
        result = checkDiagonals();
        if(result == 2)
        {
            return result;
        }
        
        if(result != 2)

        {
            drawCount++;
            if(checkDraw())
            {
                result = 1;
            }
        }
        
        return result;
    
    }
    
    void disableBoard(){
	for(int i=0; i<3; i++){
		for(int j=0; j<3; j++){
			if(!gameBoard[i][j].isDisable()){
				gameBoard[i][j].setDisable(true);
			}
		}
	}
        exitButton.setDisable(true);
    }
}