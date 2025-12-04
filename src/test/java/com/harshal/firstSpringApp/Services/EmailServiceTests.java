package com.harshal.firstSpringApp.Services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {
    @Autowired
    private EmailService emailService;

    @Test
    public void sendEmail(){
        emailService.sendEmail("believer094@gmail.com",
                "hello, harshal",
                "this email is send using spring boot");
    }
}
