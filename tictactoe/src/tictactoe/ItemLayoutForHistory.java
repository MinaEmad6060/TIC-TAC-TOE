package tictactoe;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public abstract class ItemLayoutForHistory extends HBox {

    protected final Label dataLabelHistory;
    protected final Label dataLabelHistory1;

    public ItemLayoutForHistory() {

        dataLabelHistory = new Label();
        dataLabelHistory1 = new Label();

        setPrefHeight(66.0);
        setPrefWidth(596.0);
        setStyle("-fx-background-radius: 20; -fx-border-color: #ffffff; -fx-border-radius: 20; -fx-border-style: dashed; -fx-border-width: 3; -fx-background-color: #1d1e3d;");

        dataLabelHistory.setPrefHeight(29.0);
        dataLabelHistory.setPrefWidth(445.0);
        dataLabelHistory.setText("Name");
        dataLabelHistory.setTextFill(javafx.scene.paint.Color.WHITE);
        dataLabelHistory.setFont(new Font("Cooper Black", 24.0));
        HBox.setMargin(dataLabelHistory, new Insets(14.0, 0.0, 0.0, 15.0));

        dataLabelHistory1.setLayoutX(28.0);
        dataLabelHistory1.setLayoutY(27.0);
        dataLabelHistory1.setPrefHeight(29.0);
        dataLabelHistory1.setPrefWidth(445.0);
        dataLabelHistory1.setText("Name");
        dataLabelHistory1.setTextFill(javafx.scene.paint.Color.WHITE);
        dataLabelHistory1.setFont(new Font("Cooper Black", 24.0));

        getChildren().add(dataLabelHistory);
        getChildren().add(dataLabelHistory1);

    }
}
