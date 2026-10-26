package registration;

import java.util.ArrayList;

/**
 * Хранит все регистрации и выполняет операции над ними.
 * Этот класс владеет данными; с клавиатурой он не работает (это делает Main).
 */
public class RegistrationManager {

    // Доступные мероприятия (те же, что на странице из Lab 4).
    public static final String[] EVENTS = {
            "Web Design Workshop",
            "JavaScript Quiz",
            "IT Career Talk"
    };

    // ArrayList — список, который сам увеличивается при добавлении,
    // поэтому заранее не нужно знать количество регистраций.
    private ArrayList<Registration> registrations = new ArrayList<>();

    // Следующий свободный ID. Только растёт и никогда не уменьшается,
    // поэтому после удаления ID повторно не используется.
    private int nextId = 1;

    // ---------- Валидация ----------

    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= 3;
    }

    // Упрощённая проверка: есть '@', и до и после него есть текст.
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        String value = email.trim();
        int at = value.indexOf('@');
        return at > 0 && at < value.length() - 1;
    }

    public static boolean isValidGroup(String group) {
        return group != null && !group.trim().isEmpty();
    }

    // Номер мероприятия в меню: от 1 до 3.
    public static boolean isValidEventNumber(int number) {
        return number >= 1 && number <= EVENTS.length;
    }

    // ---------- Операции ----------

    /**
     * Добавляет регистрацию, если все поля корректны.
     * Возвращает созданный объект или null, если данные неверные
     * (так неполная или ошибочная запись никогда не попадёт в список).
     */
    public Registration addRegistration(String fullName, String email, String studentGroup, int eventNumber) {
        if (!isValidName(fullName) || !isValidEmail(email)
                || !isValidGroup(studentGroup) || !isValidEventNumber(eventNumber)) {
            return null;
        }
        String eventTitle = EVENTS[eventNumber - 1];
        Registration registration = new Registration(
                nextId, fullName.trim(), email.trim(), studentGroup.trim(), eventTitle);
        registrations.add(registration);
        nextId++;
        return registration;
    }

    public void showAll() {
        if (registrations.isEmpty()) {
            System.out.println("No registrations yet.");
            return;
        }
        for (Registration r : registrations) {
            System.out.println(r);
        }
    }

    /**
     * Поиск по ID: перебираем список циклом и сравниваем ID каждого объекта.
     * Если совпадений нет — возвращаем null (это обычная ситуация, не исключение).
     */
    public Registration findById(int id) {
        for (Registration r : registrations) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    /**
     * Удаление по ID. Сначала находим индекс обычным for,
     * затем удаляем по индексу — вне цикла перебора.
     * (Удалять из ArrayList внутри for-each нельзя: будет ConcurrentModificationException.)
     */
    public boolean deleteById(int id) {
        int index = -1;
        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return false;
        }
        registrations.remove(index);
        return true;
    }

    // Выводит только регистрации на выбранное мероприятие.
    public void showByEvent(int eventNumber) {
        String eventTitle = EVENTS[eventNumber - 1];
        boolean found = false;
        for (Registration r : registrations) {
            if (r.getEventTitle().equals(eventTitle)) {
                System.out.println(r);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No registrations for \"" + eventTitle + "\".");
        }
    }
}
