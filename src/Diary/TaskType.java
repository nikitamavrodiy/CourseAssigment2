package Diary;

public enum TaskType {
    PERSONAL_TASK("Личная задача"),
    WORK_TASK("Рабочая задача");

    private final String name;

    TaskType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Тип задачи: " + name;
    }
}
