package de.LucaR.Fahrradverleih.booking;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.stereotype.Service;

import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.ImageElement;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.elements.VerticalSpacer;
import rst.pdfbox.layout.elements.render.VerticalLayoutHint;
import rst.pdfbox.layout.text.BaseFont;

@Service
public class BookingMail {
	
	private final String EMAIL = "BikeRental@gmx.de";
	private final String PASSWORD = "26C$5&Mz2pfd36";
	
	public Message prepareMessageWithAttachment(Session session, String acc, String to, String subject, ByteArrayInputStream stream){
        try {
        	to.trim();
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(acc));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            Multipart multipart = new MimeMultipart();
            MimeBodyPart msg = new MimeBodyPart();
            MimeBodyPart pdf = new MimeBodyPart();
            DataSource dataSource = new ByteArrayDataSource(stream, "application/pdf");
            pdf.setDataHandler(new DataHandler(dataSource));
            pdf.setFileName("buchung.pdf");
            msg.setContent("<p>Vielen Dank für die Buchung! Im Anhang finden Sie ihre Bestätigung.</p>", "text/html");
            multipart.addBodyPart(msg);
            multipart.addBodyPart(pdf);
            message.setContent(multipart);

            return message;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean sendMailBooking(String to, String subject, String name, String bike) {
		try {

	    	to.trim();
	        Properties properties = new Properties();
	        properties.put("mail.smtp.auth",  "true");
	        properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.host", "mail.gmx.net");
	        properties.put("mail.smtp.port", "587");

	        Session session = Session.getInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(EMAIL, PASSWORD);
	            }
	        });

	        Message message;
			message = prepareMessageWithAttachment(session, EMAIL, to, subject, createDocument(name, bike));
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
    }
    
    public ByteArrayInputStream createDocument(String name, String bike) throws IOException {
    	
    	float hMargin = 40;
    	float vMargin = 50;
    	Document document = new Document(hMargin, hMargin, vMargin, vMargin);
    	
    	ImageElement image;
    	
//    	File f = new File("qr.jpg");
//		ByteArrayInputStream bis = new ByteArrayInputStream(qrcode);
//	    BufferedImage i = ImageIO.read(bis);
//	    ImageIO.write(i, "jpg", f);
//	    
//	    image = new ImageElement("qr.jpg");
//    	
//    	image.setWidth(image.getWidth()/6);
//    	image.setHeight(image.getHeight()/6);
//    	document.add(image, new VerticalLayoutHint(Alignment.Right, 0, 0,
//    		0, 0, true));
	    
    	File f2 = new File("logo.png");
	    BufferedImage i2 = ImageIO.read(new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWBK7mPYazRkf7H9e06kRHRfkhJTQSJGzr-6CQsR86w53Qs21--vJQdFkTy54HFznV330&usqp=CAU"));
	    ImageIO.write(i2, "png", f2);
    	
	    image = new ImageElement("logo.png");
    	
    	image.setWidth(image.getWidth()/4);
    	image.setHeight(image.getHeight()/4);
    	document.add(image, new VerticalLayoutHint());
    	
    	document.add(new VerticalSpacer(120));

    	Paragraph paragraph = new Paragraph();

    	paragraph = new Paragraph();
    	paragraph.addMarkup("*Buchung für "+bike+" *\n\n", 15,
    		BaseFont.Helvetica);
    	document.add(paragraph, new VerticalLayoutHint());

    	paragraph = new Paragraph();
    	paragraph.addMarkup("Vielen Dank für Ihre Buchung.\n\n", 13,
    		BaseFont.Helvetica);
    	document.add(paragraph);

    	paragraph = new Paragraph();
    	paragraph.addMarkup("Name:"+name+"\n", 13,
    		BaseFont.Helvetica);
    	document.add(paragraph);

    	paragraph = new Paragraph();
    	paragraph.addMarkup("Die AGBs sind auf der Website unter 'localhost:3000' zu sehen. Bitte besuchen Sie 'localhost:3000' für weitere Informationen.", 6,
    		BaseFont.Times);
    	paragraph.setAbsolutePosition(new rst.pdfbox.layout.text.Position(hMargin, vMargin));
    	document.add(paragraph);

        ByteArrayInputStream in;
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        document.save(out);
        in = new ByteArrayInputStream(out.toByteArray());
        
        return in;
    	
    }

}
