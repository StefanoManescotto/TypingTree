package com.example.typingtree_java;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private boolean error = false;
    private int lastError = Integer.MAX_VALUE, currentWord = 0, lengthWrote = 0, lastLine = 0, fontSize = 20;

    public void initialize(URL location, ResourceBundle resources) {
        txtArea.setEditable(false);
        //txtArea.appe
        txtArea.appendText("Moses supposes his toeses are roses, but Moses supposes erroneously. Moses he knowses his toeses aren't roses as Moses supposes his toeses to be." +
                "Moses supposes his toeses are roses, but Moses supposes erroneously. Moses he knowses his toeses aren't roses as Moses supposes his toeses to be.");
        //txtArea.setCursor(Cursor.TEXT);
        txtArea.moveSelectedText(10);
        txtArea.setWrapText(true);
        //txtArea.setConte;

        txtArea.setStyle("-fx-font-size: " + fontSize);
        txtArea.setShowCaret(Caret.CaretVisibility.ON);
    }
    @FXML
    protected void testingButton(){
        currentWord = 0;
    }

    @FXML
    protected void controlTyped(){
        String toWrite;
        toWrite = txtArea.getText().substring(currentWord);


        //txtArea.line;

        //txtArea.prevLine(NavigationActions.SelectionPolicy.ADJUST);
       // txtArea.getCurrentLineStartInParargraph();
        //txtArea.showParagraphAtCenter(currentWord);

        txtArea.scrollYToPixel((txtArea.lookup(".text").getBoundsInLocal().getHeight()/txtArea.getParagraphLinesCount(0))
                * txtArea.lineIndex(0, currentWord + txtField.getText().length()));

       /* if(lastLine != txtArea.lineIndex(0, currentWord)){
            //txtArea.scrollYBy(10 * txtArea.lineIndex(0, currentWord));
            txtArea.scrollYToPixel(30 * txtArea.lineIndex(0, currentWord));
            lastLine = txtArea.lineIndex(0, currentWord);
        }*/
        //txtArea.wrapTextProperty().set(true);
        if(txtField.getText().length() > 0){
            //System.out.println(txtArea.lineIndex(0, currentWord) + " " + new Text(txtArea.getText()).getLayoutBounds().getHeight());

            /*Text t = new Text(txtArea.getText());
            t.setStyle("-fx-font-size: " + fontSize);

            Font f = new Font(fontSize);

            System.out.println(txtArea.lineIndex(0, currentWord) + " - " + txtArea.lookup(".text").
                    getBoundsInLocal().getHeight()/txtArea.getParagraphLinesCount(0));*/


            if(txtField.getText().equals(toWrite.substring(0, txtField.getText().length()))){
                Border b = new Border(new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
                txtField.setBorder(b);

                txtArea.displaceCaret(txtField.getText().length() + currentWord);

               /* if(currentWord > txtArea.getCurrentLineEndInParargraph()){
                    txtArea.scrollYBy(5);
                }*/

                if(Character.isWhitespace(txtField.getText().charAt(txtField.getText().length() - 1))){
                    currentWord += txtField.getText().length();
                    //txtArea.scrollXBy(9 * txtField.getText().length());
                    //txtArea.addCaret(new CaretSelectionBind<>());
                    txtField.setText("");
                    //txtField.getAlignment()
                }else{
                    /*Text t = new Text();
                    t.setStyle("-fx-fill: #4F8A10;-fx-font-weight:bold;");
                    t.setText(txtArea.getText());*/
                }
            }else{
                Border b = new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT));
                txtField.setBorder(b);
                //txtArea.setStyle(1, 2, "");
                //txtArea.getCaretBounds().get().getHeight();

            }
        }
    }

   /* private double computeTextWidth(Font font, String text, double wrappingWidth) {
        Text helper = new Text();
        helper.setFont(font);
        helper.setText(text);
        // Note that the wrapping width needs to be set to zero before
        // getting the text's real preferred width.
        helper.setWrappingWidth(0);
        helper.setLineSpacing(0);
        double w = Math.min(helper.prefHeight(-1), wrappingWidth);
        helper.setWrappingWidth((int)Math.ceil(w));
        double textWidth = Math.ceil(helper.getLayoutBounds().getHeight());
        return textWidth;
    }*/
}
