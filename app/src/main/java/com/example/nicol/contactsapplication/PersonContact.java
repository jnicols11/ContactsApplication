package com.example.nicol.contactsapplication;

public class PersonContact extends BaseContact {
    private String birthday;
    public PersonContact(String name, String phone, String address, String email, String id, String birthday) {
        super(name, phone, address, email, id);
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
