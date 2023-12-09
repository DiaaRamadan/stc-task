package com.stc.assessment.services;

import com.stc.assessment.dto.request.CreateFolderRequest;
import com.stc.assessment.entities.Item;
import com.stc.assessment.enums.ItemType;
import com.stc.assessment.exceptions.AccessDeniedException;
import com.stc.assessment.exceptions.ResourceNotFoundException;
import com.stc.assessment.repositories.ItemRepository;
import com.stc.assessment.utils.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final ItemRepository itemRepository;
    private final SpaceService spaceService;
    private final PermissionService permissionService;

    @Transactional
    public Item createFolder(CreateFolderRequest folderRequest) {

        Item space = spaceService.findSpaceById(folderRequest.spaceId());
        Optional<String> currentUserEmail = SecurityUtil.getCurrentUserEmail();
        if (currentUserEmail.isEmpty() || !permissionService.isAllowedToEdit(space.getGroup().getId(), currentUserEmail.get()))
            throw new AccessDeniedException("Access denied");

        return itemRepository.save(mapRequestToEntity(folderRequest, space));

    }

    public Item findFolderById(Long id) {
        return itemRepository.findByIdAndType(id, ItemType.FOLDER)
                .orElseThrow(() -> new ResourceNotFoundException("Folder with id [" + id + "] not found"));
    }

    private Item mapRequestToEntity(CreateFolderRequest request, Item space) {
        Item folder = new Item();
        folder.setName(request.name());
        folder.setType(ItemType.FOLDER);
        folder.setParent(space);
        folder.setGroup(space.getGroup());
        return folder;
    }

}
