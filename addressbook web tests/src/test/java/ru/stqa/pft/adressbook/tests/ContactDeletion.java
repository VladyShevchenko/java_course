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


public class ContactDeletion extends TestBase {
    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addNewContact();
            app.contact().create(new ContactData()
                    .withFirsname("firstname").withLastname("lastname").withEmail("email@email.com").withHomePhone("+1234567890").withGroup("test1"), true);
            app.goTo().HomePage();
        }
    }

    @Test()
    public void testContactDeletion() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size() -1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before.without(deletedContact)));
    }
}
