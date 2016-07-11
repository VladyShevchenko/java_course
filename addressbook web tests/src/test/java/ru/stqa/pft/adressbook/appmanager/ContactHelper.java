package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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


    public void create(ContactData contact, boolean b) {
        fillContactForm(contact, b);
        submitContactCreation();
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        EditContact();
        fillContactForm(contact, false);
        SubmitUpdate();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        DeleteContact();
        ConfirmJsAlert();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//*[@name=\"selected[]\"]"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();

        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();

            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirsname(firstName).withLastname(lastName));
        }
        return contacts;
    }


}

