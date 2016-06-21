package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

/**
 * Created by vlady on 01.06.16.
 */
public class ContactModification extends TestBase {

    @Test
    public void testContactModification() throws InterruptedException {
        app.getNavigationHelper().gotoHomePage();
        int before = app.getGroupHelper().getGroupCount();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().addNewContact();
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "email@email.com", "+1234567890", "test1"), true);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().EditContact();
        app.getContactHelper().fillContactForm(new ContactData("firstname1", "lastname1", "new@email.com", "+0987654321", null), false);
        app.getContactHelper().SubmitUpdate();
        app.getNavigationHelper().gotoHomePage();
        int after = app.getGroupHelper().getGroupCount();
        Assert.assertEquals(after, before);
        Thread.sleep(2000);

    }
}
