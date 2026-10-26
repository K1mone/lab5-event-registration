package registration;

/**
 * Одна регистрация студента на мероприятие.
 * Класс — это "шаблон", а каждый new Registration(...) — отдельный объект.
 */
public class Registration {

    // Поля private: изменить их снаружи напрямую нельзя (инкапсуляция).
    // Доступ только на чтение через геттеры ниже.
    private int id;
    private String fullName;
    private String email;
    private String studentGroup;
    private String eventTitle;

    // Конструктор вызывается при создании объекта и заполняет все пять полей.
    public Registration(int id, String fullName, String email, String studentGroup, String eventTitle) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.studentGroup = studentGroup;
        this.eventTitle = eventTitle;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getStudentGroup() {
        return studentGroup;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    // Читаемое представление объекта для вывода в консоль.
    @Override
    public String toString() {
        return id + " | " + fullName + " | " + email + " | " + studentGroup + " | " + eventTitle;
    }
}
