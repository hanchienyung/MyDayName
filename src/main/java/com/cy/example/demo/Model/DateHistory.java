package com.cy.example.demo.Model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class DateHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate userDate;

    private String femaleName;

    private String maleName;

    @ManyToMany
    private Set<AppUser> users;

    public DateHistory() {
        this.users = new HashSet<>();
    }

    public DateHistory(LocalDate userDate, String femaleName, String maleName, Set<AppUser> users) {
        this.userDate = userDate;
        this.femaleName = femaleName;
        this.maleName = maleName;
        this.users = new HashSet<>();
    }

    public void addUsertoDateHistory(AppUser aAppUser) {this.users.add(aAppUser);}


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getUserDate() {
        return userDate;
    }

    public void setUserDate(LocalDate userDate) {
        this.userDate = userDate;
    }

    public String getFemaleName() {
        return femaleName;
    }

    public void setFemaleName(String femaleName) {
        this.femaleName = femaleName;
    }

    public String getMaleName() {
        return maleName;
    }

    public void setMaleName(String maleName) {
        this.maleName = maleName;
    }

    public Set<AppUser> getUsers() {
        return users;
    }

    public void setUsers(Set<AppUser> users) {
        this.users = users;
    }
}
