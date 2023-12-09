package com.stc.assessment.repositories;

import com.stc.assessment.entities.Permission;
import com.stc.assessment.entities.PermissionGroup;
import com.stc.assessment.enums.PermissionLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    @Query("SELECT p FROM Permission p WHERE p.group.id=:groupId AND p.userEmail = :userEmail")
    Optional<Permission> findByUserEmailAndGroupId(String userEmail, Long groupId);

    Integer countByUserEmailAndGroupIdAndPermissionLevel(String userEmail, Long groupId, PermissionLevel permissionLevel);
}
