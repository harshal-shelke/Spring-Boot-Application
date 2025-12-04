package com.harshal.firstSpringApp.Controller;

import com.harshal.firstSpringApp.ApiResponse.AdviceResponse;
import com.harshal.firstSpringApp.ApiResponse.WeatherResponse;
import com.harshal.firstSpringApp.Repository.UserRepo;
import com.harshal.firstSpringApp.Services.AdviceService;
import com.harshal.firstSpringApp.Services.EmailService;
import com.harshal.firstSpringApp.Services.UserService;
import com.harshal.firstSpringApp.Services.WeatherService;
import com.harshal.firstSpringApp.entity.EmailRequest;
import com.harshal.firstSpringApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WeatherService weatherService;
    @Autowired
    private  AdviceService adviceService;
    @Autowired
    private EmailService emailService;

//    @GetMapping
//    public List<User> getAll(){
//        return userService.getAll();
//    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User userInDb = userService.findByUsername(username);
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(userInDb,HttpStatus.OK);

    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        userRepo.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("{city}")
    public ResponseEntity<?> greeting(@PathVariable String city) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather(city);
        String greeting = "";

        if (weatherResponse != null && weatherResponse.getCurrent() != null) {
            greeting = ", weather feels like " + weatherResponse.getCurrent().getFeelslike();
            greeting+=weatherResponse.getLocation().country;
        }



        return new ResponseEntity<>("hii " + authentication.getName() + greeting, HttpStatus.OK);
    }

    @GetMapping("/advice")
    public ResponseEntity<?> advice(){
//        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        AdviceResponse response= adviceService.getAdvice();
        return new ResponseEntity<>(response.getAdvice(),HttpStatus.OK);
    }

    @PostMapping("send-email")
    public String sendEmail(@RequestBody EmailRequest mail){
        emailService.sendEmail(mail.getTo(),mail.getSubject(),mail.getBody());
        return "send";
    }

}
