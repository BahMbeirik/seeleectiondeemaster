package com.selection.selectionMaster.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.selection.selectionMaster.models.Etablissement;
import com.selection.selectionMaster.services.EtablissementService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/etablissements")
public class EtablissementController {

    @Autowired
    private EtablissementService etablissementService;

    // Récupérer tous les établissements
    @GetMapping
    public ResponseEntity<List<Etablissement>> getAllEtablissements() {
        List<Etablissement> etablissements = etablissementService.getAllEtablissements();
        return ResponseEntity.ok(etablissements);
    }

    // Récupérer un établissement par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Etablissement> getEtablissementById(@PathVariable Long id) {
        return etablissementService.getEtablissementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Ajouter un établissement
    @PostMapping
    public ResponseEntity<?> createEtablissement(@RequestBody Etablissement etablissement) {
        try {
            // Validate the incoming data
            if (etablissement.getNom() == null || etablissement.getNom().isEmpty()) {
                return ResponseEntity.badRequest().body("Le nom de l'établissement est obligatoire.");
            }

            // Ensure the ID is null for new entities
            etablissement.setId(null);

            Etablissement savedEtablissement = etablissementService.saveEtablissement(etablissement);
            return ResponseEntity.ok(savedEtablissement);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement de l'établissement : " + e.getMessage());
        }
    }

    // Mettre à jour un établissement
    @PutMapping("/{id}")
    public ResponseEntity<Etablissement> updateEtablissement(@PathVariable Long id, @RequestBody Etablissement etablissementDetails) {
        Optional<Etablissement> etablissementOptional = etablissementService.getEtablissementById(id);

        if (etablissementOptional.isPresent()) {
            Etablissement etablissement = etablissementOptional.get();
            etablissement.setNom(etablissementDetails.getNom());
            Etablissement updatedEtablissement = etablissementService.saveEtablissement(etablissement);
            return ResponseEntity.ok(updatedEtablissement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un établissement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtablissement(@PathVariable Long id) {
        etablissementService.deleteEtablissement(id);
        return ResponseEntity.noContent().build();
    }
}