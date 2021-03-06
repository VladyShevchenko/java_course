package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;


import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {

        app.mail().start();
    }
    @Test
    public void testRegistration () throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String email = String.format("user%s@localhost.localdomain", now);
        app.mantis().SignUp(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String confirmation = app.mail().GetURL(mailMessages, email);
        app.mantis().setNewPassword(confirmation, password);
        assertTrue(app.newSession().login(user,password));
    }


    @AfterMethod
    public void stopMailServer() {
        app.mail().stop();
    }
}
