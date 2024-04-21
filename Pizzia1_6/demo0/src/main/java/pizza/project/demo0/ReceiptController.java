package pizza.project.demo0;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javax.swing.*;
import java.io.FileNotFoundException;

public class ReceiptController {

    @FXML
    private Button menuButton;

    @FXML
    void makePdf(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        int response = fileChooser.showSaveDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            try {
                String path = fileChooser.getSelectedFile().getAbsolutePath();
                PdfWriter writer = new PdfWriter(path);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);

                // Formatting the header
                Paragraph header = new Paragraph("Pizza Project Receipt")
                        .setBold()
                        .setFontSize(20)
                        .setTextAlignment(TextAlignment.CENTER);
                document.add(header);



                Paragraph item1 = new Paragraph("Pizza Margherita - $12.99")
                        .setTextAlignment(TextAlignment.CENTER);


                // Adding items to document
                document.add(item1);

                // Total amount
                Paragraph total = new Paragraph("Total: $14.98")
                        .setBold()
                        .setTextAlignment(TextAlignment.RIGHT);
                document.add(total);

                document.close();
                System.out.println("PDF created at " + path);
            } catch (FileNotFoundException e) {
                System.err.println("An error occurred while creating the PDF: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
