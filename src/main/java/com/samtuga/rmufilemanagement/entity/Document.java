package com.samtuga.rmufilemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Document {
    @SequenceGenerator(name = "document_sequence", sequenceName = "document_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "document_sequence")
    @Id
    private Long id;
    private String name;
    private String type;
    @Lob
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] document;

    @JsonIgnore
    @OneToOne(mappedBy = "documents")
    private IncomingCorrespondence incomingCorrespondence;
}
