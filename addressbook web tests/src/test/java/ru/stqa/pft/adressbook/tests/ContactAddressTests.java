package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase{

    @Test
    public void addressTest(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData addressInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(mergeAddress(addressInfoFromEditForm)));
    }

    private String mergeAddress(ContactData contact) {
        return Arrays.asList(contact.getAddress())
                .stream()
                .map(ContactAddressTests:: cleaned)
                .collect(Collectors.joining(""));
    }

    public static String cleaned(String address) {

        String a1= address.replaceAll("\\s+", " ");
        return a1.replaceAll("\\s","\n");
    }

}