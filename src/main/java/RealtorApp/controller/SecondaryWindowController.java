/*
 * SecondaryWindowController
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Контролер для додатковиго вікна програми RealtorApp. Відповідає за обробку подій та
 *              ініціалізацію відображення додаткових таблиць об'єктів нерухомості, угод, клієнтів, консультацій,
 *              умов та зручностей, а також редагування та додавання записів.
 */

package RealtorApp.controller;

import RealtorApp.model.entity.Object;
import RealtorApp.model.entity.*;
import RealtorApp.model.modelView.*;
import RealtorApp.util.ChoiceBoxInitializer;
import RealtorApp.util.StageManager;
import RealtorApp.util.hibernate.HibernateUtil;
import javafx.animation.PauseTransition;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;


public class SecondaryWindowController {

    Session session;
    Agreement agreement = new Agreement();
    Object object = new Object();
    Client client = new Client();
    Consultation consultation = new Consultation();
    Facilities facility = new Facilities();
    Requirement requirement = new Requirement();
    MainWindowController mainWindowController = new MainWindowController();
    StageManager stageManager = new StageManager();

    @FXML
    private Pane addRecordPane;
    @FXML
    private ImageView addRecordWindowCloseBtn;
    @FXML
    private Label addRecordDoneLabel;
    @FXML
    private Label warningLabel;
    @FXML
    private TextField idAgreementObjectTextField;
    @FXML
    private TextField idAgreementClientTextField;
    @FXML
    private TextField idAgreementPriceTextField;
    @FXML
    private DatePicker addAgreementRecordDatePicker;
    @FXML
    private ChoiceBox agreementStatusChoiceBox;
    @FXML
    private TextField objectStreetTextField;
    @FXML
    private TextField objectNumberTextField;
    @FXML
    private TextField objectAreaTextField;
    @FXML
    private TextField objectPriceTextField;
    @FXML
    private TextField objectRoomCountTextField;
    @FXML
    private ChoiceBox objectStatusChoiceBox;
    @FXML
    private TextField clientFirstNameTextField;
    @FXML
    private TextField clientSecondNameTextField;
    @FXML
    private TextField clientNumberTextField;
    @FXML
    private TextField clientIdRequirementTextField;
    @FXML
    private TextField consultationIdClientTextField;
    @FXML
    private DatePicker consultationDatePicker;
    @FXML
    private ChoiceBox consultationStatusChoiceBox;
    @FXML
    private TextField facilityIdObjectTextField;
    @FXML
    private TextField facilityMinBedroomsTextField;
    @FXML
    private TextField facilityMinBathroomsTextField;
    @FXML
    private ChoiceBox<Boolean> facilityGarageChoiceBox;
    @FXML
    private ChoiceBox<Boolean> facilityGardenChoiceBox;
    @FXML
    private ChoiceBox<Boolean> facilityPoolChoiceBox;
    @FXML
    private TextField requirementStreetTextField;
    @FXML
    private TextField requirementMaxPriceTextField;
    @FXML
    private TextField requirementMinAreaTextField;
    @FXML
    private TextField requirementMinBedroomsTextField;
    @FXML
    private TextField requirementMinBathroomsTextField;
    @FXML
    private ChoiceBox<Boolean> requirementGarageChoiceBox;
    @FXML
    private ChoiceBox<Boolean> requirementGardenChoiceBox;
    @FXML
    private ChoiceBox<Boolean> requirementPoolChoiceBox;
    @FXML
    private TableView agreementTableView;
    @FXML
    private TableView clientTableView;
    @FXML
    private TableView facilityTableView;
    @FXML
    private TableView consultationTableView;
    @FXML
    private TableView updateTableView;


    /**
     * Ініціалізує вікно додавання запису.
     * Встановлює функціональність перетягування вікна та закриття вікна.
     * Приховує мітку для підтвердження додавання запису та мітку попередження.
     *
     * @param stage Сцена, яку потрібно ініціалізувати
     */
    public void init(Stage stage) {
        stageManager.setUpDraggableStage(stage, addRecordPane);
        stageManager.setUpCloseButton(stage, addRecordWindowCloseBtn);
        initializeChoiceBox();

        if (addRecordDoneLabel != null) {
            addRecordDoneLabel.setVisible(false);
        }

        if (warningLabel != null) {
            warningLabel.setVisible(false);
        }
    }

