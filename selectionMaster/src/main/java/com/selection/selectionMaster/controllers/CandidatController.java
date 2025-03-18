package com.selection.selectionMaster.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.selection.selectionMaster.services.CandidatService;
import com.selection.selectionMaster.services.FileStorageService;
import com.selection.selectionMaster.DTO.CandidatDTO;
import com.selection.selectionMaster.models.Candidat;
import com.selection.selectionMaster.models.Etablissement;
import com.selection.selectionMaster.models.Parcours;
import com.selection.selectionMaster.repositories.EtablissementRepository;
import com.selection.selectionMaster.repositories.ParcoursRepository;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("/api/candidats")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;

    private final EtablissementRepository etablissementRepository = null;
    private final ParcoursRepository parcoursRepository = null;

    @Autowired
    private FileStorageService fileStorageService;

    
    public CandidatController(CandidatService candidatService) {
      this.candidatService = candidatService;
  }

  @PostMapping
  public ResponseEntity<?> ajouterCandidat(
          @RequestParam("nom") String nom,
          @RequestParam("prenom") String prenom,
          @RequestParam("filier") String filier,
          @RequestParam("numeroIouBac") String numeroIouBac,
          @RequestParam("anneeBac") Integer anneeBac,
          @RequestParam("anneeLicence") Integer anneeLicence,
          @RequestParam("moyenneBac") String moyenneBac,
          @RequestParam("moyenneLicence") String moyenneLicence,
          @RequestParam("moyenneReleve1") String moyenneReleve1,
          @RequestParam("moyenneReleve2") String moyenneReleve2,
          @RequestParam("moyenneReleve3") String moyenneReleve3,
          @RequestParam("moyenneReleve4") String moyenneReleve4,
          @RequestParam("moyenneReleve5") String moyenneReleve5,
          @RequestParam("moyenneReleve6") String moyenneReleve6,
          @RequestParam("etatValidation1") String etatValidation1,
          @RequestParam("etatValidation2") String etatValidation2,
          @RequestParam("etatValidation3") String etatValidation3,
          @RequestParam("etatValidation4") String etatValidation4,
          @RequestParam("etatValidation5") String etatValidation5,
          @RequestParam("etatValidation6") String etatValidation6,
          @RequestParam("etablissementId") Long etablissementId,
          @RequestParam("parcoursIds") String parcoursIds,
          @RequestParam("diplomeBacPath") MultipartFile diplomeBacPath,
          @RequestParam("diplomeLicencePath") MultipartFile diplomeLicencePath,
          @RequestParam("releve1Path") MultipartFile releve1Path,
          @RequestParam("releve2Path") MultipartFile releve2Path,
          @RequestParam("releve3Path") MultipartFile releve3Path,
          @RequestParam("releve4Path") MultipartFile releve4Path,
          @RequestParam("releve5Path") MultipartFile releve5Path,
          @RequestParam("releve6Path") MultipartFile releve6Path
  ) {
      try {
        Candidat candidat = candidatService.ajouterCandidat(
          nom, prenom, filier, numeroIouBac, anneeBac, anneeLicence, moyenneBac, moyenneLicence,
          etablissementId, parcoursIds, etatValidation1, etatValidation2, etatValidation3, etatValidation4, etatValidation5, etatValidation6,
          moyenneReleve1, moyenneReleve2, moyenneReleve3, moyenneReleve4, moyenneReleve5, moyenneReleve6,
          diplomeBacPath, diplomeLicencePath, releve1Path, releve2Path, releve3Path, releve4Path, releve5Path, releve6Path
        );
          return ResponseEntity.ok(candidat);
      } catch (RuntimeException ex) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                  .body("Failed to store file: " + ex.getMessage());
      }
  }
    
    

    // Modifier un candidat existant
    @PutMapping("/{id}")
    public Candidat modifierCandidat(@PathVariable Integer id, @RequestBody Candidat candidat) {
        candidat.setId(id);
        return candidatService.modifierCandidat(candidat);
    }

    // Supprimer un candidat
    @DeleteMapping("/{id}")
    public void supprimerCandidat(@PathVariable Long id) {
        candidatService.supprimerCandidat(id);
    }

    // Endpoint pour obtenir tous les candidats
    @GetMapping
    public List<Candidat> obtenirTousLesCandidats() {
        return candidatService.obtenirTousLesCandidats();
    }

    // Endpoint pour obtenir un candidat par son ID
    @GetMapping("/{id}")
    public Candidat obtenirCandidatParId(@PathVariable Long id) {
        return candidatService.obtenirCandidatParId(id);
    }

    // Candidats paginés
    @GetMapping("/paginated")
    public ResponseEntity<Page<Candidat>> getPaginatedCandidats(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size) {
        Page<Candidat> candidats = candidatService.getPaginatedCandidats(page, size);
        return ResponseEntity.ok(candidats);
    }

    // Recherche de candidats
    @GetMapping("/search")
    public ResponseEntity<List<Candidat>> searchCandidats(@RequestParam String query) {
        List<Candidat> candidats = candidatService.searchCandidats(query);
        return ResponseEntity.ok(candidats);
    }

    @GetMapping("/organises")
    public Map<String, Map<String, Map<String, List<Candidat>>>> getCandidatsOrganises() {
        return candidatService.candidatsParParcours();
    }
}
