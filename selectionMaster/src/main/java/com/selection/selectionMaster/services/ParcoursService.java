package com.selection.selectionMaster.services;
import com.selection.selectionMaster.models.*;
import com.selection.selectionMaster.repositories.ParcoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParcoursService {

    @Autowired
    private ParcoursRepository parcoursRepository;

    // Récupérer tous les parcours
    public List<Parcours> getAllParcours() {
        return parcoursRepository.findAll();
    }

    // Récupérer un parcours par son ID
    public Optional<Parcours> getParcoursById(Long id) {
        return parcoursRepository.findById(id);
    }

    // Ajouter ou mettre à jour un parcours
    public Parcours saveParcours(Parcours parcours) {
        return parcoursRepository.save(parcours);
    }

    // Supprimer un parcours
    public void deleteParcours(Long id) {
        parcoursRepository.deleteById(id);
    }
}