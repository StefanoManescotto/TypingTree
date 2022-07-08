package com.example.typingtree_java;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField txtField;

    @FXML
    protected void testingButton(){
        welcomeText.setText("testing text");
        txtField.setText("ciao");
    }

    @FXML
    protected void resetWord(){
        if(txtField.getText().length() > 0 && Character.isWhitespace(txtField.getText().charAt(txtField.getText().length() - 1))){
            txtField.setText("");
        }
    }
}
