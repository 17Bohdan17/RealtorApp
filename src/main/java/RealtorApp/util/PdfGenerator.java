/*
 * Генератор PDF
 *
 * Версія: 1.0
 * Автор: Чирков Богдан
 *
 * Опис: Клас для генерації PDF-документів з даних TableView у JavaFX.
 *       Надає можливість збереження таблиці у вигляді PDF-файлу та відображення попередження, якщо таблиця порожня.
 */

package RealtorApp.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGenerator {

    // Шлях до шрифту Arial
    private static final String FONT_PATH = "c:/windows/fonts/arial.ttf";
    // Колір фону для шапки таблиці
    private static final BaseColor HEADER_BACKGROUND_COLOR = new BaseColor(0, 142, 240);
    // Колір фону для парних рядків тіла таблиці
    private static final BaseColor BODY_EVEN_ROW_COLOR = new BaseColor(220, 220, 220);
    // Колір фону для непарних рядків тіла таблиці
    private static final BaseColor BODY_ODD_ROW_COLOR = BaseColor.WHITE;
    // Розмір шрифту
    private static final int FONT_SIZE = 12;

    /**
     * Генерує PDF-документ з даних TableView та відображає діалогове вікно для вибору місця збереження файлу.
     *
     * @param tableView TableView, дані якої будуть включені до PDF
     * @param label      Label, яка відображатиме попередження, якщо таблиця порожня
     */
    public <T> void generatePdfFromTableViewWithDialog(TableView<T> tableView, Label label) {
        if (!tableView.getItems().isEmpty()) {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialFileName("table_data.pdf");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                String filePath = file.getAbsolutePath();
                generatePdfFromTableView(tableView, filePath);
            }
        } else {
            showWarning(label);
        }
    }

    /**
     * Показує попередження на Label.
     *
     * @param label Label, на якому відображується попередження
     */
    public void showWarning(Label label){
        label.setVisible(true);
    }

    /**
     * Генерує PDF-документ з даних TableView та зберігає його за вказаним шляхом.
     *
     * @param tableView TableView, дані якої будуть включені до PDF
     * @param filePath   Шлях, за яким буде збережено PDF-файл
     */
    public <T> void generatePdfFromTableView(TableView<T> tableView, String filePath) {
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Устанавлюємо події для шапки сторінки
            PdfPageEventHelper eventHelper = new PdfPageEventHelper() {
                @Override
                public void onStartPage(PdfWriter writer, Document document) {
                    try {
                        PdfPTable headerTable = createHeaderTable(tableView.getColumns(), tableView, document);
                        headerTable.setWidthPercentage(100);
                        document.add(headerTable);
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onEndPage(PdfWriter writer, Document document) {
                    PdfContentByte cb = writer.getDirectContent();
                    Phrase footer = new Phrase(" " + writer.getPageNumber());
                    ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer,
                            (document.right() - document.left()) / 2 + document.leftMargin(),
                            document.bottom() - 10, 0);
                }
            };
            writer.setPageEvent(eventHelper);

            document.open();

            PdfPTable pdfTable = new PdfPTable(tableView.getColumns().size());
            pdfTable.setWidthPercentage(100);

            // Додаємо шрифт Arial для кирилиці
            BaseFont baseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, FONT_SIZE, Font.NORMAL);

            // Визначаємо ширину кожної колонки у відсотках від ширини сторінки
            float[] columnWidths = calculateColumnWidths(tableView, document);

            // Встановлюємо ширину кожної колонки
            pdfTable.setWidths(columnWidths);

            // Додаємо дані таблиці
            ObservableList<T> tableData = tableView.getItems();
            for (int i = 0; i < tableData.size(); i++) {
                T rowData = tableData.get(i);
                for (TableColumn<T, ?> column : tableView.getColumns()) {
                    Object cellData = column.getCellObservableValue(rowData).getValue();
                    String cellValue = (cellData == null) ? "" : cellData.toString();
                    PdfPCell bodyCell = new PdfPCell(new Phrase(cellValue, font));
                    bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    // Перемикаємо кольори рядків
                    bodyCell.setBackgroundColor((i % 2 == 0) ? BODY_EVEN_ROW_COLOR : BODY_ODD_ROW_COLOR);
                    pdfTable.addCell(bodyCell);
                }
            }

            document.add(pdfTable);
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для створення шапки таблиці на основі назв колонок
    @SneakyThrows
    private <T> PdfPTable createHeaderTable(ObservableList<TableColumn<T, ?>> columns,
                                            TableView<T> tableView, Document document) throws DocumentException {
        BaseFont baseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        PdfPTable table = new PdfPTable(columns.size());
        float[] columnWidths = calculateColumnWidths(tableView, document);
        table.setWidths(columnWidths);
        for (TableColumn<T, ?> column : columns) {
            PdfPCell cell = new PdfPCell(new Phrase(column.getText(), new Font(baseFont, FONT_SIZE, Font.NORMAL)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(HEADER_BACKGROUND_COLOR);
            table.addCell(cell);
        }
        return table;
    }

    // Метод для обчислення ширини кожної колонки у відсотках від ширини сторінки
    private<T> float[] calculateColumnWidths(TableView<T> tableView, Document document) {
        float totalWidth = PageSize.A4.getWidth() - document.leftMargin() - document.rightMargin();
        float[] columnWidths = new float[tableView.getColumns().size()];
        for (int i = 0; i < tableView.getColumns().size(); i++) {
            TableColumn<T, ?> column = tableView.getColumns().get(i);
            double columnWidth = column.getWidth();
            columnWidths[i] = (float) (columnWidth / totalWidth * 100);
        }
        return columnWidths;
    }
}
