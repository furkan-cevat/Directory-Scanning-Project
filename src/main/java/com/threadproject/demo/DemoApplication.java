package com.threadproject.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutionException;



@SpringBootApplication
@EnableScheduling
public class DemoApplication {

    @Autowired
    private Scanning scanning;

    @Scheduled(fixedDelayString = "60000") //Dakikada bir içindekini çalıştırıyor
    public void ScheduledFixedRate() throws ExecutionException, InterruptedException, FileNotFoundException {
        scanning.doScanning();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SpringApplication.run(DemoApplication.class, args);
    }



}


