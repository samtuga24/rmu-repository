package com.samtuga.rmufilemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IncomingCorrespondence {
    @SequenceGenerator(name = "document_sequence", sequenceName = "document_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "document_sequence")
    @Id
    private Long folioNumber;
    private String reference;
    private LocalDate dateOfLetter;
    private LocalDate dateReceived;
    private String fromWhomReceived;
    private String institutionsReference;
    private String subject;
    private String ref;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AppUser appUser;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Document documents;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MinutedDocument minutedDocument;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<FileDiary> fileDiary = new HashSet<>();

    public IncomingCorrespondence(String reference, LocalDate dateOfLetter,
                                  LocalDate dateReceived, String fromWhomReceived,
                                  String institutionsReference, String subject, String ref) {
        this.reference = reference;
        this.dateOfLetter = dateOfLetter;
        this.dateReceived = dateReceived;
        this.fromWhomReceived = fromWhomReceived;
        this.institutionsReference = institutionsReference;
        this.subject = subject;
        this.ref = ref;
    }
}
