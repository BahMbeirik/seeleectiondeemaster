package com.selection.selectionMaster.DTO;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class CandidatDTO {
  private String nom;
    private String prenom;
    private String filier;
    private String numeroIouBac;
    private int anneeBac;
    private int anneeLicence;
    private String moyenneBac;
    private String moyenneLicence;
    private String etatValidation1;
    private String etatValidation2;
    private String etatValidation3;
    private String etatValidation4;
    private String etatValidation5;
    private String etatValidation6;
    private String moyenneReleve1;
    private String moyenneReleve2;
    private String moyenneReleve3;
    private String moyenneReleve4;
    private String moyenneReleve5;
    private String moyenneReleve6;
    private Long etablissementId;
    private List<Long> parcoursIds;
    private MultipartFile diplomeBacPath;
    private MultipartFile diplomeLicencePath;
    private MultipartFile releve1Path;
    private MultipartFile releve2Path;
    private MultipartFile releve3Path;
    private MultipartFile releve4Path;
    private MultipartFile releve5Path;
    private MultipartFile releve6Path;

    //gtters and setters
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
    public String getMoyenneReleve1() {
      return moyenneReleve1;
    }
    public void setMoyenneReleve1(String moyenneReleve1) {
      this.moyenneReleve1 = moyenneReleve1;
    }
    public String getMoyenneReleve2() {
      return moyenneReleve2;
    }
    public void setMoyenneReleve2(String moyenneReleve2) {
      this.moyenneReleve2 = moyenneReleve2;
    }
    public String getMoyenneReleve3() {
      return moyenneReleve3;
    }
    public void setMoyenneReleve3(String moyenneReleve3) {
      this.moyenneReleve3 = moyenneReleve3;
    }
    public String getMoyenneReleve4() {
      return moyenneReleve4;
    }
    public void setMoyenneReleve4(String moyenneReleve4) {
      this.moyenneReleve4 = moyenneReleve4;
    }
    public String getMoyenneReleve5() {
      return moyenneReleve5;
    }
    public void setMoyenneReleve5(String moyenneReleve5) {
      this.moyenneReleve5 = moyenneReleve5;
    }
    public String getMoyenneReleve6() {
      return moyenneReleve6;
    }
    public void setMoyenneReleve6(String moyenneReleve6) {
      this.moyenneReleve6 = moyenneReleve6;
    }
    public Long getEtablissementId() {
      return etablissementId;
    }
    public void setEtablissementId(Long etablissementId) {
      this.etablissementId = etablissementId;
    }
    public List<Long> getParcoursIds() {
      return parcoursIds;
    }
    public void setParcoursIds(List<Long> parcoursIds) {
      this.parcoursIds = parcoursIds;
    }
    public MultipartFile getDiplomeBacPath() {
      return diplomeBacPath;
    }
    public void setDiplomeBacPath(MultipartFile diplomeBacPath) {
      this.diplomeBacPath = diplomeBacPath;
    }
    public MultipartFile getDiplomeLicencePath() {
      return diplomeLicencePath;
    }
    public void setDiplomeLicencePath(MultipartFile diplomeLicencePath) {
      this.diplomeLicencePath = diplomeLicencePath;
    }
    public MultipartFile getReleve1Path() {
      return releve1Path;
    }
    public void setReleve1Path(MultipartFile releve1Path) {
      this.releve1Path = releve1Path;
    }
    public MultipartFile getReleve2Path() {
      return releve2Path;
    }
    public void setReleve2Path(MultipartFile releve2Path) {
      this.releve2Path = releve2Path;
    }
    public MultipartFile getReleve3Path() {
      return releve3Path;
    }
    public void setReleve3Path(MultipartFile releve3Path) {
      this.releve3Path = releve3Path;
    }
    public MultipartFile getReleve4Path() {
      return releve4Path;
    }
    public void setReleve4Path(MultipartFile releve4Path) {
      this.releve4Path = releve4Path;
    }
    public MultipartFile getReleve5Path() {
      return releve5Path;
    }
    public void setReleve5Path(MultipartFile releve5Path) {
      this.releve5Path = releve5Path;
    }
    public MultipartFile getReleve6Path() {
      return releve6Path;
    }
    public void setReleve6Path(MultipartFile releve6Path) {
      this.releve6Path = releve6Path;
    }


  

  
  
}