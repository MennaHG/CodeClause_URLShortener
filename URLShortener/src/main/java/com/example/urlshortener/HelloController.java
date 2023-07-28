package com.example.urlshortener;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.*;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.util.Duration;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static javafx.scene.input.Clipboard.getSystemClipboard;

public class HelloController {
    @FXML
    BorderPane bp;
    @FXML
    TextField orgUrl;
    @FXML
    TextField opUrl;


    public void shorten() throws IOException {
        URL url = new URL("http://tinyurl.com/api-create.php?url=" + orgUrl.getText());
        HttpURLConnection connc = (HttpURLConnection) url.openConnection();
        connc.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connc.getInputStream()));
        String shortUrl = in.readLine();
        in.close();
        opUrl.setText(shortUrl);
    }

    public void copy(){
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(opUrl.getText()),null);
        Label label = new Label("\t Link has been copied to clipboard \t");
        //JavaNetUriAccess PopupBuilder = null;
        //Popup popup = PopupBuilder.create().content(label).width(50).height(100).autoFix(true).build();;
        Popup popup = new Popup();

        label.setStyle("-fx-font-size:36px; -fx-background-color:#2a2b2e; -fx-background-radius: 5px; -fx-color:white;");
       // label.setMinWidth(80);
        label.setTextFill(Color.WHITE);
        ///label.setMinHeight(50);
        popup.getContent().add(label);
        popup.setAutoHide(true);

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(e -> popup.hide());
        popup.show( bp.getScene().getWindow(),420,700);
        delay.play();
        //popup.hide();
    }
}