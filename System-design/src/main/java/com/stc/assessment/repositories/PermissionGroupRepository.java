package com.stc.assessment.repositories;

import com.stc.assessment.entities.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {

    Optional<PermissionGroup> findByName(String name);
}
