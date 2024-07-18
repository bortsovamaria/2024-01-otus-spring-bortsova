package ru.otus.spring.libraryclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "ru.otus.spring.libraryclient.feign")
public class LibraryClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryClientApplication.class, args);
    }
}
