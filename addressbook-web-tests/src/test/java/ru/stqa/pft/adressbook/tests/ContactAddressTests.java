package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions () {
        app.goTo().HomePage();
        if (app.contact().all().size() == 0) {
            app.goTo().addNewContact();
            File photo = new File("src/test/resources/test.png");
            app.contact().create(new ContactData()
                    .withFirsname("firstname").withLastname("lastname").withAddress("Sunrise 32 St, NY, USA")
                        .withHomePhone("+1234567890").withMobilePhone("+(123)456-78-90").withWorkPhone("123-456-789-0").
                            withEmail("test@email.com").withEmail2("test2@email.com").withEmail3("test3@email.com").withPhoto(photo));
            app.goTo().HomePage();
        }
    }

    @Test (enabled = false)
    public void addressTest(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData addressInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(mergeAddress(addressInfoFromEditForm)));
    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream()
                .collect(Collectors.joining(""));
    }
}