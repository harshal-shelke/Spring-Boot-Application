package com.harshal.firstSpringApp.Services;

import com.harshal.firstSpringApp.Repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepo userRepo;

//    @Disabled
    @Test
    public void findByUsername(){
//        assertEquals(4,3+1);
        assertNotNull(userRepo.findByUsername("harshal"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4"
    })
    public void test(int a,int b,int c){
        assertEquals(c,a+b);
    }

}
