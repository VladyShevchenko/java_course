package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by vlady on 01.06.16.
 */
public class ContactModification extends TestBase {

    @Test(enabled = false)
    public void testContactModification() throws InterruptedException {
        app.goTo().gotoHomePage();
        if (!app.getContactHelper().isThereAContact()) {
            app.goTo().addNewContact();
            app.getContactHelper().createContact(new ContactData("firstname", "lastname", "email@email.com", "+1234567890", "test1"), true);
            app.goTo().gotoHomePage();
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(1);
        app.getContactHelper().EditContact();
        ContactData contact = new ContactData(before.get(before.size() -1).getId(),"firstname", "lastname", "new@email.com", "+0987654321", null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().SubmitUpdate();
        app.goTo().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after, before);

        before.remove(before.size() - 1);
        before.add(contact);

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);

    }
}
