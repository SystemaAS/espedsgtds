package espedsgtds;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import no.systema.main.service.EmailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:test-configuration.xml")
//@TestPropertySource(locations="classpath:application-test.properties")

public class TesterEmailSender {
	
	@Autowired 
	EmailService emailService;
	
	@Test
	public void sendMail() throws Exception {
		System.out.println("OK");
		emailService.sendMail("oscar.delatorre@wisetechglobal.com", "Tillfällig lagring", false, "/ownfiles/hello_world.pdf");

	}
	
}
