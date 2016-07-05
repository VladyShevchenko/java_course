package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreation  extends TestBase {

    @Test(enabled = false)
    public void testContactCreation() throws InterruptedException {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.goTo().addNewContact();
        ContactData contact = new ContactData("firstname", "lastname", "email@email.com", "+1234567890", "test1");
        app.getContactHelper().createContact(contact, true);
        app.goTo().gotoHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }
}
