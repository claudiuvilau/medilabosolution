package com.openclassrooms.frontservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.openclassrooms.frontservice.beans.PatientBean;
import com.openclassrooms.frontservice.proxies.MicroservicePatientProxy;

@RestController
public class FrontController {

    @Autowired
    private MicroservicePatientProxy microservicePatientProxy;

    private ModelAndView modelAndView = new ModelAndView();

    @GetMapping("/Accueil")
    public ModelAndView home(Model model) {

        modelAndView.setViewName("list.html");

        List<PatientBean> patients = microservicePatientProxy.listeDesPatients();

        model.addAttribute("patients", patients);

        return modelAndView;
    }

    @GetMapping(value = "/PatientParId/{id}")
    public PatientBean recupererUnPatientById(@PathVariable int id) {

        PatientBean patient = microservicePatientProxy.recupererUnPatientById(id);

        return patient;
    }

    @GetMapping(value = "/date-naissance/{birthDay}")
    public List<PatientBean> recupererUnPatientByBirthDate(@PathVariable String birthDay) {

        List<PatientBean> patients = microservicePatientProxy.recupererUnPatientByBirthDate(birthDay);

        return patients;
    }

    @GetMapping(value = "/PatientParNom/{name}")
    public List<PatientBean> recupererUnPatientByName(@PathVariable String name) {

        List<PatientBean> patients = microservicePatientProxy.recupererUnPatientByName(name);

        return patients;

    }

    @GetMapping("/AjouterPatient")
    public ModelAndView addPatient(Model model, PatientBean patient) {

        modelAndView.setViewName("add.html");
        model.addAttribute("patient", patient);

        return modelAndView;
    }

    @PostMapping(value = "/AddPatient")
    public ResponseEntity<PatientBean> addPerson(@RequestBody PatientBean patient) {

        microservicePatientProxy.addPerson(patient);

        return new ResponseEntity<>(patient, HttpStatus.CREATED);
    }
}
