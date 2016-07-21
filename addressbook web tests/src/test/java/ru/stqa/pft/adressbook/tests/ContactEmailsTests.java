package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactEmailsTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addNewContact();
            app.contact().create(new ContactData()
                    .withFirsname("firstname").withLastname("lastname").withAddress("Sunrise 32 St, NY, USA")
                    .withHomePhone("+1234567890").withMobilePhone("+(123)456-78-90").withWorkPhone("123-456-789-0").
                            withEmail("test@email.com").withEmail2("test2@email.com").withEmail3("test3@email.com").withGroup("test1"));
            app.goTo().HomePage();
        }
    }

    @Test
    public void EmailsTest(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData emailsInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllEmails(), equalTo(mergeEmails(emailsInfoFromEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmai3())
                .stream().filter((s) -> !s.equals(""))
                .map(ContactEmailsTests:: cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
