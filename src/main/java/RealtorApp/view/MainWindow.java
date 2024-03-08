/*
 * MainWindow
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Головне вікно додатка, яке відображає основний інтерфейс користувача.
 *              Використовує FXML для відображення і контролера для управління подіями.
 */

package RealtorApp.view;

import RealtorApp.controller.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow extends Application {
    // Шлях до файлу FXML головного вікна
    private static final String path = "/fxml/MainWindow.fxml";

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = loader.load();

        // Налаштування сцени з прозорим фоном
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        // Налаштування стилю вікна на прозорий
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("images/main_icon.png"));



        // Ініціалізація контролера та передача посилання на вікно
        ((MainWindowController) loader.getController()).init(stage);

        // Показ вікна
        stage.show();
    }

    // Метод для запуску головного вікна
    public void run() {
        launch();
    }
}
