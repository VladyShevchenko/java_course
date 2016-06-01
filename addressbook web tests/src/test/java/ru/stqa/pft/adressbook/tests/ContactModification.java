package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

/**
 * Created by vlady on 01.06.16.
 */
public class ContactModification extends TestBase {

    @Test
    public void testContactModification() throws InterruptedException {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().EditContact();
        app.getContactHelper().fillContactForm(new ContactData("firstname1", "lastname1", "new@email.com", "+0987654321"));
        app.getContactHelper().SubmitUpdate();
        app.getNavigationHelper().gotoHomePage();
        Thread.sleep(2000);

    }
}
