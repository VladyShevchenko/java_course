package ru.stqa.pft.adressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

import java.io.File;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().contacts().size()==0) {
            app.goTo().HomePage();
            app.goTo().addNewContact();
            File photo = new File("src/test/resources/test.png");
            app.contact().create(new ContactData()
                    .withFirsname("firstname").withLastname("lastname").withAddress("Sunrise 32 St, NY, USA")
                    .withHomePhone("+1234567890").withEmail("test@email.com").withEmail2("email2@email.com").withEmail3("email3@email.com").withPhoto(photo));
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactModification() throws InterruptedException {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId())
                .withFirsname("NEWfirstname").withLastname("NEWlastname").
                        withEmail("new@email.com").withEmail2("updatedemail2@email.com").withEmail3("updatedemail3@email.com").withAddress("Sunrise 33 St, NY, USA")
                .withMobilePhone("+0987654321").withHomePhone("+12345678901").withWorkPhone("+0000000000");
        app.contact().modify(contact);
        app.goTo().HomePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
        verifyContactListInUI();
    }
}