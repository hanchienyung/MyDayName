package com.cy.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;




public class MainController{

    List<String> femaleNames =  Arrays.asList( "Adjoa", "Abena", "Akua", "Yaa", "Afua", "Ama", "Akosua");
    List<String> maleNames = Arrays.asList( "Kojo", "Kwabena", "Kweku", "Yaw", "Kofi", "Kwame", "Kwesi");
    List<String>

    @Autowired
    DateHistoryRepository datehistoryRepository;



    @RequestMapping("/login")
    public String login(Model model)
    {
        return "login";
    }


    @RequestMapping("/getuserdate")
    public String getuserdate(DateHistory dateHistory, Model model) //, @RequestParam("date") LocalDate date)
    {

        DateHistory datehistory = new DateHistory();
        //System.out.println ("addreportitem (request) = reportItem id / Name = " +  reportItem.getId() + " / " + reportItem.getItemName());
        datehistoryRepository.save(dateHistory);
        return "getuserdate";
    }

    @PostMapping("/getuserdate")
    public String getuserdate(HttpServletRequest request, @Valid @ModelAttribute("dateHistory") DateHistory dateHistory, BindingResult result, Model model) //, @RequestParam("date") LocalDate date)
    {


        if(result.hasErrors())
        {
            return "getuserdate";
        }

        DayOfWeek dayOfWeek = dateHistory.getUserDate().getDayOfWeek();




       // java.time.DayOfWeek dayOfWeek = dateHistory.getDayOfWeek();


        System.out.println("dayofweek is " + dayOfWeek);

        dateHistory.setMaleName(maleNames[dayOfWeek.getValue()]);

        dateHistory.setFemaleName(femaleNames[dayOfWeek.getValue()]);


        datehistoryRepository.save(dateHistory);

        return "listdatehistory";

    }

    @RequestMapping("/listdatehistory")
    public String listdatehistory(Model model)
    {
        model.addAttribute("datehistorys",datehistoryRepository.findAll());
        return "listdatehistory";
    }




   /* public String addreportitem(HttpServletRequest request, @Valid @ModelAttribute("reportitem") ReportItem reportItem, BindingResult result, Model model)
    {
        //System.out.println ("addreportitem (post) = reportItem id / Name = " +  reportItem.getId() + " / " + reportItem.getItemName());
        if(result.hasErrors())
        {
            return "addusertoreport";
        }
        String userid = request.getParameter("userid");
        AppUser appuser = userRepository.findOne(new Long(userid));
        reportItem.addUsertoReport(appuser);

        reportItem.setItemStatus("lost");
        reportitemRepository.save(reportItem);
        model.addAttribute("reportlist",reportitemRepository.findAll());

        return "redirect:/";
    }
    */


   // Scanner keyboard = new Scanner(System.in);

        //Get the current time
        //LocalDateTime rightNow= LocalDateTime.now();
        //Date from the user
        //LocalDate userDate=null;

        //Set up formatters so that you can use them later
        //DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        ///DateTimeFormatter shortMonthFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        //DateTimeFormatter longFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        //Time formatter (time only)
        //DateTimeFormatter hr24 = DateTimeFormatter.ofPattern("kk:m");

        //Output today's date in formats that have been preset
        //System.out.println("The current date is: "+rightNow.format(longFormat));
        //System.out.println("This is the current date and time unformatted: "+rightNow);

        //Output the current time in formats that have been preset
        //System.out.println("This is the current system time: "+ LocalTime.now());
        //System.out.println("This is the current system time (24 h format): "+LocalTime.now().format(hr24));

//        String aDate = "22/05/2010";
//        userDate = LocalDate.parse(aDate,dTF);
//        System.out.println(userDate.format(longFormat));






    }
