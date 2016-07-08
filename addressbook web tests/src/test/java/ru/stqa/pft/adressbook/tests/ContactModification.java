package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

/**
 * Created by vlady on 01.06.16.
 */
public class ContactModification extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().HomePage();
        if (app.contact().list().size() == 0) {
            app.goTo().addNewContact();
            app.contact().create(new ContactData()
                    .withFirsname("firstname").withLastname("lastname").withEmail("email@email.com").withPhone("+1234567890").withGroup("test1"), true);
            app.goTo().HomePage();
        }
    }

    @Test
    public void testContactModification() throws InterruptedException {
        List<ContactData> before = app.contact().list();
        int index = before.size() -1;
        ContactData contact = new ContactData().withId(before.get(index).getId())
                .withFirsname("firstname").withLastname("lastname").withEmail("new@email.com").withPhone("+0987654321").withGroup("test1");
        app.contact().modify(index, contact);
        app.goTo().HomePage();

        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after, before);

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);

    }
}
