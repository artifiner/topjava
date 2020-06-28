package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL_1_ID = START_SEQ + 2;
    public static final int MEAL_2_ID = START_SEQ + 3;
    public static final int MEAL_3_ID = START_SEQ + 4;
    public static final int MEAL_4_ID = START_SEQ + 5;
    public static final int MEAL_5_ID = START_SEQ + 6;
    public static final int MEAL_6_ID = START_SEQ + 7;
    public static final int MEAL_7_ID = START_SEQ + 8;
    public static final int MEAL_8_ID = START_SEQ + 9;
    public static final int MEAL_9_ID = START_SEQ + 10;
    public static final Meal MEAL_1_USER_JAN_30_BREAKFAST = new Meal(MEAL_1_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL_2_USER_JAN_30_LUNCH = new Meal(MEAL_2_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL_3_USER_JAN_30_DINNER = new Meal(MEAL_3_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL_4_USER_JAN_31_LIMIT = new Meal(MEAL_4_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL_5_USER_JAN_31_BREAKFAST = new Meal(MEAL_5_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static final Meal MEAL_6_USER_JAN_31_LUNCH = new Meal(MEAL_6_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static final Meal MEAL_7_USER_JAN_31_DINNER = new Meal(MEAL_7_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    public static final Meal MEAL_8_USER_FEB_1_DINNER = new Meal(MEAL_8_ID, LocalDateTime.of(2020, Month.FEBRUARY, 1, 20, 0), "Ужин", 700);
    public static final Meal MEAL_9_ADMIN_JAN_31_DINNER = new Meal(MEAL_9_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 18, 0), "Ужин админа", 1999);
    public static final LocalDate TEST_DATE = LocalDate.of(2020, Month.JANUARY, 31);

    public static Meal getNewMeal() {
        return new Meal(LocalDateTime.of(2020, Month.FEBRUARY, 1, 10, 0), "Завтрак", 700);
    }

    public static Meal getUpdatedMeal() {
        Meal updated = new Meal(MEAL_1_USER_JAN_30_BREAKFAST);
        updated.setDateTime(LocalDateTime.of(2020, Month.FEBRUARY, 23, 7, 0));
        updated.setDescription("UpdatedBreakfast");
        updated.setCalories(600);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }


}
