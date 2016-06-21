package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;


public class ContactDeletion extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().addNewContact();
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "email@email.com", "+1234567890", "test1"), true);
            app.getNavigationHelper().gotoHomePage();
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().DeleteContact();
        app.getContactHelper().ConfirmJsAlert();
        app.getNavigationHelper().gotoHomePage();
    }
}
