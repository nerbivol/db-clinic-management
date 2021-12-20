package edu.kpi.iasa.clinic.api;

import edu.kpi.iasa.clinic.api.dto.DeclarationDto;
import edu.kpi.iasa.clinic.repository.model.Clinic;
import edu.kpi.iasa.clinic.service.impl.ClinicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/declarations")
public class ClinicController {

    final private ClinicServiceImpl clinicServiceImpl;

    @Autowired
    public ClinicController(ClinicServiceImpl clinicServiceImpl) {
        this.clinicServiceImpl = clinicServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Clinic>> index(){
        final List<Clinic> declarations = clinicServiceImpl.GetAllDeclarations();
        return ResponseEntity.ok(declarations);
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<String>> showDoctors(){
        List<String> doctors = clinicServiceImpl.GetDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/doctors/{idDoctor}")
    public ResponseEntity<List<Clinic>> showByDoctorId(@PathVariable long idDoctor){
        List<Clinic> declarations = clinicServiceImpl.GetByIdDoctor(idDoctor);
        return ResponseEntity.ok(declarations);
    }

    @PostMapping
    public ResponseEntity<Clinic> create(@RequestBody DeclarationDto newDeclaration){
        final long patient =  newDeclaration.getIdPatient();
        final long doctor = newDeclaration.getIdDoctor();
        final long decl = clinicServiceImpl.createDeclaration(patient, doctor);
        final String declarationUri = String.format("/declaration/patient/%d", decl);

        return ResponseEntity.created(URI.create(declarationUri)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody DeclarationDto declaration) {
        final long idPatient = declaration.getIdPatient();
        final long idDoctor = declaration.getIdDoctor();

        clinicServiceImpl.updateDeclaration(idPatient, idDoctor);
        return ResponseEntity.noContent().build();
    }

}
