package tictactoe;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Modes extends BorderPane {

    protected final Text text;
    protected final AnchorPane anchorPane;
    protected final FlowPane flowPane;
    protected final Button butLoc;
    protected final Button butRo;
    protected final Button butOnl;

    public Modes(Stage s) {

        text = new Text();
        anchorPane = new AnchorPane();
        flowPane = new FlowPane();
        butLoc = new Button();
        butRo = new Button();
        butOnl = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        setStyle("-fx-background-image: url('tictactoe/images/background.jpg');");
        BorderPane.setAlignment(text, javafx.geometry.Pos.CENTER);
        text.setFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        text.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        text.setStrokeWidth(0.0);
        text.setStyle("-fx-text-alignment: center;");
        text.setText("Choose Play Mode");
        text.setWrappingWidth(933.341796875);
        text.setFont(new Font("Cooper Black", 100.0));
        BorderPane.setMargin(text, new Insets(20.0, 0.0, 0.0, 0.0));
        setTop(text);

        BorderPane.setAlignment(anchorPane, javafx.geometry.Pos.CENTER);
        anchorPane.setPrefHeight(40.0);
        anchorPane.setPrefWidth(1200.0);
        setBottom(anchorPane);

        BorderPane.setAlignment(flowPane, javafx.geometry.Pos.CENTER);
        flowPane.setAlignment(javafx.geometry.Pos.CENTER);
        flowPane.setOrientation(javafx.geometry.Orientation.VERTICAL);
        flowPane.setPrefHeight(606.0);
        flowPane.setPrefWidth(1200.0);
        flowPane.setVgap(30.0);

        butLoc.setAlignment(javafx.geometry.Pos.CENTER);
        butLoc.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        butLoc.setMnemonicParsing(false);
        butLoc.setPrefHeight(147.0);
        butLoc.setPrefWidth(356.0);
        butLoc.setStyle("-fx-background-radius: 25;");
        butLoc.setText("Local");
        butLoc.setTextFill(javafx.scene.paint.Color.valueOf("#d7b33e"));
        butLoc.setFont(new Font("Cooper Black", 80.0));
        butLoc.setOpaqueInsets(new Insets(300.0, 0.0, 0.0, 0.0));
           butLoc.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Welcome.navScreens(new EmptyBoard(s), s);
            }});

        butRo.setMnemonicParsing(false);
        butRo.setPrefHeight(147.0);
        butRo.setPrefWidth(356.0);
        butRo.setStyle("-fx-background-radius: 25;");
        butRo.setText("Robot");
        butRo.setTextFill(javafx.scene.paint.Color.valueOf("#d7b33e"));
        butRo.setFont(new Font("Cooper Black", 72.0));

        butOnl.setMnemonicParsing(false);
        butOnl.setPrefHeight(147.0);
        butOnl.setPrefWidth(356.0);
        butOnl.setStyle("-fx-background-radius: 25;");
        butOnl.setText("Online");
        butOnl.setTextFill(javafx.scene.paint.Color.valueOf("#d7b33e"));
        butOnl.setFont(new Font("Cooper Black", 72.0));
        setCenter(flowPane);
        setOpaqueInsets(new Insets(0.0));

        flowPane.getChildren().add(butLoc);
        flowPane.getChildren().add(butRo);
        flowPane.getChildren().add(butOnl);

    }
}
