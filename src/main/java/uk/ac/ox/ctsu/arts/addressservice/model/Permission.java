package uk.ac.ox.ctsu.arts.addressservice.model;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_sequence")
    @SequenceGenerator(name = "permission_sequence", sequenceName = "permission_sequence", allocationSize = 1)
    @Column
    private Long id;

    @Column
    private String name;

    @ManyToMany (mappedBy="permissions")
    private Set<Role> roles = new HashSet<>();


    @Column
    @CreatedDate
    private LocalDateTime changedWhen;
    @Column
    @CreatedBy
    private String changedWho;

    public Permission(String name) {
        this.name = name;
    }
    public Permission() {

    }

    public void setName(String name) {
        this.name = name;
    }


}
