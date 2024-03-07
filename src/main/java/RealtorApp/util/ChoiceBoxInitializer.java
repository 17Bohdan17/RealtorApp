/*
 * ChoiceBoxInitializer
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Клас, що надає методи для ініціалізації ChoiceBox у JavaFX.
 *              Дозволяє додавати елементи до ChoiceBox та встановлювати відомості для підказок.
 */


package RealtorApp.util;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;

import java.util.List;

public class ChoiceBoxInitializer {

    /**
     * Ініціалізує ChoiceBox, додає до нього елементи та встановлює підказку.
     *
     * @param choiceBox    ChoiceBox, який ініціалізується
     * @param items        список елементів, які додаються до ChoiceBox
     * @param tooltipText  текст підказки, яка встановлюється для ChoiceBox
     */
    public static <T> void initializeChoiceBox(ChoiceBox<T> choiceBox, List<T> items, String tooltipText) {
        try {
            choiceBox.getItems().addAll(items); // Додавання елементів до ChoiceBox
        } catch (NullPointerException ignored) {
            // Ігнорування NullPointerException, якщо choiceBox або items є null
        }

        // Створення підказки для ChoiceBox
        Tooltip tooltip = new Tooltip(tooltipText);
        // Встановлення підказки для ChoiceBox
        Tooltip.install(choiceBox, tooltip);
    }
}

