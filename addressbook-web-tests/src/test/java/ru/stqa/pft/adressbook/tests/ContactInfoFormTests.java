package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactInfoFormTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addNewContact();
            app.contact().create(new ContactData()
                    .withFirsname("firstname").withLastname("lastname").withAddress("Sunrise 32 St, NY, USA")
                    .withHomePhone("+1234567890").withMobilePhone("+(123)456-78-90").withWorkPhone("123-456-789-0").
                            withEmail("test@email.com").withEmail2("test2@email.com").withEmail3("test3@email.com"));
            app.goTo().HomePage();
        }
    }

    @Test (enabled = false)
    public void testContactCard() {
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromCardForm = app.contact().infoFromCardForm(contact);
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        assertThat(cleaned(contactInfoFromCardForm.getAllData()), equalTo((merged(contactInfoFromEditForm))));
    }

    public String merged (ContactData contact) {
        return Arrays.asList(
                contact.getFirsname(), contact.getLastname(), contact.getAddress(),
                contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                contact.getEmail(), contact.getEmail2(), contact.getEmai3())
                .stream()
                .map(ContactInfoFormTests::cleaned)
                .collect(Collectors.joining(""));
    }

    public static String cleaned(String contactData) {
        return contactData
                .replaceAll("H:", "").replaceAll("M:", "").replaceAll("W:", "")
                .replaceAll("(www.email.com)", "").replaceAll("[()]", "")
                .replaceAll("\n", "").replaceAll("\\s", "");
    }

}
