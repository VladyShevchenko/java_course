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
        type(By.name("home"), contactData.getHomePhone());

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
        contactCache = null;
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        EditContact();
        fillContactForm(contact, false);
        contactCache = null;
        SubmitUpdate();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        DeleteContact();
        contactCache = null;
        ConfirmJsAlert();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//*[@name=\"selected[]\"]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;


    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }

        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();

            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirsname(firstName).withLastname(lastName));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirsname(firstname)
                .withLastname(lastname).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withAddress(address)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

}

