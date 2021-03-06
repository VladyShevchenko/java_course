package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;


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
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmai3());
        attach(By.name("photo"), contactData.getPhoto());
    }

    public void fillContactWithoutPhotoForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirsname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmai3());


      if (creation) {
         if (contactData.getGroups().size() > 0)  {
             Assert.assertTrue(contactData.getGroups().size() == 1);
             new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
         }
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }
    public void modifyContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirsname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmai3());
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


    public void create(ContactData contact) {
        fillContactWithoutPhotoForm(contact, true);
        submitContactCreation();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        selectContactById(contact.getId());
        EditContact();
        modifyContactForm(contact);
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
            String allPhones = cells.get(5).getText();
            String allEmails = cells.get(4).getText();
            String adress = cells.get(3).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirsname(firstName).withLastname(lastName)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(adress));
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

    public ContactData infoFromCardForm(ContactData contact) {
        openContactCardById(contact.getId());
        String allData = wd.findElement(By.id("content")).getText();
        wd.navigate().back();
        return new ContactData().withAllData(allData);
    }

    private void openContactCardById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href= 'view.php?id=%s']", id))).click();
    }

    public void addToGroup(ContactData contact, GroupData group) {
        selectContactById(contact.getId());
        selectGroupForAdding(group.getName());
        click(By.name("add"));
        click(By.xpath(".//*[@id='content']/div/i/a"));
    }

    public void removeFromGroup(ContactData contact, GroupData group) {
        selectGroupForRemoval(group.getName());
        selectContactById(contact.getId());
        click(By.name("remove"));
        click(By.xpath(".//*[@id='content']/div/i/a"));
    }

    private void selectGroupForAdding(String groupName) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);

    }

    private void selectGroupForRemoval(String groupName) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);

    }
}

