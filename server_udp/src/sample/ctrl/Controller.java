package sample.ctrl;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import sample.wrk.ServerListener;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.net.SocketException;

public class Controller {

    public Button startBtn;
    public ImageView imgVoew;
    public Button shineBtn;
    private ServerListener server;

    public Controller() {
        try {
            server = new ServerListener(this);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void quitter() {
        try {
            server.setRunning(false);
            server.join();
            server = null;
            System.gc();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.exit();
    }

    public void startServer(ActionEvent actionEvent) {
        server.start();
    }

    public void killThemAll(ActionEvent actionEvent) {
        quitter();
    }

    public void showImage(BufferedImage image) {
        WritableImage wr;
        wr = new WritableImage(image.getWidth(), image.getHeight());
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                pw.setArgb(x, y, image.getRGB(x, y));
            }
        }
        imgVoew.setImage(wr);
    }


}
