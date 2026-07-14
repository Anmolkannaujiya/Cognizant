package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Hands-on 3, 6 (2. spring-data-jpa-handson)
// basic CRUD for skill

@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
