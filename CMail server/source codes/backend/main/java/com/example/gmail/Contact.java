package com.example.gmail;

import java.util.ArrayList;

public class Contact {
    public ArrayList<String> getContactMails() {
        return contactEMails;
    }

    public void setContactEMails(ArrayList<String> contactMails) {
        this.contactEMails = contactMails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void addEmail(String Email)
    {
        this.contactEMails.add(Email);
    }
    private ArrayList<String> contactEMails = new ArrayList<>();
    private String name;
    private String id;

    @Override
    public String toString() {
        return "Contact{" +
                "contactEMails=" + contactEMails +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
    public String deleteEmails(ArrayList<String> emails)
    {
        int i = 0;
        for(String iterator: emails)
        {
            contactEMails.removeIf(it -> it.equals(iterator));
        }
        return "1";
    }
}
