package tictactoe;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class HistoryScreen extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Label label;
    
    protected final ListView<HistoryScreen.MyData> listView;
    String hestoryRequest;
    Socket serverSide;
    DataInputStream listenFromServer;
    PrintStream sendMessageToServer;

    public HistoryScreen(Stage s) {

        anchorPane = new AnchorPane();
        label = new Label();
        listView = new ListView<>();
        hestoryRequest="history " + "mahmoud";
        System.out.println(hestoryRequest);
            try {
                serverSide = new Socket("127.0.0.1", 2000);
                listenFromServer = new DataInputStream(serverSide.getInputStream());
                sendMessageToServer = new PrintStream(serverSide.getOutputStream());
                sendMessageToServer.println(hestoryRequest);
                    new Thread(){
                        @Override
                        public void run(){
                            while(true)
                            {
                                try {
                                    String msg = listenFromServer.readLine();
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
                                                    addDataToListView(new MyData(recordAndTime[i], recordSteps[i]));
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
                                         
                        s.setOnCloseRequest(new EventHandler<WindowEvent>(){
                            @Override
                            public void handle(WindowEvent event) {
                                sendMessageToServer.println("Close");
                                try {
                                    sendMessageToServer.close();
                                    listenFromServer.close();
                                    serverSide.close();                        
                                } catch (IOException ex) {
                                    System.out.println("Erorr");
                                }
                            }
                        }); 

                }catch (IOException ex) {
                        System.out.println("error in creating socket");                    
                }
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
//            addDataToListView(new MyData("Test Data " + i));
//        }
//    }

private class ItemLayoutForHistoryCell extends javafx.scene.control.ListCell<MyData> {
        @Override
        protected void updateItem(MyData item, boolean empty) {
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

    public class MyData {
         String text1;
         String text2;
        public MyData(String text1,String text2) {
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

    public void addDataToListView(MyData newData) {
        listView.getItems().add(newData);
        listView.scrollTo(newData);
        updateDataInListView();
    }
}
