package com.openclassrooms.frontservice.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.openclassrooms.frontservice.beans.PatientBean;

import jakarta.validation.Valid;

@FeignClient(name = "microservice-patient", url = "localhost:8082")
public interface MicroservicePatientProxy {

    @GetMapping(value = "/Patients")
    List<PatientBean> listeDesPatients();

    @GetMapping(value = "/Patient/{id}")
    PatientBean recupererUnPatientById(@PathVariable("id") int id);

    @GetMapping(value = "/PatientBirthDay/{birthDay}")
    List<PatientBean> recupererUnPatientByBirthDate(@PathVariable("birthDay") String birthDay);

    @GetMapping(value = "/PatientName/{name}")
    List<PatientBean> recupererUnPatientByName(@PathVariable("name") String name);

    @PostMapping(value = "/AddPatient")
    PatientBean addPerson(@Valid PatientBean patient);

    @PutMapping(value = "/UpdatePatient")
    PatientBean majPerson(@Valid PatientBean patient);

}
