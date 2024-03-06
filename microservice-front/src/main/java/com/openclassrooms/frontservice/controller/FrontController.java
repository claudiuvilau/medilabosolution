package com.openclassrooms.frontservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.openclassrooms.frontservice.beans.PatientBean;
import com.openclassrooms.frontservice.proxies.MicroservicePatientProxy;
import com.openclassrooms.frontservice.service.FormatDate;

import jakarta.validation.Valid;

@RestController
public class FrontController {

    @Autowired
    private MicroservicePatientProxy microservicePatientProxy;

    @Autowired
    private FormatDate formatDateFormat;

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
    public ModelAndView addFormPatient(Model model, PatientBean patient) {

        modelAndView.setViewName("add.html");
        model.addAttribute("patient", patient);

        return modelAndView;
    }

    @PostMapping(value = "/AddPatient")
    public ModelAndView addPatient(Model model, @Valid PatientBean patient) {

        microservicePatientProxy.addPerson(patient);

        modelAndView.setViewName("list.html");

        List<PatientBean> patients = microservicePatientProxy.listeDesPatients();

        model.addAttribute("patients", patients);

        return modelAndView;
    }

    @GetMapping("/MAJPatient/{id}")
    public ModelAndView majFormPatient(@PathVariable("id") Integer id, Model model) {

        PatientBean patient = microservicePatientProxy.recupererUnPatientById(id);

        // .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        modelAndView.setViewName("update.html");
        model.addAttribute("patient", patient);

        return modelAndView;
    }

    @PutMapping(value = "/UpdatePatient/{id}")
    public ModelAndView majPatient(@PathVariable("id") Integer id, @Valid PatientBean patient,
            BindingResult result, Model model) {

        if (result.hasErrors()) {

            return home(model);

        }

        microservicePatientProxy.majPerson(patient);

        modelAndView.setViewName("update.html");

        List<PatientBean> patients = microservicePatientProxy.listeDesPatients();

        model.addAttribute("patients", patients);

        return modelAndView;
    }
}
