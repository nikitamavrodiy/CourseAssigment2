import diary.Task;
import diary.TaskRepeatability;
import diary.TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class DiaryService {
    public static Map<Integer, Task> diary = new HashMap<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            // todo: обрабатываем пункт меню 2
                            deleteTask(scanner);
                            break;
                        case 3:
                            // todo: обрабатываем пункт меню 3
                            getTasksForDay(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.nextLine() + scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String taskDescription = scanner.nextLine();
        System.out.print("Введите тип задачи(1 - персональная, 2 - рабочая): ");
        TaskType taskType = null;
        if (scanner.hasNextInt()) {
            int type = scanner.nextInt();
            if (type == 1 || type == 2) {
                taskType = type == 1 ? TaskType.PERSONAL_TASK : TaskType.WORK_TASK;
            }
            scanner.nextLine();
        }
        System.out.print("Введите дату выполнения(в формате dd.MM.yyyy HH:mm): ");
        String time = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime dateTime = null;
        try {
            dateTime = LocalDateTime.parse(time, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка: Неправильный формат даты");
            return;
        }

        System.out.println("1 - однократная\n2 - ежедневная\n3 - еженедельная\n4 - ежемесячная\n5 - ежегодная\n" +
                "Выберите тип повторяемости задачи: ");
        TaskRepeatability taskRepeatability = null;
        switch (scanner.nextInt()) {
            case 1 -> taskRepeatability = TaskRepeatability.ONE_TIME;
            case 2 -> taskRepeatability = TaskRepeatability.DAILY;
            case 3 -> taskRepeatability = TaskRepeatability.WEEKLY;
            case 4 -> taskRepeatability = TaskRepeatability.MONTHLY;
            case 5 -> taskRepeatability = TaskRepeatability.ANNUAL;
            default -> {
                System.out.println("Неправильно выбраны данные");
                return;
            }
        }
        scanner.nextLine();
        Task task = new Task(taskName, taskDescription, taskType, dateTime, taskRepeatability);
        diary.put(task.getId(), task);
    }

    private static void printMenu() {
        System.out.println(
                """
                        1. Добавить задачу
                        2. Удалить задачу
                        3. Получить задачу на указанный день
                        0. Выход
                        """
        );
    }

    private static void deleteTask(Scanner scanner) {
        System.out.print("Введите id задачи, которую хотите удалить: ");
        scanner.nextLine();
        int id = scanner.nextInt();
        diary.remove(id);
        System.out.println("Задача id"+id+" удалена.");
        scanner.nextLine();
    }

    private static void getTasksForDay(Scanner scanner) {
        System.out.print("Введите дату(в формате dd.MM.yyyy): ");
        String stringDate = scanner.nextLine() + scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        LocalDate inputDate = null;
        try {
            inputDate = LocalDate.parse(stringDate, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка: Неправильный формат даты");
            return;
        }
        for (Map.Entry<Integer,Task> entry : diary.entrySet()) {
            if (entry.getValue().getTaskDateTime().toLocalDate().equals(inputDate)) {
                System.out.println("_ID=" + entry.getKey() + " " + entry.getValue().getTaskName());
            } else if (entry.getValue().appearsIn(inputDate)) {
                System.out.println("_ID=" + entry.getKey() + " " + entry.getValue().getTaskName());
            }
        }

    }

}