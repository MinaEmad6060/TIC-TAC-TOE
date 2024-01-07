package tictactoe;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public  class SignIn extends AnchorPane {

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
    protected final FlowPane flowPane;
    protected final TextField username;
    protected final PasswordField password;
    protected final Button btnSignIn;
    protected final Text text8;
    protected final Text btnClick;

    public SignIn(Stage s) {

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
        flowPane = new FlowPane();
        username = new TextField();
        password = new PasswordField();
        btnSignIn = new Button();
        text8 = new Text();
        btnClick = new Text();

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
        imageView.setLayoutY(14.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("images/xo.png").toExternalForm()));

        btnBack.setFitHeight(68.0);
        btnBack.setFitWidth(107.0);
        btnBack.setLayoutX(35.0);
        btnBack.setLayoutY(667.0);
        btnBack.setPickOnBounds(true);
        btnBack.setPreserveRatio(true);
        btnBack.setImage(new Image(getClass().getResource("images/back.png").toExternalForm()));
               btnBack.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                Welcome.navScreens(new Modes(s), s);
            }
        });
        flowPane.setAlignment(javafx.geometry.Pos.CENTER);
        flowPane.setLayoutX(112.0);
        flowPane.setLayoutY(200.0);
        flowPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        flowPane.setPrefHeight(260.0);
        flowPane.setPrefWidth(977.0);
        flowPane.setVgap(25.0);

        username.setPrefHeight(76.0);
        username.setPrefWidth(770.0);
        username.setPromptText("User Name");
        username.setStyle("-fx-background-radius: 25;-fx-text-fill: #d7b33e;");
        username.setFont(new Font("Cooper Black", 50.0));
        username.setFocusTraversable(false);
        password.setPrefHeight(76.0);
        password.setPrefWidth(770.0);
        password.setPromptText("Password");
        password.setStyle("-fx-background-radius: 25;-fx-text-fill: #d7b33e;");
        password.setFont(new Font("Cooper Black", 50.0));
        password.setFocusTraversable(false);
        flowPane.setOpaqueInsets(new Insets(0.0));
        
            
        

        btnSignIn.setLayoutX(426.0);
        btnSignIn.setLayoutY(507.0);
        btnSignIn.setMnemonicParsing(false);
        btnSignIn.setPrefHeight(119.0);
        btnSignIn.setPrefWidth(350.0);
        btnSignIn.setStyle("-fx-background-radius: 25;");
        btnSignIn.setText("Sign In");
        btnSignIn.setTextFill(javafx.scene.paint.Color.valueOf("#1d1e3d"));
        btnSignIn.setFont(new Font("Cooper Black", 65.0));

        text8.setFill(javafx.scene.paint.Color.WHITE);
        text8.setLayoutX(371.0);
        text8.setLayoutY(711.0);
        text8.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text8.setStrokeWidth(0.0);
        text8.setText("Create new account, ");
        text8.setWrappingWidth(338.5302734375);
        text8.setFont(new Font("Cooper Black", 30.0));

        btnClick.setFill(javafx.scene.paint.Color.valueOf("#d7b33e"));
        btnClick.setLayoutX(684.0);
        btnClick.setLayoutY(712.0);
        btnClick.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        btnClick.setStrokeWidth(0.0);
        btnClick.setText(" Click here");
        btnClick.setWrappingWidth(338.5302734375);
        btnClick.setFont(new Font("Cooper Black", 30.0));
         btnClick.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                Welcome.navScreens(new SignUp(s), s);
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
        flowPane.getChildren().add(username);
        flowPane.getChildren().add(password);
        getChildren().add(flowPane);
        getChildren().add(btnSignIn);
        getChildren().add(text8);
        getChildren().add(btnClick);

    }
    
  
}
