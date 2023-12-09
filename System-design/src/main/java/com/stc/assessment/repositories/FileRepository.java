package com.stc.assessment.repositories;

import com.stc.assessment.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    @Query(value = "SELECT f.* FROM files f " +
            "JOIN public.items i on i.id = f.item_id " +
            "JOIN public.permission_groups pg on pg.id = i.group_id " +
            "JOIN public.permissions p on pg.id = p.group_id " +
            "WHERE user_email = :userEmail " +
            "AND f.id = :id ", nativeQuery = true)
    Optional<File> findFileByIdAndUserEmail(Long id, String userEmail);
}
