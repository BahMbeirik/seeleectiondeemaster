package com.selection.selectionMaster.models;

import jakarta.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "candidats")
public class Candidat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @NotBlank(message = "Le nom est obligatoire")
    @Column(name = "nom", nullable = false)
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Column(name = "prenom", nullable = false)
    private String prenom;

    @NotBlank(message = "La filière est obligatoire")
    @Column(name = "filier", nullable = false)
    private String filier;

    @NotBlank(message = "Le numéro de bac ou I est obligatoire")
    @Column(name = "numero_I_ou_bac", nullable = false)
    private String numeroIouBac;

    @NotNull(message = "L'année du bac est obligatoire")
    @Column(name = "annee_bac", nullable = false)
    private Integer anneeBac;

    @NotNull(message = "L'année de la licence est obligatoire")
    @Column(name = "annee_licence", nullable = false)
    private Integer anneeLicence;

    @NotBlank(message = "La moyenne du bac est obligatoire")
    @Column(name = "moyenne_bac", nullable = false)
    private String moyenneBac;

    @NotBlank(message = "La moyenne de la licence est obligatoire")
    @Column(name = "moyenne_licence", nullable = false)
    private String moyenneLicence;

    @Column(name = "diplome_bac_path")
    private String diplomeBacPath;

    @Column(name = "diplome_licence_path")
    private String diplomeLicencePath;

    @Column(name = "releve1_path")
    private String releve1Path;

    @Column(name = "moyenne_releve1")
    private String moyenneReleve1;

    @Column(name = "releve2_path")
    private String releve2Path;

    @Column(name = "moyenne_releve2")
    private String moyenneReleve2;

    @Column(name = "releve3_path")
    private String releve3Path;

    @Column(name = "moyenne_releve3")
    private String moyenneReleve3;

    @Column(name = "releve4_path")
    private String releve4Path;

    @Column(name = "moyenne_releve4")
    private String moyenneReleve4;

    @Column(name = "releve5_path")
    private String releve5Path;

    @Column(name = "moyenne_releve5")
    private String moyenneReleve5;

    @Column(name = "releve6_path")
    private String releve6Path;

    @Column(name = "moyenne_releve6")
    private String moyenneReleve6;

    @Column(name = "bonnus", nullable = true)
    private Float bonnus;

    @Column(name = "malus", nullable = true)
    private Double malus;

    @Column(name = "penalite", nullable = true)
    private Double penalite;

    @Column(name = "moyenne_classement", nullable = true)
    private Double moyenneClassement;

    @NotBlank(message = "L'état de validation 1 est obligatoire")
    @Column(name = "etat_validation1", nullable = false)
    private String etatValidation1;

    @NotBlank(message = "L'état de validation 2 est obligatoire")
    @Column(name = "etat_validation2", nullable = false)
    private String etatValidation2;

    @NotBlank(message = "L'état de validation 3 est obligatoire")
    @Column(name = "etat_validation3", nullable = false)
    private String etatValidation3;

    @NotBlank(message = "L'état de validation 4 est obligatoire")
    @Column(name = "etat_validation4", nullable = false)
    private String etatValidation4;

    @NotBlank(message = "L'état de validation 5 est obligatoire")
    @Column(name = "etat_validation5", nullable = false)
    private String etatValidation5;

    @NotBlank(message = "L'état de validation 6 est obligatoire")
    @Column(name = "etat_validation6", nullable = false)
    private String etatValidation6;

    @Column(name = "parcours")
    private String parcours;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
        name = "candidat_parcours",
        joinColumns = @JoinColumn(name = "candidat_id"),
        inverseJoinColumns = @JoinColumn(name = "parcours_id")
    )
    @JsonIgnoreProperties("candidats")
    private Set<Parcours> parcoursChoisis = new HashSet<>();
    

    @ManyToOne
    @JoinColumn(name = "etablissement_id", nullable = false)
    @JsonIgnoreProperties("candidats") // Évite les références circulaires
    private Etablissement etablissement;

    @Column(name = "statut")
    private String statut;
    
    // Constructeur
    public Candidat() {
      this.penalite = 0.0; // Initialisation par défaut
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getFilier() {
        return filier;
    }

    public void setFilier(String filier) {
        this.filier = filier;
    }

    public String getNumeroIouBac() {
        return numeroIouBac;
    }

    public void setNumeroIouBac(String numeroIouBac) {
        this.numeroIouBac = numeroIouBac;
    }

    public int getAnneeBac() {
        return anneeBac;
    }

    public void setAnneeBac(int anneeBac) {
        this.anneeBac = anneeBac;
    }

    public int getAnneeLicence() {
        return anneeLicence;
    }

    public void setAnneeLicence(int anneeLicence) {
        this.anneeLicence = anneeLicence;
    }

    public String getMoyenneBac() {
        return moyenneBac;
    }

    public void setMoyenneBac(String moyenneBac) {
        this.moyenneBac = moyenneBac;
    }

    public String getMoyenneLicence() {
        return moyenneLicence;
    }

    public void setMoyenneLicence(String moyenneLicence) {
        this.moyenneLicence = moyenneLicence;
    }

    public String getDiplomeBacPath() {
        return diplomeBacPath;
    }

    public void setDiplomeBacPath(String diplomeBacPath) {
        this.diplomeBacPath = diplomeBacPath;
    }

    public String getDiplomeLicencePath() {
        return diplomeLicencePath;
    }

    public void setDiplomeLicencePath(String diplomeLicencePath) {
        this.diplomeLicencePath = diplomeLicencePath;
    }

    public String getReleve1Path() {
        return releve1Path;
    }

    public void setReleve1Path(String releve1Path) {
        this.releve1Path = releve1Path;
    }

    public String getMoyenneReleve1() {
        return moyenneReleve1;
    }

    public void setMoyenneReleve1(String moyenneReleve1) {
        this.moyenneReleve1 = moyenneReleve1;
    }

    public String getReleve2Path() {
        return releve2Path;
    }

    public void setReleve2Path(String releve2Path) {
        this.releve2Path = releve2Path;
    }

    public String getMoyenneReleve2() {
        return moyenneReleve2;
    }

    public void setMoyenneReleve2(String moyenneReleve2) {
        this.moyenneReleve2 = moyenneReleve2;
    }

    public String getReleve3Path() {
        return releve3Path;
    }

    public void setReleve3Path(String releve3Path) {
        this.releve3Path = releve3Path;
    }

    public String getMoyenneReleve3() {
        return moyenneReleve3;
    }

    public void setMoyenneReleve3(String moyenneReleve3) {
        this.moyenneReleve3 = moyenneReleve3;
    }

    public String getReleve4Path() {
        return releve4Path;
    }

    public void setReleve4Path(String releve4Path) {
        this.releve4Path = releve4Path;
    }

    public String getMoyenneReleve4() {
        return moyenneReleve4;
    }

    public void setMoyenneReleve4(String moyenneReleve4) {
        this.moyenneReleve4 = moyenneReleve4;
    }

    public String getReleve5Path() {
        return releve5Path;
    }

    public void setReleve5Path(String releve5Path) {
        this.releve5Path = releve5Path;
    }

    public String getMoyenneReleve5() {
        return moyenneReleve5;
    }

    public void setMoyenneReleve5(String moyenneReleve5) {
        this.moyenneReleve5 = moyenneReleve5;
    }

    public String getReleve6Path() {
        return releve6Path;
    }

    public void setReleve6Path(String releve6Path) {
        this.releve6Path = releve6Path;
    }

    public String getMoyenneReleve6() {
        return moyenneReleve6;
    }

    public void setMoyenneReleve6(String moyenneReleve6) {
        this.moyenneReleve6 = moyenneReleve6;
    }

    public Float getBonnus() {
        return bonnus;
    }

    public void setBonnus(Float bonnus) {
        this.bonnus = bonnus;
    }

    public Double getMalus() {
        return malus;
    }

    public void setMalus(Double malus) {
        this.malus = malus;
    }

    
    public Double getPenalite() {
      return penalite != null ? penalite : 0.0;
    }

    public void setPenalite(Double penalite) {
        this.penalite = penalite;
    }

    public Double getMoyenneClassement() {
        return moyenneClassement;
    }

    public void setMoyenneClassement(Double moyenneClassement) {
        this.moyenneClassement = moyenneClassement;
    }

    public String getEtatValidation1() {
        return etatValidation1;
    }

    public void setEtatValidation1(String etatValidation1) {
        this.etatValidation1 = etatValidation1;
    }

    public String getEtatValidation2() {
        return etatValidation2;
    }

    public void setEtatValidation2(String etatValidation2) {
        this.etatValidation2 = etatValidation2;
    }

    public String getEtatValidation3() {
        return etatValidation3;
    }

    public void setEtatValidation3(String etatValidation3) {
        this.etatValidation3 = etatValidation3;
    }

    public String getEtatValidation4() {
        return etatValidation4;
    }

    public void setEtatValidation4(String etatValidation4) {
        this.etatValidation4 = etatValidation4;
    }

    public String getEtatValidation5() {
        return etatValidation5;
    }

    public void setEtatValidation5(String etatValidation5) {
        this.etatValidation5 = etatValidation5;
    }

    public String getEtatValidation6() {
        return etatValidation6;
    }

    public void setEtatValidation6(String etatValidation6) {
        this.etatValidation6 = etatValidation6;
    }

    public Set<Parcours> getParcoursChoisis() {
        return parcoursChoisis;
    }

    public void setParcoursChoisis(Set<Parcours> parcoursChoisis) {
        this.parcoursChoisis = parcoursChoisis;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
    public String getParcours() { return parcours; }
    public void setParcours(String parcours) { this.parcours = parcours; }
}