package org.example.Hibernate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AppFx.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("RealtorApp");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/house.png")));

        // Передача stage до контроллеру
        ((MainWindowController) loader.getController()).init(stage);

        stage.show();
    }

    // Метод запуску додатку
    public void run() {
        launch();
    }
}
