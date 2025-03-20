package com.selection.selectionMaster.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.selection.selectionMaster.models.Candidat;

public interface CandidatRepository extends JpaRepository<Candidat, Long> {
  // Recherche par nom ET prénom
  Optional<Candidat> findByNomAndPrenom(String nom, String prenom);
  
  // Recherche par nom (seulement le nom)
  List<Candidat> findByNomContainingIgnoreCase(String query);
  
  // OU recherche par nom OU prénom 
  @Query("SELECT c FROM Candidat c WHERE LOWER(c.nom) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(c.prenom) LIKE LOWER(CONCAT('%', :query, '%'))")
  List<Candidat> searchByNomOrPrenom(@Param("query") String query);

  List<Candidat> findAllByOrderByMoyenneClassementDesc();
}