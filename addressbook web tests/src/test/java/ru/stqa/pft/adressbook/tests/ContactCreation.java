package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreation  extends TestBase {

    @Test
    public void testContactCreation() throws InterruptedException {
        Contacts before = app.contact().all();
        app.goTo().addNewContact();
        ContactData contact = new ContactData()
                .withFirsname("firstname").withLastname("lastname").withEmail("email@email.com").withPhone("+1234567890").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size() +1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

    @Test
    public void testBadContactCreation() throws InterruptedException {
        Contacts before = app.contact().all();
        app.goTo().addNewContact();
        ContactData contact = new ContactData()
                .withFirsname("firstname'").withLastname("lastname").withEmail("email@email.com").withPhone("+1234567890").withGroup("test1");
        app.contact().create(contact, true);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}
