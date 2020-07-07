package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Transactional
    int deleteByIdAndUser_Id(int id, int userId);
    List<Meal> getAllByUser_IdOrderByDateTimeDesc(int userId);
    List<Meal> getAllByDateTimeGreaterThanEqualAndDateTimeLessThanAndUser_IdOrderByDateTimeDesc(
            LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
