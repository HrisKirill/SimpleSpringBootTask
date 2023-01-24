package com.example.proxybandtechnicaltask;

import com.example.proxybandtechnicaltask.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProxyBandTechnicalTaskApplication implements CommandLineRunner {

    private final UserRepository repository;

    @Autowired
    public ProxyBandTechnicalTaskApplication(UserRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProxyBandTechnicalTaskApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
    }
}
