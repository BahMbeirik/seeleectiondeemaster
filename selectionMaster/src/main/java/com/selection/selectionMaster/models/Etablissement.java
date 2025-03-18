package com.selection.selectionMaster.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "etablissements")
public class Etablissement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false)
    private String nom;

    @OneToMany(mappedBy = "etablissement")
    @JsonIgnoreProperties("etablissement")
    private Set<Candidat> candidats = new HashSet<>();
    

    // No-argument constructor required by JPA
    public Etablissement() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<Candidat> getCandidats() {
        return candidats;
    }

    public void setCandidats(Set<Candidat> candidats) {
        this.candidats = candidats;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etablissement that = (Etablissement) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // toString
    @Override
    public String toString() {
        return "Etablissement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}