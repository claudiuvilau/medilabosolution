package com.patient.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.patient.exceptions.PatientNotFoundException;
import com.patient.model.Patient;
import com.patient.repository.PatientRepository;

@RestController
public class PatientController {

    @Autowired
    PatientRepository patientRepository;

    /**
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
     * @param id à saisir l'ID du patient qui se trouve dans la table
     * @return le patirant avec l'ID donné
     */
    @GetMapping(value = "/Patient/{id}")
    public Optional<Patient> recupererUnPatientById(@PathVariable int id) {

        Optional<Patient> patient = patientRepository.findById(id);

        if (!patient.isPresent())
            throw new PatientNotFoundException("Le patient correspondant à l'id " + id + " n'existe pas");

        return patient;
    }

    @GetMapping(value = "/PatientBirthDay/{birthDay}")
    public Optional<Patient> recupererUnPatientByBirthDate(@PathVariable String birthDay) {

        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(birthDay, pattern);

        Optional<Patient> patient = patientRepository.findBydateNaissance(birthDate);

        if (!patient.isPresent())
            throw new PatientNotFoundException(
                    "Le patient correspondant avec la date de naissance " + birthDate + " n'existe pas");

        return patient;
    }

    @GetMapping(value = "/PatientName/{name}")
    public Optional<Patient> recupererUnPatientByName(@PathVariable String name) {

        Optional<Patient> patient = patientRepository.findByNom(name);

        if (!patient.isPresent())
            throw new PatientNotFoundException(
                    "Le patient correspondant avec le nom " + name + " n'existe pas");

        return patient;
    }

}
