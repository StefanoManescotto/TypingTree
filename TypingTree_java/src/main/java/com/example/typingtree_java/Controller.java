package com.example.typingtree_java;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import org.fxmisc.richtext.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField txtField;
    @FXML
    private InlineCssTextArea txtArea;
    private int rightLetters = 0, currentWord = 0, lastLength = 0, fontSize = 20;
    private double txtHeight = 0;

    public void initialize(URL location, ResourceBundle resources) {
        txtArea.setEditable(false);
        txtArea.appendText("Moses supposes his toeses are roses, but Moses supposes erroneously. Moses he knowses his toeses aren't roses as Moses supposes his toeses to be." +
                "Moses supposes his toeses are roses, but Moses supposes erroneously. Moses he knowses his toeses aren't roses as Moses supposes his toeses to be.");

        txtArea.moveSelectedText(10);
        txtArea.setWrapText(true);

        txtArea.setStyle("-fx-font-size: " + fontSize+";");

        txtArea.setShowCaret(Caret.CaretVisibility.ON);
        txtArea.displaceCaret(0);
    }
    @FXML
    protected void testingButton(){
        currentWord = 0;
    }

    @FXML
    protected void controlTyped(){
        String toWrite;
        toWrite = txtArea.getText().substring(currentWord);

        txtHeight = 0;
        for (Node n: txtArea.lookupAll(".text")) {
            if(txtHeight < n.boundsInParentProperty().get().getMaxY()){
                txtHeight = n.boundsInParentProperty().get().getMaxY();
            }
        }

        txtArea.scrollYToPixel((txtHeight/txtArea.getParagraphLinesCount(0))
                * txtArea.lineIndex(0, currentWord + txtField.getText().length()));


        txtArea.displaceCaret(txtField.getText().length() + currentWord);

        if(lastLength > txtField.getText().length()){
            txtArea.clearStyle(currentWord + txtField.getText().length(), currentWord + lastLength);
            lastLength = txtField.getText().length();
            if(txtField.getText().length() < rightLetters){
                rightLetters = txtField.getText().length();
            }
        }else{
            if(txtField.getText().length() > 0){
                lastLength++;

                if(txtField.getText().equals(toWrite.substring(0, txtField.getText().length()))){
                    rightLetters = txtField.getText().length();

                    Border b = new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
                    txtField.setBorder(b);

                    txtArea.setStyle(0, currentWord + txtField.getText().length(), "-fx-stroke: green;");

                    if(Character.isWhitespace(txtField.getText().charAt(txtField.getText().length() - 1))){
                        rightLetters = 0;
                        lastLength = 0;
                        currentWord += txtField.getText().length();

                        txtField.setText("");
                    }
                }else{
                    Border b = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
                    txtField.setBorder(b);

                    txtArea.setStyle(currentWord + rightLetters, currentWord + txtField.getText().length(), "-fx-stroke: red;");
                }
            }
        }
    }
}
