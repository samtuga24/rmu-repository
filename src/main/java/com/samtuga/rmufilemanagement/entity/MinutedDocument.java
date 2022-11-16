package com.samtuga.rmufilemanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MinutedDocument {
    @SequenceGenerator(name = "minuted_sequence", sequenceName = "minuted_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "minuted_sequence")
    @Id
    private Long id;
    private String name;
    private String type;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] document;
}