    /**
     * Обробляє натискання кнопки для додавання запису про об'єкт.
     * Виконує перевірку та збереження введених даних про об'єкт у базі даних.
     * При успішному збереженні відображає мітку підтвердження додавання запису,
     * інакше відображає мітку попередження.
     */
    public void clickAddObjectRecordButton() {
        try {
            // Оголошення змінних для збереження введених даних про об'єкт
            String objectStreet;
            int objectNumber;
            double objectArea;
            int objectPrice;
            String objectStatus;
            short objectRoomCount;

            // Початок транзакції для збереження даних
            startTransaction();

            // Отримання введених даних з текстових полів та вибраних значень
            objectStreet = objectStreetTextField.getText();
            objectNumber = Integer.parseInt(objectNumberTextField.getText());
            objectArea = Double.parseDouble(objectAreaTextField.getText());
            objectPrice = Integer.parseInt(objectPriceTextField.getText());
            objectStatus = (String) objectStatusChoiceBox.getValue();
            objectRoomCount =
                    Short.parseShort(objectRoomCountTextField.getText());

            object = new Object();

            // Збереження введених даних у об'єкті класу Object
            object.setStreet(objectStreet);
            object.setStreetNum(objectNumber);
            object.setArea(objectArea);
            object.setPrice(BigDecimal.valueOf(objectPrice));
            object.setStatus(objectStatus);
            object.setRoomCount(objectRoomCount);

            // Збереження об'єкта у базі даних та завершення транзакції
            session.persist(object);
            session.getTransaction().commit();

            // Відображення мітки підтвердження додавання запису
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);

            // Очищення полів введення даних
            objectStreetTextField.clear();
            objectNumberTextField.clear();
            objectAreaTextField.clear();
            objectPriceTextField.clear();
            objectStatusChoiceBox.setValue(null);
            objectRoomCountTextField.clear();
        } catch (NullPointerException | NumberFormatException e) {
            // Відображення мітки попередження у разі помилки введення даних
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }

    /**
     * Обробляє натискання кнопки для відкриття таблиці об'єктів у формі угоди.
     */
    public void clickOpenObjectTableInAgreementButton() {
        agreementTableView.getColumns().clear();
        mainWindowController.initObjectTable(agreementTableView);
        initObjectTableInAgreementSelectId();
    }

