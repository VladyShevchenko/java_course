package ru.stqa.pft.adressbook.model;

import java.io.File;
import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@XStreamAlias("contact")
@Entity
@Table (name = "addressbook")
public class ContactData {
    @XStreamOmitField
    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firsname;

    @Expose
    @Column(name = "lastname")
    private String lastname;

    @Expose
    @Type(type = "text")
    @Column(name = "home")
    private String home;

    @Expose
    @Type(type = "text")
    @Column(name ="mobile")
    private String mobile;

    @Expose
    @Type(type = "text")
    @Column(name ="work")
    private String work;

    @Expose
    @Type(type = "text")
    @Column(name = "address")
    private String address;

    @Expose
    @Type(type = "text")
    @Column(name = "email")
    private String email;

    @XStreamOmitField
    @Type(type = "text")
    @Column(name = "email2")
    private String email2;

    @XStreamOmitField
    @Type(type = "text")
    @Column(name = "email3")
    private String email3;

    @XStreamOmitField
    @Transient
    private String group;

    @XStreamOmitField
    @Transient
    private String allPhones;

    @XStreamOmitField
    @Transient
    private String allEmails;

    @XStreamOmitField
    @Transient
    private String allData;

    @Column(name = "photo")
    @Transient
    @Type(type = "text")
    private String photo;

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }



    public String getAllData() {
        return allData;
    }

    public ContactData withAllData(String allData) {
        this.allData = allData;
        return this;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirsname(String firsname) {
        this.firsname = firsname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withHomePhone(String home) {
        this.home = home;
        return this;
    }

    public ContactData withMobilePhone(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public ContactData withWorkPhone(String work) {
        this.work = work;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public String getFirsname() {
        return firsname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getHomePhone() {
        return home;
    }

    public String getMobilePhone() {
        return mobile;
    }

    public String getWorkPhone() {
        return work;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmai3() {
        return email3;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ContactData{" +

                "id=" + id +
                ", firstName='" + firsname + '\'' +
                ", lastName='" + lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        return firsname != null ? firsname.equals(that.firsname) : that.firsname == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firsname != null ? firsname.hashCode() : 0);
        return result;
    }


}
