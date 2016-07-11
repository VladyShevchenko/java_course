package ru.stqa.pft.adressbook.model;

public class ContactData {
    private int id = Integer.MAX_VALUE;
    private String firsname;
    private String lastname;
    private String email;
    private String phone;
    private String group;

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

    public int getId() {
        return id;
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

    public ContactData withId(int id) {
        this.id = id;
        return this;

    }

    public ContactData withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withFirsname(String firsname) {
        this.firsname = firsname;
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +

                "id=" + id +
                ", firstName='" + firsname + '\'' +
                ", lastName='" + lastname + '\'' +
                '}';
    }

}
