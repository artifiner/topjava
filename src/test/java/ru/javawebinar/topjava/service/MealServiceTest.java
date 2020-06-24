package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_1_ID, USER_ID);
        assertMatch(meal, MEAL_1_USER_JAN_30_BREAKFAST);
    }

    @Test
    public void getWrongUser() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_1_ID, ADMIN_ID));
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void delete() {
        service.delete(MEAL_1_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> service.get(MEAL_1_ID, USER_ID));
    }

    @Test
    public void deleteWrongUser() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_1_ID, ADMIN_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void getBetweenInclusiveRange() {
        assertMatch(service.getBetweenInclusive(TEST_DATE, TEST_DATE, USER_ID),
                MEAL_7_USER_JAN_31_DINNER,
                MEAL_6_USER_JAN_31_LUNCH,
                MEAL_5_USER_JAN_31_BREAKFAST,
                MEAL_4_USER_JAN_31_LIMIT);
    }

    @Test
    public void getBetweenInclusiveAfter() {
        assertMatch(service.getBetweenInclusive(TEST_DATE, null, USER_ID),
                MEAL_8_USER_FEB_1_DINNER,
                MEAL_7_USER_JAN_31_DINNER,
                MEAL_6_USER_JAN_31_LUNCH,
                MEAL_5_USER_JAN_31_BREAKFAST,
                MEAL_4_USER_JAN_31_LIMIT);
    }

    @Test
    public void getBetweenInclusiveBefore() {
        assertMatch(service.getBetweenInclusive(null, TEST_DATE, USER_ID),
                MEAL_7_USER_JAN_31_DINNER,
                MEAL_6_USER_JAN_31_LUNCH,
                MEAL_5_USER_JAN_31_BREAKFAST,
                MEAL_4_USER_JAN_31_LIMIT,
                MEAL_3_USER_JAN_30_DINNER,
                MEAL_2_USER_JAN_30_LUNCH,
                MEAL_1_USER_JAN_30_BREAKFAST);
    }

    @Test
    public void getBetweenInclusiveNoFilter() {
        assertMatch(service.getBetweenInclusive(null, null, USER_ID),
                MEAL_8_USER_FEB_1_DINNER,
                MEAL_7_USER_JAN_31_DINNER,
                MEAL_6_USER_JAN_31_LUNCH,
                MEAL_5_USER_JAN_31_BREAKFAST,
                MEAL_4_USER_JAN_31_LIMIT,
                MEAL_3_USER_JAN_30_DINNER,
                MEAL_2_USER_JAN_30_LUNCH,
                MEAL_1_USER_JAN_30_BREAKFAST);
    }

    @Test
    public void getAll() {
        assertMatch(service.getAll(USER_ID),
                MEAL_8_USER_FEB_1_DINNER,
                MEAL_7_USER_JAN_31_DINNER,
                MEAL_6_USER_JAN_31_LUNCH,
                MEAL_5_USER_JAN_31_BREAKFAST,
                MEAL_4_USER_JAN_31_LIMIT,
                MEAL_3_USER_JAN_30_DINNER,
                MEAL_2_USER_JAN_30_LUNCH,
                MEAL_1_USER_JAN_30_BREAKFAST);
    }

    @Test
    public void update() {
        Meal updatedMeal = getUpdatedMeal();
        service.update(updatedMeal, USER_ID);
        assertMatch(service.get(MEAL_1_ID, USER_ID), updatedMeal);
    }

    @Test
    public void updateWrongUser() {
        Meal updatedMeal = getUpdatedMeal();
        assertThrows(NotFoundException.class, () -> service.update(updatedMeal, ADMIN_ID));
    }

    @Test
    public void updateNotFound() {
        Meal updatedMeal = getUpdatedMeal();
        updatedMeal.setId(NOT_FOUND);
        assertThrows(NotFoundException.class, () -> service.update(updatedMeal, USER_ID));
    }

    @Test
    public void create() {
        Meal newMeal = getNewMeal();
        Meal createdMeal = service.create(newMeal, USER_ID);
        Integer newId = createdMeal.getId();
        newMeal.setId(newId);
        assertMatch(createdMeal, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }
}