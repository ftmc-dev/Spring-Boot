package com.example.patient.service;

import com.example.patient.model.Patient;
import com.example.patient.repository.PatientRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient savePatient(Patient Patient) {
        if (patientRepository.existsByIsbn(Patient.getIsbn())) {
            throw new EntityExistsException("Patient with ISBN " + Patient.getIsbn() + " already exists");
        }
        return patientRepository.save(Patient);
    }

    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Long id) {
        Optional<Patient> Patient = patientRepository.findPatientById(id);
        if (Patient.isEmpty()) {
            throw new RuntimeException("Patient not found");
        }
        return Patient.get();

    }

    public List<Patient> getPatientByAuthor(String author) {
        List<Patient> Patient = patientRepository.findPatientByAuthor(author);

        if (Patient.isEmpty()) {
            throw new RuntimeException("Patient not found");
        }
        return Patient;
    }

    public void deletePatientById(Long id) {
       patientRepository.deleteById(id);

    }

    public Patient updatePatient(Long id, Patient Patient) {
        if (patientRepository.existsById(id)) {
            return patientRepository.save(Patient);
        }
        throw new RuntimeException("Patient not found");
    }
}
