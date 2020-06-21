package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    {
        MealsUtil.MEALS.forEach(meal -> this.save(meal, MealsUtil.DEFAULT_USER_ID));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        log.info("save {}", meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.computeIfAbsent(userId, k -> new ConcurrentHashMap<>()).put(meal.getId(), meal);
            return meal;
        }
        return get(meal.getId(), userId) != null
                && repository.get(userId).replace(meal.getId(), meal) != null
                ? meal : null;
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {} for userId={}", id, userId);
        return get(id, userId) != null && repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Map<Integer, Meal> userMealMap = repository.get(userId);
        return userMealMap == null ? null : userMealMap.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll for userId={}", userId);
        return getFilteredByPredicate(userId, meal -> true);
    }

    @Override
    public List<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("getFilteredByDate for userId={} from {} to {}", userId, startDate, endDate);
        return getFilteredByPredicate(userId, meal -> DateTimeUtil.isBetweenHalfOpen(
                meal.getDateTime(),
                startDate.atTime(LocalTime.MIN),
                endDate.atTime(LocalTime.MAX)
        ));
    }

    public List<Meal> getFilteredByPredicate(int userId, Predicate<Meal> predicate) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return  userMeals == null ? Collections.emptyList() : userMeals.values().stream()
                .filter(predicate)
                .sorted(Comparator.comparing(Meal::getDate).reversed())
                .collect(Collectors.toList());
    }
}

