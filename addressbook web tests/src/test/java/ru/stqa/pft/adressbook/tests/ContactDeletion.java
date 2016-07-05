package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.List;


public class ContactDeletion extends TestBase {

    @Test(enabled = false)
    public void testContactDeletion() {
        app.goTo().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.goTo().addNewContact();
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "email@email.com", "+1234567890", "test1"), true);
            app.goTo().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().selectContact(before.size()-1);
        app.getContactHelper().DeleteContact();
        app.getContactHelper().ConfirmJsAlert();
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        Assert.assertEquals(before, after);

    }
}
