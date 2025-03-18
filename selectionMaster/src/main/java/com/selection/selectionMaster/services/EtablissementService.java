package com.selection.selectionMaster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.selection.selectionMaster.models.Etablissement;
import com.selection.selectionMaster.repositories.EtablissementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EtablissementService {

    @Autowired
    private EtablissementRepository etablissementRepository;

    // Récupérer tous les établissements
    public List<Etablissement> getAllEtablissements() {
        return etablissementRepository.findAll();
    }

    // Récupérer un établissement par son ID
    public Optional<Etablissement> getEtablissementById(Long id) {
        return etablissementRepository.findById(id);
    }

    // Ajouter ou mettre à jour un établissement
    public Etablissement saveEtablissement(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }

    // Supprimer un établissement
    public void deleteEtablissement(Long id) {
        etablissementRepository.deleteById(id);
    }
}