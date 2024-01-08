package tictactoe;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public  class SignUp extends AnchorPane {

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
    protected final FlowPane flowPane;
    protected final TextField username;
    protected final Label lableUser;
    protected final PasswordField password;
    protected final Label lablePass;
    protected final PasswordField confPass;
    protected final Label lableConf;
    protected final Button btnSignUp;
    protected final Text text8;
    protected final Text textSignIn;
    protected final ImageView btnBack;

    public SignUp(Stage s) {

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
        flowPane = new FlowPane();
        username = new TextField();
        lableUser = new Label();
        password = new PasswordField();
        lablePass = new Label();
        confPass = new PasswordField();
        lableConf = new Label();
        btnSignUp = new Button();
        text8 = new Text();
        textSignIn = new Text();
        btnBack = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        setStyle("-fx-background-color: #1D1E3D;");

        anchorPane.setLayoutY(-28.0);
        anchorPane.setPrefHeight(223.0);
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
        imageView.setLayoutX(24.0);
        imageView.setLayoutY(47.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("images/xo.png").toExternalForm()));
        anchorPane.setOpaqueInsets(new Insets(0.0));

        flowPane.setAlignment(javafx.geometry.Pos.CENTER);
        flowPane.setLayoutX(113.0);
        flowPane.setLayoutY(156.0);
        flowPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        flowPane.setPrefHeight(414.0);
        flowPane.setPrefWidth(977.0);
        flowPane.setVgap(10.0);

        username.setPrefHeight(76.0);
        username.setPrefWidth(770.0);
        username.setPromptText("User Name");
        username.setStyle("-fx-background-radius: 25;-fx-text-fill: #d7b33e;");
        username.setFont(new Font("Cooper Black", 50.0));
        username.setCursor(Cursor.NONE);
        username.setFocusTraversable(false);
        username.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkIsEmpty(username);
            }
        });

        lableUser.setPrefHeight(24.0);
        lableUser.setPrefWidth(713.0);
        lableUser.setText("");
        lableUser.setTextFill(javafx.scene.paint.Color.valueOf("#dd3939"));
        lableUser.setFont(new Font("Cooper Black", 20.0));

        password.setPrefHeight(76.0);
        password.setPrefWidth(770.0);
        password.setPromptText("Password");
        password.setStyle("-fx-background-radius: 25;-fx-text-fill: #d7b33e;");
        password.setFont(new Font("Cooper Black", 50.0));
        password.setFocusTraversable(false);
        password.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkIsEmpty(password);
            }
        });

        lablePass.setPrefHeight(24.0);
        lablePass.setPrefWidth(707.0);
        lablePass.setText("");
        lablePass.setTextFill(javafx.scene.paint.Color.valueOf("#dd3939"));
        lablePass.setFont(new Font("Cooper Black", 20.0));

        confPass.setPrefHeight(76.0);
        confPass.setPrefWidth(770.0);
        confPass.setPromptText("Confirm Password");
        confPass.setStyle("-fx-background-radius: 25;-fx-text-fill: #d7b33e;");
        confPass.setFont(new Font("Cooper Black", 50.0));
        confPass.setFocusTraversable(false);
        confPass.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                checkIsEmpty(confPass);
            }
        });

        lableConf.setPrefHeight(24.0);
        lableConf.setPrefWidth(766.0);
        lableConf.setText("tt");
        lableConf.setTextFill(javafx.scene.paint.Color.valueOf("#dd3939"));
        lableConf.setFont(new Font("Cooper Black", 20.0));
        flowPane.setOpaqueInsets(new Insets(0.0));

        btnSignUp.setLayoutX(423.0);
        btnSignUp.setLayoutY(590.0);
        btnSignUp.setMnemonicParsing(false);
        btnSignUp.setPrefHeight(119.0);
        btnSignUp.setPrefWidth(358.0);
        btnSignUp.setStyle("-fx-background-radius: 25;");
        btnSignUp.setText("Sign Up");
        btnSignUp.setTextFill(javafx.scene.paint.Color.valueOf("#1d1e3d"));
        btnSignUp.setFont(new Font("Cooper Black", 65.0));
        

        text8.setFill(javafx.scene.paint.Color.WHITE);
        text8.setLayoutX(364.0);
        text8.setLayoutY(753.0);
        text8.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text8.setStrokeWidth(0.0);
        text8.setText("Already have an account?");
        text8.setWrappingWidth(429.5302734375);
        text8.setFont(new Font("Cooper Black", 30.0));

        textSignIn.setFill(javafx.scene.paint.Color.valueOf("#d7b33e"));
        textSignIn.setLayoutX(766.0);
        textSignIn.setLayoutY(753.0);
        textSignIn.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        textSignIn.setStrokeWidth(0.0);
        textSignIn.setText("Sign In");
        textSignIn.setWrappingWidth(338.5302734375);
        textSignIn.setFont(new Font("Cooper Black", 30.0));
        textSignIn.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                Welcome.navScreens(new SignIn(s), s);
            }
        });
        btnBack.setFitHeight(68.0);
        btnBack.setFitWidth(107.0);
        btnBack.setLayoutX(22.0);
        btnBack.setLayoutY(691.0);
        btnBack.setPickOnBounds(true);
        btnBack.setPreserveRatio(true);
        btnBack.setImage(new Image(getClass().getResource("images/back.png").toExternalForm()));
        btnBack.setImage(new Image(getClass().getResource("images/back.png").toExternalForm()));
        btnBack.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                Welcome.navScreens(new Modes(s), s);
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
        flowPane.getChildren().add(username);
        flowPane.getChildren().add(lableUser);
        flowPane.getChildren().add(password);
        flowPane.getChildren().add(lablePass);
        flowPane.getChildren().add(confPass);
        flowPane.getChildren().add(lableConf);
        getChildren().add(flowPane);
        getChildren().add(btnSignUp);
        getChildren().add(text8);
        getChildren().add(textSignIn);
        getChildren().add(btnBack);

    }
    
    boolean checkPasswordLength(String password){
       if((password.length()<6)) 
           return false ;
        else
            return true;
    }
    boolean checkUserNameLength(String userName){
       if((userName.length()<3)) 
           return false ;
        else
            return true;
    }
    public boolean checkPasswords(String password , String confirmPassword){

        if(password.equals(confirmPassword)){
            return true ;
        }
        else{
            return false;
        }
    }
    private void checkIsEmpty(TextField textField) {
        if (textField.getText().isEmpty()) {
            System.out.println(textField.getPromptText() + " is empty");
            
            if(textField.getPromptText().equals("User Name")){
                    lableUser.setText("username is empty");
            }
            else if(textField.getPromptText().equals("Password")){
                    lablePass.setText("password is empty");
            }  
            else if(textField.getPromptText().equals("Confirm Password")){
                    lableConf.setText("Please Confirm your password");
            }
        }
        else {
        if (textField.getPromptText().equals("User Name")) {
            lableUser.setText("");
        } else if (textField.getPromptText().equals("Password")) {
            lablePass.setText("");
        }
        else if (textField.getPromptText().equals("Confirm Password")) {
            lableConf.setText("");
        }
    }
    }
}