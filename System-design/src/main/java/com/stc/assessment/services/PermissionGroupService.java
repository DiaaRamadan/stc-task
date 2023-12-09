package com.stc.assessment.services;

import com.stc.assessment.dto.request.CreatePermissionGroupRequest;
import com.stc.assessment.entities.Item;
import com.stc.assessment.entities.PermissionGroup;
import com.stc.assessment.exceptions.BadRequestException;
import com.stc.assessment.exceptions.ResourceNotFoundException;
import com.stc.assessment.repositories.PermissionGroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermissionGroupService {

    public static final String ADMIN_GROUP = "Admin";

    private final PermissionGroupRepository groupRepository;

    @Transactional
    public PermissionGroup createPermissionGroup(CreatePermissionGroupRequest groupRequest) {
        Optional<PermissionGroup> groupByName = groupRepository.findByName(groupRequest.name());
        if (groupByName.isPresent())
            throw new BadRequestException("Group Already exist");

        PermissionGroup group = new PermissionGroup();
        group.setName(groupRequest.name());
        return groupRepository.save(group);
    }

    public Optional<PermissionGroup> getPermissionGroupByName(String name) {
        return groupRepository.findByName(name);
    }

    public PermissionGroup getPermissionGroupById(long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Group with id [" + id + "] not found"));
    }

}
