package com.stc.assessment.entities;

import com.stc.assessment.enums.PermissionLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permissions")
@Getter
@Setter
public class Permission extends BaseEntity{


    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "permission_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private PermissionLevel permissionLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private PermissionGroup group;

}
