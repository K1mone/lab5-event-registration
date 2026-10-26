package registration;

import java.util.Scanner;

/**
 * Консольный интерфейс: показывает меню, читает ввод с клавиатуры
 * и вызывает методы объекта RegistrationManager.
 * Данные хранит не Main, а manager.
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final RegistrationManager manager = new RegistrationManager();

    public static void main(String[] args) {
        boolean running = true;
        // Меню повторяется, пока пользователь не выберет 0.
        while (running) {
            printMenu();
            Integer option = readInt("Choose an option: ");
            if (option == null) {
                continue; // ввели не число — сообщение уже выведено, показываем меню снова
            }
            switch (option) {
                case 1 -> handleAdd();
                case 2 -> manager.showAll();
                case 3 -> handleFind();
                case 4 -> handleDelete();
                case 5 -> handleShowByEvent();
                case 0 -> running = false;
                default -> System.out.println("Unknown option. Please choose 0-5.");
            }
            System.out.println();
        }
        System.out.println("Goodbye!");
    }

    private static void printMenu() {
        System.out.println("1. Add registration");
        System.out.println("2. Show all registrations");
        System.out.println("3. Find registration by ID");
        System.out.println("4. Delete registration by ID");
        System.out.println("5. Show registrations for an event");
        System.out.println("0. Exit");
    }

    /**
     * Читает строку и превращает её в число.
     * try/catch перехватывает NumberFormatException, если введены буквы,
     * поэтому программа не падает. В этом случае возвращается null.
     */
    private static Integer readInt(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine(); // nextLine() читает всю строку, включая пробелы
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            System.out.println("Please enter a whole number.");
            return null;
        }
    }

    // Выбор мероприятия по номеру. Повторяет запрос, пока не введено 1-3.
    private static int readEventNumber() {
        while (true) {
            for (int i = 0; i < RegistrationManager.EVENTS.length; i++) {
                System.out.println("  " + (i + 1) + ". " + RegistrationManager.EVENTS[i]);
            }
            Integer number = readInt("Select event: ");
            if (number == null) {
                continue;
            }
            if (RegistrationManager.isValidEventNumber(number)) {
                return number;
            }
            System.out.println("Event must be a number from 1 to 3.");
        }
    }

    // Каждое поле запрашивается заново, пока не будет корректным.
    // Объект создаётся только после того, как все поля проверены.
    private static void handleAdd() {
        String name;
        while (true) {
            System.out.print("Full name: ");
            name = scanner.nextLine();
            if (RegistrationManager.isValidName(name)) break;
            System.out.println("Name must contain at least 3 characters.");
        }

        String email;
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (RegistrationManager.isValidEmail(email)) break;
            System.out.println("Email must contain '@' with text before and after it.");
        }

        String group;
        while (true) {
            System.out.print("Student group: ");
            group = scanner.nextLine();
            if (RegistrationManager.isValidGroup(group)) break;
            System.out.println("Student group must not be blank.");
        }

        int eventNumber = readEventNumber();

        Registration created = manager.addRegistration(name, email, group, eventNumber);
        if (created != null) {
            System.out.println("Registration created. ID: " + created.getId());
        } else {
            System.out.println("Registration was not saved: invalid data.");
        }
    }

    private static void handleFind() {
        Integer id = readInt("Enter ID: ");
        if (id == null) {
            return;
        }
        Registration found = manager.findById(id);
        if (found != null) {
            System.out.println(found);
        } else {
            System.out.println("Registration not found.");
        }
    }

    private static void handleDelete() {
        Integer id = readInt("Enter ID: ");
        if (id == null) {
            return;
        }
        if (manager.deleteById(id)) {
            System.out.println("Registration " + id + " deleted.");
        } else {
            System.out.println("Registration not found. Nothing was deleted.");
        }
    }

    private static void handleShowByEvent() {
        int eventNumber = readEventNumber();
        manager.showByEvent(eventNumber);
    }
}
