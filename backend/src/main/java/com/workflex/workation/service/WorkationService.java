package com.workflex.workation.service;
import com.workflex.workation.model.Workation;
import com.workflex.workation.repository.WorkationRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
@Service public class WorkationService {
 private final WorkationRepository repository;
 public WorkationService(WorkationRepository repository){this.repository=repository;}
 public List<Workation> findAll(){return repository.findAll(Sort.by("workationId"));}
}
