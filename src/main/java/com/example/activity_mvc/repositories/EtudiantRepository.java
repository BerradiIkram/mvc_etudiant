package com.example.activity_mvc.repositories;

import com.example.activity_mvc.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository   extends JpaRepository<Etudiant,Long> {

        Page<Etudiant> findByNomContains(String kw, Pageable pageable);
}
