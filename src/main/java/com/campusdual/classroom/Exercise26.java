package com.campusdual.classroom;

import com.campusdual.util.Utils;

public class Exercise26 {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        boolean running = true;

        while (running) {
            System.out.println("Menú:");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Seleccionar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("5. Salir");
            int opcion = Utils.integer("Selecciona una opción: ");

            switch (opcion) {
                case 1:
                    String nombre = Utils.string("Introduce el nombre: ");
                    String apellidos = Utils.string("Introduce los apellidos: ");
                    String numeroTelefono = Utils.string("Introduce el número de teléfono: ");
                    Contact contacto = new Contact(nombre, apellidos, numeroTelefono);
                    phonebook.addContact(contacto);
                    break;
                case 2:
                    phonebook.showPhonebook();
                    break;
                case 3:
                    String codigoSeleccionado = Utils.string("Introduce el código del contacto: ");
                    phonebook.selectContact(codigoSeleccionado);
                    break;
                case 4:
                    String codigoEliminar = Utils.string("Introduce el código del contacto a eliminar: ");
                    phonebook.deleteContact(codigoEliminar);
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}