package com.cy.example.demo.Controller;

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


    @Autowired
    DateHistoryRepository datehistoryRepository;


    @RequestMapping("/")
    public String mainpage(Model model)
    {
        return "dateform";
    }

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
        model.addAttribute("datehistorys", dateHistory);
        return "getuserdate";
    }

    @PostMapping("/getuserdate")
    public String getuserdate(HttpServletRequest request, DateHistory dateHistory, Model model)
    {
        //System.out.println("entering post getuserdate");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        String userdate = request.getParameter("userdate");

        LocalDate localDate = LocalDate.parse(userdate, formatter);

        DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        System.out.println("dayofweek is " + dayOfWeek);

        dateHistory.setUserDate(localDate);
        dateHistory.setMaleName(maleNames.get((dayOfWeek.getValue())-1));
        dateHistory.setFemaleName(femaleNames.get((dayOfWeek.getValue())-1));
        datehistoryRepository.save(dateHistory);
        model.addAttribute("datehistorys", dateHistory);
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
