package tictactoe;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ItemLayoutForHistory extends HBox {

    protected final AnchorPane anchorPane;
    protected final Label dataLabel;
    protected final ImageView imageView;
    protected final Text gameRecordText;
    protected final VBox imageWrapper;

    public ItemLayoutForHistory() {
        anchorPane = new AnchorPane();
        dataLabel = new Label();
        imageView = new ImageView();
        gameRecordText = new Text();
        imageWrapper = new VBox();

        setPrefHeight(75.0);
        setPrefWidth(980.0);
        setStyle("-fx-background-color: #1d1e3d;-fx-border-color: #ffffff; -fx-border-radius: 20; -fx-border-style: dashed;");

        anchorPane.setPrefHeight(75.0);
        anchorPane.setPrefWidth(980.0);
        anchorPane.setStyle("-fx-background-color: #1d1e3d; -fx-border-width: 3; -fx-border-radius: 20;");

        dataLabel.setLayoutX(36.0);
        dataLabel.setLayoutY(14.0);
        dataLabel.setPrefHeight(40.0);
        dataLabel.setPrefWidth(500.0);
        dataLabel.setText("Name");
        dataLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        dataLabel.setFont(new Font("Cooper Black", 20.0));

        imageView.setFitHeight(65.0);
        imageView.setFitWidth(65.0);
        imageView.setLayoutX(950.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        Image videoImage = new Image(getClass().getResource("images/video.png").toExternalForm());
        imageView.setImage(videoImage);
        imageWrapper.setAlignment(Pos.CENTER);
        imageWrapper.getChildren().add(imageView);
        imageView.setLayoutX(930.0);
        gameRecordText.setFill(javafx.scene.paint.Color.WHITE);
        gameRecordText.setLayoutX(700.0);
        gameRecordText.setLayoutY(45.0);
        gameRecordText.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        gameRecordText.setStrokeWidth(0.0);
        gameRecordText.setText("test");
        gameRecordText.setWrappingWidth(300.0);
        gameRecordText.setFont(new Font(20.0));

        anchorPane.getChildren().addAll(dataLabel, imageView, gameRecordText);
        getChildren().add(anchorPane);
    }
}
