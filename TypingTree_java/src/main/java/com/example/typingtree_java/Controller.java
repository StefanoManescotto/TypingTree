package com.example.typingtree_java;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import org.fxmisc.richtext.*;

import java.net.URL;
import java.util.ResourceBundle;

import java.time.Duration;
import java.time.Instant;

public class Controller implements Initializable {

    @FXML
    private TextField txtField;
    @FXML
    private InlineCssTextArea txtArea;
    @FXML
    private Label wpmLbl;
    private int rightLetters = 0, currentWord = 0, lastLength = 0, fontSize = 20;
    Instant start;
    public void initialize(URL location, ResourceBundle resources) {
        txtArea.setEditable(false);
        txtArea.appendText("I met him down near the border. Said he wanted me to work with him on a job. Range war. But he said it'd be easy. " +
                "All we had to worry about was a drunken sheriff. Are you sure you don't want some coffee?");

        txtArea.moveSelectedText(10);
        txtArea.setWrapText(true);

        txtArea.setStyle("-fx-font-size: " + fontSize+";");

        txtArea.setShowCaret(Caret.CaretVisibility.ON);
        txtArea.displaceCaret(0);

        wpmLbl.setText("0");
    }
    @FXML
    protected void testingButton(){
        currentWord = 0;
        txtArea.clearStyle(0);
        txtArea.displaceCaret(0);
        txtArea.scrollYToPixel(0);
        txtField.setText("");
        lastLength = 0;
        rightLetters = 0;
    }

    @FXML
    protected void controlTyped(){
        double txtHeight = 0;
        String toWrite;

        toWrite = txtArea.getText().substring(currentWord);

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
                if(txtField.getText().length() == 1 && currentWord == 0){
                    start = Instant.now();
                }else{
                    Duration timeElapsed = Duration.between(start, Instant.now());
                    wpmLbl.setText(String.valueOf((int)(((currentWord)/(timeElapsed.toMillis()/1000d))*60)/5));
                }

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
