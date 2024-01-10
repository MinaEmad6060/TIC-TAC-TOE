package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Modes extends BorderPane {

    protected final AnchorPane anchorPane;
    protected final AnchorPane anchorPane0;
    protected final ImageView backButton;
    protected final AnchorPane anchorPane1;
    protected final Text text;
    protected final ImageView imageView;
    protected final FlowPane flowPane;

    protected final Button localButton;
    protected final Button robotButton;
    protected final Button onlineButton;


    public Modes(Stage stage) {

        anchorPane = new AnchorPane();
        anchorPane0 = new AnchorPane();
        backButton = new ImageView();
        anchorPane1 = new AnchorPane();
        text = new Text();
        imageView = new ImageView();
        flowPane = new FlowPane();


        localButton = new Button();
        robotButton = new Button();
        onlineButton = new Button();


        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        setStyle("-fx-background-image: url('tictactoe/images/background.jpg');");

        BorderPane.setAlignment(text, javafx.geometry.Pos.CENTER);

        //setStyle("-fx-background-image: url('https://res.cloudinary.com/dingqmolp/image/upload/v1703785854/demjoyl1efqwk1d5lrhm.jpg ');");

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(105.0);
        anchorPane.setPrefWidth(1200.0);

        anchorPane0.setLayoutX(83.0);
        anchorPane0.setLayoutY(-70.0);
        anchorPane0.setPrefHeight(110.0);
        anchorPane0.setPrefWidth(200.0);

        backButton.setFitHeight(90.0);
        backButton.setFitWidth(101.0);
        backButton.setLayoutX(68.0);
        backButton.setLayoutY(2.0);
        backButton.setPickOnBounds(true);
        backButton.setPreserveRatio(true);
        backButton.setImage(new Image(getClass().getResource("images/back.png").toExternalForm()));
        setBottom(anchorPane);
        setOpaqueInsets(new Insets(0.0));
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                Welcome.navScreens(new Welcome(stage), stage);
            }
        });

        BorderPane.setAlignment(anchorPane1, javafx.geometry.Pos.CENTER);
        anchorPane1.setPrefHeight(167.0);
        anchorPane1.setPrefWidth(1200.0);

        text.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text.setLayoutX(133.0);
        text.setLayoutY(118.0);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setStyle("-fx-text-alignment: center;");
        text.setText("Choose Play Mode");
        text.setWrappingWidth(933.341796875);
        text.setFont(new Font("Cooper Black", 100.0));

        imageView.setFitHeight(81.0);
        imageView.setFitWidth(123.0);
        imageView.setLayoutX(14.0);
        imageView.setLayoutY(6.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("images/xo.png").toExternalForm()));
        setTop(anchorPane1);

        BorderPane.setAlignment(flowPane, javafx.geometry.Pos.CENTER);
        flowPane.setAlignment(javafx.geometry.Pos.CENTER);
        flowPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        flowPane.setPrefHeight(533.0);
        flowPane.setPrefWidth(1200.0);
        flowPane.setVgap(30.0);

        localButton.setAlignment(javafx.geometry.Pos.CENTER);
        localButton.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        localButton.setMnemonicParsing(false);
        localButton.setPrefHeight(147.0);
        localButton.setPrefWidth(356.0);
        localButton.setStyle("-fx-background-radius: 25;");
        localButton.setText("Local");
        localButton.setTextFill(javafx.scene.paint.Color.valueOf("#d7b33e"));
        localButton.setFont(new Font("Cooper Black", 80.0));
        localButton.setOpaqueInsets(new Insets(300.0, 0.0, 0.0, 0.0));
        localButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Welcome.navScreens(new EmptyBoard(stage), stage);
            }});

        robotButton.setMnemonicParsing(false);
        robotButton.setPrefHeight(147.0);
        robotButton.setPrefWidth(356.0);
        robotButton.setStyle("-fx-background-radius: 25;");
        robotButton.setText("Robot");
        robotButton.setTextFill(javafx.scene.paint.Color.valueOf("#d7b33e"));
        robotButton.setFont(new Font("Cooper Black", 72.0));
        
        robotButton.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Welcome.navScreens(new RobotMode(stage), stage);
            }});

        onlineButton.setMnemonicParsing(false);
        onlineButton.setPrefHeight(147.0);
        onlineButton.setPrefWidth(356.0);
        onlineButton.setStyle("-fx-background-radius: 25;");
        onlineButton.setText("Online");
        onlineButton.setTextFill(javafx.scene.paint.Color.valueOf("#d7b33e"));
        onlineButton.setFont(new Font("Cooper Black", 72.0));
         onlineButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {

                Welcome.navScreens(new SignIn(stage), stage);
    }
            
        });
        setRight(flowPane);

        anchorPane.getChildren().add(anchorPane0);
        anchorPane.getChildren().add(backButton);
        anchorPane1.getChildren().add(text);
        anchorPane1.getChildren().add(imageView);
        flowPane.getChildren().add(localButton);
        flowPane.getChildren().add(robotButton);
        flowPane.getChildren().add(onlineButton);
}
}
