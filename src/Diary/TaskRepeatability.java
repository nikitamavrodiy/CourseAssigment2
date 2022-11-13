package Diary;

import java.time.LocalDate;
import java.time.LocalDateTime;

public enum TaskRepeatability {
    ONE_TIME("однократная"),
    DAILY("ежедневная"),
    WEEKLY("еженедельная"),
    MONTHLY("ежемесячная"),
    ANNUAL("ежегодная");

    private final String name;

    TaskRepeatability(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Тип повторяемости" + name;
    }
}
