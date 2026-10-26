# Lab 5 — Event Registration (Java OOP)

Console application that manages event registrations using objects and an in-memory `ArrayList`.
Continues the Event Registration scenario from Lab 4.

## Requirements
- JDK 17 or later
- Gradle (wrapper included, or use the IDE)

## How to run
**From IntelliJ IDEA:** open the project, open `src/main/java/registration/Main.java` and click Run next to `main()`.

**From the terminal:**
```bash
./gradlew run --console=plain
```

## Classes
- **`Registration`** — a model of one registration. It has private fields (`id`, `fullName`, `email`, `studentGroup`, `eventTitle`), a constructor that sets all of them, getters, and `toString()`.
- **`RegistrationManager`** — owns the `ArrayList<Registration>` and performs all operations: add with validation and automatic unique IDs, show all, find by ID, delete by ID, and filter by event.
- **`Main`** — the console menu. It reads keyboard input with `Scanner`, handles invalid numbers with `try/catch`, and calls methods of `RegistrationManager`.

## Events
1. Web Design Workshop
2. JavaScript Quiz
3. IT Career Talk
