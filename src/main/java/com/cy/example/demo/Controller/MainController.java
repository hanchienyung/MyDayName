package com.cy.example.demo.Controller;

import com.cy.example.demo.Model.AppUser;
import org.springframework.security.core.Authentication;
import com.cy.example.demo.Model.AppUserRepository;
import com.cy.example.demo.Model.DateHistory;
import com.cy.example.demo.Model.DateHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController{

    List<String> femaleNames =  Arrays.asList( "Adjoa", "Abena", "Akua", "Yaa", "Afua", "Ama", "Akosua");
    List<String> maleNames = Arrays.asList( "Kojo", "Kwabena", "Kweku", "Yaw", "Kofi", "Kwame", "Kwesi");
    List<String> chineseZodiac = Arrays.asList("monkey","rooster","dog","pig", "rat", "ox","tiger","rabbit","dragon","snake","horse","sheep");
    List<String> astrologSign = Arrays.asList( "capricorn", "aquarius", "pisces", "aries", "taurus", "gemini", "cancer", "leo", "virgo", "libra", "scorpio", "sagittarius");


    @Autowired
    DateHistoryRepository datehistoryRepository;

    @Autowired
    AppUserRepository appUserRepository;


    @RequestMapping("/")
    public String mainpage(Model model)
    {
        return "mainpage";
    }

    @RequestMapping("/login")
    public String login(Model model)
    {
        return "login";
    }


    @RequestMapping("/getuserdate")
    public String getuserdate(DateHistory dateHistory, Model model) //, @RequestParam("date") LocalDate date)
    {

        //System.out.println ("addreportitem (request) = reportItem id / Name = " +  reportItem.getId() + " / " + reportItem.getItemName());

        model.addAttribute("datehistorys", dateHistory);
        return "dateform";
    }

    @PostMapping("/processuserdate")
    public String processuserdate(HttpServletRequest request, DateHistory dateHistory, Model model, Authentication auth)
    {
        DateHistory datehistory = new DateHistory();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        String userdate = request.getParameter("userdate");

        LocalDate localDate = LocalDate.parse(userdate, formatter);

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        //System.out.println("dayofweek is " + dayOfWeek);

        dateHistory.setUserDate(localDate);
        dateHistory.setMaleName(maleNames.get((dayOfWeek.getValue())-1));
        dateHistory.setFemaleName(femaleNames.get((dayOfWeek.getValue())-1));

        dateHistory.setAstrologSign(astrologSign.get(dateHistory.getZodSign(localDate)));
        dateHistory.setChineseZodiac(chineseZodiac.get(dateHistory.getChineseSign(localDate)));

        datehistoryRepository.save(dateHistory);


        model.addAttribute("datehistorys", dateHistory);
        return "listdatehistory";
    }

    @RequestMapping("/listdatehistory")
    public String listdatehistory(Model model, Authentication auth)
    {
        AppUser user = appUserRepository.findAppUserByUsername(auth.getName());
        model.addAttribute("datehistorys",datehistoryRepository.findDateHistoriesByUsers(user));
        return "listdatehistory";
    }


    @RequestMapping("/showhistory")
    public String showhistory(Model model, Authentication auth)
    {
        AppUser user = appUserRepository.findAppUserByUsername(auth.getName());
        model.addAttribute("users",datehistoryRepository.findDateHistoriesByUsers(user));
        //model.addAttribute("users", datehistoryRepository.findAllByUserDate(user));

        return "showhistory";
    }

}
