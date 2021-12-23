package edu.kpi.iasa.clinic.api;

import edu.kpi.iasa.clinic.repository.model.Clinic;
import edu.kpi.iasa.clinic.service.impl.ClinicServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/clinic")
public class ClinicController {

    private final ClinicServiceImpl clinicServiceImpl;

    @GetMapping
    public ResponseEntity<List<Clinic>> index(){
        return ResponseEntity.ok(clinicServiceImpl.getAllDiagnostic());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clinic> showById(@PathVariable long id){
        try{
            final Clinic clinic = clinicServiceImpl.getDiagnosticById(id);
            return ResponseEntity.ok(clinic);
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Clinic clinic){
        final long clinicId = clinicServiceImpl.createDiagnostic(clinic);
        final String clinicUri = String.format("/clinic/%d", clinicId);
        return ResponseEntity.created(URI.create(clinicUri)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody Clinic clinic){
        final long idPatient = clinic.getIdPatient();
        final String complains = clinic.getComplains();
        final String conclusion = clinic.getConclusion();
        final String diagnose = clinic.getDiagnose();
        final double price = clinic.getPrice();
        final String additionalReview = clinic.getAdditionalReview();

        try {
            clinicServiceImpl.updateDiagnostic(id, idPatient, complains, conclusion, diagnose, price, additionalReview);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        clinicServiceImpl.deleteDiagnostic(id);
        return ResponseEntity.noContent().build();
    }

}
