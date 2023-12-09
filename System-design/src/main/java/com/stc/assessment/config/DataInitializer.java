package com.stc.assessment.config;

import com.stc.assessment.dto.request.CreatePermissionGroupRequest;
import com.stc.assessment.dto.request.PermissionRequest;
import com.stc.assessment.entities.PermissionGroup;
import com.stc.assessment.enums.PermissionLevel;
import com.stc.assessment.services.PermissionGroupService;
import com.stc.assessment.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    private final PermissionGroupService permissionGroupService;
    private final PermissionService permissionService;

    @Override
    public void run(ApplicationArguments args) {
        PermissionGroup permissionGroup = initialGroupIfNotExist(PermissionGroupService.ADMIN_GROUP);
        initialPermissions(permissionGroup, "editor@email.com", PermissionLevel.EDIT);
        initialPermissions(permissionGroup, "viewer@email.com", PermissionLevel.VIEW);
    }

    private PermissionGroup initialGroupIfNotExist(String name) {

        Optional<PermissionGroup> permissionGroup = permissionGroupService.getPermissionGroupByName(name);
        return permissionGroup.orElseGet(() -> permissionGroupService.createPermissionGroup(new CreatePermissionGroupRequest(name)));
    }

    private void initialPermissions(PermissionGroup group, String userEmail, PermissionLevel level) {
        if (!permissionService.isPermissionExist(userEmail, group.getId(), level))
            permissionService.createPermission(new PermissionRequest(level, userEmail, group.getId()));
    }
}
