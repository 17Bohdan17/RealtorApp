/*
 * StageManager
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, що надає методи для налаштування дій з вікнами JavaFX.
 *              Дозволяє налаштовувати можливість перетягування вікна, кнопки закриття та кнопки мінімізації.
 */


package RealtorApp.util;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class StageManager {
    private double x, y;

    /**
     * Налаштовує можливість перетягування вікна за панеллю заголовка.
     *
     * @param stage      вікно, яке можна перетягувати
     * @param titlePane  панель заголовка вікна
     */
    public void setUpDraggableStage(Stage stage, Pane titlePane) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }

    /**
     * Налаштовує кнопку закриття вікна.
     *
     * @param stage    вікно, яке можна закрити
     * @param btnClose кнопка закриття
     */
    public void setUpCloseButton(Stage stage, ImageView btnClose) {
        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
    }

    /**
     * Налаштовує кнопку мінімізації вікна.
     *
     * @param stage       вікно, яке можна мінімізувати
     * @param btnMinimize кнопка мінімізації
     */
    public void setUpMinimizeButton(Stage stage, ImageView btnMinimize) {
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }
}
