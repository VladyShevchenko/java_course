package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactData;


public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirsname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());
        type(By.name("home"), contactData.getPhone());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectContact() {
        click(By.xpath("//*[@name=\"selected[]\"]"));
    }

    public void DeleteContact() {
        click(By.xpath("//input[@value=\"Delete\"]"));
    }

    public void ConfirmJsAlert() {
        wd.switchTo().alert().accept();
    }

    public void EditContact() {
        click(By.xpath("//img[@title=\"Edit\"]"));
    }

    public void SubmitUpdate() {
        click(By.xpath("//input[@value=\"Update\"]"));
    }


    public void createContact(ContactData contact, boolean b) {
        fillContactForm(contact, b);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//*[@name=\"selected[]\"]"));
    }
}
