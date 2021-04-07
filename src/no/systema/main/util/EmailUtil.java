package no.systema.main.util;

import java.io.File;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;



public class EmailUtil {
	private static final Logger logger = Logger.getLogger(EmailUtil.class.getName());
	
	public void sendEmail(Session session, String toEmail, String subject, String body, String attachmentFileName){
		try
	    {
	      MimeMessage msg = new MimeMessage(session);
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");

	      msg.setFrom(new InternetAddress("noreply@systema.no", "NoReply"));
	      msg.setReplyTo(InternetAddress.parse("noreply@systema.no", false));
	      msg.setSubject(subject, "UTF-8");
	      msg.setSentDate(new Date());
	      msg.setRecipients(RecipientType.TO, InternetAddress.parse(toEmail, false));
	      
	      if(StringUtils.isEmpty(attachmentFileName)){
	    	  msg.setText(body, "UTF-8");
	      }else{
	    	  if(new File(attachmentFileName).exists()){
			      //Attachment
			      // Create the message body part
		          BodyPart messageBodyPart = new MimeBodyPart();
		          // Fill the message
		          messageBodyPart.setText(body);
		         
		          // Create a multipart message for attachment
		          Multipart multipart = new MimeMultipart();
		          // Set text message part
		          multipart.addBodyPart(messageBodyPart);
		          // Second part is attachment
		          messageBodyPart = new MimeBodyPart();
		          DataSource source = new FileDataSource(attachmentFileName);
		          messageBodyPart.setDataHandler(new DataHandler(source));
		          messageBodyPart.setFileName(FilenameUtils.getName(attachmentFileName));
		          
		          multipart.addBodyPart(messageBodyPart);
		          // Send the complete message parts
		          msg.setContent(multipart);
	    	  }else{
	    		  msg.setText("File to attach does not exist ???", "UTF-8");
	    	  }
	      }
	      
	      logger.warn("Message is ready... preparing to send email");
    	  Transport.send(msg); 
	      logger.warn("EMail Sent Successfully!!");
	      
	    }
	    catch (Exception e) {
	      e.printStackTrace();
	    }
		
	}
	
}
