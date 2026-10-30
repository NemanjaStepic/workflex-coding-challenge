package com.workflex.workation.repository;
import com.workflex.workation.model.Workation;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WorkationRepository extends JpaRepository<Workation,String> {}
