package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreation  extends TestBase {

    @Test
    public void testContactCreation() throws InterruptedException {
        List<ContactData> before = app.contact().list();
        app.goTo().addNewContact();
        ContactData contact = new ContactData()
                .withFirsname("firstname").withLastname("lastname").withEmail("email@email.com").withPhone("+1234567890").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().HomePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);

        Assert.assertEquals(before, after);
    }
}
