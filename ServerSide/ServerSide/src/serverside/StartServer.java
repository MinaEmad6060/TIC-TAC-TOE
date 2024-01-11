package serverside;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartServer extends AnchorPane {

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
    protected final Text text8;
    protected final Text text9;
    protected final Text text10;
    protected final Text text11;
    protected final Button startBtn;
    protected final Button stopBtn;
    protected final AnchorPane anchorPane0;
    protected final Text allUsersLabel;
    protected final AnchorPane anchorPane1;
    protected final Text onlineUsersLabel;
    protected final Text text12;
    protected final Text text13;
    protected final AnchorPane anchorPane2;
    protected final Text text14;
    protected final Text availableUsersLabel;
    static boolean isStart = false;
    Socket serverSocket;
    DataInputStream listenFromServer;
    PrintStream sendMessageToServer;
    boolean test=false;
    static ServerSide serverSide;

    public StartServer(Stage s) {

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
        text8 = new Text();
        text9 = new Text();
        text10 = new Text();
        text11 = new Text();
        startBtn = new Button();
        stopBtn = new Button();
        anchorPane0 = new AnchorPane();
        allUsersLabel = new Text();
        anchorPane1 = new AnchorPane();
        onlineUsersLabel = new Text();
        text12 = new Text();
        text13 = new Text();
        anchorPane2 = new AnchorPane();
        text14 = new Text();
        availableUsersLabel = new Text();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        setStyle("-fx-background-color: #1D1E3D;");

        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(1200.0);

        text.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text.setLayoutX(314.0);
        text.setLayoutY(147.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("e");
        text.setFont(new Font("Cooper Black", 100.0));

        text0.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text0.setLayoutX(367.0);
        text0.setLayoutY(147.0);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("r");
        text0.setFont(new Font("Cooper Black", 100.0));

        text1.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text1.setLayoutX(249.0);
        text1.setLayoutY(147.0);
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("S");
        text1.setFont(new Font("Cooper Black", 100.0));

        text2.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text2.setLayoutX(420.0);
        text2.setLayoutY(147.0);
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("v");
        text2.setFont(new Font("Cooper Black", 100.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text3.setLayoutX(475.0);
        text3.setLayoutY(147.0);
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("e");
        text3.setFont(new Font("Cooper Black", 100.0));

        text4.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text4.setLayoutX(528.0);
        text4.setLayoutY(147.0);
        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.setText("r");
        text4.setFont(new Font("Cooper Black", 100.0));

        text5.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text5.setLayoutX(597.0);
        text5.setLayoutY(147.0);
        text5.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text5.setStrokeWidth(0.0);
        text5.setText("C");
        text5.setFont(new Font("Cooper Black", 100.0));

        text6.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text6.setLayoutX(669.0);
        text6.setLayoutY(147.0);
        text6.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text6.setStrokeWidth(0.0);
        text6.setText("o");
        text6.setFont(new Font("Cooper Black", 100.0));

        text7.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text7.setLayoutX(729.0);
        text7.setLayoutY(147.0);
        text7.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text7.setStrokeWidth(0.0);
        text7.setText("n");
        text7.setFont(new Font("Cooper Black", 100.0));

        imageView.setFitHeight(53.0);
        imageView.setFitWidth(102.0);
        imageView.setLayoutX(14.0);
        imageView.setLayoutY(14.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("/images/xo.png").toExternalForm()));

        text8.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text8.setLayoutX(796.0);
        text8.setLayoutY(147.0);
        text8.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text8.setStrokeWidth(0.0);
        text8.setText("t");
        text8.setFont(new Font("Cooper Black", 100.0));

        text9.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text9.setLayoutX(842.0);
        text9.setLayoutY(147.0);
        text9.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text9.setStrokeWidth(0.0);
        text9.setText("r");
        text9.setFont(new Font("Cooper Black", 100.0));

        text10.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text10.setLayoutX(895.0);
        text10.setLayoutY(147.0);
        text10.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text10.setStrokeWidth(0.0);
        text10.setText("o");
        text10.setFont(new Font("Cooper Black", 100.0));

        text11.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text11.setLayoutX(955.0);
        text11.setLayoutY(147.0);
        text11.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text11.setStrokeWidth(0.0);
        text11.setText("l");
        text11.setFont(new Font("Cooper Black", 100.0));

        startBtn.setAlignment(javafx.geometry.Pos.CENTER);
        startBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        startBtn.setLayoutX(174.0);
        startBtn.setLayoutY(607.0);
        startBtn.setMnemonicParsing(false);
        startBtn.setPrefHeight(77.0);
        startBtn.setPrefWidth(253.0);
        startBtn.setStyle("-fx-background-radius: 25; -fx-background-color: #0EB21F;");
        startBtn.setText("Start");
        startBtn.setTextFill(javafx.scene.paint.Color.WHITE);
        startBtn.setFont(new Font("Cooper Black", 50.0));
        startBtn.setOpaqueInsets(new Insets(300.0, 0.0, 0.0, 0.0));
        startBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!isStart){
                    new Thread(new ServerRunner()).start();
                }
                try {
                        serverSocket = new Socket("127.0.0.1", 2000);
                        listenFromServer = new DataInputStream(serverSocket.getInputStream());
                        sendMessageToServer = new PrintStream(serverSocket.getOutputStream());

                        new Thread(){
                            @Override
                            public void run(){
                                while(true){
                                    try {
                                        sendMessageToServer.println("information");
                                        Thread.sleep(20000);
                                    } catch (InterruptedException ex) {
                                        Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }.start();
                        new Thread(){
                            @Override
                            public void run(){

                                while(true)
                                {
                                    try {
                                        String msg = listenFromServer.readLine();
                                        String[] parts = msg.split(" ", 3);
                                        String allUsers = parts[0];
                                        String onlineUsers = parts[1];
                                        String availableUsers = parts[2];
                                        Platform.runLater(new Runnable() {
                                                @Override public void run() {
                                                    allUsersLabel.setText(allUsers);
                                                    onlineUsersLabel.setText(onlineUsers);
                                                    availableUsersLabel.setText(availableUsers);   
                                                }
                                            });
                                        
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                } 
                            }
                        }.start();
                        
                                                
                        s.setOnCloseRequest(new EventHandler<WindowEvent>(){
                            @Override
                            public void handle(WindowEvent event) {
                                sendMessageToServer.println("Close");
                                try {
                                    sendMessageToServer.close();
                                    listenFromServer.close();
                                    serverSocket.close();                        
                                } catch (IOException ex) {
                                    System.out.println("Erorr");
                                }
                            }
                        }); 

                }   catch (IOException ex) {
                        System.out.println("error in creating socket");                    
                }
            }
        });

        stopBtn.setAlignment(javafx.geometry.Pos.CENTER);
        stopBtn.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        stopBtn.setLayoutX(738.0);
        stopBtn.setLayoutY(607.0);
        stopBtn.setMnemonicParsing(false);
        stopBtn.setPrefHeight(77.0);
        stopBtn.setPrefWidth(253.0);
        stopBtn.setStyle("-fx-background-radius: 25; -fx-background-color: #BD1919;");
        stopBtn.setText("Stop");
        stopBtn.setTextFill(javafx.scene.paint.Color.WHITE);
        stopBtn.setFont(new Font("Cooper Black", 50.0));
        stopBtn.setOpaqueInsets(new Insets(300.0, 0.0, 0.0, 0.0));
        stopBtn.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
               StartServer.serverSide.closeServer();
            }
        });

        anchorPane0.setLayoutX(121.0);
        anchorPane0.setLayoutY(292.0);
        anchorPane0.setPrefHeight(200.0);
        anchorPane0.setPrefWidth(222.0);
        anchorPane0.setStyle("-fx-background-color: #FFD652; -fx-background-radius: 30;");

        allUsersLabel.setFill(javafx.scene.paint.Color.valueOf("#5319bd"));
        allUsersLabel.setLayoutX(63.0);
        allUsersLabel.setLayoutY(132.0);
        allUsersLabel.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        allUsersLabel.setStrokeWidth(0.0);
        allUsersLabel.setText("100");
        allUsersLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        allUsersLabel.setWrappingWidth(94.66474056243896);
        allUsersLabel.setFont(new Font("Cooper Black", 52.0));

        anchorPane1.setLayoutX(489.0);
        anchorPane1.setLayoutY(290.0);
        anchorPane1.setPrefHeight(200.0);
        anchorPane1.setPrefWidth(222.0);
        anchorPane1.setStyle("-fx-background-color: #FFD652; -fx-background-radius: 30;");

        onlineUsersLabel.setFill(javafx.scene.paint.Color.valueOf("#5319bd"));
        onlineUsersLabel.setLayoutX(67.0);
        onlineUsersLabel.setLayoutY(132.0);
        onlineUsersLabel.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        onlineUsersLabel.setStrokeWidth(0.0);
        onlineUsersLabel.setText("80");
        onlineUsersLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        onlineUsersLabel.setWrappingWidth(88.00000596046448);
        onlineUsersLabel.setFont(new Font("Cooper Black", 52.0));

        text12.setFill(javafx.scene.paint.Color.valueOf("#5319bd"));
        text12.setLayoutX(40.0);
        text12.setLayoutY(64.0);
        text12.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text12.setStrokeWidth(0.0);
        text12.setText("Online");
        text12.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text12.setWrappingWidth(137.00000596046448);
        text12.setFont(new Font("Cooper Black", 40.0));

        text13.setFill(javafx.scene.paint.Color.valueOf("#5319bd"));
        text13.setLayoutX(-364.0);
        text13.setLayoutY(62.0);
        text13.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text13.setStrokeWidth(0.0);
        text13.setText("All Users");
        text13.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text13.setWrappingWidth(214.00000596046448);
        text13.setFont(new Font("Cooper Black", 40.0));

        anchorPane2.setLayoutX(865.0);
        anchorPane2.setLayoutY(290.0);
        anchorPane2.setPrefHeight(200.0);
        anchorPane2.setPrefWidth(222.0);
        anchorPane2.setStyle("-fx-background-color: #FFD652; -fx-background-radius: 30;");

        text14.setFill(javafx.scene.paint.Color.valueOf("#5319bd"));
        text14.setLayoutX(12.0);
        text14.setLayoutY(59.0);
        text14.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text14.setStrokeWidth(0.0);
        text14.setText("Avaliable");
        text14.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text14.setWrappingWidth(198.00000596046448);
        text14.setFont(new Font("Cooper Black", 40.0));

        availableUsersLabel.setFill(javafx.scene.paint.Color.valueOf("#5319bd"));
        availableUsersLabel.setLayoutX(84.0);
        availableUsersLabel.setLayoutY(132.0);
        availableUsersLabel.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        availableUsersLabel.setStrokeWidth(0.0);
        availableUsersLabel.setText("6");
        availableUsersLabel.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        availableUsersLabel.setWrappingWidth(53.02733927965164);
        availableUsersLabel.setFont(new Font("Cooper Black", 52.0));

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
        anchorPane.getChildren().add(text8);
        anchorPane.getChildren().add(text9);
        anchorPane.getChildren().add(text10);
        anchorPane.getChildren().add(text11);
        getChildren().add(anchorPane);
        getChildren().add(startBtn);
        getChildren().add(stopBtn);
        anchorPane0.getChildren().add(allUsersLabel);
        getChildren().add(anchorPane0);
        anchorPane1.getChildren().add(onlineUsersLabel);
        anchorPane1.getChildren().add(text12);
        anchorPane1.getChildren().add(text13);
        getChildren().add(anchorPane1);
        anchorPane2.getChildren().add(text14);
        anchorPane2.getChildren().add(availableUsersLabel);
        getChildren().add(anchorPane2);

    }
}

class ServerRunner implements Runnable {
        @Override
        public void run() {
            StartServer.isStart = true;
            StartServer.serverSide = new ServerSide();
        }
}
