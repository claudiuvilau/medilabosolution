package com.patient.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.patient.exceptions.PatientNotFoundException;
import com.patient.model.Patient;
import com.patient.repository.PatientRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    /**
     * Cette méthode récupère la liste de tous les patients
     * 
     * @return la liste des patients
     */
    @GetMapping(value = "/Patients")
    public List<Patient> listeDesPatients() {

        List<Patient> patients = patientRepository.findAll();

        if (patients.isEmpty()) {
            throw new PatientNotFoundException("Aucun patient n'est disponible");
        } else

            return patients;

    }

    /**
     * Cette méthode récupère un patien selon son ID
     * 
     * @param id à saisir l'ID du patient qui se trouve dans la table
     * @return le patient avec l'ID donné
     */
    @GetMapping(value = "/Patient/{id}")
    public Optional<Patient> recupererUnPatientById(@PathVariable int id) {

        Optional<Patient> patient = patientRepository.findById(id);

        if (!patient.isPresent())
            throw new PatientNotFoundException("Le patient correspondant à l'id " + id + " n'existe pas");

        return patient;
    }

    /**
     * Cette méthode récupère une list des patients avec la date de naissance en
     * format AAAA-MM-JJ
     * 
     * @param birthDay
     * @return la liste des patients ayant la date de naissance donnée
     */
    @GetMapping(value = "/PatientBirthDay/{birthDay}")
    public List<Patient> recupererUnPatientByBirthDate(@PathVariable String birthDay) {

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthDay, pattern);

        List<Patient> patients = patientRepository.findBydateNaissance(birthDate);

        if (patients.isEmpty())
            throw new PatientNotFoundException(
                    "Le patient correspondant avec la date de naissance " + birthDate + " n'existe pas");

        return patients;
    }

    /**
     * Cette méthode récupère une liste des patients qui correspondent au nom donné
     * 
     * @param name
     * @return la liste des patients ayant le nom donné
     */
    @GetMapping(value = "/PatientName/{name}")
    public List<Patient> recupererUnPatientByName(@PathVariable String name) {

        List<Patient> patients = patientRepository.findByNom(name);

        if (patients.isEmpty())
            throw new PatientNotFoundException(
                    "Le patient correspondant avec le nom " + name + " n'existe pas");

        return patients;
    }

    /**
     * 
     * Cette méthode ajoute un nouveau patient. Si l'ID donné existe => la methode
     * met à
     * jour le patient. Si l'ID donné n'existe pas => le patient sera ajouté. Si
     * l'ID non spécifié => ajout d'un nouveau patient
     * 
     * @param patient
     * @param request
     * @param response
     * @return ResponseEntity<Patient>
     */
    @PostMapping(value = "/AddPatient")
    public ResponseEntity<Patient> addPerson(@RequestBody Patient patient,
            HttpServletResponse response) {

        patientRepository.save(patient);

        response.setStatus(201);
        return new ResponseEntity<>(patient, HttpStatus.valueOf(response.getStatus()));
    }

    @PutMapping(value = "/UpdatePatient")
    public ResponseEntity<Patient> updatePerson(@RequestBody Patient patient,
            HttpServletResponse response) {

        patientRepository.save(patient);

        response.setStatus(200);
        return new ResponseEntity<>(patient, HttpStatus.valueOf(response.getStatus()));
    }

    /**
     * 
     * Cette méthode supprime un patient si l'ID donné existe
     * 
     * @param response
     * @return ResponseEntity<Void>
     */
    @DeleteMapping(value = "/DeletePatientById/{id}")
    public ResponseEntity<Void> deletePersonById(
            @PathVariable int id,
            HttpServletResponse response) {

        patientRepository.deleteById(id);

        response.setStatus(200);
        return ResponseEntity.status(response.getStatus()).build();
    }

}
