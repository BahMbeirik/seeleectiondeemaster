package com.selection.selectionMaster.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.selection.selectionMaster.repositories.CandidatRepository;
import com.selection.selectionMaster.repositories.EtablissementRepository;
import com.selection.selectionMaster.repositories.ParcoursRepository;
import com.selection.selectionMaster.models.Candidat;
import com.selection.selectionMaster.models.Etablissement;
import com.selection.selectionMaster.models.Parcours;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CandidatService {

    @Autowired
    private CandidatRepository candidatRepository;

    @Autowired
    private EtablissementRepository etablissementRepository;

    @Autowired
    private ParcoursRepository parcoursRepository;

    public List<Candidat> obtenirTousLesCandidats() {
        return candidatRepository.findAll();
    }

    public Candidat obtenirCandidatParId(Long id) {
        return candidatRepository.findById(id).orElse(null);
    }

    // private final String UPLOAD_DIR = "uploads/";

    public CandidatService(CandidatRepository candidatRepository) {
        this.candidatRepository = candidatRepository;
    }

     


    @Autowired
    private FileStorageService fileStorageService;
    
    public Candidat ajouterCandidat(
        String nom, String prenom, String filier, String numeroIouBac, Integer anneeBac, Integer anneeLicence,
        String moyenneBac, String moyenneLicence, Long etablissementId, String parcoursIds, String etatValidation1,
        String etatValidation2, String etatValidation3, String etatValidation4, String etatValidation5, String etatValidation6,
        String moyenneReleve1, String moyenneReleve2, String moyenneReleve3, String moyenneReleve4, String moyenneReleve5, String moyenneReleve6,
        MultipartFile diplomeBacPath, MultipartFile diplomeLicencePath, MultipartFile releve1Path,
        MultipartFile releve2Path, MultipartFile releve3Path, MultipartFile releve4Path,
        MultipartFile releve5Path, MultipartFile releve6Path
    ) {
        // Récupérer l'établissement
        Etablissement etablissement = etablissementRepository.findById(etablissementId)
                .orElseThrow(() -> new RuntimeException("Établissement non trouvé"));
    
        // Récupérer les parcours
        Set<Parcours> parcoursChoisis = Arrays.stream(parcoursIds.split(","))
                .map(id -> parcoursRepository.findById(Long.parseLong(id)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    
        // تحويل قائمة المسارات إلى سلسلة نصية
        String parcoursString = parcoursChoisis.stream()
                .map(Parcours::getNom) // افترض أن Parcours يحتوي على حقل `nom`
                .collect(Collectors.joining(","));
    
        // Enregistrer les fichiers avec numeroIouBac et le type de fichier
        String diplomeBacPathFile = fileStorageService.storeFile(diplomeBacPath, numeroIouBac, "diplomeBac");
        String diplomeLicencePathFile = fileStorageService.storeFile(diplomeLicencePath, numeroIouBac, "diplomeLicence");
        String releve1PathFile = fileStorageService.storeFile(releve1Path, numeroIouBac, "releve1");
        String releve2PathFile = fileStorageService.storeFile(releve2Path, numeroIouBac, "releve2");
        String releve3PathFile = fileStorageService.storeFile(releve3Path, numeroIouBac, "releve3");
        String releve4PathFile = fileStorageService.storeFile(releve4Path, numeroIouBac, "releve4");
        String releve5PathFile = fileStorageService.storeFile(releve5Path, numeroIouBac, "releve5");
        String releve6PathFile = fileStorageService.storeFile(releve6Path, numeroIouBac, "releve6");
    
        // Créer et sauvegarder le candidat
        Candidat candidat = new Candidat();
        candidat.setNom(nom);
        candidat.setPrenom(prenom);
        candidat.setFilier(filier);
        candidat.setNumeroIouBac(numeroIouBac);
        candidat.setAnneeBac(anneeBac);
        candidat.setAnneeLicence(anneeLicence);
        candidat.setMoyenneBac(moyenneBac);
        candidat.setMoyenneLicence(moyenneLicence);
        candidat.setEtatValidation1(etatValidation1);
        candidat.setEtatValidation2(etatValidation2); 
        candidat.setEtatValidation3(etatValidation3);
        candidat.setEtatValidation4(etatValidation4);
        candidat.setEtatValidation5(etatValidation5);
        candidat.setEtatValidation6(etatValidation6);
        candidat.setMoyenneReleve1(moyenneReleve1);
        candidat.setMoyenneReleve2(moyenneReleve2);
        candidat.setMoyenneReleve3(moyenneReleve3);
        candidat.setMoyenneReleve4(moyenneReleve4);
        candidat.setMoyenneReleve5(moyenneReleve5);
        candidat.setMoyenneReleve6(moyenneReleve6);
        candidat.setDiplomeBacPath(diplomeBacPathFile);
        candidat.setDiplomeLicencePath(diplomeLicencePathFile);
        candidat.setReleve1Path(releve1PathFile);
        candidat.setReleve2Path(releve2PathFile);
        candidat.setReleve3Path(releve3PathFile);
        candidat.setReleve4Path(releve4PathFile);
        candidat.setReleve5Path(releve5PathFile);
        candidat.setReleve6Path(releve6PathFile);
        candidat.setEtablissement(etablissement);
        candidat.setParcoursChoisis(parcoursChoisis);
        candidat.setParcours(parcoursString); // حفظ أسماء المسارات كسلسلة نصية
    
        // Initialiser penalite si nécessaire
        if (candidat.getPenalite() == null) {
            candidat.setPenalite(0.0);
        }
    
        calculBonnus(candidat);
        calculMalus(candidat);
        calculerEtTrierParMC(candidat);
    
        return candidatRepository.save(candidat);
    }
  
  

  public Candidat modifierCandidat(Candidat candidat) {
      return candidatRepository.save(candidat);
  }

  private void calculBonnus(Candidat candidat) {
    Float BONUS = 0.0f;
    int annes = candidat.getAnneeLicence() - candidat.getAnneeBac();
    if (annes == 3) {
        BONUS += 1;
    }
    if ("Premier Session".equals(candidat.getEtatValidation1())) BONUS += 0.4f;
    if ("Premier Session".equals(candidat.getEtatValidation2())) BONUS += 0.4f;
    if ("Premier Session".equals(candidat.getEtatValidation3())) BONUS += 0.4f;
    if ("Premier Session".equals(candidat.getEtatValidation4())) BONUS += 0.4f;
    if ("Premier Session".equals(candidat.getEtatValidation5())) BONUS += 0.4f;
    if ("Premier Session".equals(candidat.getEtatValidation6())) BONUS += 0.4f;
    candidat.setBonnus(BONUS);
  }


  private void calculMalus(Candidat candidat) {
    double MALUS = 0;
    int annes = candidat.getAnneeLicence();
    while (annes < 2022) {
        MALUS -= 0.25;
        annes++;
    }
    candidat.setMalus(MALUS);
  }

  private void calculerEtTrierParMC(Candidat candidat) {
      double MNS = 0;
      int count = 0;

      if (candidat.getMoyenneReleve1() != null && !candidat.getMoyenneReleve1().isEmpty()) {
          try {
              MNS += Double.parseDouble(candidat.getMoyenneReleve1());
              count++;
          } catch (NumberFormatException e) {
              System.err.println("Invalid value for moyenneReleve1: " + candidat.getMoyenneReleve1());
          }
      }
      if (candidat.getMoyenneReleve2() != null && !candidat.getMoyenneReleve2().isEmpty()) {
          try {
              MNS += Double.parseDouble(candidat.getMoyenneReleve2());
              count++;
          } catch (NumberFormatException e) {
              System.err.println("Invalid value for moyenneReleve2: " + candidat.getMoyenneReleve2());
          }
      }
      if (candidat.getMoyenneReleve3() != null && !candidat.getMoyenneReleve3().isEmpty()) {
          try {
              MNS += Double.parseDouble(candidat.getMoyenneReleve3());
              count++;
          } catch (NumberFormatException e) {
              System.err.println("Invalid value for moyenneReleve3: " + candidat.getMoyenneReleve3());
          }
      }
      if (candidat.getMoyenneReleve4() != null && !candidat.getMoyenneReleve4().isEmpty()) {
          try {
              MNS += Double.parseDouble(candidat.getMoyenneReleve4());
              count++;
          } catch (NumberFormatException e) {
              System.err.println("Invalid value for moyenneReleve4: " + candidat.getMoyenneReleve4());
          }
      }
      if (candidat.getMoyenneReleve5() != null && !candidat.getMoyenneReleve5().isEmpty()) {
          try {
              MNS += Double.parseDouble(candidat.getMoyenneReleve5());
              count++;
          } catch (NumberFormatException e) {
              System.err.println("Invalid value for moyenneReleve5: " + candidat.getMoyenneReleve5());
          }
      }
      if (candidat.getMoyenneReleve6() != null && !candidat.getMoyenneReleve6().isEmpty()) {
          try {
              MNS += Double.parseDouble(candidat.getMoyenneReleve6());
              count++;
          } catch (NumberFormatException e) {
              System.err.println("Invalid value for moyenneReleve6: " + candidat.getMoyenneReleve6());
          }
      }

      MNS = (count > 0) ? MNS / count : 0;
      double MC = MNS + candidat.getBonnus() + candidat.getMalus() - candidat.getPenalite();
      candidat.setMoyenneClassement(MC);
  }  
  
  public void supprimerCandidat(Long id) {
      candidatRepository.deleteById(id);
  }

  public Page<Candidat> getPaginatedCandidats(int page, int size) {
    PageRequest pageable = PageRequest.of(page, size);
    return candidatRepository.findAll(pageable);
  }

    public List<Candidat> searchCandidats(String query) {
      // Choisissez l'implémentation souhaitée :
      // return candidatRepository.findByNomContainingIgnoreCase(query); // Seulement le nom
      
      // OU
      return candidatRepository.searchByNomOrPrenom(query); // Nom + prénom
    }


    

    public Map<String, Map<String, Map<String, List<Candidat>>>> candidatsParParcours() {
        Map<String, Map<String, Map<String, List<Candidat>>>> candidatsOrganises = new HashMap<>();
        Map<String, Integer> quotasGlobal = new HashMap<>();
        Map<String, Integer> candidatsCountParParcours = new HashMap<>();
        Map<String, Map<String, Integer>> quotasSpecialites = new HashMap<>();
        Map<String, Map<String, Integer>> candidatsCountParSpecialite = new HashMap<>();
        List<Parcours> parcoursList = parcoursRepository.findAll();

        // Remplir la map des quotas globaux et initialiser les compteurs
        for (Parcours p : parcoursList) {
            if (p != null && p.getNom() != null) {
                quotasGlobal.put(p.getNom(), p.getQuota());
                candidatsCountParParcours.put(p.getNom(), 0);

                // Définition des quotas par spécialité pour chaque parcours
                Map<String, Integer> quotas = new HashMap<>();
                switch (p.getNom()) {
                    case "SI":
                        quotas.put("DA2I", 7);
                        quotas.put("MIAGE", 5);
                        quotas.put("MI", 3);
                        quotas.put("MA", 0);
                        break;
                    case "RSC":
                        quotas.put("DA2I", 4);
                        quotas.put("MIAGE", 3);
                        quotas.put("MI", 2);
                        quotas.put("MA", 0);
                        break;
                    case "SDD":
                        quotas.put("DA2I", 0);
                        quotas.put("MIAGE", 0);
                        quotas.put("MI", 12);
                        quotas.put("MA", 5);
                        break;
                    default:
                        quotas.put("DA2I", 0);
                        quotas.put("MIAGE", 0);
                        quotas.put("MI", 0);
                        quotas.put("MA", 0);
                        break;
                }
                quotasSpecialites.put(p.getNom(), quotas);
            }
        }

        // Récupération de tous les candidats
        List<Candidat> candidats = candidatRepository.findAllByOrderByMoyenneClassementDesc();

        for (Candidat c : candidats) {
            if (c == null || c.getParcours() == null || c.getParcours().trim().isEmpty()) {
                continue;
            }

            String[] parcoursPref = c.getParcours().split(",");
            boolean placed = false;

            // Essayer de placer le candidat dans ses préférences
            for (String parcours : parcoursPref) {
                parcours = parcours.trim();
                if (parcours.isEmpty() || !quotasGlobal.containsKey(parcours)) continue;

                String etablissement = c.getEtablissement().getNom();
                String specialite = c.getFilier();

                // Vérifier si le quota global du parcours est respecté
                int quotaGlobal = quotasGlobal.get(parcours);
                int totalCandidatsDansParcours = candidatsCountParParcours.get(parcours);

                if (totalCandidatsDansParcours >= quotaGlobal) {
                    continue;
                }

                // Initialisation des structures si nécessaire
                candidatsOrganises
                    .computeIfAbsent(parcours, k -> new HashMap<>())
                    .computeIfAbsent(etablissement, k -> new HashMap<>())
                    .computeIfAbsent(specialite, k -> new ArrayList<>());

                candidatsCountParSpecialite
                    .computeIfAbsent(parcours, k -> new HashMap<>())
                    .putIfAbsent(specialite, 0);

                int quotaSpecialite = quotasSpecialites.getOrDefault(parcours, new HashMap<>()).getOrDefault(specialite, Integer.MAX_VALUE);
                int totalCandidatsDansSpecialite = candidatsCountParSpecialite.get(parcours).get(specialite);

                // Vérifier s'il y a encore de la place dans cette spécialité
                if (totalCandidatsDansSpecialite < quotaSpecialite) {
                    candidatsOrganises.get(parcours).get(etablissement).get(specialite).add(c);
                    candidatsCountParSpecialite.get(parcours).put(specialite, totalCandidatsDansSpecialite + 1);
                    candidatsCountParParcours.put(parcours, totalCandidatsDansParcours + 1);
                    placed = true;
                    c.setStatut("retenu");
                    break;
                } else if (totalCandidatsDansSpecialite == quotaSpecialite) {
                    candidatsOrganises.get(parcours).get(etablissement).get(specialite).add(c);
                    candidatsCountParSpecialite.get(parcours).put(specialite, totalCandidatsDansSpecialite + 1);
                    candidatsCountParParcours.put(parcours, totalCandidatsDansParcours + 1);
                    placed = true;
                    c.setStatut("en attente");
                    break;
                }
            }

            // Si le candidat n'a pas été placé dans ses préférences, chercher le parcours le plus proche
            if (!placed) {
                String closestParcours = findClosestParcours(candidatsOrganises, quotasSpecialites, parcoursPref);
                if (closestParcours != null) {
                    String etablissement = c.getEtablissement().getNom();
                    String specialite = c.getFilier();

                    int quotaGlobal = quotasGlobal.get(closestParcours);
                    int totalCandidatsDansParcours = candidatsCountParParcours.get(closestParcours);

                    if (totalCandidatsDansParcours < quotaGlobal) {
                        candidatsOrganises.computeIfAbsent(closestParcours, k -> new HashMap<>())
                            .computeIfAbsent(etablissement, k -> new HashMap<>())
                            .computeIfAbsent(specialite, k -> new ArrayList<>())
                            .add(c);

                        int totalCandidatsDansSpecialite = candidatsCountParSpecialite.get(closestParcours).getOrDefault(specialite, 0);
                        candidatsCountParSpecialite.get(closestParcours).put(specialite, totalCandidatsDansSpecialite + 1);
                        candidatsCountParParcours.put(closestParcours, totalCandidatsDansParcours + 1);

                        int quotaSpecialite = quotasSpecialites.getOrDefault(closestParcours, new HashMap<>()).getOrDefault(specialite, Integer.MAX_VALUE);
                        if (totalCandidatsDansSpecialite < quotaSpecialite) {
                            c.setStatut("retenu");
                        } else if (totalCandidatsDansSpecialite == quotaSpecialite) {
                            c.setStatut("en attente");
                        } else {
                            c.setStatut("non retenu");
                        }
                    } else {
                        c.setStatut("non retenu");
                    }
                } else {
                    String etablissement = c.getEtablissement().getNom();
                    String specialite = c.getFilier();

                    candidatsOrganises.computeIfAbsent(parcoursPref[0], k -> new HashMap<>())
                        .computeIfAbsent(etablissement, k -> new HashMap<>())
                        .computeIfAbsent(specialite, k -> new ArrayList<>())
                        .add(c);

                    c.setStatut("non retenu");
                }
            }

            // Mise à jour du candidat en base
            candidatRepository.save(c);
        }

        return candidatsOrganises;
    }

    private String findClosestParcours(Map<String, Map<String, Map<String, List<Candidat>>>> candidatsOrganises,
                                      Map<String, Map<String, Integer>> quotasSpecialites,
                                      String[] parcoursPref) {
        String closestParcours = null;
        int maxEspaceRestant = -1;

        for (String parcours : parcoursPref) {
            parcours = parcours.trim();
            if (parcours.isEmpty() || !quotasSpecialites.containsKey(parcours)) continue;

            int totalQuota = quotasSpecialites.get(parcours).values().stream().mapToInt(Integer::intValue).sum();
            int currentCount = candidatsOrganises.getOrDefault(parcours, new HashMap<>())
                .values().stream()
                .flatMap(e -> e.values().stream())
                .mapToInt(List::size)
                .sum();

            int espaceRestant = totalQuota - currentCount;
            if (espaceRestant > maxEspaceRestant) {
                maxEspaceRestant = espaceRestant;
                closestParcours = parcours;
            }
        }

        return closestParcours;
    }
}