    /**
     * Ініціалізує обробник подвійного натискання.
     */
    public void initObjectTableInAgreementSelectId() {
        // Подвійне натискання на таблицю
        agreementTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                // Отримання об'єкта
                ObjectViewModel selectedObject =
                        (ObjectViewModel) agreementTableView
                                .getSelectionModel().getSelectedItem();
                // Отримання ідентифікатора
                SimpleIntegerProperty objectId = selectedObject.getObjectId();
                String parseObjectId = String.valueOf(objectId.get());
                idAgreementObjectTextField.setText(parseObjectId);
            }
        });
    }

    /**
     * Обробляє натискання кнопки для додавання запису про угоду.
     * Виконує перевірку та збереження введених даних про угоду у базі даних.
     * При успішному збереженні відображає мітку підтвердження додавання запису,
     * інакше відображає мітку попередження.
     */
    public void clickAddAgreementRecordButton() {
        String objectIdText;
        String clientIdText;
        Date agreementDate;
        String agreementPriceText;
        String agreementStatus;

        try {
            // Отримання введених даних з текстових полів та вибраних значень
            objectIdText = idAgreementObjectTextField.getText().trim();
            clientIdText = idAgreementClientTextField.getText().trim();
            agreementDate = Date.valueOf(addAgreementRecordDatePicker.getValue());
            agreementPriceText = idAgreementPriceTextField.getText().trim();
            agreementStatus = (String) agreementStatusChoiceBox.getValue();

            // Перетворення рядкових значень у числа
            int objectId = Integer.parseInt(objectIdText);
            int clientId = Integer.parseInt(clientIdText);
            int agreementPrice = Integer.parseInt(agreementPriceText);

            // Початок транзакції для збереження даних
            startTransaction();

            agreement = new Agreement();
            // Збереження введених даних у об'єкті класу Agreement
            agreement.setObjectId(objectId);
            agreement.setClientId(clientId);
            agreement.setAgreementDate(agreementDate);
            agreement.setAgreementPrice(agreementPrice);
            agreement.setAgreementStatus(agreementStatus);

            // Збереження об'єкта у базі даних та завершення транзакції
            session.persist(agreement);
            session.getTransaction().commit();

            // Відображення мітки підтвердження додавання запису
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);

            // Очищення полів введення даних
            idAgreementObjectTextField.clear();
            idAgreementClientTextField.clear();
            addAgreementRecordDatePicker.setValue(null);
            idAgreementPriceTextField.clear();
            agreementStatusChoiceBox.setValue(null);

        } catch (NullPointerException e) {
            // Відображення мітки попередження у разі помилки введення даних
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }

    /**
     * Відкриває таблицю клієнтів у контексті угоди.
     */
    public void clickOpenClientTableInAgreementButton() {
        // Очищення колонок таблиці
        agreementTableView.getColumns().clear();
        // Ініціалізація таблиці клієнтів у контексті угоди
        mainWindowController.initClientTable(agreementTableView);
        // Ініціалізація обробника вибору ідентифікатора клієнта у контексті угоди
        initClientTableInAgreementSelectId();
    }

    /**
     * Ініціалізує обробник вибору ідентифікатора клієнта у контексті угоди.
     */
    public void initClientTableInAgreementSelectId() {
        // Встановлення обробника подвійного натискання на таблицю
        agreementTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Перевірка кількості натискань
                // Отримання вибраного клієнта
                ClientViewModel selectedClient = (ClientViewModel) agreementTableView.getSelectionModel().getSelectedItem();
                // Отримання ідентифікатора клієнта
                SimpleIntegerProperty clientId = selectedClient.getClientId();
                // Перетворення ідентифікатора в рядок
                String parseClientId = String.valueOf(clientId.get());
                // Встановлення ідентифікатора клієнта в відповідне поле введення
                idAgreementClientTextField.setText(parseClientId);
            }
        });
    }

    /**
     * Додає запис клієнта.
     */
    public void clickAddClientRecordButton() {
        String firstName;
        String secondName;
        long number;
        int idRequirement;

        try {
            startTransaction();
            // Отримання даних з полів введення
            firstName = clientFirstNameTextField.getText();
            secondName = clientSecondNameTextField.getText();
            number = Long.parseLong(clientNumberTextField.getText());
            idRequirement =
                    Integer.parseInt(clientIdRequirementTextField.getText());

            client = new Client();

            // Заповнення об'єкта клієнта
            client.setFirstName(firstName);
            client.setSecondName(secondName);
            client.setContactNum(number);
            client.setReqId(idRequirement);

            // Збереження клієнта в базі даних
            session.persist(client);
            session.getTransaction().commit();


            // Показ успішного повідомлення
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);

            // Очищення полів введення
            clientFirstNameTextField.clear();
            clientSecondNameTextField.clear();
            clientNumberTextField.clear();
            clientIdRequirementTextField.clear();
        } catch (NullPointerException | NumberFormatException e) {
            // Показ помилки у випадку недійсних або відсутніх даних
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }


    /**
     * Відкриває таблицю вимог у контексті клієнта.
     */
    public void clickOpenRequirementInClientTableButton() {
        // Очищення колонок таблиці
        clientTableView.getColumns().clear();
        // Ініціалізація таблиці вимог у контексті клієнта
        mainWindowController.initRequirementTable(clientTableView);
        // Ініціалізація обробника вибору ідентифікатора вимоги у контексті клієнта
        initRequirementTableInClientSelectId();
    }

    /**
     * Ініціалізує обробник вибору ідентифікатора вимоги у контексті клієнта.
     */
    public void initRequirementTableInClientSelectId() {
        // Встановлення обробника подвійного натискання на таблицю
        clientTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Перевірка кількості натискань
                // Отримання вибраної вимоги
                RequirementViewModel selectedRequirement =
                        (RequirementViewModel) clientTableView
                                .getSelectionModel().getSelectedItem();
                // Отримання ідентифікатора вимоги
                SimpleIntegerProperty reqId = selectedRequirement.getReqId();
                // Перетворення ідентифікатора в рядок
                String parseReqId = String.valueOf(reqId.get());
                // Встановлення ідентифікатора вимоги в відповідне поле введення
                clientIdRequirementTextField.setText(parseReqId);
            }
        });
    }

    /**
     * Додає запис консультації.
     */
    public void clickAddConsultationRecordButton() {
        int clientId;
        Date consultationDate;
        String consultationStatus;

        try {
            // Початок транзакції
            startTransaction();

            // Отримання та парсинг даних про клієнта, дати та статусу консультації
            clientId = Integer.parseInt(consultationIdClientTextField.getText());
            consultationDate = Date.valueOf(consultationDatePicker.getValue());
            consultationStatus = (String) consultationStatusChoiceBox.getValue();

            consultation = new Consultation();

            // Встановлення отриманих даних для запису консультації
            consultation.setClientId(clientId);
            consultation.setConsDate(consultationDate);
            consultation.setConsStatus(consultationStatus);

            // Збереження запису консультації у базі даних
            session.persist(consultation);
            session.getTransaction().commit();

            // Відображення мітки успішного додавання запису
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);

            // Очищення полів введення
            consultationIdClientTextField.clear();
            consultationDatePicker.setValue(null);
            consultationStatusChoiceBox.setValue(null);
        } catch (NullPointerException | NumberFormatException e) {
            // Відображення мітки з попередженням
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }

    /**
     * Відкриває таблицю клієнтів у контексті консультацій.
     */
    public void clickOpenClientInConsultationTableButton() {
        // Очищення колонок таблиці
        consultationTableView.getColumns().clear();
        // Ініціалізація таблиці клієнтів у контексті консультацій
        mainWindowController.initClientTable(consultationTableView);
        // Ініціалізація обробника вибору ідентифікатора клієнта у контексті консультацій
        initClientTableInConsultationSelectId();
    }

    /**
     * Ініціалізує обробник вибору ідентифікатора клієнта у контексті консультацій.
     */
    public void initClientTableInConsultationSelectId() {
        // Встановлення обробника подвійного натискання на таблицю
        consultationTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Перевірка кількості натискань
                // Отримання вибраного клієнта
                ClientViewModel selectedClient =
                        (ClientViewModel) consultationTableView
                                .getSelectionModel().getSelectedItem();
                // Отримання ідентифікатора клієнта
                SimpleIntegerProperty clientId = selectedClient.getClientId();
                // Перетворення ідентифікатора в рядок
                String parseClientId = String.valueOf(clientId.get());
                // Встановлення ідентифікатора клієнта в відповідне поле введення
                consultationIdClientTextField.setText(parseClientId);
            }
        });
    }


    /**
     * Додає запис про об'єкт нерухомості.
     */
    public void clickAddFacilityRecordButton() {
        int objectId;
        short minBedrooms;
        short minBathrooms;
        boolean garage;
        boolean garden;
        boolean pool;

        try {
            // Початок транзакції
            startTransaction();

            // Отримання та парсинг даних про об'єкт нерухомості
            objectId = Integer.parseInt(facilityIdObjectTextField.getText());
            minBedrooms =
                    Short.parseShort(facilityMinBedroomsTextField.getText());
            minBathrooms =
                    Short.parseShort(facilityMinBathroomsTextField.getText());
            garage = facilityGarageChoiceBox.getValue();
            garden = facilityGardenChoiceBox.getValue();
            pool = facilityPoolChoiceBox.getValue();

            facility = new Facilities();

            // Встановлення отриманих даних для запису про об'єкт нерухомості
            facility.setObjectReferenceId(objectId);
            facility.setMinBedrooms(minBedrooms);
            facility.setMinBathrooms(minBathrooms);
            facility.setGarage(garage);
            facility.setGarden(garden);
            facility.setPool(pool);

            // Збереження запису про об'єкт нерухомості у базі даних
            session.persist(facility);
            session.getTransaction().commit();


            // Відображення мітки з попередженням
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);

            // Очищення полів введення
            facilityIdObjectTextField.clear();
            facilityMinBedroomsTextField.clear();
            facilityMinBathroomsTextField.clear();
            facilityGarageChoiceBox.setValue(null);
            facilityGardenChoiceBox.setValue(null);
            facilityPoolChoiceBox.setValue(null);

        } catch (NullPointerException | NumberFormatException e) {
            // Відображення мітки з попередженням
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }

    /**
     * Відкриває таблицю об'єктів нерухомості.
     */
    public void clickOpenObjectInFacilityTableButton() {
        // Очищення колонок таблиці
        facilityTableView.getColumns().clear();
        // Ініціалізація таблиці об'єктів нерухомості
        mainWindowController.initFacilityTable(facilityTableView);
        // Ініціалізація обробника вибору ідентифікатора об'єкта нерухомості
        initObjectTableInFacilitySelectId();
    }

    /**
     * Ініціалізує обробник вибору ідентифікатора об'єкта нерухомості.
     */
    public void initObjectTableInFacilitySelectId() {
        // Встановлення обробника подвійного натискання на таблицю
        facilityTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Перевірка кількості натискань
                // Отримання вибраного об'єкта нерухомості
                FacilityViewModel selectedObject =
                        (FacilityViewModel) facilityTableView
                                .getSelectionModel().getSelectedItem();
                // Отримання ідентифікатора об'єкта нерухомості
                SimpleIntegerProperty objectId =
                        selectedObject.getObjectReferenceId();
                // Перетворення ідентифікатора в рядок
                String parseObjectId = String.valueOf(objectId.get());
                // Встановлення ідентифікатора об'єкта нерухомості в відповідне поле введення
                facilityIdObjectTextField.setText(parseObjectId);
            }
        });
    }


    /**
     * Додає запис про вимогу.
     */
    public void clickAddRequirementRecordButton() {
        String street;
        int maxPrice;
        double minArea;
        short minBedrooms;
        short minBathrooms;
        boolean garage;
        boolean garden;
        boolean pool;

        try {
            // Початок транзакції
            startTransaction();

            // Отримання та парсинг даних про вимогу
            street = requirementStreetTextField.getText();
            maxPrice =
                    Integer.parseInt(requirementMaxPriceTextField.getText());
            minArea =
                    Double.parseDouble(requirementMinAreaTextField.getText());
            minBedrooms =
                    Short.parseShort(requirementMinBedroomsTextField.getText());
            minBathrooms =
                    Short.parseShort(requirementMinBathroomsTextField.getText());
            garage = requirementGarageChoiceBox.getValue();
            garden = requirementGardenChoiceBox.getValue();
            pool = requirementPoolChoiceBox.getValue();

            requirement = new Requirement();

            // Встановлення отриманих даних для запису про вимогу
            requirement.setReqStreet(street);
            requirement.setReqMaxPrice(maxPrice);
            requirement.setReqMinimalArea(minArea);
            requirement.setReqMinBedrooms(minBedrooms);
            requirement.setReqMinBathrooms(minBathrooms);
            requirement.setReqGarage(garage);
            requirement.setReqGarden(garden);
            requirement.setReqPool(pool);

            // Збереження запису про вимогу у базі даних
            session.persist(requirement);
            session.getTransaction().commit();

            // Відображення мітки з попередженням
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);

            // Очищення полів введення
            requirementStreetTextField.clear();
            requirementMaxPriceTextField.clear();
            requirementMinAreaTextField.clear();
            requirementMinBedroomsTextField.clear();
            requirementMinBathroomsTextField.clear();
            requirementGarageChoiceBox.setValue(null);
            requirementGardenChoiceBox.setValue(null);
            requirementPoolChoiceBox.setValue(null);
        } catch (NullPointerException | NumberFormatException e) {
            // Відображення мітки з попередженням
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }

    /**
     * Закриває мітку після певного періоду часу.
     *
     * @param label Мітка, яку треба закрити.
     */
    private void labelClose(Label label) {
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3));

        visiblePause.setOnFinished(event ->
                label.setVisible(false));
        visiblePause.play();
    }

    /**
     * Розпочинає транзакцію для роботи з базою даних.
     */
    private void startTransaction() {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
    }

    /**
     * Ініціалізує вміст випадаючих списків з вказаними значеннями і поясненнями.
     */
    private void initializeChoiceBox() {
        ChoiceBoxInitializer.initializeChoiceBox(objectStatusChoiceBox,
                Arrays.asList("FOR_SALE", "AVAILABLE", "UNDER_CONTRACT"),
                """
                        1. "FOR_SALE" - об'єкт доступний тільки для продажу.
                        2. "AVAILABLE" - об'єкт доступний для продажу або оренди.
                        3. "UNDER_CONTRACT" - об'єкт вже під контрактом.""");

        ChoiceBoxInitializer.initializeChoiceBox(agreementStatusChoiceBox,
                Arrays.asList("PENDING", "COMPLETED", "ACTIVE"),
                """
                        1. "PENDING" - в очікуванні
                        2. "COMPLETED" - завершено
                        3. "ACTIVE" - активний""");

        ChoiceBoxInitializer.initializeChoiceBox(consultationStatusChoiceBox,
                Arrays.asList("COMPLETED", "SCHEDULED"),
                "1. COMPLETED - завершено,\n" +
                        "2. SCHEDULED - заплановано");

        ChoiceBoxInitializer.initializeChoiceBox(facilityGarageChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");

        ChoiceBoxInitializer.initializeChoiceBox(facilityGardenChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");

        ChoiceBoxInitializer.initializeChoiceBox(facilityPoolChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");

        ChoiceBoxInitializer.initializeChoiceBox(requirementGarageChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");

        ChoiceBoxInitializer.initializeChoiceBox(requirementGardenChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");

        ChoiceBoxInitializer.initializeChoiceBox(requirementPoolChoiceBox,
                Arrays.asList(true, false),
                "true - в наявності,\n" +
                        "false - Немає");
    }


    /**
     * Відкриває таблицю об'єктів для оновлення.
     */
    public void clickOpenObjectTableForUpdate() {
        // Очищає колонки у таблиці оновлення
        updateTableView.getColumns().clear();
        // Ініціалізує таблицю об'єктів через головний контролер
        mainWindowController.initObjectTable(updateTableView);
        // Викликає метод для обробки подвійного натискання на елемент таблиці
        clickObjectTableItem();
    }

    /**
     * Обробляє подвійне натискання на об'єкт у таблиці для оновлення.
     */
    public void clickObjectTableItem() {
        // Встановлює обробник подвійного натискання на таблицю
        updateTableView.setOnMouseClicked(event -> {
            // Перевіряє, чи подвійний клік
            if (event.getClickCount() == 2) {
                // Отримує вибраний об'єкт з таблиці
                ObjectViewModel selectedObject =
                        (ObjectViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                // Зберігає оригінальний об'єкт для оновлення
                object = selectedObject.getOriginalObject();

                // Отримує властивості об'єкта для відображення у відповідних полях
                SimpleStringProperty street =
                        selectedObject.getStreet();
                SimpleIntegerProperty streetNum =
                        selectedObject.getStreet_num();
                SimpleDoubleProperty area =
                        selectedObject.getArea();
                SimpleObjectProperty<BigDecimal> price =
                        selectedObject.getPrice();
                SimpleStringProperty status =
                        selectedObject.getStatus();
                SimpleIntegerProperty roomCount =
                        selectedObject.getRoomCount();

                // Встановлює значення відповідних полів на формі
                objectStreetTextField.setText(String.valueOf(street.get()));
                objectNumberTextField.setText(String.valueOf(streetNum.get()));
                objectAreaTextField.setText(String.valueOf(area.get()));
                objectPriceTextField.setText(String.valueOf(price.get()));
                objectStatusChoiceBox.setValue(status.get());
                objectRoomCountTextField.setText(
                        String.valueOf(roomCount.get()));
            }
        });
    }

    /**
     * Оновлює запис про об'єкт.
     */
    public void clickUpdateObjectRecordButton() {
        try {
            // Оновлює властивості об'єкта
            object.setStreet(objectStreetTextField.getText());
            object.setStreetNum(Integer.parseInt(objectNumberTextField.getText()));
            object.setArea(Double.parseDouble(objectAreaTextField.getText()));
            object.setPrice(BigDecimal.valueOf(Integer.parseInt(
                    objectPriceTextField.getText())));
            object.setStatus((String) objectStatusChoiceBox.getValue());
            object.setRoomCount(Short.parseShort(
                    objectRoomCountTextField.getText()));

            // Починає транзакцію та оновлює об'єкт у базі даних
            startTransaction();
            session.update(object);
            session.getTransaction().commit();
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);
        } catch (NullPointerException | NumberFormatException e) {
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }

    /**
     * Відкриває таблицю угод для оновлення.
     */
    public void clickOpenAgreementTableForUpdate() {
        // Очищає колонки у таблиці оновлення
        updateTableView.getColumns().clear();
        // Ініціалізує таблицю угод через головний контролер
        mainWindowController.initAgreementTable(updateTableView);
        // Викликає метод для обробки подвійного натискання на елемент таблиці
        clickAgreementTableItem();
    }

    /**
     * Обробляє подвійне натискання на угоду у таблиці для оновлення.
     */
    public void clickAgreementTableItem() {
        // Встановлює обробник подвійного натискання на таблицю
        updateTableView.setOnMouseClicked(event -> {
            // Перевіряє, чи подвійний клік
            if (event.getClickCount() == 2) {
                // Отримує вибрану угоду з таблиці
                AgreementViewModel selectedAgreement =
                        (AgreementViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                // Зберігає оригінальну угоду для оновлення
                agreement = selectedAgreement.getOriginalAgreement();

                // Отримує властивості угоди для відображення у відповідних полях
                SimpleIntegerProperty objectId =
                        selectedAgreement.getObjectId();
                SimpleIntegerProperty clientId =
                        selectedAgreement.getClientId();
                SimpleObjectProperty<Date> agreementDate =
                        selectedAgreement.getAgreementDate();
                SimpleIntegerProperty price =
                        selectedAgreement.getAgreementPrice();
                SimpleStringProperty status =
                        selectedAgreement.getAgreementStatus();

                // Встановлює значення відповідних полів на формі
                idAgreementObjectTextField.setText(
                        String.valueOf(objectId.get()));
                idAgreementClientTextField.setText(
                        String.valueOf(clientId.get()));
                addAgreementRecordDatePicker.setValue(
                        agreementDate.get().toLocalDate());
                idAgreementPriceTextField.setText(
                        String.valueOf(price.get()));
                agreementStatusChoiceBox.setValue(
                        status.get());
            }
        });
    }

    /**
     * Оновлює запис про угоду.
     */
    public void clickUpdateAgreementRecordButton() {
        try {
            // Оновлює властивості угоди
            agreement.setObjectId(Integer.valueOf(
                    idAgreementObjectTextField.getText()));
            agreement.setClientId(Integer.valueOf(
                    idAgreementClientTextField.getText()));
            agreement.setAgreementDate(Date.valueOf(
                    addAgreementRecordDatePicker.getValue()));
            agreement.setAgreementPrice(Integer.valueOf(
                    idAgreementPriceTextField.getText()));
            agreement.setAgreementStatus(
                    (String) agreementStatusChoiceBox.getValue());

            // Починає транзакцію та оновлює угоду у базі даних
            startTransaction();
            session.update(agreement);
            session.getTransaction().commit();
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);
        } catch (NullPointerException | NumberFormatException e) {
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }


    /**
     * Відкриває таблицю клієнтів для оновлення.
     */
    public void clickOpenClientTableForUpdate() {
        // Очищає колонки у таблиці оновлення
        updateTableView.getColumns().clear();
        // Ініціалізує таблицю клієнтів через головний контролер
        mainWindowController.initClientTable(updateTableView);
        // Викликає метод для обробки подвійного натискання на елемент таблиці
        clickClientTableItem();
    }

    /**
     * Обробляє подвійне натискання на клієнта у таблиці для оновлення.
     */
    public void clickClientTableItem() {
        // Встановлює обробник подвійного натискання на таблицю
        updateTableView.setOnMouseClicked(event -> {
            // Перевіряє, чи подвійний клік
            if (event.getClickCount() == 2) {
                // Отримує вибраного клієнта з таблиці
                ClientViewModel selectedClient =
                        (ClientViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                // Зберігає оригінального клієнта для оновлення
                client = selectedClient.getOriginalClient();

                // Отримує властивості клієнта для відображення у відповідних полях
                SimpleStringProperty name =
                        selectedClient.getFirstName();
                SimpleStringProperty surname =
                        selectedClient.getSecondName();
                SimpleLongProperty phoneNum =
                        selectedClient.getContactNum();
                SimpleIntegerProperty reqId =
                        selectedClient.getReqId();

                // Встановлює значення відповідних полів на формі
                clientFirstNameTextField.setText(name.get());
                clientSecondNameTextField.setText(surname.get());
                clientNumberTextField.setText(String.valueOf(phoneNum.get()));
                clientIdRequirementTextField.setText(
                        String.valueOf(reqId.get()));
            }
        });
    }

    /**
     * Оновлює запис про клієнта.
     */
    public void clickUpdateClientRecordButton() {
        try {
            // Оновлює властивості клієнта
            client.setFirstName(clientFirstNameTextField.getText());
            client.setSecondName(clientSecondNameTextField.getText());
            client.setContactNum(Long.valueOf(clientNumberTextField.getText()));
            client.setReqId(Integer.valueOf(
                    clientIdRequirementTextField.getText()));

            // Починає транзакцію та оновлює клієнта у базі даних
            startTransaction();
            session.update(client);
            session.getTransaction().commit();
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);
        } catch (NullPointerException | NumberFormatException e) {
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }


    /**
     * Відкриває таблицю об'єктів для оновлення.
     */
    public void clickOpenFacilityTableForUpdate() {
        // Очищає колонки у таблиці оновлення
        updateTableView.getColumns().clear();
        // Ініціалізує таблицю об'єктів через головний контролер
        mainWindowController.initFacilityTable(updateTableView);
        // Викликає метод для обробки подвійного натискання на елемент таблиці
        clickFacilityTableItem();
    }

    /**
     * Обробляє подвійне натискання на об'єкт у таблиці для оновлення.
     */
    public void clickFacilityTableItem() {
        // Встановлює обробник подвійного натискання на таблицю
        updateTableView.setOnMouseClicked(event -> {
            // Перевіряє, чи подвійний клік
            if (event.getClickCount() == 2) {
                // Отримує вибраний об'єкт з таблиці
                FacilityViewModel selectedFacility =
                        (FacilityViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                // Зберігає оригінальний об'єкт для оновлення
                facility = selectedFacility.getOriginalFacility();

                // Отримує властивості об'єкта для відображення у відповідних полях
                SimpleIntegerProperty objectId =
                        selectedFacility.getObjectReferenceId();
                SimpleObjectProperty<Short> bedroomsNum =
                        selectedFacility.getMinBedrooms();
                SimpleObjectProperty<Short> bathroomsNum =
                        selectedFacility.getMinBathrooms();
                SimpleBooleanProperty garage =
                        selectedFacility.getGarage();
                SimpleBooleanProperty garden =
                        selectedFacility.getGarden();
                SimpleBooleanProperty pool =
                        selectedFacility.getPool();

                // Встановлює значення відповідних полів на формі
                facilityIdObjectTextField.setText(
                        String.valueOf(objectId.get()));
                facilityMinBedroomsTextField.setText(
                        String.valueOf(bedroomsNum.get()));
                facilityMinBathroomsTextField.setText(
                        String.valueOf(bathroomsNum.get()));
                facilityGarageChoiceBox.setValue(garage.get());
                facilityGardenChoiceBox.setValue(garden.get());
                facilityPoolChoiceBox.setValue(pool.get());
            }
        });
    }

    /**
     * Оновлює запис про об'єкт.
     */
    public void clickUpdateFacilityRecordButton() {
        try {
            // Оновлює властивості об'єкта
            facility.setObjectReferenceId(
                    Integer.valueOf(facilityIdObjectTextField.getText()));
            facility.setMinBedrooms(
                    Short.valueOf(facilityMinBedroomsTextField.getText()));
            facility.setMinBathrooms(
                    Short.valueOf(facilityMinBathroomsTextField.getText()));
            facility.setGarage(facilityGarageChoiceBox.getValue());
            facility.setGarden(facilityGardenChoiceBox.getValue());
            facility.setPool(facilityPoolChoiceBox.getValue());

            // Починає транзакцію та оновлює об'єкт у базі даних
            startTransaction();
            session.update(facility);
            session.getTransaction().commit();
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);
        } catch (NullPointerException | NumberFormatException e) {
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }



    /**
     * Відкриває таблицю вимог для оновлення.
     */
    public void clickOpenRequirementTableForUpdate() {
        // Очищає колонки у таблиці оновлення
        updateTableView.getColumns().clear();
        // Ініціалізує таблицю вимог через головний контролер
        mainWindowController.initRequirementTable(updateTableView);
        // Викликає метод для обробки подвійного натискання на елемент таблиці
        clickRequirementTableItem();
    }

    /**
     * Обробляє подвійне натискання на вимогу у таблиці для оновлення.
     */
    public void clickRequirementTableItem() {
        // Встановлює обробник подвійного натискання на таблицю
        updateTableView.setOnMouseClicked(event -> {
            // Перевіряє, чи подвійний клік
            if (event.getClickCount() == 2) {
                // Отримує вибрану вимогу з таблиці
                RequirementViewModel selectedRequirement =
                        (RequirementViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                // Зберігає оригінальну вимогу для оновлення
                requirement = selectedRequirement.getOriginalRequirement();

                // Отримує властивості вимоги для відображення у відповідних полях
                SimpleStringProperty reqStreet =
                        selectedRequirement.getReqStreet();
                SimpleIntegerProperty reqMaxPrice =
                        selectedRequirement.getReqMaxPrice();
                SimpleDoubleProperty reqMinArea =
                        selectedRequirement.getReqMinimalArea();
                SimpleIntegerProperty reqMinBedrooms =
                        selectedRequirement.getReqMinBedrooms();
                SimpleIntegerProperty reqMinBathrooms =
                        selectedRequirement.getReqMinBathrooms();
                SimpleBooleanProperty reqGarage =
                        selectedRequirement.getReqGarage();
                SimpleBooleanProperty reqGarden =
                        selectedRequirement.getReqGarden();
                SimpleBooleanProperty reqPool =
                        selectedRequirement.getReqPool();

                // Встановлює значення відповідних полів на формі
                requirementStreetTextField.setText(reqStreet.get());
                requirementMaxPriceTextField.setText(
                        String.valueOf(reqMaxPrice.get()));
                requirementMinAreaTextField.setText(
                        String.valueOf(reqMinArea.get()));
                requirementMinBedroomsTextField.setText(
                        String.valueOf(reqMinBedrooms.get()));
                requirementMinBathroomsTextField.setText(
                        String.valueOf(reqMinBathrooms.get()));
                requirementGarageChoiceBox.setValue(reqGarage.get());
                requirementGardenChoiceBox.setValue(reqGarden.get());
                requirementPoolChoiceBox.setValue(reqPool.get());
            }
        });
    }

    /**
     * Оновлює запис про вимогу.
     */
    public void clickUpdateRequirementRecordButton() {
        try {
            // Оновлює властивості вимоги
            requirement.setReqStreet(requirementStreetTextField.getText());
            requirement.setReqMaxPrice(
                    Integer.valueOf(requirementMaxPriceTextField.getText()));
            requirement.setReqMinimalArea(
                    Double.valueOf(requirementMinAreaTextField.getText()));
            requirement.setReqMinBedrooms(
                    Short.valueOf(requirementMinBedroomsTextField.getText()));
            requirement.setReqMinBathrooms(
                    Short.valueOf(requirementMinBathroomsTextField.getText()));
            requirement.setReqGarage(requirementGarageChoiceBox.getValue());
            requirement.setReqGarden(requirementGardenChoiceBox.getValue());
            requirement.setReqPool(requirementPoolChoiceBox.getValue());

            // Починає транзакцію та оновлює вимогу у базі даних
            startTransaction();
            session.update(requirement);
            session.getTransaction().commit();
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);
        } catch (NullPointerException | NumberFormatException e) {
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }


    /**
     * Відкриває таблицю консультацій для оновлення.
     */
    public void clickOpenConsultationTableForUpdate() {
        // Очищає колонки у таблиці оновлення
        updateTableView.getColumns().clear();
        // Ініціалізує таблицю консультацій через головний контролер
        mainWindowController.initConsultationTable(updateTableView);
        // Викликає метод для обробки подвійного натискання на елемент таблиці
        clickConsultationTableItem();
    }

    /**
     * Обробляє подвійне натискання на консультацію у таблиці для оновлення.
     */
    public void clickConsultationTableItem() {
        // Встановлює обробник подвійного натискання на таблицю
        updateTableView.setOnMouseClicked(event -> {
            // Перевіряє, чи подвійний клік
            if (event.getClickCount() == 2) {
                // Отримує вибрану консультацію з таблиці
                ConsultationViewModel selectedConsultation =
                        (ConsultationViewModel) updateTableView
                                .getSelectionModel().getSelectedItem();

                // Зберігає оригінальну консультацію для оновлення
                consultation = selectedConsultation.getOriginalConsultation();

                // Отримує властивості консультації для відображення у відповідних полях
                SimpleIntegerProperty clientId =
                        selectedConsultation.getClientId();
                SimpleObjectProperty<Date> date =
                        selectedConsultation.getConsDate();
                SimpleStringProperty status =
                        selectedConsultation.getConsStatus();

                // Встановлює значення відповідних полів на формі
                consultationIdClientTextField.setText(
                        String.valueOf(clientId.get()));
                consultationDatePicker.setValue(date.get().toLocalDate());
                consultationStatusChoiceBox.setValue(status.get());
            }
        });
    }

    /**
     * Оновлює запис про консультацію.
     */
    public void clickUpdateConsultationRecordButton() {
        try {
            // Оновлює властивості консультації
            consultation.setClientId(
                    Integer.valueOf(consultationIdClientTextField.getText()));
            consultation.setConsDate(
                    Date.valueOf(consultationDatePicker.getValue()));
            consultation.setConsStatus(
                    (String) consultationStatusChoiceBox.getValue());

            // Починає транзакцію та оновлює консультацію у базі даних
            startTransaction();
            session.update(consultation);
            session.getTransaction().commit();
            addRecordDoneLabel.setVisible(true);
            labelClose(addRecordDoneLabel);
        } catch (NullPointerException | NumberFormatException e) {
            warningLabel.setVisible(true);
            labelClose(warningLabel);
        }
    }
}
