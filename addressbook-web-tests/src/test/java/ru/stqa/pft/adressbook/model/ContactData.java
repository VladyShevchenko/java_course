package ru.stqa.pft.adressbook.model;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable (name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData>groups = new HashSet<GroupData>();

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

    public int getId() {
        return id;
    }


    public Groups getGroups() {
        return new Groups(groups);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firsname != null ? !firsname.equals(that.firsname) : that.firsname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (home != null ? !home.equals(that.home) : that.home != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (work != null ? !work.equals(that.work) : that.work != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
        if (email3 != null ? !email3.equals(that.email3) : that.email3 != null) return false;

        if (allPhones != null ? !allPhones.equals(that.allPhones) : that.allPhones != null) return false;
        if (allEmails != null ? !allEmails.equals(that.allEmails) : that.allEmails != null) return false;
        return allData != null ? allData.equals(that.allData) : that.allData == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firsname != null ? firsname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (home != null ? home.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (work != null ? work.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (email3 != null ? email3.hashCode() : 0);
        result = 31 * result + (allPhones != null ? allPhones.hashCode() : 0);
        result = 31 * result + (allEmails != null ? allEmails.hashCode() : 0);
        result = 31 * result + (allData != null ? allData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "allData='" + allData + '\'' +
                ", allEmails='" + allEmails + '\'' +
                ", allPhones='" + allPhones + '\'' +
                ", email3='" + email3 + '\'' +
                ", email2='" + email2 + '\'' +
                ", email='" + email + '\'' +
                ", work='" + work + '\'' +
                ", address='" + address + '\'' +
                ", mobile='" + mobile + '\'' +
                ", home='" + home + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firsname='" + firsname + '\'' +
                ", id=" + id +
                '}';
    }


    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
