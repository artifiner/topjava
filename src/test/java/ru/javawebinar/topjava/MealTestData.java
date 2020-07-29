package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.Month;
import java.util.List;

import static java.time.LocalDateTime.of;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static TestMatcher<Meal> MEAL_MATCHER = TestMatcher.usingFieldsComparator(Meal.class, "user");

    public static final int NOT_FOUND = 10;
    public static final int MEAL1_ID = START_SEQ + 2;
    public static final int ADMIN_MEAL_ID = START_SEQ + 9;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 500);
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, of(2020, Month.JANUARY, 31, 13, 0), "Обед", 1000);
    public static final Meal MEAL7 = new Meal(MEAL1_ID + 6, of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 510);
    public static final Meal ADMIN_MEAL1 = new Meal(ADMIN_MEAL_ID, of(2020, Month.JANUARY, 31, 14, 0), "Админ ланч", 510);
    public static final Meal ADMIN_MEAL2 = new Meal(ADMIN_MEAL_ID + 1, of(2020, Month.JANUARY, 31, 21, 0), "Админ ужин", 1500);

    public static final MealTo MEAL1_TO = new MealTo(MEAL1.id(), MEAL1.getDateTime(), MEAL1.getDescription(), MEAL1.getCalories(), false);
    public static final MealTo MEAL2_TO = new MealTo(MEAL2.id(), MEAL2.getDateTime(), MEAL2.getDescription(), MEAL2.getCalories(), false);
    public static final MealTo MEAL3_TO = new MealTo(MEAL3.id(), MEAL3.getDateTime(), MEAL3.getDescription(), MEAL3.getCalories(), false);
    public static final MealTo MEAL4_TO = new MealTo(MEAL4.id(), MEAL4.getDateTime(), MEAL4.getDescription(), MEAL4.getCalories(), true);
    public static final MealTo MEAL5_TO = new MealTo(MEAL5.id(), MEAL5.getDateTime(), MEAL5.getDescription(), MEAL5.getCalories(), true);
    public static final MealTo MEAL6_TO = new MealTo(MEAL6.id(), MEAL6.getDateTime(), MEAL6.getDescription(), MEAL6.getCalories(), true);
    public static final MealTo MEAL7_TO = new MealTo(MEAL7.id(), MEAL7.getDateTime(), MEAL7.getDescription(), MEAL7.getCalories(), true);

    public static final List<Meal> MEALS = List.of(MEAL7, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1);
    public static final List<MealTo> MEAL_TOS = List.of(MEAL7_TO, MEAL6_TO, MEAL5_TO, MEAL4_TO, MEAL3_TO, MEAL2_TO, MEAL1_TO);
    public static final List<Meal> FILTERED_MEALS = List.of(MEAL2, MEAL1);
    public static final List<MealTo> FILTERED_MEAL_TOS = List.of(MEAL2_TO, MEAL1_TO);

    public static final String START_DATE = "2020-01-30";
    public static final String END_DATE = "2020-01-30";
    public static final String START_TIME = "00:00:00";
    public static final String END_TIME = "14:00:00";

    public static Meal getNew() {
        return new Meal(null, of(2020, Month.FEBRUARY, 1, 18, 0), "Созданный ужин", 300);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, MEAL1.getDateTime(), "Обновленный завтрак", 200);
    }
}
