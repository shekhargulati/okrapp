package com.shekhargulati.okrapp.core.repository;

import com.shekhargulati.okrapp.core.domain.Objective;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ObjectiveRepository extends JpaRepository<Objective, String> {
}
