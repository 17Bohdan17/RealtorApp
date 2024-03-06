package RealtorApp.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import lombok.SneakyThrows;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfGenerator {

    private static final String FONT_PATH = "c:/windows/fonts/arial.ttf";
    private static final BaseColor HEADER_BACKGROUND_COLOR = new BaseColor(0, 142, 240);
    private static final BaseColor BODY_EVEN_ROW_COLOR = new BaseColor(220, 220, 220);
    private static final BaseColor BODY_ODD_ROW_COLOR = BaseColor.WHITE;
    private static final int FONT_SIZE = 12;

    public <T> void generatePdfFromTableViewWithDialog(TableView<T> tableView) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName("table_data.pdf");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        File file = fileChooser.showSaveDialog(null);
        if (file != null) {
            String filePath = file.getAbsolutePath();
            generatePdfFromTableView(tableView, filePath);
        }
    }


    public <T> void generatePdfFromTableView(TableView<T> tableView, String filePath) {
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filePath));

            // Устанавливаем события для шапки страницы
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

            // Добавляем шрифт Arial для кириллицы
            BaseFont baseFont = BaseFont.createFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont, FONT_SIZE, Font.NORMAL);

            // Вычисляем ширину каждой колонки в процентах от ширины страницы
            float[] columnWidths = calculateColumnWidths(tableView, document);

            // Устанавливаем ширину каждой колонки
            pdfTable.setWidths(columnWidths);

            // Добавляем данные таблицы
            ObservableList<T> tableData = tableView.getItems();
            for (int i = 0; i < tableData.size(); i++) {
                T rowData = tableData.get(i);
                for (TableColumn<T, ?> column : tableView.getColumns()) {
                    Object cellData = column.getCellObservableValue(rowData).getValue();
                    String cellValue = (cellData == null) ? "" : cellData.toString();
                    PdfPCell bodyCell = new PdfPCell(new Phrase(cellValue, font));
                    bodyCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    // Чередуем цвета строк
                    bodyCell.setBackgroundColor((i % 2 == 0) ? BODY_EVEN_ROW_COLOR : BODY_ODD_ROW_COLOR);
                    pdfTable.addCell(bodyCell);
                }
            }

            document.add(pdfTable);
            document.close();
            System.out.println("PDF file is created successfully!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }


    // Метод для создания шапки таблицы на основе названий колонок
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

    // Метод для вычисления ширины каждой колонки в процентах от ширины страницы
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
