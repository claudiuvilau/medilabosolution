package com.openclassrooms.medilabosolution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.medilabosolution.beans.PatientBean;
import com.openclassrooms.medilabosolution.proxies.MicroservicePatientsProxy;

@RestController
public class MedilabosolutionController {

    @Autowired
    private MicroservicePatientsProxy microservicePatientsProxy;

    @RequestMapping("/ListPatients")
    public List<PatientBean> listeDesPatients() {

        List<PatientBean> patientsList = microservicePatientsProxy.listeDesPatients();

        return patientsList;

    }

    @RequestMapping("/IdPatient/{id}")
    public PatientBean patientById(@PathVariable int id) {

        PatientBean patientId = microservicePatientsProxy.recupererUnPatientById(id);

        return patientId;

    }

    @RequestMapping("/BirthdayPatient/{birthDay}")
    public List<PatientBean> patientByBirthday(@PathVariable String birthDay) {

        List<PatientBean> patientsList = microservicePatientsProxy.recupererUnPatientByBirthDate(birthDay);

        return patientsList;

    }

    @RequestMapping("/NamePatient/{name}")
    public List<PatientBean> patientByName(@PathVariable String name) {

        List<PatientBean> patientsList = microservicePatientsProxy.recupererUnPatientByName(name);

        return patientsList;

    }

    @PostMapping(value = "/AddPatient")
    public void addPerson(@RequestBody PatientBean patient) {

        microservicePatientsProxy.addPerson(patient);

    }

    @PutMapping(value = "/UpdatePatient")
    public void updatePerson(@RequestBody PatientBean patient) {

        microservicePatientsProxy.updatePerson(patient);

    }

    @DeleteMapping(value = "/DeletePatientById/{id}")
    public void deletePersonById(
            @PathVariable int id) {

        microservicePatientsProxy.deletePersonById(id);
    }

}
