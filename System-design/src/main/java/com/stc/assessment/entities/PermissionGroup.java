package com.stc.assessment.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permission_groups")
@Getter
@Setter
public class PermissionGroup extends BaseEntity {

    @Column(name = "group_name", nullable = false, unique = true)
    private String name;

}
