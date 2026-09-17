package com.example.patient.controller;

import com.example.patient.model.Patient;
import com.example.patient.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
     @Autowired
     private PatientService patientService;

    @PostMapping
    public Patient addPatient(@RequestBody Patient patient) {
        return PatientService.savePatient(patient);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return PatientService.getAllPatient();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return PatientService.getPatientById(id);
    }

    @GetMapping("/author/{author}")
    public List<Patient> getPatientsByAuthor(@PathVariable String author) {
        return PatientService.getPatientByAuthor(author);
    }

    @DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable Long id) {
        PatientService.deletePatientById(id);
    }

    @PatchMapping("/{id}/available")
    public Patient updatePatientAvailable(@PathVariable Long id, @RequestParam boolean available) {
        return PatientService.updatePatientAvailable(id, available);
    }

    @PutMapping
    public Patient updatePatient(@RequestBody Patient Patient, @PathVariable Long id) {
        return PatientService.updatePatient(id, Patient);
    }

}