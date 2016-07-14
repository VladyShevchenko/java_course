package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addNewContact();
            app.contact().create(new ContactData()
                    .withFirsname("firstname").withLastname("lastname").withAddress("Sunrise 32 St, NY, USA")
                    .withHomePhone("+1234567890").withMobilePhone("+(123)456-78-90").withWorkPhone("123-456-789-0").
                            withEmail("test@email.com").withEmail2("test2@email.com").withEmail3("test3@email.com").withGroup("test1"), true);
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactModification() throws InterruptedException {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirsname("firstname").withLastname("lastname").withEmail("new@email.com").withHomePhone("+0987654321").withGroup("test1");
        app.contact().modify(contact);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}