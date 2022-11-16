package com.samtuga.rmufilemanagement.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FileDiary {
    @SequenceGenerator(name = "diary_sequence", sequenceName = "diary_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "diary_sequence")
    @Id
    private long serialNumber;
    private LocalDate dateOpened;
    private String reference;
    private String fileTitle;
    private String ref;
    private String previousFileNumber;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Department department;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Unit unit;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private AppUser appUser;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<IncomingCorrespondence> incomingCorrespondences = new HashSet<>();


    public FileDiary(LocalDate dateOpened, String reference, String fileTitle, String ref, String previousFileNumber) {
        this.dateOpened = dateOpened;
        this.reference = reference;
        this.fileTitle = fileTitle;
        this.ref = ref;
        this.previousFileNumber = previousFileNumber;
    }
}
