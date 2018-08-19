package com.exxeta.maau.tutorialmaau.repository;


import com.exxeta.maau.tutorialmaau.model.Factory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactoryRepository extends JpaRepository<Factory, Long> {
}
