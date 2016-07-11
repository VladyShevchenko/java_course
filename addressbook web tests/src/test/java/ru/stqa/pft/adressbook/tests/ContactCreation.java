package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Set;

public class ContactCreation  extends TestBase {

    @Test
    public void testContactCreation() throws InterruptedException {
        Set<ContactData> before = app.contact().all();
        app.goTo().addNewContact();
        ContactData contact = new ContactData()
                .withFirsname("firstname").withLastname("lastname").withEmail("email@email.com").withPhone("+1234567890").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().HomePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);


        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}
