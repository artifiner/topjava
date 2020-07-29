package ru.javawebinar.topjava.to;

import java.time.LocalDateTime;
import java.util.Objects;

public class MealTo {
    private Integer id;

    private LocalDateTime dateTime;

    private String description;

    private int calories;

    private boolean excess;

    public MealTo() {

    }

    public MealTo(Integer id, LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExcess() {
        return excess;
    }

    @Override
    public String toString() {
        return "MealTo{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + excess +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MealTo)) {
            return false;
        }
        MealTo that = (MealTo) obj;
        return Objects.equals(id, that.id)
                && Objects.equals(dateTime, that.dateTime)
                && Objects.equals(description, that.description)
                && (calories == that.calories)
                && (excess == that.excess);
    }

    @Override
    public int hashCode() {
        int hashCode = 42;
        hashCode = hashCode * 37 + (id == null ? 0 : id.hashCode());
        hashCode = hashCode * 37 + (dateTime == null ? 0 : dateTime.hashCode());
        hashCode = hashCode * 37 + (description == null ? 0 : description.hashCode());
        hashCode = hashCode * 37 + calories;
        hashCode = hashCode * 37 + (excess ? 0 : 1);
        return hashCode;
    }
}
