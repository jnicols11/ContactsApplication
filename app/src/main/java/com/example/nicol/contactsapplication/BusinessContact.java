package com.example.nicol.contactsapplication;

public class BusinessContact extends BaseContact{
    private String hours;
    private String url;

    public BusinessContact(String name, String phone, String address, String email, String id, String hours, String url) {
        super(name, phone, address, email, id);
        this.hours = hours;
        this.url = url;
    }

    public BusinessContact(){
        super("Some Business", "some number", "some address", "some email", "business");
        this.hours = "9am-5pm";
        this.url = "somewebsite.com";
    }


    //Setters and Getters
    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
