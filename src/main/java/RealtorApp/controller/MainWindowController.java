
/*
 * MainWindowController
 *
 * Version: 1.0
 * Author: Чирков Богдан
 *
 * Description: Контролер для головного вікна програми RealtorApp. Відповідає за обробку подій та
 *              ініціалізацію відображення таблиць об'єктів нерухомості, угод, клієнтів, консультацій,
 *              умов та зручностей, а також за відображення, та видалення записів у цих таблицях.
 */

package RealtorApp.controller;

import RealtorApp.enums.RecordStatusEnum;
import RealtorApp.enums.TableEnum;
import RealtorApp.model.entity.Object;
import RealtorApp.model.entity.*;
import RealtorApp.model.modelView.*;
import RealtorApp.util.PdfGenerator;
import RealtorApp.util.StageManager;
import RealtorApp.util.hibernate.HibernateUtil;
import RealtorApp.view.RecordWindow;
import javafx.animation.PauseTransition;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.hibernate.Session;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class MainWindowController {
    RecordWindow recordWindow = new RecordWindow();
    StageManager stageManager = new StageManager();
    TableEnum    tableEnum;
    @FXML
    private Pane      titlePane;
    @FXML
    private ImageView btnClose;
    @FXML
    private ImageView btnMinimize;
    @FXML
    private TableView mainTableView;
    @FXML
    private Label     warningLabel;
    @FXML
    private ImageView wallpaperImage;
    @FXML
    private MenuButton addTableMenuBtn;
    @FXML
    private MenuButton addRecordMenu;
    @FXML
    private MenuButton updateRecordMenu;
    @FXML
    private Button deleteButton;
    @FXML
    private Button printButton;





    private double x;
    private double y;


    /**
    Ініціалізація головного вікна додатку
     */
    public void init(Stage stage) {
            stageManager.setUpDraggableStage(stage, titlePane);
            stageManager.setUpCloseButton(stage, btnClose);
            stageManager.setUpMinimizeButton(stage, btnMinimize);
            mainTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);
            mainTableView.autosize();
            warningLabel.setVisible(false);
            initTooltips();
    }


    /**
     * Додає запис про об'єкт нерухомості.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void addObjectRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_OBJECT;
        recordWindow.init();
    }

    /**
     * Додає запис про угоду.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void addAgreementRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_AGREEMENT;
        recordWindow.init();
    }

    /**
     * Додає запис про клієнта.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void addClientRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_CLIENT;
        recordWindow.init();
    }

    /**
     * Додає запис про консультацію.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void addConsultationRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_CONSULTATION;
        recordWindow.init();
    }

    /**
     * Додає запис про зручності.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void addFacilityRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_FACILITY;
        recordWindow.init();
    }

    /**
     * Додає запис про вимогу.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void addRequirementRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.ADD_REQUIREMENT;
        recordWindow.init();
    }


    /**
     * Оновлює запис про об'єкт.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void updateObjectRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_OBJECT;
        recordWindow.init();
    }

    /**
     * Оновлює запис про угоду.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void updateAgreementRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_AGREEMENT;
        recordWindow.init();
    }

    /**
     * Оновлює запис про клієнта.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void updateClientRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_CLIENT;
        recordWindow.init();
    }

    /**
     * Оновлює запис про консультацію.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void updateConsultationRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_CONSULTATION;
        recordWindow.init();
    }

    /**
     * Оновлює запис про обладнання.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void updateFacilityRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_FACILITY;
        recordWindow.init();
    }

    /**
     * Оновлює запис про вимогу.
     *
     * @throws IOException виникає, якщо виникає проблема при ініціалізації вікна запису
     */
    public void updateRequirementRecord() throws IOException {
        recordWindow.recordStatusEnum = RecordStatusEnum.UPDATE_REQUIREMENT;
        recordWindow.init();
    }


    /**
     * Обробляє натискання кнопки для додавання таблиці об'єктів.
     */
    public void clickButtonAddObjectTable() {
        initObjectTable(mainTableView);
        wallpaperImage.setVisible(false);
    }

    /**
     * Обробляє натискання кнопки для додавання таблиці угод.
     */
    public void clickButtonAddAgreementTable() {
        initAgreementTable(mainTableView);
        wallpaperImage.setVisible(false);
    }

    /**
     * Обробляє натискання кнопки для додавання таблиці клієнтів.
     */
    public void clickButtonAddClientTable() {
        initClientTable(mainTableView);
        wallpaperImage.setVisible(false);
    }

    /**
     * Обробляє натискання кнопки для додавання таблиці консультацій.
     */
    public void clickButtonAddConsultationTable() {
        initConsultationTable(mainTableView);
        wallpaperImage.setVisible(false);
    }

    /**
     * Обробляє натискання кнопки для додавання таблиці обладнання.
     */
    public void clickButtonAddFacilityTable() {
        initFacilityTable(mainTableView);
        wallpaperImage.setVisible(false);
    }

    /**
     * Обробляє натискання кнопки для додавання таблиці вимог.
     */
    public void clickButtonAddRequirementTable() {
        initRequirementTable(mainTableView);
        wallpaperImage.setVisible(false);
    }

    /**
     * Обробляє натискання кнопки для видалення обраного запису з таблиці.
     */
    public void clickDeleteRecordButton() {
        deleteSelectedRecord(mainTableView);
    }

    /**
     * Видаляє вибраний запис з таблиці з обробкою винятків.
     *
     * @param tableView таблиця, з якої видаляється запис
     */
    public void deleteSelectedRecord(TableView<?> tableView) {
        // Відкриваємо нову сесію Hibernate
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            // Спробуємо видалити вибраний запис з таблиці
            switch (tableEnum) {
                case OBJECT:
                    ObjectViewModel selectedObject = (ObjectViewModel)
                            tableView.getSelectionModel().getSelectedItem();

                    Object object = selectedObject.getOriginalObject();
                    closeTransaction(object, session);
                    tableView.getItems().remove(selectedObject);
                    break;

                case AGREEMENT:
                    AgreementViewModel selectedAgreement = (AgreementViewModel)
                            tableView.getSelectionModel().getSelectedItem();

                    Agreement agreement = selectedAgreement.getOriginalAgreement();
                    closeTransaction(agreement, session);

                    tableView.getItems().remove(selectedAgreement);
                    break;

                case CLIENT:
                    ClientViewModel selectedClient = (ClientViewModel)
                            tableView.getSelectionModel().getSelectedItem();

                    Client client = selectedClient.getOriginalClient();
                    closeTransaction(client, session);
                    tableView.getItems().remove(selectedClient);
                    break;

                case REQUIREMENT:
                    RequirementViewModel selectedRequirement =
                            (RequirementViewModel)
                                    tableView.getSelectionModel().getSelectedItem();

                    Requirement requirement =
                            selectedRequirement.getOriginalRequirement();
                    closeTransaction(requirement, session);

                    tableView.getItems().remove(selectedRequirement);
                    break;

                case FACILITY:
                    FacilityViewModel selectedFacility = (FacilityViewModel)
                            tableView.getSelectionModel().getSelectedItem();

                    Facilities facility = selectedFacility.getOriginalFacility();
                    closeTransaction(facility, session);

                    tableView.getItems().remove(selectedFacility);
                    break;

                case CONSULTATION:
                    ConsultationViewModel selectedConsultation =
                            (ConsultationViewModel)
                                    tableView.getSelectionModel().getSelectedItem();

                    Consultation consultation =
                            selectedConsultation.getOriginalConsultation();
                    closeTransaction(consultation, session);

                    tableView.getItems().remove(selectedConsultation);
                    break;
            }
        } catch (NullPointerException e) {
            // Якщо виникає NullPointerException, показуємо попередження
            warningLabel.setVisible(true);
            // Приховуємо попередження після 3 секунд
            labelClose(warningLabel);
        } finally {
            // Закриваємо сесію Hibernate в будь-якому випадку
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Створює вспливаючі підказки до кнопок основного меню.
     */
    private void initTooltips(){
        Tooltip.install(addTableMenuBtn, new Tooltip(
                "Оберіть таблицю для відображення"));

        Tooltip.install(addRecordMenu, new Tooltip(
                "Оберіть тип запису для додавання"));

        Tooltip.install(updateRecordMenu, new Tooltip(
                "Оберіть тип запису для редагування"));

        Tooltip.install(deleteButton, new Tooltip(
                "Оберіть запис на таблиці для видалення"));

        Tooltip.install(printButton, new Tooltip(
                "Відобразіть таблицю для друкування"));
    }

    /**
     * Закриває попередження після певного періоду часу.
     *
     * @param label попередження, яке закривається
     */
    private void labelClose(Label label) {
        // Пауза для видимості попередження
        PauseTransition visiblePause = new PauseTransition(
                Duration.seconds(3));

        // Після закінчення паузи приховуємо попередження
        visiblePause.setOnFinished(event -> label.setVisible(false));
        visiblePause.play();
    }

    /**
     * Закриває транзакцію Hibernate і сесію.
     *
     * @param someObject об'єкт, який видаляється з бази даних
     * @param session    сесія Hibernate, в якій відбувається транзакція
     */
    private void closeTransaction(java.lang.Object someObject, Session session) {
        /* Відкриваємо транзакцію, видаляємо об'єкт з бази даних,
        завершуємо транзакцію та закриваємо сесію */
        session.beginTransaction();
        session.delete(someObject);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Створює PDF-документ з вмістом таблиці та відображає діалогове вікно.
     * Після генерації закриває попереджувальний підпис через 3 секунди.
     */
    public void createPdfDocument() {
        PdfGenerator pdfGenerator = new PdfGenerator();
        pdfGenerator.generatePdfFromTableViewWithDialog(mainTableView, warningLabel);
        labelClose(warningLabel);
    }


    /**
     * Ініціалізує таблицю об'єктів нерухомості відповідно до моделі даних.
     *
     * @param tableView таблиця, яку потрібно ініціалізувати
     */
    public void initObjectTable(TableView tableView) {
        // Константи для ширини колонок
        final int ID_COLUMN_WIDTH = 30;
        final int STREET_COLUMN_WIDTH = 130;
        final int STREET_NUM_COLUMN_WIDTH = 40;
        final int AREA_COLUMN_WIDTH = 60;
        final int PRICE_COLUMN_WIDTH = 80;
        final int STATUS_COLUMN_WIDTH = 140;
        final int ROOM_COUNT_COLUMN_WIDTH = 90;

        ObjectModel objectModel = new ObjectModel();   // Створюємо модель об'єктів нерухомості
        tableEnum = TableEnum.OBJECT;                  // Встановлюємо тип таблиці

        tableView.getColumns().clear();                // Очищуємо колонки таблиці

        // Створюємо колонки таблиці з відповідними назвами
        TableColumn<ObjectViewModel, Integer> idColumn =
                new TableColumn<>("ID");
        TableColumn<ObjectViewModel, String> streetColumn =
                new TableColumn<>("Вулиця");
        TableColumn<ObjectViewModel, Integer> streetNumColumn =
                new TableColumn<>("№");
        TableColumn<ObjectViewModel, Double> areaColumn =
                new TableColumn<>("Площа");
        TableColumn<ObjectViewModel, BigDecimal> priceColumn =
                new TableColumn<>("Ціна");
        TableColumn<ObjectViewModel, String> statusColumn =
                new TableColumn<>("Статус");
        TableColumn<ObjectViewModel, Integer> roomCountColumn =
                new TableColumn<>("К-сть кімнат");

        // Встановлюємо ширину колонок
        idColumn.setPrefWidth(ID_COLUMN_WIDTH);
        streetColumn.setPrefWidth(STREET_COLUMN_WIDTH);
        streetNumColumn.setPrefWidth(STREET_NUM_COLUMN_WIDTH);
        areaColumn.setPrefWidth(AREA_COLUMN_WIDTH);
        priceColumn.setPrefWidth(PRICE_COLUMN_WIDTH);
        statusColumn.setPrefWidth(STATUS_COLUMN_WIDTH);
        roomCountColumn.setPrefWidth(ROOM_COUNT_COLUMN_WIDTH);

        // Встановлюємо значення для кожної колонки
        idColumn.setCellValueFactory(cellData -> Bindings.createObjectBinding(
                () -> cellData.getValue().getObjectId().getValue()));
        streetColumn.setCellValueFactory(
                cellData -> cellData.getValue().getStreet());
        streetNumColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getStreet_num().getValue()));
        areaColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getArea().getValue()));
        priceColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getPrice().getValue()));
        statusColumn.setCellValueFactory(
                cellData -> cellData.getValue().getStatus());
        roomCountColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getRoomCount().getValue()));

        // Встановлюємо дані у таблицю
        tableView.setItems(objectModel.getObjectViewModels());
        // Додаємо колонки до таблиці
        tableView.getColumns().addAll(idColumn, streetColumn,
                streetNumColumn, areaColumn, priceColumn, statusColumn,
                roomCountColumn);
    }


    /**
     * Ініціалізує таблицю договорів з моделлю договорів.
     *
     * @param tableView Таблиця, яку потрібно ініціалізувати
     */
    public void initAgreementTable(TableView tableView) {
        // Створення моделі договорів
        AgreementModel agreementModel = new AgreementModel();

        // Очищення колонок таблиці
        tableView.getColumns().clear();

        // Встановлення типу таблиці на AGREEMENT
        tableEnum = TableEnum.AGREEMENT;

        // Створення колонок таблиці
        TableColumn<AgreementViewModel, Integer> agreementIdColumn =
                new TableColumn<>("ID");
        TableColumn<AgreementViewModel, Integer> objectIdColumn =
                new TableColumn<>("ID об'єкту");
        TableColumn<AgreementViewModel, Integer> clientIdColumn =
                new TableColumn<>("ID клієнта");
        TableColumn<AgreementViewModel, Date> agreementDateColumn =
                new TableColumn<>("Дата укладання");
        TableColumn<AgreementViewModel, Integer> agreementPriceColumn =
                new TableColumn<>("Ціна послуги");
        TableColumn<AgreementViewModel, String> agreementStatusColumn =
                new TableColumn<>("Статус");

        // Встановлення значень для фабрики значень в колонках
        agreementIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getAgreementId()
                                .getValue()));

        objectIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getObjectId().getValue()));

        clientIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getClientId().getValue()));

        agreementDateColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getAgreementDate()
                                .getValue()));

        agreementPriceColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getAgreementPrice()
                                .getValue()));

        agreementStatusColumn.setCellValueFactory(
                cellData -> cellData.getValue().getAgreementStatus());

        // Встановлення елементів у таблицю
        tableView.setItems(agreementModel.getAgreementViewModels());

        // Додавання колонок до таблиці
        tableView.getColumns().addAll(agreementIdColumn, objectIdColumn,
                clientIdColumn, agreementDateColumn, agreementPriceColumn,
                agreementStatusColumn);
    }

    /**
     * Ініціалізує таблицю клієнтів з моделлю клієнтів.
     *
     * @param tableView Таблиця, яку потрібно ініціалізувати
     */
    public void initClientTable(TableView tableView) {
        // Створення моделі клієнтів
        ClientModel clientModel = new ClientModel();

        // Очищення колонок таблиці
        tableView.getColumns().clear();

        // Встановлення типу таблиці на CLIENT
        tableEnum = TableEnum.CLIENT;

        // Створення колонок таблиці
        TableColumn<ClientViewModel, Integer> clientIdColumn =
                new TableColumn<>("ID");
        TableColumn<ClientViewModel, String> firstNameColumn =
                new TableColumn<>("Ім'я");
        TableColumn<ClientViewModel, String> secondNameColumn =
                new TableColumn<>("Прізвище");
        TableColumn<ClientViewModel, Long> contactNumColumn =
                new TableColumn<>("Номер телефону");
        TableColumn<ClientViewModel, Integer> reqIdColumn =
                new TableColumn<>("ID вимоги");

        // Встановлення значень для фабрики значень в колонках
        clientIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getClientId().getValue()));

        firstNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getFirstName());

        secondNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getSecondName());

        contactNumColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getContactNum().getValue()));

        reqIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqId().getValue()));

        // Встановлення елементів у таблицю
        tableView.setItems(clientModel.getClientViewModels());

        // Додавання колонок до таблиці
        tableView.getColumns().addAll(clientIdColumn, firstNameColumn,
                secondNameColumn, contactNumColumn, reqIdColumn);
    }


    /**
     * Ініціалізує таблицю консультацій з моделлю консультацій.
     *
     * @param tableView Таблиця, яку потрібно ініціалізувати
     */
    public void initConsultationTable(TableView tableView) {
        // Створення моделі консультацій
        ConsultationModel consultationModel = new ConsultationModel();

        // Очищення колонок таблиці
        tableView.getColumns().clear();

        // Встановлення типу таблиці на CONSULTATION
        tableEnum = TableEnum.CONSULTATION;

        // Створення колонок таблиці
        TableColumn<ConsultationViewModel, Integer> consIdColumn =
                new TableColumn<>("ID");
        TableColumn<ConsultationViewModel, Integer> clientIdColumn =
                new TableColumn<>("ID клієнта");
        TableColumn<ConsultationViewModel, Date> consDateColumn =
                new TableColumn<>("Дата");
        TableColumn<ConsultationViewModel, String> consStatusColumn =
                new TableColumn<>("Статус");

        // Встановлення значень для фабрики значень в колонках
        consIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getConsId().getValue()));

        clientIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getClientId().getValue()));

        consDateColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getConsDate().getValue()));

        consStatusColumn.setCellValueFactory(
                cellData -> cellData.getValue().getConsStatus());

        // Встановлення елементів у таблицю
        tableView.setItems(consultationModel.getConsultationViewModels());

        // Додавання колонок до таблиці
        tableView.getColumns().addAll(consIdColumn, clientIdColumn,
                consDateColumn, consStatusColumn);
    }

    /**
     * Ініціалізує таблицю приміщень з моделлю приміщень.
     *
     * @param tableView Таблиця, яку потрібно ініціалізувати
     */
    public void initFacilityTable(TableView tableView) {
        // Створення моделі приміщень
        FacilityModel facilityModel = new FacilityModel();

        // Очищення колонок таблиці
        tableView.getColumns().clear();

        // Встановлення типу таблиці на FACILITY
        tableEnum = TableEnum.FACILITY;

        // Створення колонок таблиці
        TableColumn<FacilityViewModel, Integer> facilityIdColumn =
                new TableColumn<>("ID");
        TableColumn<FacilityViewModel, Integer> objectReferenceIdColumn =
                new TableColumn<>("ID об'єкту");
        TableColumn<FacilityViewModel, Short> minBedroomsColumn =
                new TableColumn<>("Спальні кімнати (min)");
        TableColumn<FacilityViewModel, Short> minBathroomsColumn =
                new TableColumn<>("Ванні кімнати (min)");
        TableColumn<FacilityViewModel, Boolean> garageColumn =
                new TableColumn<>("Гараж");
        TableColumn<FacilityViewModel, Boolean> gardenColumn =
                new TableColumn<>("Сад");
        TableColumn<FacilityViewModel, Boolean> poolColumn =
                new TableColumn<>("Басейн");

        // Встановлення значень для фабрики значень в колонках
        facilityIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getFacilityId().getValue()));

        objectReferenceIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getObjectReferenceId()
                                .getValue()));

        minBedroomsColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getMinBedrooms()
                                .getValue()));

        minBathroomsColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getMinBathrooms()
                                .getValue()));

        garageColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getGarage().getValue()));

        gardenColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getGarden().getValue()));

        poolColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getPool().getValue()));

        // Встановлення елементів у таблицю
        tableView.setItems(facilityModel.getFacilityViewModels());

        // Додавання колонок до таблиці
        tableView.getColumns().addAll(facilityIdColumn,
                objectReferenceIdColumn, minBedroomsColumn, minBathroomsColumn,
                garageColumn, gardenColumn, poolColumn);
    }



    /**
     * Ініціалізує таблицю вимог з моделлю вимог.
     *
     * @param tableView Таблиця, яку потрібно ініціалізувати
     */
    public void initRequirementTable(TableView tableView) {
        final int ID_COLUMN_WIDTH = 30;
        final int GARAGE_COLUMN_WIDTH = 40;
        final int GARDEN_COLUMN_WIDTH = 40;
        final int POOL_COLUMN_WIDTH = 40;
        final int MIN_COLUMN_WIDTH = 40;

        RequirementModel requirementModel = new RequirementModel();   // Створення моделі вимог
        tableView.getColumns().clear();    // Очищення колонок таблиці
        tableEnum = TableEnum.REQUIREMENT;   // Встановлення типу таблиці на REQUIREMENT

        // Створення колонок таблиці
        TableColumn<RequirementViewModel, Integer> reqIdColumn =
                new TableColumn<>("ID");
        TableColumn<RequirementViewModel, Integer> reqMinBedroomsColumn =
                new TableColumn<>("К-сть спален");
        TableColumn<RequirementViewModel, Integer> reqMinBathroomsColumn =
                new TableColumn<>("К-сть ванних");
        TableColumn<RequirementViewModel, Double> reqMinimalAreaColumn =
                new TableColumn<>("Площа (min)");
        TableColumn<RequirementViewModel, Integer> reqMaxPriceColumn =
                new TableColumn<>("Ціна (max)");
        TableColumn<RequirementViewModel, String> reqStreetColumn =
                new TableColumn<>("Вулиця");
        TableColumn<RequirementViewModel, Boolean> reqGarageColumn =
                new TableColumn<>("Гараж");
        TableColumn<RequirementViewModel, Boolean> reqGardenColumn =
                new TableColumn<>("Сад");
        TableColumn<RequirementViewModel, Boolean> reqPoolColumn =
                new TableColumn<>("Басейн");

        // Встановлення ширини колонок
        reqIdColumn.setPrefWidth(ID_COLUMN_WIDTH);
        reqGarageColumn.setPrefWidth(GARAGE_COLUMN_WIDTH);
        reqGardenColumn.setPrefWidth(GARDEN_COLUMN_WIDTH);
        reqPoolColumn.setPrefWidth(POOL_COLUMN_WIDTH);
        reqMinBathroomsColumn.setPrefWidth(MIN_COLUMN_WIDTH);
        reqMinBedroomsColumn.setPrefWidth(MIN_COLUMN_WIDTH);
        reqMinimalAreaColumn.setPrefWidth(MIN_COLUMN_WIDTH);
        reqMaxPriceColumn.setPrefWidth(MIN_COLUMN_WIDTH);

        // Встановлення значень для фабрики значень в колонках
        reqIdColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqId().getValue()));

        reqMinBedroomsColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqMinBedrooms()
                                .getValue()));

        reqMinBathroomsColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqMinBathrooms()
                                .getValue()));

        reqMinimalAreaColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqMinimalArea().
                                getValue()));

        reqMaxPriceColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqMaxPrice()
                                .getValue()));

        reqStreetColumn.setCellValueFactory(
                cellData -> cellData.getValue().getReqStreet());

        reqGarageColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqGarage().getValue()));

        reqGardenColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqGarden().getValue()));

        reqPoolColumn.setCellValueFactory(
                cellData -> Bindings.createObjectBinding(
                        () -> cellData.getValue().getReqPool().getValue()));

        // Встановлення елементів у таблицю
        tableView.setItems(requirementModel.getRequirementViewModels());

        // Додавання колонок до таблиці
        tableView.getColumns().addAll(reqIdColumn, reqMinBedroomsColumn,
                reqMinBathroomsColumn, reqMinimalAreaColumn, reqMaxPriceColumn,
                reqStreetColumn, reqGarageColumn, reqGardenColumn,
                reqPoolColumn);
    }
}

































