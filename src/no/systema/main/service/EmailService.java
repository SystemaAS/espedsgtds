package no.systema.main.service;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.commons.lang3.StringUtils;
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
    
    @Value("${tlagring.smtp.host.server.port}")
    private String smtpPort;
    
    @Value("${tlagring.smtp.timeout}")
    private String smtpTimeout;
    
    //if auth is used
    @Value("${tlagring.smtp.auth.protocol}")
    private String smtpAuthProtocol;
    @Value("${tlagring.smtp.auth.user}")
    private String smtpAuthUser;
    @Value("${tlagring.smtp.auth.pwd}")
    private String smtpAuthPwd;
    
    
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
    public void sendMail(String to, String subject, String body) {
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
    	try{
	    	if(sendFileToTullverket==1){
		    	Properties props = System.getProperties();
			    props.put("mail.smtp.host", this.smtpHostServer);
			    props.put("mail.smtp.port", this.smtpPort);
			    props.put("mail.smtp.connectiontimeout", this.smtpTimeout);
			    props.put("mail.smtp.timeout", this.smtpTimeout);
			    
			    if(Integer.parseInt(smtpDebug)>0){
			    	props.put("mail.debug", "true");
			    }
			    
			    //START create session with or without authentication (check with SMTP-server systema administrator)
			    Session session = null;
			    if(StringUtils.isNotEmpty(this.smtpAuthUser) && StringUtils.isNotEmpty(this.smtpAuthPwd) && StringUtils.isNotEmpty(this.smtpAuthProtocol)){
			    	logger.warn("Smtp with Auth...");
			    	// ******************** FOR TLS ******************	
			    	if("tls".equals(this.smtpAuthProtocol.toLowerCase())){
			    		logger.warn("Smtp with TLS...");
			    		props.put("mail.smtp.starttls.enable", "true"); //TLS
			    		props.put("mail.smtp.auth", "true");
			    		
			    	}else{	
			    		// ******************** FOR SSL ******************
			    		logger.warn("Smtp with SSL...");
					    props.put("mail.smtp.auth", "true");
			            props.put("mail.smtp.socketFactory.port", this.smtpPort);
			            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			            
			    	}
			    	session = Session.getInstance(props, new javax.mail.Authenticator() {
			                    protected PasswordAuthentication getPasswordAuthentication() {
			                        return new PasswordAuthentication(smtpAuthUser, smtpAuthPwd);
			                    }
			                });
			    }else{
			    	logger.warn("Smtp without Auth...");
			    	props.put("mail.smtp.auth", "false");
			    	session = Session.getInstance(props, null);
			    }
			    //END session creation 
			    
			    String targetEmail = this.emailTullverketDtl;
			    if(avvik){ 
			    	targetEmail = this.emailTullverketAvvik; 
			    }
			    EmailUtil.sendEmail(session, targetEmail, subject, body, attachmentFileName);
	    	}
    	}catch (Exception e){
    		logger.error("ERROR SMTP:" + e.toString());
    		e.printStackTrace();		
    	}
    }
    
}
