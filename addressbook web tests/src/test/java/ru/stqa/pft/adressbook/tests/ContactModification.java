package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Set;

public class ContactModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addNewContact();
            app.contact().create(new ContactData()
                    .withFirsname("firstname").withLastname("lastname").withEmail("email@email.com").withPhone("+1234567890").withGroup("test1"), true);
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactModification() throws InterruptedException {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirsname("firstname").withLastname("lastname").withEmail("new@email.com").withPhone("+0987654321").withGroup("test1");
        app.contact().modify(contact);
        app.goTo().HomePage();

        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after, before);

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);

    }
}