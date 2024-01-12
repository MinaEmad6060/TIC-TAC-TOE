/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.available_users;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class YourItemController {
    @FXML
    private Label dataLabel;
    @FXML
    private Label dataLabel2;

    public void setData(yourData data) {
        dataLabel.setText(data.getText());
        dataLabel2.setText(data.getText2());
        
    }
}
