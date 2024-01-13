package tictactoe;

import javafx.event.ActionEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import static tictactoe.Welcome.navScreens;


public  class OnlineHome extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Text text;
    protected final Text text0;
    protected final Text text1;
    protected final Text text2;
    protected final Text text3;
    protected final Text text4;
    protected final Text text5;
    protected final Text text6;
    protected final Text text7;
    protected final ImageView imageView;
    protected final ImageView btnBack;
    protected final Button btnPlay;
    protected final Button btnHistory;
    Stage stage;
    Socket serverSide;
    DataInputStream listenFromServer;
    PrintStream sendMessageToServer;
    String availableRequest;
    String logoutRequest;
    boolean test=false;


    public OnlineHome(Stage s) {
        stage=s;
        anchorPane = new AnchorPane();
        text = new Text();
        text0 = new Text();
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
        text4 = new Text();
        text5 = new Text();
        text6 = new Text();
        text7 = new Text();
        imageView = new ImageView();
        btnBack = new ImageView();
        btnPlay = new Button();
        btnHistory = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        //setStyle("-fx-background-color: #1D1E3D;");
        setStyle("-fx-background-image: url('tictactoe/images/background.jpg');");

        anchorPane.setPrefHeight(226.0);
        anchorPane.setPrefWidth(1200.0);

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

        imageView.setFitHeight(53.0);
        imageView.setFitWidth(102.0);
        imageView.setLayoutX(14.0);
        imageView.setLayoutY(21.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("images/xo.png").toExternalForm()));

        btnBack.setFitHeight(77.0);
        btnBack.setFitWidth(130.0);
        btnBack.setLayoutX(34.0);
        btnBack.setLayoutY(669.0);
        btnBack.setPickOnBounds(true);
        btnBack.setPreserveRatio(true);
        btnBack.setImage(new Image(getClass().getResource("images/back.png").toExternalForm()));
        //mina
        btnBack.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                exitAlert();
            }
        });

        btnPlay.setLayoutX(425.0);
        btnPlay.setLayoutY(279.0);
        btnPlay.setMnemonicParsing(false);
        btnPlay.setPrefHeight(130.0);
        btnPlay.setPrefWidth(350.0);
        btnPlay.setStyle("-fx-background-radius: 25;");
        btnPlay.setText("Play");
        btnPlay.setTextFill(javafx.scene.paint.Color.valueOf("#d7b33e"));
        btnPlay.setFont(new Font("Cooper Black", 70.0));
        btnPlay.setOnAction(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        try {
                            availableRequest="Available " + SignIn.currentUser;
                            serverSide = new Socket("127.0.0.1", 2000);
                            listenFromServer = new DataInputStream(serverSide.getInputStream());
                            sendMessageToServer = new PrintStream(serverSide.getOutputStream());
                            sendMessageToServer.println(availableRequest);
                            
                            //Welcome.navScreens(new (stage), stage);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

        btnHistory.setLayoutX(425.0);
        btnHistory.setLayoutY(456.0);
        btnHistory.setMnemonicParsing(false);
        btnHistory.setPrefHeight(130.0);
        btnHistory.setPrefWidth(350.0);
        btnHistory.setStyle("-fx-background-radius: 25;");
        btnHistory.setText("History");
        btnHistory.setTextFill(javafx.scene.paint.Color.valueOf("#d7b33e"));
        btnHistory.setFont(new Font("Cooper Black", 65.0));
        btnHistory.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                //Welcome.navScreens(new , stage);
            }
        });

        anchorPane.getChildren().add(text);
        anchorPane.getChildren().add(text0);
        anchorPane.getChildren().add(text1);
        anchorPane.getChildren().add(text2);
        anchorPane.getChildren().add(text3);
        anchorPane.getChildren().add(text4);
        anchorPane.getChildren().add(text5);
        anchorPane.getChildren().add(text6);
        anchorPane.getChildren().add(text7);
        anchorPane.getChildren().add(imageView);
        getChildren().add(anchorPane);
        getChildren().add(btnBack);
        getChildren().add(btnPlay);
        getChildren().add(btnHistory);

    }
    
    //mina
    public void exitAlert() {
        Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle("Exit Game");
                alert.setHeaderText("");
                alert.setContentText("Do you want to Sign out?");
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
                        try {
                            logoutRequest="Logout " + SignIn.currentUser;
                            serverSide = new Socket("127.0.0.1", 2000);
                            listenFromServer = new DataInputStream(serverSide.getInputStream());
                            sendMessageToServer = new PrintStream(serverSide.getOutputStream());
                            sendMessageToServer.println(logoutRequest);
                            Welcome.navScreens(new SignIn(stage), stage);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                alert.showAndWait();
    }
}
