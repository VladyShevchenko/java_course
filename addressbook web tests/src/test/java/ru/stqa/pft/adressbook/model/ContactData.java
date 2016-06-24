package ru.stqa.pft.adressbook.model;

public class ContactData {
    private int id;
    private final String firsname;
    private final String lastname;
    private final String email;
    private final String phone;
    private String group;

    public ContactData(int id, String firsname, String lastname, String email, String phone, String group) {
        this.id = id;
        this.firsname = firsname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.group = group;
    }



    public ContactData(String firsname, String lastname, String email, String phone, String group) {
        this.id = Integer.MAX_VALUE;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (firsname != null ? !firsname.equals(that.firsname) : that.firsname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;

    }

    @Override
    public int hashCode() {
        int result = firsname != null ? firsname.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
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
