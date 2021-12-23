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

    @GetMapping("/doctor/{idDoctor}")
    public ResponseEntity<List<Declaration>> showByDoctorId(@PathVariable long idDoctor){
        List<Declaration> declarations = declarationServiceImpl.GetByDoctorId(idDoctor);
        return ResponseEntity.ok(declarations);
    }

    @GetMapping("/user/{idPatient}")
    public ResponseEntity<Declaration> showByPatientId(@PathVariable long idPatient){
        return ResponseEntity.ok(declarationServiceImpl.GetByIdPatient(idPatient));
    }

    @PostMapping
    public ResponseEntity<Declaration> create(@RequestBody DeclarationDto newDeclaration){
        final long patient =  newDeclaration.getIdPatient();
        final long doctor = newDeclaration.getIdDoctor();
        final long decl = declarationServiceImpl.createDeclaration(patient, doctor);
        final String declarationUri = String.format("/declaration/patient/%d", decl);

        return ResponseEntity.created(URI.create(declarationUri)).build();
    }

}
