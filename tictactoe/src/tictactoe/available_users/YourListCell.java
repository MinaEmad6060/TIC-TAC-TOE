/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.available_users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;

import java.io.IOException;
public class YourListCell extends ListCell<yourData> {
    @Override
    protected void updateItem(yourData item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("your_item_layout.fxml"));
            try {
                Parent root = loader.load();
                YourItemController controller = loader.getController();
                controller.setData(item);
                setGraphic(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}