package assign.first.keepnote.service;

import java.io.FileOutputStream;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

public class GenPdf {
    public static void main(String[] args) {
        // Specify the path to the PDF file
        String outputPdfPath = "C:\\Users\\CGG\\Pictures\\today.pdf";

        // Initialize the PDF writer
        try {
            PdfWriter writer = new PdfWriter(new FileOutputStream(outputPdfPath));

            // Initialize the PDF document
            PdfDocument pdf = new PdfDocument(writer);

            // Initialize the document layout
            Document document = new Document(pdf);
            ///////////////////// gen code

            // Add title
            Paragraph title = new Paragraph("Official Document")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(20)
                    .setBold();
            document.add(title);
            // Add header
            Paragraph header = new Paragraph("Header: Company Name")
                    .setTextAlignment(TextAlignment.RIGHT)
                    .setFontSize(12)
                    .setMarginBottom(20);
            document.add(header);
            // Add content
            document.add(new Paragraph("This is an official document created using iText.")
                    .setFontSize(14));

            // Add table of contents
            document.add(new Paragraph("Table of Contents")
                    .setFontSize(16)
                    .setBold()
                    .setMarginTop(20)
                    .setMarginBottom(10));

            Table tableOfContents = new Table(2);
            tableOfContents.setWidth(UnitValue.createPercentValue(100));
            tableOfContents.addCell(new Cell().add(new Paragraph("Section"))
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                    .setBorder(Border.NO_BORDER));
            tableOfContents.addCell(new Cell().add(new Paragraph("Page Number"))
                    .setBackgroundColor(ColorConstants.LIGHT_GRAY)
                    .setBorder(Border.NO_BORDER));
            tableOfContents.addCell(new Cell().add(new Paragraph("Introduction")));
            tableOfContents.addCell(new Cell().add(new Paragraph("1")));
            tableOfContents.addCell(new Cell().add(new Paragraph("Details")));
            tableOfContents.addCell(new Cell().add(new Paragraph("2")));
            document.add(tableOfContents);

            // Add section content
            document.add(new Paragraph("Introduction")
                    .setFontSize(16)
                    .setBold()
                    .setMarginTop(20));
            document.add(new Paragraph("This section introduces the document.")
                    .setFontSize(14)
                    .setMarginBottom(10));

            document.add(new Paragraph("Details")
                    .setFontSize(16)
                    .setBold()
                    .setMarginTop(20));
            document.add(new Paragraph("This section provides detailed information.")
                    .setFontSize(14)
                    .setMarginBottom(10));

            // Add footer
            Paragraph footer = new Paragraph("Footer: Confidential")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(12)
                    .setMarginTop(20);
            document.add(footer);
            ///////////////////// gen code
            // Close the document

            document.close();

            System.out.println("PDF created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
