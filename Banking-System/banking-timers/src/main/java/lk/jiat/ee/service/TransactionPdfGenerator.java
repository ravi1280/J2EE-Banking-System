package lk.jiat.ee.service;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import lk.jiat.ee.entity.Transaction;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

public class TransactionPdfGenerator {

    public void generateCustomerTransactionsPdf(String customerName,String number, List<Transaction> transactions) throws Exception {
        String safeCustomerName = customerName.replaceAll("[^a-zA-Z0-9]", "_");
        String fileName = "Customer_Transactions_" + safeCustomerName + "_" + LocalDate.now() + ".pdf";
        String dest = "C:/pdfs/" + fileName;

        File file = new File(dest);
        file.getParentFile().mkdirs();

        PdfWriter writer = new PdfWriter(new FileOutputStream(file));
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        PdfFont boldFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD);
        PdfFont regularFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA);

        document.add(new Paragraph(" Monthly Account Transaction Report")
                .setFont(boldFont)
                .setFontSize(20)
                .setFontColor(ColorConstants.BLUE)
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20));

        document.add(new Paragraph("Customer: " + customerName)
                .setFont(regularFont)
                .setFontSize(12));
        document.add(new Paragraph("Account Number: " + number)
                .setFont(regularFont)
                .setFontSize(12));
        document.add(new Paragraph("Report Date: " + LocalDate.now().toString())
                .setFont(regularFont)
                .setFontSize(12));
        document.add(new Paragraph("\n"));

        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 3, 5, 3}));
        table.setWidth(UnitValue.createPercentValue(100));


        String[] headers = {"No", "Date", "Description", "Type"};
        for (String h : headers) {
            Cell headerCell = new Cell()
                    .add(new Paragraph(h).setFont(boldFont).setFontSize(12).setFontColor(ColorConstants.WHITE))
                    .setBackgroundColor(ColorConstants.DARK_GRAY)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setPadding(5);
            table.addHeaderCell(headerCell);
        }


        int index = 1;
        for (Transaction t : transactions) {
            Color rowColor = (index % 2 == 0) ? ColorConstants.LIGHT_GRAY : ColorConstants.WHITE;

            table.addCell(new Cell().add(new Paragraph(String.valueOf(index++)).setFont(regularFont))
                    .setBackgroundColor(rowColor).setTextAlignment(TextAlignment.CENTER).setPadding(4));

            table.addCell(new Cell().add(new Paragraph(t.getTimestamp().toString()).setFont(regularFont))
                    .setBackgroundColor(rowColor).setPadding(4));

            table.addCell(new Cell().add(new Paragraph(t.getDescription()).setFont(regularFont))
                    .setBackgroundColor(rowColor).setPadding(4));

            table.addCell(new Cell().add(new Paragraph(t.getType().toString()).setFont(regularFont))
                    .setBackgroundColor(rowColor).setPadding(4));
        }

        document.add(table);
        document.close();

        System.out.println("✅ PDF generated: " + dest);
    }
}