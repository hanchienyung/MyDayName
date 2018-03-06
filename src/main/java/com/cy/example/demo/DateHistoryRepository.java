package com.cy.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.time.DayOfWeek;

public interface DateHistoryRepository extends CrudRepository<DateHistory, Long> {
    DateHistory findDateHistoryByDayofweek(DayOfWeek dayOfWeek);
}
