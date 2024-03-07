/*
 * RecordWindow
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас для відображення додаткового вікна додавання або оновлення запису.
 *              Залежно від статусу запису обирається відповідний FXML-файл.
 */

package RealtorApp.view;

import RealtorApp.controller.SecondaryWindowController;
import RealtorApp.enums.RecordStatusEnum;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class RecordWindow {

    FXMLLoader loader;
    public RecordStatusEnum recordStatusEnum;
    public int addRecordStatus;

    // Метод для ініціалізації вікна
    public void init() throws IOException {
        switch (recordStatusEnum){
            // Вибір відповідного FXML-файлу залежно від статусу запису
            case ADD_OBJECT:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/AddObject.fxml"));
                break;

            case ADD_AGREEMENT:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/AddAgreement.fxml"));
                break;

            case ADD_CLIENT:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/AddClient.fxml"));
                break;

            case ADD_CONSULTATION:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/AddConsultation.fxml"));
                break;

            case ADD_FACILITY:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/AddFacility.fxml"));
                break;

            case ADD_REQUIREMENT:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/AddRequirement.fxml"));
                break;

            case UPDATE_OBJECT:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/UpdateObject.fxml"));
                break;

            case UPDATE_AGREEMENT:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/UpdateAgreement.fxml"));
                break;

            case UPDATE_CLIENT:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/UpdateClient.fxml"));
                break;

            case UPDATE_CONSULTATION:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/UpdateConsultation.fxml"));
                break;

            case UPDATE_FACILITY:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/UpdateFacility.fxml"));
                break;

            case UPDATE_REQUIREMENT:
                loader = new FXMLLoader(getClass()
                        .getResource("/fxml/UpdateRequirement.fxml"));
                break;

            default:
                throw new IllegalStateException("Unexpected value: " +
                        recordStatusEnum);
        }

        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        // Налаштування сцени з прозорим фоном
        scene.setFill(Color.TRANSPARENT);
        // Налаштування стилю вікна на прозорий
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);

        // Ініціалізація контролера вікна та передача посилання на вікно
        ((SecondaryWindowController)loader.getController()).init(stage);
        // Показ вікна
        stage.show();
    }
}
