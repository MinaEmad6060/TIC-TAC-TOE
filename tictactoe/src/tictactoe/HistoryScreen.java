package tictactoe;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HistoryScreen extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final ListView<HistoryScreen.TextData> listView;
    protected final ImageView btnBack;
    String[] playersAndDateAndTime;
    String[] playersNameAfterSplit;
    static String player1;
    static String player2;
    static String dateAndTime;
    static String stepsString="";
    

    
   
    String hestoryRequest;

    public HistoryScreen(Stage s) {

        anchorPane = new AnchorPane();
        label = new Label();
        listView = new ListView();
        btnBack = new ImageView();
        hestoryRequest = "history " + SignIn.currentUser;
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        setStyle("-fx-background-color: #1d1e3d;");

        anchorPane.setPrefHeight(105.0);
        anchorPane.setPrefWidth(1200.0);

        label.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label.setLayoutX(10.0);
        label.setLayoutY(10.0);
        label.setPrefHeight(65.0);
        label.setPrefWidth(1190.0);
        label.setText("History");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        label.setFont(new Font("Cooper Black", 48.0));
        label.setPadding(new Insets(10.0, 0.0, 0.0, 20.0));

        listView.setLayoutX(82.0);
        listView.setLayoutY(117.0);
        listView.setPrefHeight(551.0);
        listView.setPrefWidth(1080.0);
        //listView.setStyle("-fx-background-color: #1d1e3d");
        listView.setStyle(
    "-fx-background-color: #1d1e3d; " +
    "-fx-control-inner-background: #1d1e3d; " +
    "-fx-background: #1d1e3d; " +
    "-fx-padding: 10; " +
    "-fx-border-color: transparent; " +
    "-fx-scrollbar-face-color: transparent; " +
    "-fx-scrollbar-highlight-color: transparent; " +
    "-fx-scrollbar-shadow-color: transparent; " +
    "-fx-scrollbar-base-color: transparent; " +
    "-fx-background-insets: 0; " +
    "-fx-background-radius: 0;"
);




        btnBack.setFitHeight(68.0);
        btnBack.setFitWidth(107.0);
        btnBack.setLayoutX(21.0);
        btnBack.setLayoutY(685.0);
        btnBack.setPickOnBounds(true);
        btnBack.setPreserveRatio(true);
        btnBack.setImage(new Image(getClass().getResource("images/back.png").toExternalForm()));
        btnBack.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent event) {
                Welcome.navScreens(new OnlineHome(s), s);
            }
        });

        getChildren().add(anchorPane);
        getChildren().add(label);
        getChildren().add(listView);
        getChildren().add(btnBack);
        System.out.println(hestoryRequest);
        listView.setCellFactory(param -> new ItemLayoutForHistoryCell());

        SignIn.sendMessageToServer.println(hestoryRequest);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String msg = SignIn.listenFromServer.readLine();
                        System.out.println(msg);
                        String[] allRecords = msg.split("\\*");
                        // mahmoud-mina 13/1/2024 01:30.X00,O01,X10,O11,X21,O20,X02,O12,X22*
                        for (int i = 0; i < allRecords.length; i++) {
                            String[] recordForPlayer = allRecords[i].split("\\.");
                            final String[] recordAndTime = new String[recordForPlayer.length / 2];
                            final String[] recordSteps = new String[recordForPlayer.length / 2];
                            for (int j = 0; j < recordForPlayer.length; j++) {
                                if (j % 2 == 0) {
                                    recordAndTime[j / 2] = recordForPlayer[j];
                                } else {
                                    recordSteps[j / 2] = recordForPlayer[j];
                                }
                            }

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    for (int i = 0; i < recordAndTime.length; i++) {
                                        addDataToListView(new HistoryScreen.TextData(recordAndTime[i], recordSteps[i]));
                                    }

                                }
                            });
                        }

                        break;
                    } catch (IOException ex) {
                        break;
                    }
                }

            }
        }.start();

        listView.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.PRIMARY) {
                TextData selectedItem = (TextData) listView.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    // Handle on the selected item
                    stepsString=selectedItem.getText2();
                    //System.out.println("Clicked on: "+selectedItem.getText()+ stepsString );
                    playersAndDateAndTime = selectedItem.getText().split(" ",2);
                    playersNameAfterSplit=playersAndDateAndTime[0].split("-");
                    player1=playersNameAfterSplit[0];
                    player2=playersNameAfterSplit[1];
                    dateAndTime =playersAndDateAndTime[1];
                    System.out.println("name1"+player1 +" name2"+player2+" date and time"+dateAndTime +" steps"+stepsString);
                    Welcome.navScreens(new CheckVar(s), s);
                }
            }
        });

    }

    private class ItemLayoutForHistoryCell extends javafx.scene.control.ListCell<TextData> {

        @Override
        protected void updateItem(TextData item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                ItemLayoutForHistory itemLayout = new ItemLayoutForHistory();
                itemLayout.dataLabel.setText(item.getText());
                itemLayout.gameRecordText.setText(item.getText2());
                itemLayout.gameRecordText.setOpacity(0);
                setGraphic(itemLayout);
            }
        }
    }

    public class TextData {

        String text1;
        String text2;

        public TextData(String text1, String text2) {
            this.text1 = text1;
            this.text2 = text2;
        }

        public String getText2() {
            return text2;
        }

        public void setText2(String text2) {
            this.text2 = text2;
        }

        public void setText(String text1) {
            this.text1 = text1;
        }

        public String getText() {
            return text1;
        }
    }

    public void updateDataInListView() {
        listView.refresh();
    }

    public void addDataToListView(TextData newData) {
        listView.getItems().add(newData);
        listView.scrollTo(newData);
        updateDataInListView();
    }
}
