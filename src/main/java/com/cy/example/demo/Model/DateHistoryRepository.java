package com.cy.example.demo.Model;

import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;

public interface DateHistoryRepository extends CrudRepository<DateHistory, Long> {
    HashSet<DateHistory> findDateHistoriesByUsers(AppUser appUser) ;
    HashSet<DateHistory> findAllByUserDate(AppUser appUser);

}
