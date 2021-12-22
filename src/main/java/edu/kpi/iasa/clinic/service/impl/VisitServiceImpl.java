package edu.kpi.iasa.clinic.service.impl;

import edu.kpi.iasa.clinic.repository.VisitRepository;
import edu.kpi.iasa.clinic.repository.model.Visit;
import edu.kpi.iasa.clinic.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VisitServiceImpl implements VisitService {
    private final VisitRepository visitRepository;

    @Override
    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    @Override
    public Visit getVisitById(long id) throws IllegalArgumentException {
        final Optional<Visit> maybeVisit = visitRepository.findById(id);

        if (maybeVisit.isPresent())
            return maybeVisit.get();
        else
            throw new IllegalArgumentException("Invalid visit ID");
    }

    @Override
    public long createVisit(long idPatient, LocalDate dateVisit, LocalTime timeVisit) {
        final Visit visit = new Visit(idPatient, dateVisit, timeVisit);
        final Visit savedVisit = visitRepository.save(visit);

        return savedVisit.getId();
    }

    @Override
    public void deleteVisit(long id) {
        visitRepository.deleteById(id);
    }
}
