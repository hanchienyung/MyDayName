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

    private String chineseZodiac;

    private String astrologSign;

    @ManyToMany
    private Set<AppUser> users;

    public DateHistory() {
        this.users = new HashSet<>();
    }

    public DateHistory(LocalDate userDate, String femaleName, String maleName, Set<AppUser> users) {
        this.userDate = userDate;
        this.femaleName = femaleName;
        this.maleName = maleName;
        this.chineseZodiac = chineseZodiac;
        this.astrologSign = astrologSign;
        this.users = new HashSet<>();
    }

    public void addUser(AppUser aAppUser) {this.users.add(aAppUser);}


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

    public String getAstrologSign() {
            return astrologSign;
    }

    public void setAstrologSign(String astrologSign) {
        this.astrologSign = astrologSign;
    }

    public int getZodSign(LocalDate userDate) {

       Integer month = userDate.getMonthValue();
       Integer day = userDate.getDayOfMonth();

       if (month == 1)
           if (day <= 19)
              return 0;
           else
              return 1;
       else if (month == 2)
           if (day <= 18)
              return 1;
           else
              return 2;
       else if (month == 3)
           if (day <= 20)
              return 2;
           else
              return 3;
       else if (month == 4)
            if (day <= 19)
              return 3;
            else
              return 4;
       else if (month == 5)
            if (day <= 21)
              return 4;
            else
              return 5;
       else if (month == 6)
            if (day <= 21)
              return 5;
            else
              return 6;
       else if (month == 7)
            if (day <= 23)
              return 6;
            else
              return 7;
       else if (month == 8)
            if (day <= 23)
              return 7;
            else
              return 8;
       else if (month == 9)
            if (day <= 23)
              return 8;
            else
              return 9;
       else if (month == 10)
            if (day <= 23)
               return 9;
             else
               return 10;
       else if (month == 11)
            if (day <= 22)
               return 10;
            else
               return 11;
       else
           return 0;

    /*       ((userDate.isEqual() or userDate.isafter()) and   between 12/22 and  1/19 ) { return 0;}
        else if (userDate between 1/20 and 2/18 ) {return 1;}
        else if (userDate between 2/19 and 3/20) {return 2;}
        else if (userDate between 3/21 and 4/19 ) {return 3;}
        else if (userDate between 4/20 and 5/20 ) {return 4;}
        else if (userDate between 5/21 and 6/20 ) {return 5;}
        else if (userDate between 6/21 and 7/22 ) {return 6;}
        else if (userDate between 7/23 and 8/22 ) {return 7;}
        else if (userDate between 8/23 and 9/22 ) {return 8;}
        else if (userDate between 9/23 and 10/22 ) {return 9;}
        else if (userDate between 10/23 and 11/21 ) {return 10;}
        else if (userDate between )11/22 and 12/21) {return 11;}
        else { return 0;}

       return 0;
*/
    }


    public String getChineseZodiac() {
        return chineseZodiac;
    }

    public void setChineseZodiac(String chineseZodiac) {
        this.chineseZodiac = chineseZodiac;
    }

    public int getChineseSign(LocalDate userDate) {
        Integer year = userDate.getYear();
        Integer remainder;

        remainder = year % 12;
        return remainder;
    }

}
