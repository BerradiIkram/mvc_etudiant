package com.example.activity_mvc;

import com.example.activity_mvc.entities.Etudiant;
import com.example.activity_mvc.repositories.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

import static com.example.activity_mvc.entities.gener.FEMININ;
import static com.example.activity_mvc.entities.gener.MASCULIN;
import static com.example.activity_mvc.entities.regle_non.non;
import static com.example.activity_mvc.entities.regle_non.regle;

@SpringBootApplication
public class ActivityMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityMvcApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner (EtudiantRepository etudiantRepository) {
        return args -> {
            etudiantRepository.save(new Etudiant(null,"Omayma","itaqi","omayma@gmail.com",new Date(),FEMININ,non));
            etudiantRepository.save(new Etudiant(null,"Mohamed","badauoi","mohamed@gmail.com",new Date(),MASCULIN,regle));
            etudiantRepository.save(new Etudiant(null,"Khalid","dahbi","khalide@gmail.com",new Date(),MASCULIN,non));
            etudiantRepository.save(new Etudiant(null,"Hanan","hanafi","hanae@gmail.com",new Date(),FEMININ,non));
            etudiantRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });



        };
    }

}
