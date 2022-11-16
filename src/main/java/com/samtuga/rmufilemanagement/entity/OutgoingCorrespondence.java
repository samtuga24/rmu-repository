package com.samtuga.rmufilemanagement.entity;

import com.samtuga.rmufilemanagement.customIdGenerator.PrefixedCustomId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OutgoingCorrespondence {
    @SequenceGenerator(name = "outgoing_sequence", sequenceName = "outgoing_sequence")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "outgoing_sequence")
    @Id
    private Long folioNumber;
    private String reference;
    private LocalDate dateReceivedForDispatch;
    private LocalDate dateDispatched;
    private String subject;
    private String sub;
    private String modeOfDispatch;
    private String ref;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Address> addressee = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    private AppUser appUser;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Document documents;

    public OutgoingCorrespondence(String reference, LocalDate dateReceivedForDispatch, LocalDate dateDispatched,
                                  String subject, String sub, String modeOfDispatch, String ref) {
        this.reference = reference;
        this.dateReceivedForDispatch = dateReceivedForDispatch;
        this.dateDispatched = dateDispatched;
        this.subject = subject;
        this.sub = sub;
        this.modeOfDispatch = modeOfDispatch;
        this.ref = ref;
    }
}
