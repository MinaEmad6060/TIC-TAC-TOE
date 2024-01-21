package tictactoe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public  class itemLayoutForAvailable extends HBox {

    protected final Label dataLabel;
    protected final FlowPane flowPane;
    protected final Label label;

    public itemLayoutForAvailable() {

        dataLabel = new Label();
        flowPane = new FlowPane();
        label = new Label();

        setPrefHeight(66.0);
        setPrefWidth(596.0);
        setStyle("-fx-background-radius: 20; -fx-border-color: #ffffff; -fx-border-radius: 20; -fx-border-style: dashed; -fx-border-width: 3; -fx-background-color: #1d1e3d;");

        dataLabel.setPrefHeight(29.0);
        dataLabel.setPrefWidth(750.0);
        dataLabel.setText("Name");
        dataLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        dataLabel.setFont(new Font("Cooper Black", 35.0));
       
        HBox.setMargin(dataLabel, new Insets(14.0, 0.0, 0.0, 15.0));

        flowPane.setMaxHeight(USE_PREF_SIZE);
        flowPane.setMinHeight(USE_PREF_SIZE);
        flowPane.setPrefHeight(45.0);
        flowPane.setPrefWidth(56.0);
        flowPane.setStyle("-fx-border-radius: 20; -fx-background-color: #ffd652; -fx-background-radius: 20;");
        HBox.setMargin(flowPane, new Insets(7.0, 0.0, 0.0, 150.0));

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setText("25");
        
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        label.setFont(new Font("Cooper Black", 30.0));
      
        FlowPane.setMargin(label, new Insets(2.0, 0.0, 0.0, 10.0));

        getChildren().add(dataLabel);
        flowPane.getChildren().add(label);
        getChildren().add(flowPane);

    }
}
