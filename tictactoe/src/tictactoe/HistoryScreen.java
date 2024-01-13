package tictactoe;

import java.io.IOException;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HistoryScreen extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Label label;
    
    protected final ListView<HistoryScreen.TextData> listView;
    String hestoryRequest;

    public HistoryScreen(Stage s) {

        anchorPane = new AnchorPane();
        label = new Label();
        listView = new ListView<>();
        hestoryRequest="history " + SignIn.currentUser;
        System.out.println(hestoryRequest);
                SignIn.sendMessageToServer.println(hestoryRequest);
                    new Thread(){
                        @Override
                        public void run(){
                            while(true)
                            {
                                try {
                                    String msg = SignIn.listenFromServer.readLine();
                                    System.out.println(msg);
                                    String[] allRecords = msg.split("\\*");
                                    // mahmoud-mina 13/1/2024 01:30.X00,O01,X10,O11,X21,O20,X02,O12,X22*
                                    for (int i=0;i<allRecords.length;i++) {
                                        String[] recordForPlayer=allRecords[i].split("\\.");
                                        final String[] recordAndTime=new String[recordForPlayer.length/2];
                                        final String[] recordSteps=new String[recordForPlayer.length/2];
                                        for(int j=0;j<recordForPlayer.length;j++) {
                                            if (j%2==0){
                                                recordAndTime[j/2]=recordForPlayer[j];
                                                System.out.println(recordAndTime[j/2]);
                                            } else {
                                                recordSteps[j/2]=recordForPlayer[j];
                                                System.out.println(recordSteps[j/2]);
                                            }
                                        }

                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {
                                                // change ui
                                                for (int i = 0; i < recordAndTime.length; i++) {
                                                    addDataToListView(new TextData(recordAndTime[i], recordSteps[i]));
                                                }
                                                

                                            }
                                        });
                                    }
                                
                                
                                }catch (IOException ex) {
                                    break;
                                }
                            }


                        }
                    }.start();

                
          listView.setOnMouseClicked(event -> {
              if (event.getButton() == MouseButton.PRIMARY) {
                  TextData selectedItem = listView.getSelectionModel().getSelectedItem();
                  if (selectedItem != null) {
                      // Handle on the selected item
                      System.out.println("Clicked on: " + selectedItem.getText2());
                  }
              }
          });

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

        listView.setLayoutX(3.0);
        listView.setLayoutY(123.0);
        listView.setPrefHeight(657.0);
        listView.setPrefWidth(1200.0);
        listView.setStyle("-fx-background-color: #1d1e3d;");
        
        getChildren().add(anchorPane);
        getChildren().add(label);
        getChildren().add(listView);
        listView.setCellFactory(param -> new ItemLayoutForHistoryCell());

        // Add 20 test data
        // add20TestData();
        

    }

//    void add20TestData() {
//        for (int i = 1; i <= 50; i++) {
//            addDataToListView(new TextData("Test Data " + i));
//        }
//    }

private class ItemLayoutForHistoryCell extends javafx.scene.control.ListCell<TextData> {
        @Override
        protected void updateItem(TextData item, boolean empty){
            super.updateItem(item, empty);
            if (empty||item==null) {
                setText(null);
                setGraphic(null);
            } else{
                ItemLayoutForHistory itemLayout = new ItemLayoutForHistory();
                itemLayout.dataLabel.setText(item.getText());
                itemLayout.gameRecordText.setText(item.getText2());
                itemLayout.gameRecordText.setOpacity(0); 
                setGraphic(itemLayout);
            }
        }
    }

    public class TextData{
         String text1;
         String text2;
        public TextData(String text1,String text2) {
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
