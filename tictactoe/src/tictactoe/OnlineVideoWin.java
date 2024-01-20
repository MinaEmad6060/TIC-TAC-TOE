package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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
        
        playerNumber.setText(SignIn.currentUser);
        
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        setStyle("-fx-background-color: #1D1E3D;");
        String path;
        if(!BoardOnline.winner){
            path = getClass().getResource("/tictactoe/videos/looser.mp4").toExternalForm();
        }else{
            path = getClass().getResource("/tictactoe/videos/winner.mp4").toExternalForm();
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
        winExitBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.stop();
                Welcome.navScreens(new AvailableUsers(s), s);
            }});

        winPlayAgainBtn.setMnemonicParsing(false);
        winPlayAgainBtn.setPrefHeight(75.0);
        winPlayAgainBtn.setPrefWidth(250.0);
        winPlayAgainBtn.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 15px;");
        winPlayAgainBtn.setText("Play Again");
        winPlayAgainBtn.setTextFill(javafx.scene.paint.Color.valueOf("#7949d0"));
        winPlayAgainBtn.setFont(new Font("Cooper Black", 35.0));
        winPlayAgainBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.stop();
                Welcome.navScreens(new BoardOnline(s), s);
            }});
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

    }
}