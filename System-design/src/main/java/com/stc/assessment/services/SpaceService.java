package com.stc.assessment.services;

import com.stc.assessment.dto.request.CreateSpaceRequest;
import com.stc.assessment.entities.Item;
import com.stc.assessment.entities.PermissionGroup;
import com.stc.assessment.enums.ItemType;
import com.stc.assessment.exceptions.ResourceNotFoundException;
import com.stc.assessment.repositories.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpaceService {

    private final ItemRepository itemRepository;
    private final PermissionGroupService permissionGroupService;

    @Transactional
    public Item createSpace(CreateSpaceRequest createSpace) {
        PermissionGroup group = permissionGroupService.getPermissionGroupByName(PermissionGroupService.ADMIN_GROUP)
                .orElseThrow(() -> new ResourceNotFoundException("Permission group with " + PermissionGroupService.ADMIN_GROUP + " not found"));

        return itemRepository.save(mapRequestToEntity(createSpace, group));
    }

    public Item findSpaceById(Long id) {
        return itemRepository.findByIdAndType(id, ItemType.SPACE)
                .orElseThrow(() -> new ResourceNotFoundException("Space with id [" + id + "] not found"));
    }

    private Item mapRequestToEntity(CreateSpaceRequest createSpace, PermissionGroup group) {
        Item space = new Item();
        space.setName(createSpace.name());
        space.setType(ItemType.SPACE);
        space.setGroup(group);
        return space;
    }

}
