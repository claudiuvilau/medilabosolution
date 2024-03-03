package com.openclassrooms.frontservice.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openclassrooms.frontservice.beans.PatientBean;

@FeignClient(name = "microservice-gateway", url = "localhost:8888")
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
    ResponseEntity<PatientBean> addPerson(@RequestBody PatientBean patient);

}
