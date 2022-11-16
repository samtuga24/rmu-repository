package com.samtuga.rmufilemanagement.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class FileTransitSheet {
    @SequenceGenerator(name = "transit_sequence", sequenceName = "transit_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "transit_sequence")
    @Id
    private long transitId;
    private LocalDate date;
    private String fileTitle;
    private String sentTo;
    private String reference;
    private String signature;
    private String remarks;
    private LocalDate returnedDate;
    private String returningOfficer;
    private String status;
    private String ref;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    private AppUser appUser;

    @OneToOne
    private FileDiary fileDiary;

    public FileTransitSheet(LocalDate date, String fileTitle, String sentTo, String reference,
                            String signature, String remarks, LocalDate returnedDate,
                            String returningOfficer, String status, String ref) {
        this.date = date;
        this.fileTitle = fileTitle;
        this.sentTo = sentTo;
        this.reference = reference;
        this.signature = signature;
        this.remarks = remarks;
        this.returnedDate = returnedDate;
        this.returningOfficer = returningOfficer;
        this.status = status;
        this.ref = ref;
    }
}
