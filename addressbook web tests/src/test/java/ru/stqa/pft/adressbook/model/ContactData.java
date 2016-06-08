package ru.stqa.pft.adressbook.model;

public class ContactData {
    private final String firsname;
    private final String lastname;
    private final String email;
    private final String phone;
    private String group;

    public ContactData(String firsname, String lastname, String email, String phone, String group) {
        this.firsname = firsname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.group = group;
    }

    public String getFirsname() {
        return firsname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getGroup() {
        return group;
    }
}
