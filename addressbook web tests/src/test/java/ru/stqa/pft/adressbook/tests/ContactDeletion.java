package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletion extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContact();
        app.getContactHelper().DeleteContact();
        app.getContactHelper().ConfirmJsAlert();
        app.getNavigationHelper().gotoHomePage();
    }
}
