package com.westeros.data;

import com.westeros.data.model.Company;
import com.westeros.data.repositories.ICatalogData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class WesterosDataApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(WesterosDataApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
