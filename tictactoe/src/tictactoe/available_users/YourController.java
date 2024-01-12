/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.available_users;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class YourController implements Initializable {
    @FXML
    private ListView<yourData> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize your controller, if needed
        updateUI();
    }

    private void updateUI() {
        // Sample data
        yourData data1 = new yourData("Item 1" , "slael");
        yourData data2 = new yourData("Item 2" , "mina");
        yourData data3 = new yourData("Item 3" , "test");

        // Add data to the list
        listView.setItems(FXCollections.observableArrayList(data1, data2, data3));

        // Set the cell factory
        listView.setCellFactory(param -> new YourListCell());
    }
}
