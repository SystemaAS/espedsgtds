package no.systema.tds.admin.test;


import java.util.Properties;
import javax.mail.Session;

import no.systema.main.util.EmailUtil;

public class JavaMailSender {
	
	public static void main(String[] args) {
		
	    System.out.println("SimpleEmail Start");
		
	    String smtpHostServer = "10.13.3.22";
	    String emailID = "oscar.delatorre@wisetechglobal.com";
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", smtpHostServer);
	    Session session = Session.getInstance(props, null);
	    new EmailUtil().sendEmail(session, emailID,"SimpleEmail Testing Subject", "Tillfällig lagring", "/ownfiles/hello_world.pdf");
	    
	}

}
