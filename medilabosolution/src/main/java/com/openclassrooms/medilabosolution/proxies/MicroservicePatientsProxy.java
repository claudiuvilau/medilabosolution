package com.openclassrooms.medilabosolution.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.openclassrooms.medilabosolution.beans.PatientBean;

@FeignClient(name = "microservice-patient", url = "localhost:8082") // name = name in application.properties of
                                                                    // microservice patient
public interface MicroservicePatientsProxy {

        @GetMapping(value = "/Patients")
        List<PatientBean> listeDesPatients();

        @GetMapping(value = "/Patient/{id}")
        PatientBean recupererUnPatientById(@PathVariable("id") int id);

        @GetMapping(value = "/PatientBirthDay/{birthDay}")
        List<PatientBean> recupererUnPatientByBirthDate(@PathVariable("birthDay") String birthDay);

        @GetMapping(value = "/PatientName/{name}")
        List<PatientBean> recupererUnPatientByName(@PathVariable("name") String name);

        @PostMapping(value = "/PostPatient")
        PatientBean addPerson(@RequestBody PatientBean patient);

        @PutMapping(value = "/PutPatient")
        PatientBean updatePerson(@RequestBody PatientBean patient);

        @DeleteMapping(value = "/DeletePatientById/{id}")
        void deletePersonById(@PathVariable("id") int id);
}
