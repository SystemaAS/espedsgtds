package no.systema.main.service;

import java.util.Properties;

import javax.mail.Session;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import no.systema.main.util.EmailUtil;


@Service
public class EmailService {
	
    @Autowired
    private JavaMailSender mailSender;
      
    @Value("${tlagring.smtp.host.server}")
    private String smtpHostServer;
    
    @Value("${tlagring.smtp.debug}")
    private String smtpDebug;
	
	@Value("${tlagring.smtp.email.dtl.tullverket}")
    private String emailTullverketDtl;
	
	@Value("${tlagring.smtp.email.dtl.avvikelser.tullverket}")
    private String emailTullverketAvvik;
	
	@Value("${tlagring.smtp.send.file.to.tullverket}")
    private Integer sendFileToTullverket;
	
	private static final Logger logger = Logger.getLogger(EmailService.class.getName());
    
   /**
    * Uses Spring Mail ...not working ??
    * @param to
    * @param subject
    * @param body
    */
    public void sendMail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
    
    /**
     * Uses Java Mail API directly 
     * @param subject
     * @param body
     * @param avvik
     */
    public void sendMail(String subject, String body, boolean avvik, String attachmentFileName){
    	if(sendFileToTullverket==1){
	    	Properties props = System.getProperties();
		    props.put("mail.smtp.host", this.smtpHostServer);
		    if(Integer.parseInt(smtpDebug)>0){
		    	props.put("mail.debug", "true");
		    }
		    Session sessionInstance = Session.getInstance(props, null);
		    String targetEmail = this.emailTullverketDtl;
		    if(avvik){ 
		    	targetEmail = this.emailTullverketAvvik; 
		    }
		    EmailUtil.sendEmail(sessionInstance, targetEmail, subject, body, attachmentFileName);
    	}
    }
  
    
}
