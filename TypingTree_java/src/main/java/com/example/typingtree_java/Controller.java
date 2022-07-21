package com.example.typingtree_java;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
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

    private int rightLetters = 0, currentWord = 0, lastLength = 0, fontSize = 20, totalCharactersWrote = 0;
    Instant start;

    public void initialize(URL location, ResourceBundle resources) {
        txtArea.setEditable(false);
        //txtArea.appendText("I met him down near the border. Said he wanted me to work with him on a job. Range war. But he said it'd be easy. " +
        //        "All we had to worry about was a drunken sheriff. Are you sure you don't want some coffee?");


        txtArea.appendText(GeneratePhrase.getRandomWords(100));


        txtArea.moveSelectedText(10);
        txtArea.setWrapText(true);

        txtArea.setStyle("-fx-font-size: " + fontSize+";");

        txtArea.setShowCaret(Caret.CaretVisibility.ON);
        txtArea.displaceCaret(0);

        wpmLbl.setText("0");
        //txtArea.requestFollowCaret();
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
        totalCharactersWrote = 0;
    }

    @FXML
    protected void controlTyped(){
        String toWrite;
        toWrite = txtArea.getText().substring(currentWord);

        scrollTxtArea();
        txtArea.displaceCaret(txtField.getText().length() + currentWord);

        if(lastLength > txtField.getText().length()){
            deletedCharacter();
        }else{
            if(txtField.getText().length() > 0){
                updateWPM();
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
                        totalCharactersWrote += txtField.getText().length();

                        txtField.setText("");

                        minimumWordsCheck();
                    }
                }else{
                    Border b = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
                    txtField.setBorder(b);

                    txtArea.setStyle(currentWord + rightLetters, currentWord + txtField.getText().length(), "-fx-stroke: red;");
                }
            }
        }
    }

    /**
     * scroll the textArea to the word that is currently being written
     */
    private void scrollTxtArea(){
        double txtHeight = 0;

        for (Node n: txtArea.lookupAll(".text")) {
            if(txtHeight < n.boundsInParentProperty().get().getMaxY()){
                txtHeight = n.boundsInParentProperty().get().getMaxY();
            }
        }

        //System.out.println(currentWord + " " + txtHeight + " " + txtArea.getParagraphLinesCount(0) + " " + txtHeight/txtArea.getParagraphLinesCount(0));

        txtArea.scrollYToPixel((txtHeight/txtArea.getParagraphLinesCount(0))
                * txtArea.lineIndex(0, currentWord + txtField.getText().length()));
    }

    /**
     * if a character has been canceled in the textField, than reset the style of the canceled character in the textArea
     */
    private void deletedCharacter(){
        txtArea.clearStyle(currentWord + txtField.getText().length(), currentWord + lastLength);
        lastLength = txtField.getText().length();

        if(txtField.getText().length() < rightLetters){
            rightLetters = txtField.getText().length();
        }
    }

    /**
     * calculate the wpm and update the wpmLbl
     */
    private void updateWPM(){
        if(txtField.getText().length() == 1 && currentWord == 0){
            start = Instant.now();
        }else{
            Duration timeElapsed = Duration.between(start, Instant.now());
            wpmLbl.setText(String.valueOf((int)(((totalCharactersWrote)/(timeElapsed.toMillis()/1000d))*60)/5));
        }
    }

    /**
     * the words remaining to write has to be >~ 40
     */
    private void minimumWordsCheck(){
        if(txtArea.getText().length()/5 - currentWord/5 < 90){
            System.out.println("Adding text");
            double a = txtArea.getEstimatedScrollY();
            //currentWord -= (txtArea.getText().indexOf(" ") + 1);
            //txtArea.deleteText(0, txtArea.getText().indexOf(" ") + 1);
            //txtArea.appendText(GeneratePhrase.getRandomWords(20));
            txtArea.append(GeneratePhrase.getRandomWords(10), "-fx-font-size: " + fontSize+";");
            txtArea.displaceCaret(currentWord);

            txtArea.scrollYToPixel(a);
        }
    }
}
