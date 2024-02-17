package com.openclassrooms.medilabosolution.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "patients")
public class Patient {

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
    @NotBlank(message = "Account is mandatory")
    private Date dateNaissance;

    @Column(name = "genre")
    @NotBlank(message = "Account is mandatory")
    private String genre;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "telephone")
    private String telephone;

}
