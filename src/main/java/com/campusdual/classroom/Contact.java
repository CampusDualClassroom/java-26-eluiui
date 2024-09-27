package com.campusdual.classroom;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;

public class Contact implements ICallActions {
    private String name;
    private String surnames;
    private String phone;
    private String code;

    public Contact(String name, String surnames, String phone) {
        this.name = name;
        this.surnames = surnames;
        this.phone = phone;
        this.code = generateCode(name, surnames);;
    }

    private String generateCode(String name, String surnames) {
        String normalizedName = normalizeString(name);
        String normalizedSurnames = normalizeString(surnames);

        StringBuilder code = new StringBuilder();
        Set<String> addedSurnames = new HashSet<>();

        appendFirstCharacter(code, normalizedName);
        appendSurnames(code, normalizedSurnames.split(" "), addedSurnames);

        return code.toString();
    }

    private String normalizeString(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase();
    }

    private void appendFirstCharacter(StringBuilder code, String normalizedName) {
        if (!normalizedName.isEmpty()) {
            code.append(normalizedName.charAt(0));
        }
    }

    private void appendSurnames(StringBuilder code, String[] surnameParts, Set<String> addedSurnames) {
        if (surnameParts.length == 0) return;

        if (surnameParts.length == 1) {
            addSurname(code, addedSurnames, surnameParts[0]);
        } else {
            for (int i = 0; i < surnameParts.length; i++) {
                String surnamePart = surnameParts[i];

                if (surnameParts.length > 3 && i >= 3) {
                    addSurname(code, addedSurnames, surnamePart);
                } else {
                    handleSurnamePart(code, addedSurnames, surnameParts, i);
                }
            }
            addLastSurname(code, addedSurnames, surnameParts);
        }
    }

    private void handleSurnamePart(StringBuilder code, Set<String> addedSurnames, String[] surnameParts, int index) {
        String surnamePart = surnameParts[index];
        if (surnamePart.length() < 4) {
            addSurname(code, addedSurnames, surnamePart);
        } else if (index < surnameParts.length - 1) {
            String firstChar = String.valueOf(surnamePart.charAt(0));
            if (!addedSurnames.contains(firstChar)) {
                code.append(firstChar);
                addedSurnames.add(firstChar);
            }
        }
    }

    private void addSurname(StringBuilder code, Set<String> addedSurnames, String surname) {
        if (!addedSurnames.contains(surname)) {
            code.append(surname);
            addedSurnames.add(surname);
        }
    }

    private void addLastSurname(StringBuilder code, Set<String> addedSurnames, String[] surnameParts) {
        String lastSurname = surnameParts[surnameParts.length - 1];
        addSurname(code, addedSurnames, lastSurname);
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getPhone() {
        return phone;
    }

    public String getCode() {
        return code;
    }

    @Override
    public void callMyNumber() {
        System.out.println("Llamando a " + this.name + " " + this.surnames + " al número " + this.phone);
    }

    @Override
    public void callOtherNumber(String number) {
        System.out.println("Llamando a " + number + " desde " + this.name + " " + this.surnames);
    }

    @Override
    public void showContactDetails() {
        System.out.println("Nombre: " + this.name);
        System.out.println("Apellidos: " + this.surnames);
        System.out.println("Teléfono: " + this.phone);
        System.out.println("Código: " + this.code);
    }
}
