package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.adressbook.model.ContactData;


public class ContactHelper extends HelperBase {

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirsname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());
        type(By.name("home"), contactData.getPhone());

    }

    public void selectContact() {
        click(By.xpath("//input[@type=\"checkbox\"]"));
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





}
