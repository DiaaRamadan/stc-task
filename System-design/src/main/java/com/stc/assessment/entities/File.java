package com.stc.assessment.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "files")
@Getter
@Setter
public class File extends BaseEntity {

    @Lob
    @Column(name = "binary_file", nullable = false)
    private byte[] binaries;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "size")
    private Long size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

}
