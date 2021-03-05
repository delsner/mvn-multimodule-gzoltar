package de.doubleslash.demo.multimodule.coverage.four.repositories;

import de.doubleslash.demo.multimodule.coverage.four.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    Optional<Project> findByName(String name);
}
