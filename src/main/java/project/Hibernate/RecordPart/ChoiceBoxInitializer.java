package project.Hibernate.RecordPart;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;

import java.util.List;

    public class ChoiceBoxInitializer {

        public static <T> void initializeChoiceBox(ChoiceBox<T> choiceBox, List<T> items, String tooltipText) {
            try {
                choiceBox.getItems().addAll(items);
            } catch (NullPointerException ignored) {
            }

            Tooltip tooltip = new Tooltip(tooltipText);
            Tooltip.install(choiceBox, tooltip);
        }
}
