package com.stc.assessment.services;

import com.stc.assessment.dto.request.PermissionRequest;
import com.stc.assessment.entities.Permission;
import com.stc.assessment.entities.PermissionGroup;
import com.stc.assessment.enums.PermissionLevel;
import com.stc.assessment.repositories.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionService {

    private final PermissionRepository permissionRepository;
    private final PermissionGroupService permissionGroupService;

    public boolean isAllowedToEdit(Long groupId, String userEmail) {
        Optional<Permission> permission = permissionRepository.findByUserEmailAndGroupId(userEmail, groupId);
        return permission.isPresent() && permission.get().getPermissionLevel().equals(PermissionLevel.EDIT);
    }

    public boolean isAllowedToView(Long groupId, String userEmail) {
        Optional<Permission> permission = permissionRepository.findByUserEmailAndGroupId(userEmail, groupId);
        return permission.isPresent() && permission.get().getPermissionLevel().equals(PermissionLevel.VIEW);
    }

    public Permission createPermission(PermissionRequest permissionRequest) {
        PermissionGroup permissionGroup = permissionGroupService.getPermissionGroupById(permissionRequest.groupId());
        Permission permission = mapRequestToEntity(permissionRequest, permissionGroup);
        return permissionRepository.save(permission);
    }

    public boolean isPermissionExist(String userEmail, Long groupId, PermissionLevel permissionLevel) {
        return permissionRepository.countByUserEmailAndGroupIdAndPermissionLevel(userEmail, groupId, permissionLevel) > 0;
    }

    private Permission mapRequestToEntity(PermissionRequest permissionRequest, PermissionGroup group) {
        Permission permission = new Permission();
        permission.setPermissionLevel(permissionRequest.level());
        permission.setUserEmail(permissionRequest.userEmail());
        permission.setGroup(group);
        return permission;
    }

}
