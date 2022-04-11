package com.example.activity_mvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
@Entity
@AllArgsConstructor @NoArgsConstructor@Data
public class Etudiant {
   @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private gener gener;
    private regle_non regle_non;



}