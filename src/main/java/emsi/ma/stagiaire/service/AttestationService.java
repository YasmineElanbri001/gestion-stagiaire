package emsi.ma.stagiaire.service;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AttestationService {

    public ByteArrayInputStream generateAttestationPdf(String nomStagiaire, String service, String dateDebut, String dateFin) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Création d'un document PDF
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            LocalDate dateActuelle = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String date = dateActuelle.format(formatter);

            // Ajout d'une image (logo par exemple)
            InputStream imageStream = getClass().getResourceAsStream("/static/logo/logo.png");
            if (imageStream != null) {
                ImageData imageData = ImageDataFactory.create(imageStream.readAllBytes());
                Image image = new Image(imageData);
                image.setWidth(300);
                image.setHorizontalAlignment(HorizontalAlignment.CENTER);
                document.add(image);
            }

            // Ajout du titre
            Paragraph title = new Paragraph("\n\nAttestation de Stage")
                    .setBold()
                    .setFontSize(20)
                    .setTextAlignment(com.itextpdf.layout.properties.TextAlignment.CENTER);
            document.add(title);

            // Ajout du contenu
            Paragraph contenu = new Paragraph();
            contenu.add(new Text("Cette attestation certifie que " + nomStagiaire + " a effectué un stage au sein du service " + service + ".\n"))
                    .add(new Text("Le stage a débuté le " + dateDebut + " et s'est terminé le " + dateFin + ".\n\n"))
                    .add(new Text("Fait à Béni Mellal, le " + date + ".\n\n"))
                    .add(new Text("Signature: ____________________"));
            document.add(contenu);

            // Fermeture du document
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}

