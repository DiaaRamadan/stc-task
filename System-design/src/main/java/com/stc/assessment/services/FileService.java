package com.stc.assessment.services;

import com.stc.assessment.dto.request.CreateFileRequest;
import com.stc.assessment.dto.response.FileMetaData;
import com.stc.assessment.entities.File;
import com.stc.assessment.entities.Item;
import com.stc.assessment.enums.ItemType;
import com.stc.assessment.exceptions.AccessDeniedException;
import com.stc.assessment.exceptions.BadRequestException;
import com.stc.assessment.exceptions.ResourceNotFoundException;
import com.stc.assessment.repositories.FileRepository;
import com.stc.assessment.utils.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository fileRepository;
    private final FolderService folderService;
    private final SpaceService spaceService;
    private final PermissionService permissionService;

    public File saveFile(CreateFileRequest fileRequest) throws IOException {

        Item fileItem = getFileItem(fileRequest);
        Optional<String> currentUserEmail = SecurityUtil.getCurrentUserEmail();

        if (currentUserEmail.isEmpty() || !permissionService.isAllowedToEdit(fileItem.getGroup().getId(), currentUserEmail.get()))
            throw new AccessDeniedException("Access denied");

        File file = mapRequestToEntity(fileRequest, fileItem);
        return fileRepository.save(file);
    }

    public File getFileById(Long id) {

        File file = fileRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("File with Id [" + id + "] not found"));

        Optional<String> currentUserEmail = SecurityUtil.getCurrentUserEmail();
        Item item = file.getItem();

        if (currentUserEmail.isEmpty() || !(permissionService.isAllowedToView(item.getId(), currentUserEmail.get()) ||
                permissionService.isAllowedToEdit(item.getId(), currentUserEmail.get())))

            throw new AccessDeniedException("Access denied");

        return file;
    }

    @Transactional
    public FileMetaData getFileMetaData(Long id) {
        String email = SecurityUtil.getCurrentUserEmail()
                .orElseThrow(() -> new AccessDeniedException("Access denied"));

        File file = fileRepository.findFileByIdAndUserEmail(id, email).orElseThrow(() -> new ResourceNotFoundException("File not exist"));
        Item item = file.getItem();

        String spaceName = item.getName();
        String folderName = null;
        if (item.getType().equals(ItemType.FOLDER)) {
            folderName = item.getName();
            spaceName = item.getParent().getName();
        }
        return new FileMetaData(file.getSize(), file.getName(), folderName, spaceName);

    }

    private File mapRequestToEntity(CreateFileRequest fileRequest, Item item) throws IOException {
        File file = new File();
        file.setItem(item);
        file.setName(fileRequest.file().getOriginalFilename());
        file.setBinaries(fileRequest.file().getBytes());
        file.setSize(fileRequest.file().getSize());
        return file;
    }

    private Item getFileItem(CreateFileRequest fileRequest) {
        Item item;
        if (Objects.nonNull(fileRequest.folderId())) {
            item = folderService.findFolderById(fileRequest.folderId());

            if (!Objects.equals(item.getParent().getId(), fileRequest.spaceId()))
                throw new BadRequestException("Invalid space or folder");
        } else
            item = spaceService.findSpaceById(fileRequest.spaceId());

        return item;
    }

}
