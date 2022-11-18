package diary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Task {
    private final String taskName;
    private final String taskDescription;
    private final TaskType taskType;
    private final LocalDateTime taskDateTime;
    private final TaskRepeatability taskRepeatability;
    private final int id;

    public static int counter = 0;

    public Task(String taskName, String taskDescription, TaskType taskType, LocalDateTime taskDateTime , TaskRepeatability taskRepeatability) {
        this.taskName = checkField(taskName);
        this.taskDescription = checkField(taskDescription);
        this.taskType = checkField(taskType);
        this.taskDateTime = taskDateTime;
        this.taskRepeatability = checkField(taskRepeatability);
        id = counter + 1;
        counter++;
    }

    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public boolean appearsIn(LocalDate localDate) {
        boolean isAppears;
        switch (taskRepeatability) {
            case DAILY ->
                    isAppears = taskDateTime.toLocalDate().equals(localDate)
                            || taskDateTime.toLocalDate().isBefore(localDate);
            case WEEKLY ->
                    isAppears = taskDateTime.toLocalDate().equals(localDate)
                            || (taskDateTime.toLocalDate().isBefore(localDate)
                            && localDate.getDayOfWeek().equals(taskDateTime.getDayOfWeek()));
            case MONTHLY ->
                    isAppears =  taskDateTime.toLocalDate().equals(localDate)
                            || (taskDateTime.toLocalDate().isBefore(localDate)
                            && localDate.getDayOfMonth() == taskDateTime.getDayOfMonth());

            case ANNUAL ->
                    isAppears = taskDateTime.toLocalDate().isBefore(localDate)
                            && localDate.getDayOfMonth() == taskDateTime.getDayOfMonth()
                            && localDate.getMonth() == taskDateTime.getMonth();
            default -> isAppears =  taskDateTime.toLocalDate().equals(localDate);
        }
        return isAppears;
    }

    private String checkField(String field) {
        if (field == null || field.isBlank()) {
            throw new IllegalArgumentException("Ошибка: введены не все данные.");
        }
        return field;
    }

    private TaskType checkField(TaskType field) {
        if (field == null || field.getName().isBlank()) {
            throw new IllegalArgumentException("Ошибка: введены не все данные.");
        }
        return field;
    }

    private TaskRepeatability checkField(TaskRepeatability field) {
        if (field == null || field.getName().isBlank()) {
            throw new IllegalArgumentException("Ошибка: введены не все данные.");
        }
        return field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskName, task.taskName) && Objects.equals(taskDescription, task.taskDescription) && taskType == task.taskType && Objects.equals(taskDateTime, task.taskDateTime) && taskRepeatability == task.taskRepeatability;
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName, taskDescription, taskType, taskDateTime, taskRepeatability);
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public TaskRepeatability getTaskRepeatability() {
        return taskRepeatability;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return taskName;
    }

}
