/*
 * Main
 *
 * Версія: 1.0
 * Автор: Чирков Богдан
 *
 * Опис: Точка входу до програми RealtorApp. Створює екземпляр контролера MainWindow
 *       та запускає програму.
 */
package RealtorApp.controller;

import RealtorApp.view.MainWindow;

public class Main {
    public static void main(String[] args) {
        MainWindow controller = new MainWindow();
        controller.run();
    }
}
