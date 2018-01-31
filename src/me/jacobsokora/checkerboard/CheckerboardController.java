/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jacobsokora.checkerboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author jacob
 */
public class CheckerboardController implements Initializable {
    
    @FXML
    private VBox vbox;
    
    @FXML
    private MenuBar menuBar;
    
    private Checkerboard currentBoard;
    
    @FXML
    public void on16x16Selected(ActionEvent event) {
        resizeBoard(16);
    }
    
    @FXML
    public void on10x10Selected(ActionEvent event) {
        resizeBoard(10);
    }
    
    @FXML
    public void on8x8Selected(ActionEvent event) {
        resizeBoard(8);
    }
    
    @FXML
    public void on3x3Selected(ActionEvent event) {
        resizeBoard(3);
    }
    
    @FXML
    public void onDefaultColorSelected(ActionEvent event) {
        changeColor(Color.RED, Color.BLACK);
    }
    
    @FXML
    public void onBlueColorSelected(ActionEvent event) {
        changeColor(Color.SKYBLUE, Color.BLUE);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    private void changeColor(Color lightColor, Color darkColor) {
        Checkerboard newBoard = new Checkerboard(currentBoard.getNumRows(), currentBoard.getNumCols(), vbox.getWidth(), vbox.getHeight() - menuBar.getHeight(), lightColor, darkColor);
        vbox.getChildren().remove(currentBoard.getBoard());
        vbox.getChildren().add(newBoard.build());
        currentBoard = newBoard;
    }
    
    private void resizeBoard(int rows) {
        Checkerboard newBoard = new Checkerboard(rows, rows, vbox.getWidth(), vbox.getHeight() - menuBar.getHeight(), currentBoard.getLightColor(), currentBoard.getDarkColor());
        vbox.getChildren().remove(currentBoard.getBoard());
        vbox.getChildren().add(newBoard.build());
        currentBoard = newBoard;
    }
    
    public void start() {
        currentBoard = new Checkerboard(8, 8, vbox.getWidth(), vbox.getHeight() - menuBar.getHeight());
        vbox.getChildren().add(currentBoard.build());
        ChangeListener<Number> sizeChangeListener = (a, b, c) -> {
            Checkerboard newBoard = new Checkerboard(currentBoard.getNumRows(), currentBoard.getNumCols(), vbox.getWidth(), vbox.getHeight() - menuBar.getHeight(), currentBoard.getLightColor(), currentBoard.getDarkColor());
            vbox.getChildren().remove(currentBoard.getBoard());
            vbox.getChildren().add(newBoard.build());
            currentBoard = newBoard;
        };
        vbox.getScene().widthProperty().addListener(sizeChangeListener);
        vbox.getScene().heightProperty().addListener(sizeChangeListener);
    }
}
