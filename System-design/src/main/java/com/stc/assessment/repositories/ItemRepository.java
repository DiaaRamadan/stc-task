package com.stc.assessment.repositories;

import com.stc.assessment.entities.Item;
import com.stc.assessment.enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Item> findByIdAndType(Long id, ItemType type);

}
