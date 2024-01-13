package tictactoe;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ItemLayoutForHistory extends HBox {

    protected final AnchorPane anchorPane;
    public static Label dataLabel;
    protected final ImageView imageView;
    public static Text gameRecordText;

    public ItemLayoutForHistory() {

        anchorPane = new AnchorPane();
        dataLabel = new Label();
        imageView = new ImageView();
        gameRecordText = new Text();

        setPrefHeight(100.0);
        setPrefWidth(1100.0);
        setStyle("-fx-border-color: #ffffff; -fx-border-radius: 20; -fx-border-style: dashed; -fx-border-width: 3; -fx-background-color: #1d1e3d;");

        anchorPane.setPrefHeight(100.0);
        anchorPane.setPrefWidth(1100.0);

        dataLabel.setLayoutX(44.0);
        dataLabel.setLayoutY(18.0);
        dataLabel.setPrefHeight(65.0);
        dataLabel.setPrefWidth(649.0);
        dataLabel.setText("Name");
        dataLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        dataLabel.setFont(new Font("Cooper Black", 30.0));

        imageView.setFitHeight(93.0);
        imageView.setFitWidth(121.0);
        imageView.setLayoutX(980.0);
        imageView.setLayoutY(5.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("images/video.png").toExternalForm()));

        gameRecordText.setFill(javafx.scene.paint.Color.WHITE);
        gameRecordText.setLayoutX(636.0);
        gameRecordText.setLayoutY(63.0);
        gameRecordText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        gameRecordText.setStrokeWidth(0.0);
        gameRecordText.setFont(new Font(30.0));

        anchorPane.getChildren().add(dataLabel);
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(gameRecordText);
        getChildren().add(anchorPane);

    }
}
