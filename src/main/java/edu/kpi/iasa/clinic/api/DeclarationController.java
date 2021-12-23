package edu.kpi.iasa.clinic.api;

import edu.kpi.iasa.clinic.api.dto.DeclarationDto;
import edu.kpi.iasa.clinic.repository.model.Declaration;
import edu.kpi.iasa.clinic.service.impl.DeclarationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/declarations")
public class DeclarationController {

    final private DeclarationServiceImpl declarationServiceImpl;

    @Autowired
    public DeclarationController(DeclarationServiceImpl declarationServiceImpl) {
        this.declarationServiceImpl = declarationServiceImpl;
    }

    @GetMapping
    public ResponseEntity<List<Declaration>> index(){
        final List<Declaration> declarations = declarationServiceImpl.GetAllDeclarations();
        return ResponseEntity.ok(declarations);
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<String>> showAllDoctors(){
        return ResponseEntity.ok(declarationServiceImpl.GetAllDoctor());
    }

    @GetMapping("/doctors/{idDoctor}")
    public ResponseEntity<List<Declaration>> showByDoctorId(@PathVariable long idDoctor){
        List<Declaration> declarations = declarationServiceImpl.GetByDoctorId(idDoctor);
        return ResponseEntity.ok(declarations);
    }

    @GetMapping("/doctors/my-patients")
    public ResponseEntity<List<Declaration>> showDoctorPatient(){
        List<Declaration> declarations = declarationServiceImpl.GetDoctorPatient();
        return ResponseEntity.ok(declarations);
    }

    @GetMapping("/patients/{idPatient}")
    public ResponseEntity<Declaration> showByPatientId(@PathVariable long idPatient){
        return ResponseEntity.ok(declarationServiceImpl.GetByIdPatient(idPatient));
    }

    @PostMapping
    public ResponseEntity<Declaration> create(@RequestBody DeclarationDto newDeclaration){
        final long doctor = newDeclaration.getIdDoctor();
        final long decl = declarationServiceImpl.createDeclaration(doctor);
        final String declarationUri = String.format("/declaration/patient/%d", decl);

        return ResponseEntity.created(URI.create(declarationUri)).build();
    }

}
