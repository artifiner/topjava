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
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
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
            repository.put(meal.getId(), meal);
            return meal;
        }
        return get(meal.getId(), userId) != null
                && repository.replace(meal.getId(), meal) != null
                ? meal : null;
    }

    @Override
    public boolean delete(int id, int userId) {
        log.info("delete {} for userId={}", id, userId);
        return get(id, userId) != null && repository.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get {}", id);
        Meal meal = repository.get(id);
        return meal != null && meal.getUserId() == userId ? meal : null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll for userId={}", userId);
        return getFilteredByPredicate(meal -> meal.getUserId() == userId);
    }

    @Override
    public List<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate) {
        log.info("getFilteredByDate for userId={} from {} to {}", userId, startDate, endDate);
        return getFilteredByPredicate(meal -> meal.getUserId() == userId
                && DateTimeUtil.isBetweenHalfOpen(
                meal.getDateTime(),
                startDate.atTime(LocalTime.MIN),
                endDate.atTime(LocalTime.MAX)
        ));
    }

    public List<Meal> getFilteredByPredicate(Predicate<Meal> predicate) {
        return repository.values().stream()
                .filter(predicate)
                .sorted((meal1, meal2) -> meal2.getDate().compareTo(meal1.getDate()))
                .collect(Collectors.toList());
    }
}

