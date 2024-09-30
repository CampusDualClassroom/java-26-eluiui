package com.campusdual.classroom;

import java.util.HashMap;
import java.util.Map;

public class Phonebook {
    private Map<String, Contact> contacts;

    public Phonebook() {
        this.contacts = new HashMap<>();
    }

    public Map<String, Contact> getData() {
        return contacts;
    }

    public void addContact(Contact contact) {
        contacts.put(contact.getCode(), contact);
    }

    public void deleteContact(String code) {
        contacts.remove(code);
    }

    public void showPhonebook() {
        for (Contact contact : contacts.values()) {
            contact.showContactDetails();
        }
    }
    public void selectContact(String code) {
        if (contacts.containsKey(code)) {
            Contact selectedContact = contacts.get(code);
            selectedContact.contactMenu(selectedContact);
        } else {
            System.out.println("No se encontró ningún contacto con el código: " + code);
        }
    }
}