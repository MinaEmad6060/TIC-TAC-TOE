package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Welcome extends BorderPane {

    protected final FlowPane flowPane;
    protected final Text text;
    protected final Text text0;
    protected final FlowPane flowPane0;
    protected final Text text1;
    protected final Text text2;
    protected final Text text3;
    protected final Text text4;
    protected final Text text5;
    protected final Text text6;
    protected final Text text7;
    protected final Text text8;
    protected final Text text9;
    protected final AnchorPane anchorPane;
    protected final ImageView imageView;
    protected final AnchorPane anchorPane0;
    protected final Button butStart;

    public Welcome(Stage s) {

        flowPane = new FlowPane();
        text = new Text();
        text0 = new Text();
        flowPane0 = new FlowPane();
        text1 = new Text();
        text2 = new Text();
        text3 = new Text();
        text4 = new Text();
        text5 = new Text();
        text6 = new Text();
        text7 = new Text();
        text8 = new Text();
        text9 = new Text();
        anchorPane = new AnchorPane();
        imageView = new ImageView();
        anchorPane0 = new AnchorPane();
        butStart = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        //setStyle("-fx-background-color: #1D1E3D;");
          setStyle("-fx-background-image: url('tictactoe/images/background.jpg');");


        BorderPane.setAlignment(flowPane, javafx.geometry.Pos.CENTER);
        flowPane.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        flowPane.setColumnHalignment(javafx.geometry.HPos.CENTER);
        flowPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        flowPane.setPrefHeight(200.0);
        flowPane.setPrefWidth(200.0);
        flowPane.setStyle("-fx-border-radius: 20;");
        flowPane.setVgap(10.0);

        text.setFill(javafx.scene.paint.Color.WHITE);
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setText("  Welcome");
        text.setWrappingWidth(567.341796875);
        text.setFont(new Font("Cooper Black", 100.0));

        text0.setFill(javafx.scene.paint.Color.WHITE);
        text0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text0.setStrokeWidth(0.0);
        text0.setText("  To");
        text0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        text0.setWrappingWidth(388.87890625);
        text0.setFont(new Font("Cooper Black", 100.0));

        flowPane0.setAlignment(javafx.geometry.Pos.TOP_CENTER);
        flowPane0.setColumnHalignment(javafx.geometry.HPos.CENTER);
        flowPane0.setPrefHeight(86.0);
        flowPane0.setPrefWidth(568.0);

        text1.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text1.setStrokeWidth(0.0);
        text1.setText("T");
        text1.setFont(new Font("Cooper Black", 72.0));

        text2.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text2.setStrokeWidth(0.0);
        text2.setText("I");
        text2.setFont(new Font("Cooper Black", 72.0));

        text3.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text3.setStrokeWidth(0.0);
        text3.setText("C ");
        text3.setFont(new Font("Cooper Black", 72.0));

        text4.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text4.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text4.setStrokeWidth(0.0);
        text4.setText("T");
        text4.setFont(new Font("Cooper Black", 72.0));

        text5.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text5.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text5.setStrokeWidth(0.0);
        text5.setText("A");
        text5.setFont(new Font("Cooper Black", 72.0));

        text6.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text6.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text6.setStrokeWidth(0.0);
        text6.setText("C ");
        text6.setFont(new Font("Cooper Black", 72.0));

        text7.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text7.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text7.setStrokeWidth(0.0);
        text7.setText("T");
        text7.setFont(new Font("Cooper Black", 72.0));

        text8.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text8.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text8.setStrokeWidth(0.0);
        text8.setText("O");
        text8.setFont(new Font("Cooper Black", 72.0));

        text9.setFill(javafx.scene.paint.Color.valueOf("#00d6ff"));
        text9.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text9.setStrokeWidth(0.0);
        text9.setText("E");
        text9.setFont(new Font("Cooper Black", 72.0));
        flowPane.setOpaqueInsets(new Insets(0.0));
        setCenter(flowPane);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(0.0);
        anchorPane.setPrefWidth(1200.0);

        imageView.setFitHeight(89.0);
        imageView.setFitWidth(200.0);
        imageView.setLayoutX(14.0);
        imageView.setLayoutY(7.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("images/xo.png").toExternalForm()));
        setTop(anchorPane);

        BorderPane.setAlignment(anchorPane0, javafx.geometry.Pos.CENTER);
        anchorPane0.setPrefHeight(200.0);
        anchorPane0.setPrefWidth(200.0);

        butStart.setAlignment(javafx.geometry.Pos.CENTER);
        butStart.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        butStart.setLayoutX(439.0);
        butStart.setLayoutY(-15.0);
        butStart.setMnemonicParsing(false);
        butStart.setPrefHeight(143.0);
        butStart.setPrefWidth(323.0);
        butStart.setStyle("-fx-background-radius: 25;");
        butStart.setText("Start");
        
        butStart.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
//       
//        Parent root2 = new Modes(s);
//  
//        Scene scene2 = new Scene(root2);
//        
//       s.setScene(scene2);
//        s.show();

                navScreens(new Modes(s), s);
    }

            //}
            
        });
        butStart.setTextFill(javafx.scene.paint.Color.valueOf("#d7b33e"));
        butStart.setFont(new Font("Cooper Black", 80.0));
        butStart.setOpaqueInsets(new Insets(300.0, 0.0, 0.0, 0.0));
        setBottom(anchorPane0);

        flowPane.getChildren().add(text);
        flowPane.getChildren().add(text0);
        flowPane0.getChildren().add(text1);
        flowPane0.getChildren().add(text2);
        flowPane0.getChildren().add(text3);
        flowPane0.getChildren().add(text4);
        flowPane0.getChildren().add(text5);
        flowPane0.getChildren().add(text6);
        flowPane0.getChildren().add(text7);
        flowPane0.getChildren().add(text8);
        flowPane0.getChildren().add(text9);
        flowPane.getChildren().add(flowPane0);
        anchorPane.getChildren().add(imageView);
        anchorPane0.getChildren().add(butStart);

    }
    public static void navScreens(Parent ref,Stage stage){
        
 
        Parent root=ref;
  
        Scene scene = new Scene(root);
        
       stage.setScene(scene);
        stage.show();
    }
}
