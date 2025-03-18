package com.selection.selectionMaster.controllers;
import com.selection.selectionMaster.DTO.ParcoursDTO;
import com.selection.selectionMaster.models.*;
import com.selection.selectionMaster.services.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/parcours")
public class ParcoursController {

    @Autowired
    private ParcoursService parcoursService;

    // Récupérer tous les parcours
    @GetMapping
    public List<ParcoursDTO> getAllParcours() {
        return parcoursService.getAllParcours().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Convertir une entité Parcours en DTO
    private ParcoursDTO convertToDTO(Parcours parcours) {
        ParcoursDTO dto = new ParcoursDTO();
        dto.setId(parcours.getId());
        dto.setNom(parcours.getNom());
        dto.setQuota(parcours.getQuota());
        return dto;
    }

    // Récupérer un parcours par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Parcours> getParcoursById(@PathVariable Long id) {
        return parcoursService.getParcoursById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Ajouter un parcours
    @PostMapping
    public Parcours createParcours(@RequestBody Parcours parcours) {
        return parcoursService.saveParcours(parcours);
    }

    // Mettre à jour un parcours
    @PutMapping("/{id}")
    public ResponseEntity<Parcours> updateParcours(@PathVariable Long id, @RequestBody Parcours parcoursDetails) {
        Optional<Parcours> parcoursOptional = parcoursService.getParcoursById(id);

        if (parcoursOptional.isPresent()) {
            Parcours parcours = parcoursOptional.get();
            parcours.setNom(parcoursDetails.getNom());
            parcours.setQuota(parcoursDetails.getQuota());
            Parcours updatedParcours = parcoursService.saveParcours(parcours);
            return ResponseEntity.ok(updatedParcours);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un parcours
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParcours(@PathVariable Long id) {
        parcoursService.deleteParcours(id);
        return ResponseEntity.noContent().build();
    }
}