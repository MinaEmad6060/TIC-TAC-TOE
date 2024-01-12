package tictactoe;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class History extends BorderPane {

    protected final Label label;
    protected final ScrollPane scrollPane;
    protected final ListView<MyData> listView;

    public History(Stage s) {

        label = new Label();
        scrollPane = new ScrollPane();
        listView = new ListView<>();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(784.0);
        setPrefWidth(1200.0);
        setStyle("-fx-background-color: #1d1e3d;");

        BorderPane.setAlignment(label, javafx.geometry.Pos.TOP_LEFT);
        label.setAlignment(javafx.geometry.Pos.TOP_LEFT);
        label.setText("History");
        label.setTextFill(javafx.scene.paint.Color.valueOf("#ffd652"));
        label.setFont(new Font("Cooper Black", 48.0));
        BorderPane.setMargin(label, new Insets(10.0));
        label.setPadding(new Insets(10.0, 0.0, 0.0, 20.0));
        setTop(label);

        BorderPane.setAlignment(scrollPane, javafx.geometry.Pos.CENTER);
        scrollPane.setStyle("-fx-background-color: #1d1e3d;");

        listView.setPrefHeight(784.0);
        listView.setPrefWidth(1181.0);
        listView.setStyle("-fx-background-color: #1d1e3d;");
        scrollPane.setContent(listView);
        setCenter(scrollPane);
        listView.setCellFactory(param -> new ItemLayoutForHistoryCell());

    }

    

    private static class ItemLayoutForHistoryCell extends javafx.scene.control.ListCell<MyData> {
        @Override
        protected void updateItem(MyData item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setText(null);
                setGraphic(null);
            } else {
                ItemLayoutForHistory itemLayout = new ItemLayoutForHistory() {};
                
                itemLayout.dataLabelHistory.setText(item.getText());
                itemLayout.dataLabelHistory1.setText("Additional Text"); 
                setGraphic(itemLayout);
            }
        }
    }
    public class MyData {
        private String text;

        public MyData(String text) {
            this.text = text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
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
