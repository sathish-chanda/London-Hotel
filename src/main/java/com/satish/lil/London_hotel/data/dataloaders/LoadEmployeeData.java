package com.satish.lil.London_hotel.data.dataloaders;

import com.satish.lil.London_hotel.data.entity.Employee;
import com.satish.lil.London_hotel.data.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadEmployeeData {
//    We saved this data into postgres database. Now we can load it from the remote postgres server directly.
//    private static final Logger log = LoggerFactory.getLogger(LoadEmployeeData.class);
//
//    @Bean
//    CommandLineRunner initEmployeeData(EmployeeRepository employeeRepository) {
//        return args -> {
//            log.info("Preloading " + employeeRepository.save(new Employee("Bilbo Baggins", "burglar")));
//            log.info("Preloading " + employeeRepository.save(new Employee("Frodo Baggins", "thief")));
//        };
//    }
}
