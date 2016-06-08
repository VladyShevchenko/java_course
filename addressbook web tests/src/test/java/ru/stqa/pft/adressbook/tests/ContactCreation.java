package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

public class ContactCreation  extends TestBase {

    @Test
    public void testContactCreation() throws InterruptedException {
        app.getNavigationHelper().addNewContact();
        app.getContactHelper().fillContactForm(new ContactData("firstname", "lastname", "email@email.com", "+1234567890", "test1"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHomePage();
        Thread.sleep(2000);
    }
}
