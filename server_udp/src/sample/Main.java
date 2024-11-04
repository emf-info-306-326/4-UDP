package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.ctrl.Controller;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ctrl/sample.fxml"));
        Parent mainView = loader.load();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(mainView));
        primaryStage.show();

        Controller c = loader.getController();

        primaryStage.setOnCloseRequest(e -> {

            // pour éviter que la fenêtre principale ne se ferme dans tous les cas
            e.consume();

            // lors d'une demande de sortie, laisser le travail à faire au contrôleur
            c.quitter();

        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
