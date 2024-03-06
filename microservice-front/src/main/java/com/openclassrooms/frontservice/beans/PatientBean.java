package com.openclassrooms.frontservice.beans;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "patients")
public class PatientBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nom")
    @NotBlank(message = "Account is mandatory")
    private String nom;

    @Column(name = "prenom")
    @NotBlank(message = "Account is mandatory")
    private String prenom;

    @Column(name = "date_de_naissance")
    @NotNull(message = "Account is mandatory")
    private LocalDate dateNaissance;

    @Column(name = "genre")
    @NotBlank(message = "Account is mandatory")
    private String genre;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;

    public PatientBean() {
    }

    public PatientBean(Integer id, @NotBlank(message = "Account is mandatory") String nom,
            @NotBlank(message = "Account is mandatory") String prenom,
            @NotBlank(message = "Account is mandatory") LocalDate dateNaissance,
            @NotBlank(message = "Account is mandatory") String genre, String adresse, String telephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.genre = genre;
        this.adresse = adresse;
        this.telephone = telephone;
    }

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

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
