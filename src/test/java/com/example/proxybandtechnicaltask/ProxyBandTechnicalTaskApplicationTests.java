package com.example.proxybandtechnicaltask;

import com.example.proxybandtechnicaltask.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProxyBandTechnicalTaskApplicationTests {

    @Autowired
    UserController userController;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    void mainTest(){
        ProxyBandTechnicalTaskApplication.main(new String[]{});
    }
}
